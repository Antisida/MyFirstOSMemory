import org.alex73.osmemory.*;
import java.io.File;
import java.util.*;

public class ConnectivityTest {

    MemoryStorage data = null;

    public static void main(String[] args) {
        ConnectivityTest connectivityTest = new ConnectivityTest();
        MemoryStorage data = null;
        try {
          //data = new O5MReader().read(new File("e:\\osmtmp\\RU-SEV.o5m"));
             data = new O5MReader().read(new File("e:\\osmtmp\\RU-NIZ.o5m"));
          //  data = new O5MReader().read(new File("c:\\osm\\RU-N_01.o5m"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<OsmWay> ss = connectivityTest.dataToWays(data);
        connectivityTest.connectivityMethod(ss);


        //System.out.println(ss.size());

//        ArrayList<ArrayList> generalListOfSubgraf = new ArrayList<>();      //Массив, куда складываются рабочие подграфы
//        ArrayList<TreeSet> generalListOfNodesOfSubgraf = new ArrayList<>(); //Массив рабочих массивов точек
//        ArrayList<OsmWay> firstSubgraf = new ArrayList<>();
//        //TreeSet<Long> firstSetOfNodes = new TreeSet<>();
//
//        long[] longs1;
//        TreeSet<Long> tmp1 = new TreeSet<>();    // новый set для точек обрабатываемого вея
//        longs1 = ss.get(0).getNodeIds();         // вытаскиваем в массив id точек 1-го вея
//        for (long lon: longs1) tmp1.add(lon);    // каждую точку добавляем в set
//
//        generalListOfNodesOfSubgraf.add(tmp1);   // сохраняем set точек первого вея в массив сетов
//        firstSubgraf.add(ss.get(0));            // сохраняем вей в первый субграф
//        generalListOfSubgraf.add(firstSubgraf); // сохраняем первый субграф в массив субграфов
//
//        ArrayList<Integer> tempListForMergeSubgraf = new ArrayList<>(); //массивы для индексов объединямых субграфов и их точек
//        ArrayList<Integer> tempListForMergeSubgrafNodes = new ArrayList<>();
//
//        int index = 0;
//
//        for (OsmWay osmWay: ss){              // берем попорядку веи из массива
//            tempListForMergeSubgraf.clear();
//            tempListForMergeSubgrafNodes.clear();
//            long[] longs;
//
//            TreeSet<Long> tmp = new TreeSet<>();    // новый set для точек обрабатываемого вея
//            longs = osmWay.getNodeIds();         // вытаскиваем в массив id точек 1-го вея
//            for (TreeSet set: generalListOfNodesOfSubgraf){ // для каждого сета точек, которые уже находятся
//               // boolean i = false;
//                index++;
//                for (long Lo: longs){                     // сравниваем точки
//                    //if (set.contains(Lo)) i = true;
//
//                    if (set.contains(Lo)) {
//                        tempListForMergeSubgraf.add(generalListOfNodesOfSubgraf.indexOf(set));  // если точка найдена добавляем индекс сета в массив для объединения
//                        tempListForMergeSubgrafNodes.add(generalListOfNodesOfSubgraf.indexOf(set));
//                    }
//                }
//            }
//            // тут надо объединить субграфы
//            if (!tempListForMergeSubgraf.isEmpty()){
//
//            }
//            // или, если нечего объединять, добавить новый субграф
//            else {
//                longs = osmWay.getNodeIds();
//                for (long LL: longs) {
//                   // System.out.println(LL);
//                    tmp.add(LL);
//                }
//
//                generalListOfNodesOfSubgraf.add(tmp);
//                ArrayList<OsmWay> tmpSubgraf = new ArrayList<>();
//                //System.out.println(osmWay.getObjectID());
//
//                tmpSubgraf.add(osmWay);
//
//                generalListOfSubgraf.add(tmpSubgraf);
//            }
//
//
//
//        }
//        System.out.println("размер generalListOfSubgraf = " + generalListOfSubgraf.size());
//        System.out.println("размер generalListOfNodesOfSubgraf = " + generalListOfNodesOfSubgraf.size());
//        for (ArrayList<OsmWay> arrayLists: generalListOfSubgraf){
//
//            for (OsmWay osmWays: arrayLists ){
//                System.out.println(osmWays.getObjectID());
//            }
//        }
//        System.out.println(index);


// алгоритм поиска подграфов: елсли найден подграф с точкой, заносим его индекс из Ареейлиста в массив и ищем дальше.
        // если сли найден еще один подграф с точкой заносим его индекс в массив и т.д
        // объединяем найденные подграфы в один и удаляем ненужные



      //  for (long l: t) System.out.println(l);
//        Map<String, String> map = new HashMap<>();
//
//        for (OsmWay osmObject: ss){
//            osmObject.getNodeIds();
//
//            map = osmObject.extractTags(finalData);
//
//            for (Map.Entry<String, String> entry: map.entrySet()){
//
//                    System.out.print(entry.getKey() + " == " + entry.getValue());
//                    System.out.println();
//
//            }
//        }





//        for (IOsmObject osmObject: ss){
//            map = osmObject.extractTags(finalData);
//            for (Map.Entry<String, String> entry: map.entrySet()){
//                if (entry.getKey().equals("highway") || entry.getKey().equals("name")) {
//                    System.out.print(entry.getKey() + " == " + entry.getValue());
//                    System.out.println();
//                }
//            }
//        }
//
//        System.out.println(map.size());
//        System.out.println(ss.size());
//        for (short i = 0;  i < 1000; i++) {
//            // if (osmObject.getTag((short)0x2).equals()) {
//            //if (osmObject.getTag("name", finalData).equals("Кузбасская улица")) {
//            //if (ss.get(100).getTag(i)!=null)
//            System.out.println(ss.get(100).getTag(i));
//        }
    }




    //void mergeSubgraf(ArrayList<Integer> indexForMegre){    }

    void connectivityMethod(ArrayList<OsmWay> inputWays){


        ArrayList<ArrayList> generalListOfSubgraf = new ArrayList<>();      //Массив 1 уровня для подграфор, подграфы тоже массивы
        ArrayList<TreeSet<Long>> generalListOfNodesOfSubgraf = new ArrayList<>(); //Массив 1 уровня для Set`ов точек, по индексу они соответствуют соответствующим массивам в
        ArrayList<OsmWay> firstSubgraf = new ArrayList<>();                     //для первого вея

//        long[] nodeIDs;                                              //сюдя будем складывать массив getNodeIds
//        TreeSet<Long> tmp1 = new TreeSet<>();    // новый set для точек обрабатываемого вея
//        nodeIDs = inputWays.get(0).getNodeIds();         // вытаскиваем в массив id точек 1-го вея
//        for (long nID: nodeIDs) tmp1.add(nID);    // каждую точку добавляем в set


        //firstSubgraf.add(inputWays.get(0));            // сохраняем вей в первый субграф
        //generalListOfSubgraf.add(firstSubgraf); // сохраняем первый субграф в массив субграфов

        ArrayList<Integer> tempListForMergeSubgraf = new ArrayList<>(); //массивы для индексов объединямых субграфов и их точек
       // int i = 0;
        for (OsmWay osmWay: inputWays){              // берем попорядку веи из массива
          ///  System.out.println(inputWays.indexOf(osmWay) + " из " +inputWays.size());
         //   System.out.println("i"+i++);
            long[] nodeIDs;
            nodeIDs = osmWay.getNodeIds();         // вытаскиваем в массив id точек 1-го вея
            if(generalListOfNodesOfSubgraf.isEmpty()) {    // если главный массив пуст, то добавляем туда первый сет
                TreeSet<Long> tmp1 = new TreeSet<>();    // новый set для точек обрабатываемого вея
                for (long nID : nodeIDs) tmp1.add(nID);    // каждую точку добавляем в set
                generalListOfNodesOfSubgraf.add(tmp1);   // сохраняем set точек первого вея в массив сетов
                System.err.println("ПЕРВЫЙ ГРАФ ДОБАВЛЕН");
            }
            else {
                tempListForMergeSubgraf.clear();
////                System.out.println(tempListForMergeSubgraf.size() +" - размер Массива tempListForMergeSubgraf после очистки");


                for (TreeSet<Long> set : generalListOfNodesOfSubgraf) { // для каждого сета точек, которые уже находятся
                    for (long Lo : nodeIDs) {// сравниваем точки
//                    System.err.println(Lo);
//                    System.err.println(tempListForMergeSubgraf.size()+"size");
                        if (set.contains(Lo)) {
                            if (!tempListForMergeSubgraf.contains(generalListOfNodesOfSubgraf.indexOf(set)))
                            tempListForMergeSubgraf.add(generalListOfNodesOfSubgraf.indexOf(set));  // если точка найдена добавляем индекс сета в массив для объединения
 ////                            System.out.println("совпадение в сете: " + generalListOfNodesOfSubgraf.indexOf(set)+ ", сейчас будем объединять !!!!!!!!!!!");
                        }
                    }
                }

                // если есть что объединять то объединяме
////                System.err.println(tempListForMergeSubgraf.size()+" - размер Массива tempListForMergeSubgraf");
                if (tempListForMergeSubgraf.size() > 0) {
                    //добавляем текущий вей к первому из списка к объединению
//                    System.err.print("объединяем: ");
//                    for (Integer i1 : tempListForMergeSubgraf) {
//                        System.err.print(i1 + ", ");
//                    }
//                    System.err.println();
                    int indexFforMerge = tempListForMergeSubgraf.get(0);

//                ArrayList<OsmWay> tmpSubG = generalListOfSubgraf.get(indexFforMerge);
//                tmpSubG.add(osmWay);
//                generalListOfSubgraf.remove(indexFforMerge);
//                generalListOfSubgraf.add(indexFforMerge, tmpSubG);

                    //добавляем точки к сету точек
                   // добавляем точки вея к первому набору точек, в котором совпадение обнаружилось
                    TreeSet<Long> tmpSet = generalListOfNodesOfSubgraf.get(indexFforMerge);
                  ////  System.out.print("добавляем к сету: ");
                  ////  for (Long LONGF: tmpSet){
                  ////      System.out.print(LONGF + " ");
                 ////   }
                  //  System.out.println();
                    //System.err.println("размер сета до добавления = " + tmpSet.size());
                   //// System.out.print("эти точки: ");
                    for (long LL : nodeIDs) {
                   ////System.out.print(LL+ " ");
//                    System.out.println();
                        tmpSet.add(LL);
                    }
                    ////System.out.println();
                   //// System.out.print("получаем: ");
                    ////for (Long LLL: tmpSet ) System.out.print(" "+ LLL);
                    //System.err.println("размер сета после добавления = " + tmpSet.size());
                    generalListOfNodesOfSubgraf.remove(indexFforMerge);
                    generalListOfNodesOfSubgraf.add(indexFforMerge, tmpSet);


                    TreeSet<Long> tmpSetFMerge = new TreeSet<>();
                   //// System.out.println("слияние: ");
                    for (Integer integer : tempListForMergeSubgraf) { // добавляем все субграфы с совпадениями
                       //// System.out.print("массив:");
                       //// for (Long LS: tmpSetFMerge) System.out.print(LS+ " ");
                       //// System.out.println();
                        for (Long LONG: generalListOfNodesOfSubgraf.get(integer)){
                           //// System.out.print("добавляем " + LONG + ": ");
                            ////System.out.println(tmpSetFMerge.add(LONG));

                            tmpSetFMerge.add(LONG);
                        }
                        //tmpSetFMerge.add(generalListOfNodesOfSubgraf.get(integer).


                        //System.out.print(tmpSetFMerge.addAll(generalListOfNodesOfSubgraf.get(integer)));
                       //// System.out.print(generalListOfNodesOfSubgraf.get(integer).size() + "+");
                       //// System.out.print(" (="+tmpSetFMerge.size() + ") ");
                    }
                   ////System.out.println();
                   //// System.out.println("размер объединенного сета = " +tmpSetFMerge.size());
  ////                  System.err.println("размер главного массива до удаления = " + generalListOfNodesOfSubgraf.size());

                    for (int integer1 = tempListForMergeSubgraf.size() - 1; integer1 > -1; integer1--){
                        int iii = integer1;
                        int nSub = tempListForMergeSubgraf.get(iii);
                       //// System.out.println("удаляем субграф: " + nSub);
                            generalListOfNodesOfSubgraf.remove(nSub);
  ////                      System.err.println("remove! сет номер: " + integer);
  ////                      System.err.println("размер главного массива после удаления = " + generalListOfNodesOfSubgraf.size());
                    }


//                    for (Integer integer : tempListForMergeSubgraf) { // удаляем все с совпадениями из главного массива
//                        int i2 = integer;
//                        generalListOfNodesOfSubgraf.remove(i2);
//                        System.err.println("remove! сет номер: " + i2);
//                        System.err.println("размер главного массива после удаления = " + generalListOfNodesOfSubgraf.size());
//                    }
                    generalListOfNodesOfSubgraf.add(tmpSetFMerge);
   ////                 System.err.println("добавили! ");
//                ArrayList<OsmWay> tLWAY = new ArrayList<>();
//                for (Integer integer: tempListForMergeSubgraf){
//                    tLWAY.addAll(generalListOfSubgraf.get(integer));
//                }
//                for (Integer integer: tempListForMergeSubgraf){
//                    generalListOfSubgraf.remove(integer);
//                }
//                generalListOfSubgraf.add(tLWAY);
                }
                // или, если нечего объединять, добавить новый субграф
                else {
                    TreeSet<Long> tmp = new TreeSet<>();    // новый set для точек обрабатываемого вея
                    for (long LL : nodeIDs) tmp.add(LL);  //добавляем в новый сет точки
   ////                 System.err.println("новый субграф ______________________ ");
   ////                 System.err.println("размер главного массива после добавления нового субграфа " + generalListOfNodesOfSubgraf.size());

                    generalListOfNodesOfSubgraf.add(tmp);   // добавляем новы сет точек в главный массив точек

//                ArrayList<OsmWay> tmpSubgraf = new ArrayList<>();  //новый субгарф
//                tmpSubgraf.add(osmWay);     //добавляем в новый субграф обрабатываемый вей
//                generalListOfSubgraf.add(tmpSubgraf);  //новый субграф добавляем в главный массив субграфов
                }
               // System.err.println(generalListOfNodesOfSubgraf.size());
            }
        }
        System.err.println(generalListOfNodesOfSubgraf.size());
        System.out.println("-----------------------");
        int size1 = 0;
        for (TreeSet<Long> set: generalListOfNodesOfSubgraf){
            System.out.println(set.size());
            size1 =size1+ set.size();
            if (set.size()<140){
                for (Long Lpo: set){
                    System.out.print(Lpo + " ");
                }
                System.out.println();
            }
        }
        System.out.println("all = "+ size1);
    }








    /*  Метод возвращающий ArrayList всех значащих веев */
    ArrayList<OsmWay> dataToWays(MemoryStorage data) {
        ArrayList<OsmWay> ss = new ArrayList<>();
        data.byTag("highway", o -> {
            if (
                            o.getTag("highway", data).equals("service") ||
                            o.getTag("highway", data).equals("unclassified") ||
                            o.getTag("highway", data).equals("residential") ||
                            o.getTag("highway", data).equals("tertiary") ||
                            o.getTag("highway", data).equals("secondary") ||
                            o.getTag("highway", data).equals("primary") ||
                            o.getTag("highway", data).equals("motorway") ||
                            o.getTag("highway", data).equals("trunk") ||
                            o.getTag("highway", data).equals("living_street") ||
                            o.getTag("highway", data).equals("motorway_link") ||
                            o.getTag("highway", data).equals("trunk_link") ||
                            o.getTag("highway", data).equals("primary_link") ||
                            o.getTag("highway", data).equals("secondary_link")
                    )
                if (o instanceof OsmWay) ss.add((OsmWay) o);
        });
        return ss;
    }

}
