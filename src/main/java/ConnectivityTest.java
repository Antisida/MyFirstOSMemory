import org.alex73.osmemory.*;
import java.io.File;
import java.util.*;

public class ConnectivityTest {
    public static void main(String[] args) {
        MemoryStorage data = null;


        try {
             //data = new O5MReader().read(new File("e:\\osmtmp\\RU-NIZ.o5m"));
            data = new O5MReader().read(new File("c:\\osm\\RU-N_01.o5m"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(ss.size());

        ArrayList<ArrayList> generalListOfSubgraf = new ArrayList<>();      //Массив, куда складываются рабочие подграфы
        ArrayList<TreeSet> generalListOfNodesOfSubgraf = new ArrayList<>(); //Массив рабочих массивов точек
        ArrayList<OsmWay> firstSubgraf = new ArrayList<>();
        //TreeSet<Long> firstSetOfNodes = new TreeSet<>();

        long[] longs1;
        TreeSet<Long> tmp1 = new TreeSet<>();    // новый set для точек обрабатываемого вея
        longs1 = ss.get(0).getNodeIds();         // вытаскиваем в массив id точек 1-го вея
        for (long lon: longs1) tmp1.add(lon);    // каждую точку добавляем в set

        generalListOfNodesOfSubgraf.add(tmp1);   // сохраняем set точек первого вея в массив сетов
        firstSubgraf.add(ss.get(0));            // сохраняем вей в первый субграф
        generalListOfSubgraf.add(firstSubgraf); // сохраняем первый субграф в массив субграфов

        ArrayList<Integer> tempListForMergeSubgraf = new ArrayList<>(); //массивы для индексов объединямых субграфов и их точек
        ArrayList<Integer> tempListForMergeSubgrafNodes = new ArrayList<>();

        int index = 0;

        for (OsmWay osmWay: ss){              // берем попорядку веи из массива
            tempListForMergeSubgraf.clear();
            tempListForMergeSubgrafNodes.clear();
            long[] longs;

            TreeSet<Long> tmp = new TreeSet<>();    // новый set для точек обрабатываемого вея
            longs = osmWay.getNodeIds();         // вытаскиваем в массив id точек 1-го вея
            for (TreeSet set: generalListOfNodesOfSubgraf){ // для каждого сета точек, которые уже находятся
               // boolean i = false;
                index++;
                for (long Lo: longs){                     // сравниваем точки
                    //if (set.contains(Lo)) i = true;

                    if (set.contains(Lo)) {
                        tempListForMergeSubgraf.add(generalListOfNodesOfSubgraf.indexOf(set));  // если точка найдена добавляем индекс сета в массив для объединения
                        tempListForMergeSubgrafNodes.add(generalListOfNodesOfSubgraf.indexOf(set));
                    }
                }
            }
            // тут надо объединить субграфы
            if (!tempListForMergeSubgraf.isEmpty()){

            }
            // или, если нечего объединять, добавить новый субграф
            else {
                longs = osmWay.getNodeIds();
                for (long LL: longs) {
                   // System.out.println(LL);
                    tmp.add(LL);
                }

                generalListOfNodesOfSubgraf.add(tmp);
                ArrayList<OsmWay> tmpSubgraf = new ArrayList<>();
                //System.out.println(osmWay.getObjectID());

                tmpSubgraf.add(osmWay);

                generalListOfSubgraf.add(tmpSubgraf);
            }



        }
        System.out.println("размер generalListOfSubgraf = " + generalListOfSubgraf.size());
        System.out.println("размер generalListOfNodesOfSubgraf = " + generalListOfNodesOfSubgraf.size());
        for (ArrayList<OsmWay> arrayLists: generalListOfSubgraf){

            for (OsmWay osmWays: arrayLists ){
                System.out.println(osmWays.getObjectID());
            }
        }
        System.out.println(index);


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




    void mergeSubgraf(){}

        // медот возвращающий ArrayList всех значащих веев
    ArrayList<OsmWay> dataToWays(MemoryStorage data) {
        MemoryStorage memoryStorage = data;
        ArrayList<OsmWay> ss = new ArrayList<>();
        memoryStorage.byTag("highway", o -> {
            if (
                            o.getTag("highway", memoryStorage).equals("service") ||
                            o.getTag("highway", memoryStorage).equals("unclassified") ||
                            o.getTag("highway", memoryStorage).equals("residential") ||
                            o.getTag("highway", memoryStorage).equals("tertiary") ||
                            o.getTag("highway", memoryStorage).equals("secondary") ||
                            o.getTag("highway", memoryStorage).equals("primary") ||
                            o.getTag("highway", memoryStorage).equals("motorway") ||
                            o.getTag("highway", memoryStorage).equals("trunk") ||
                            o.getTag("highway", memoryStorage).equals("living_street") ||
                            o.getTag("highway", memoryStorage).equals("motorway_link") ||
                            o.getTag("highway", memoryStorage).equals("trunk_link") ||
                            o.getTag("highway", memoryStorage).equals("primary_link") ||
                            o.getTag("highway", memoryStorage).equals("secondary_link")
                    )
                if (o instanceof OsmWay) ss.add((OsmWay) o);
        });
        return ss;
    }

}
