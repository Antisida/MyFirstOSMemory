import com.koloboke.collect.set.LongSet;
import com.koloboke.collect.set.hash.HashLongSetFactory;
import org.alex73.osmemory.MemoryStorage;
import org.alex73.osmemory.OsmWay;

import java.io.*;
import java.util.ArrayList;

import static com.koloboke.collect.set.hash.HashLongSets.getDefaultFactory;

public class BaseTest {

    private static long timeF;

    private static long timeFind;
    private static long delta = 0L;
    private static long timeFindSumm = 0L;
    private static long timeMerge = 0;
    private static long timeMergeSumm = 0;
    private static long counter = 0;

    static boolean[] outerTestCompletedRegions = new boolean[150];
    static OsmRegion[] innerTestCompletedRegions = new OsmRegion[150];
    static ReadyData[] finalDatas = new ReadyData[100];

    // обработан уже регион?
    boolean isInnerTestComplete(int iID) {
        if (innerTestCompletedRegions[iID] != null) return true;
        return false;
    }

    void aggregeteTest(RussianRegion russianRegion) {
        processReg(russianRegion);
        generalOuterConnectivityTest(russianRegion);
        afterTest();
    }

    // если регион больше не нужен соседям, данные сохраняются, регион удаляется
    private void afterTest() {
        OsmRegion o1 = null;
        //OsmRegion osmRegion = (OsmRegion)innerTestCompletedRegions[id];
        for (OsmRegion osmRegion : innerTestCompletedRegions) {
            if (osmRegion != null) {
                o1 = osmRegion;
                if (!o1.isUseful()) {
                    System.out.println("!!!!!!! " + o1.name);
                    System.out.println(o1.isolatedSets.size());
                    System.out.println(o1.fofDeleteSets.size());
                    o1.isolatedSets.removeAll(o1.fofDeleteSets);


                    System.out.println(o1.isolatedSets.size());
                    o1.outData.finalIsolatedSets = o1.isolatedSets;
                    BaseTest.finalDatas[o1.ID] = o1.outData;
                    o1.outData = null;
                    innerTestCompletedRegions[o1.ID] = null;
                }

            }
        }
    }

    void processReg(RussianRegion russianRegion) {
        if (isInnerTestComplete(russianRegion.id) == false) {
            OsmRegion osmRegion;
            if (russianRegion.id == 101 || russianRegion.id == 102) {
                osmRegion = deSerializeOsmRegion(russianRegion);
                innerTestCompletedRegions[osmRegion.ID] = osmRegion; //cразу добавляем десериализованный объект в массив
            } else {
                osmRegion = new OsmRegion(russianRegion);
            }

            System.out.println("-----------------------");
            System.out.println(osmRegion.name);
            osmRegion.isolatedSets = innerConnectivityTest(osmRegion.O5M_Data);
            osmRegion.O5M_Data = null;
            innerTestCompletedRegions[russianRegion.id] = osmRegion;
        }
    }

//    void processReg(int ID) {
//        if (isInnerTestComplete(ID) == false) {
//            OsmRegion osmRegion;
//            if (ID > 100){
//                osmRegion = new OsmRegion(ID);
//                innerTestCompletedRegions[osmRegion.ID] = osmRegion; //cразу добавляем десериализованный объект в массив
//            }else {
//                osmRegion = new OsmRegion(ID);
//            }
//
//            System.out.println("-----------------------");
//            System.out.println(osmRegion.name);
//            osmRegion.isolatedSets = innerConnectivityTest(osmRegion.O5M_Data);
//            osmRegion.O5M_Data = null;
//            innerTestCompletedRegions[ID] = osmRegion;
//        }
//    }

    //general processor
    ArrayList<LongSet> innerConnectivityTest(MemoryStorage memoryStorage) {
        HashLongSetFactory hashLongSetFactory = getDefaultFactory();
        ArrayList<OsmWay> inputWays = dataToWays(memoryStorage);

        long st = System.nanoTime();
        timeF = System.nanoTime();

        ArrayList<LongSet> generalListOfNodesOfSubgraf = new ArrayList<>(); //Массив 1 уровня для Set`ов точек, по индексу они соответствуют соответствующим массивам в
        ArrayList<Integer> tempListForMergeSubgraf = new ArrayList<>(); //массивы для индексов объединямых субграфов и их точек
        for (OsmWay osmWay : inputWays) {              // берем попорядку веи из массива

            counter++;

            long[] nodeIDs;
            nodeIDs = osmWay.getNodeIds();         // вытаскиваем в массив id точек 1-го вея
            if (generalListOfNodesOfSubgraf.isEmpty()) {    // если главный массив пуст, то добавляем туда первый сет
                LongSet longTmp = hashLongSetFactory.newMutableSet(nodeIDs); // новый set для точек обрабатываемого вея // каждую точку добавляем в set
                generalListOfNodesOfSubgraf.add(longTmp);   // сохраняем set точек первого вея в массив сетов

            } else {
                tempListForMergeSubgraf.clear();
                timeFind = System.nanoTime();
                for (LongSet set : generalListOfNodesOfSubgraf) { // для каждого сета точек, которые уже находятся
                    for (long Lo : nodeIDs) {// сравниваем точки
                        if (set.contains(Lo)) {
                            if (!tempListForMergeSubgraf.contains(generalListOfNodesOfSubgraf.indexOf(set)))
                                tempListForMergeSubgraf.add(generalListOfNodesOfSubgraf.indexOf(set));  // если точка найдена добавляем индекс сета в массив для объединения
                        }
                    }
                }
                timeFind = System.nanoTime() - timeFind;
                timeFindSumm += timeFind;

                // если есть что объединять то объединяме
                if (tempListForMergeSubgraf.size() > 0) {
                    timeMerge = System.nanoTime();

                    int indexForMerge = tempListForMergeSubgraf.get(0);  //добавляем точки к сету точек
                    LongSet tmpSet = generalListOfNodesOfSubgraf.get(indexForMerge);  //добавляем точки вея к первому набору точек, в котором совпадение обнаружилось

                    for (long LL : nodeIDs) {
                        tmpSet.add(LL);
                    }
                    generalListOfNodesOfSubgraf.remove(indexForMerge);
                    generalListOfNodesOfSubgraf.add(indexForMerge, tmpSet);

                    LongSet tmpSetFMerge = hashLongSetFactory.newMutableSet();

                    for (Integer integer : tempListForMergeSubgraf) { // добавляем все субграфы с совпадениями
                        tmpSetFMerge.addAll(generalListOfNodesOfSubgraf.get(integer));
                    }

                    // добавляем все субграфы с совпадениями
                    for (int integer1 = tempListForMergeSubgraf.size() - 1; integer1 > -1; integer1--) {
                        int nSub = tempListForMergeSubgraf.get(integer1);
                        generalListOfNodesOfSubgraf.remove(nSub);
                    }
                    generalListOfNodesOfSubgraf.add(tmpSetFMerge);

                    timeMerge = System.nanoTime() - timeMerge;
                    timeMergeSumm += timeMerge;
                }
                // или, если нечего объединять, добавить новый субграф
                else {
                    LongSet tmp = hashLongSetFactory.newMutableSet(nodeIDs);    // новый set для точек обрабатываемого вея  //добавляем в новый сет точки
                    generalListOfNodesOfSubgraf.add(tmp);   // добавляем новы сет точек в главный массив точек
                }
            }

//            if (counter % 10_000 == 0) {
//                delta = (System.nanoTime()) - timeF;
//                System.err.println("Среднее за 10_000 = " + delta / 10_000_000_000F);
//                System.out.println("размер = " + generalListOfNodesOfSubgraf.size());
//                System.out.println("Среднее время поиска = " + timeFindSumm / 10_000_000_000F);
//                System.out.println("Среднее время объединения = " + timeMergeSumm / 10_000_000_000F);
//                timeF = System.nanoTime();
//                timeFindSumm = 0;
//                timeMergeSumm = 0;
//            }
        }

//        delta = (System.nanoTime()) - timeF;
//        System.err.println("Среднее за 10_000 = " + delta/10_000_000_000F);
//        System.out.println("размер = " + generalListOfNodesOfSubgraf.size());
//        System.out.println("Среднее время поиска = " + timeFindSumm/10_000_000_000F);
//        System.out.println("Среднее время объединения = " + timeMergeSumm/10_000_000_000F);


        long fn = System.nanoTime();

        System.out.println("Время:" + (fn - st) / 1000_000_000 + "сек");
        System.out.println("Количество графов: " + generalListOfNodesOfSubgraf.size());
        System.out.println("-----------------------");
//        int size1 = 0;
//        for (LongSet set: generalListOfNodesOfSubgraf){
//            System.out.println("Размер графа: " +set.size());
//            size1 =size1+ set.size();
//            if (set.size() < 2000){
//                for (long Lpo: set){
//                    System.out.print(Lpo + " ");
//                }
//                System.out.println();
//            }
//        }
        //       System.out.println("all = "+ size1);

        return generalListOfNodesOfSubgraf;
    }

    //postprocessor
    void generalOuterConnectivityTest(RussianRegion russianRegion) {

        OsmRegion myRegion = innerTestCompletedRegions[russianRegion.id];

        // проверяем все ли соседи обработаны, обрабатываем необработанных соседей
        for (int neighbor : myRegion.neighbors) {
            if (isInnerTestComplete(neighbor) == false) {
                RussianRegion neighborRussianRegion = null;
                for (RussianRegion russianR : RussianRegion.values()) {
                    if (russianR.id == neighbor) {
                        neighborRussianRegion = russianR;
                    }
                }
                processReg(neighborRussianRegion);
            }
        }

        // проверяем связность с соседями и удаляем сеты, которые связаны с сетами соседа
        ArrayList<LongSet> myIsoletedSets = myRegion.isolatedSets;
        for (int myNeighbor : myRegion.neighbors) {        //для каждого соседа
            ArrayList<LongSet> neighbrIsoletedSets = innerTestCompletedRegions[myNeighbor].isolatedSets;  // берем сеты у соседа
            myRegion.fofDeleteSets.addAll(simpleOuterConnectivityTest1(myIsoletedSets, neighbrIsoletedSets));
        }
        outerTestCompletedRegions[myRegion.ID] = true;
    }

    ArrayList<LongSet> simpleOuterConnectivityTest1
            (ArrayList<LongSet> myIsoletedSets, ArrayList<LongSet> neighbrIsoletedSets) {
        ArrayList<LongSet> forDeleteSets = new ArrayList<>();
        for (LongSet setNeighbor : neighbrIsoletedSets) { //в сетах соседа берем по сету
            for (LongSet mySet : myIsoletedSets) {
                for (long myNode : mySet) { //каждую точку из нашего сета
                    if (setNeighbor.contains(myNode)  //проверяем есть ли эта точка в сетах соседа
                            && (setNeighbor.size() > 500)
                            ) {
                        if (!forDeleteSets.contains(mySet)) {
                            forDeleteSets.add(mySet);
                            break;
                        }
                    }
                }
            }
        }
        return forDeleteSets; // возвращаем список сетов которые имеют связанность, и которые можно удалить
    }


    private ArrayList<OsmWay> dataToWays(MemoryStorage data) {
        ArrayList<OsmWay> ss = new ArrayList<>();
        data.byTag("highway", o -> {//добавить сюда ferry = паром
            if (
//                   o.getTag("highway", data).equals("service") ||
//                       o.getTag("highway", data).equals("living_street") ||
                    o.getTag("highway", data).equals("unclassified") ||

                            o.getTag("highway", data).equals("residential") ||
                            o.getTag("highway", data).equals("tertiary") ||

                            o.getTag("highway", data).equals("tertiary_link") ||
                            o.getTag("highway", data).equals("secondary") ||

                            o.getTag("highway", data).equals("secondary_link") ||
                            o.getTag("highway", data).equals("primary") ||

                            o.getTag("highway", data).equals("primary_link") ||
                            o.getTag("highway", data).equals("motorway_link") ||
                            o.getTag("highway", data).equals("motorway") ||
                            o.getTag("highway", data).equals("trunk") ||
                            // o.getTag("ferry", data).equals("trunk") ||
                            o.getTag("highway", data).equals("trunk_link")
                    )
                if (o instanceof OsmWay) ss.add((OsmWay) o);
        });
        return ss;
    }

    void serializeOsmRegion(OsmRegion osmRegion) {
        try {
            ObjectOutputStream outputStream =
                    new ObjectOutputStream(new BufferedOutputStream(
                            new FileOutputStream(new File("z:\\osmtmp\\" + osmRegion.name + ".bin"))));
            outputStream.writeObject(osmRegion);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Не удалось создать bin-файл");
        }
    }

    OsmRegion deSerializeOsmRegion(RussianRegion russianRegion) {
        OsmRegion osmRegion = null;
        try {
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new BufferedInputStream(
                            new FileInputStream("z:\\osmtmp\\" + russianRegion.name() + ".bin")));
            osmRegion = (OsmRegion)objectInputStream.readObject();
            objectInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Не удалось восстановить объект из файла");
        }
        return osmRegion;
    }


}