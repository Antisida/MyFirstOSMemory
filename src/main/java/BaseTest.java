import com.koloboke.collect.set.LongSet;
import com.koloboke.collect.set.hash.HashLongSetFactory;
import org.alex73.osmemory.MemoryStorage;
import org.alex73.osmemory.OsmWay;

import java.util.ArrayList;
import java.util.Iterator;

import static com.koloboke.collect.set.hash.HashLongSets.getDefaultFactory;

public class BaseTest {

    private static long timeF;
    private static long timeFind;
    private static long delta = 0L;
    private static long timeFindSumm = 0L;
    private static long timeMerge = 0;
    private static long timeMergeSumm = 0;
    private static long counter = 0;

    static boolean[] completedRegion = new boolean[100];
    static OsmRegion[] listReady1 = new OsmRegion[100];
    static ReadyData[] resultList = new ReadyData[100];

    // обработан уже регион?
    boolean isReady(int iID) {
        if (listReady1[iID] != null) return true;
        return false;
    }

    void aggregeteTest(int ID) {
        processReg(ID);
        connectTestNeighbors(ID);
        afterTest(ID);
    }

    private void afterTest(int id) {
        OsmRegion osmRegion = listReady1[id];
        resultList[id] = osmRegion.outData;

        System.out.print("Список сетов для удаления: " );
        for (Integer i: osmRegion.idSetForDelete) {
            System.out.print(i + " ");
        }

        if (!osmRegion.isUseful()) {
            Iterator<LongSet> iterator = osmRegion.afterGenProcessor.iterator();
            while (iterator.hasNext()){
                LongSet longs = iterator.next();
                for (Integer idForDel: osmRegion.idSetForDelete){
                    if (osmRegion.afterGenProcessor.indexOf(longs) == idForDel) {
                        iterator.remove();
                        System.out.println("Удалил " + idForDel);
                    }
                }

            }
            listReady1[id] = null;
        }
    }

    void processReg(int ID) {
        if (!isReady(ID)) {
            OsmRegion osmRegion = new OsmRegion(ID);
            System.out.println("-----------------------");
            System.out.println(osmRegion.name);
            osmRegion.afterGenProcessor = connectTest(osmRegion.O5M_Data);
            listReady1[ID] = osmRegion;
        }
    }

    //general processor
    ArrayList<LongSet> connectTest(MemoryStorage memoryStorage) {
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

            }
            else {
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
    void connectTestNeighbors(int ID) {
        OsmRegion OUR_Region = listReady1[ID];

        // проверяем все ли соседи обработаны, обрабатываем необработанных соседей
        for (int n : OUR_Region.neighbors) {
            if (!isReady(n)) {
                processReg(n);
            }
        }

        // проверяем связность с соседями и удаляем сеты, которые связаны с сетами соседа
        ArrayList<LongSet> waysOUR = OUR_Region.afterGenProcessor;
        for (int neighbor : OUR_Region.neighbors) {        //для каждого соседа
            ArrayList<LongSet> waysN = listReady1[neighbor].afterGenProcessor;  // берем сеты у соседа
            for (LongSet longsN : waysN) { //в сетах соседа берем по сету
                for (int i = waysOUR.size() - 1; i > -1; i--) {
                    LongSet longsOUR = waysOUR.get(i);  //в своих сетах берем по сету
                    //if (longsOUR.size() > 1000) continue;  //если наш сет большой предполагаем что его не надо обсчитывать
                    for (long lo : longsOUR) { //каждую точку из нашего сета
                        if (longsN.contains(lo)
                                & (longsN.size() > 50)
                        ) {   //проверяем есть ли эта точка в сетах соседа
                            OUR_Region.idSetForDelete.add(i);
                            //waysOUR.remove(i); //если точка соседска удаляем сет из нашего набора
                            break;
                        }
                    }
                }
            }
        }
        completedRegion[OUR_Region.ID] = true;
        OUR_Region.isDone = true;

//        int size1 = 0;
//        for (LongSet set : waysOUR) {
//            System.err.println("Размер графа: " + set.size());
//            size1 = size1 + set.size();
//            if (set.size() < 2000) {
//                for (long Lpo : set) {
//                    System.err.print(Lpo + " ");
//                }
//                System.err.println();
//            }
//        }

        OUR_Region.outData.outConnectivity = waysOUR;
    }

    private ArrayList<OsmWay> dataToWays(MemoryStorage data) {
        ArrayList<OsmWay> ss = new ArrayList<>();
        data.byTag("highway", o -> {//добавить сюда ferry = паром
            if (
//                   o.getTag("highway", data).equals("service") ||
//                       o.getTag("highway", data).equals("living_street") ||
//                      o.getTag("highway", data).equals("unclassified") ||
//                    o.getTag("highway", data).equals("residential") ||
                    o.getTag("highway", data).equals("tertiary") ||
                            o.getTag("highway", data).equals("tertiary_link") ||
                            o.getTag("highway", data).equals("secondary") ||
                            o.getTag("highway", data).equals("secondary_link") ||
                            o.getTag("highway", data).equals("primary") ||
                            o.getTag("highway", data).equals("primary_link") ||
                            o.getTag("highway", data).equals("motorway_link") ||
                            o.getTag("highway", data).equals("motorway") ||
                            o.getTag("highway", data).equals("trunk") ||
                            o.getTag("highway", data).equals("trunk_link")
            )
                if (o instanceof OsmWay) ss.add((OsmWay) o);
        });
        return ss;
    }
}
