import org.alex73.osmemory.*;
import java.io.File;
import java.util.*;

public class ConnectivityTest {
    public static void main(String[] args) {
        MemoryStorage data = null;
        MemoryStorage data1 = null;
        List<OsmWay> ss = new ArrayList<>();
        try {
            // data = new O5MReader().read(new File("e:\\osmtmp\\RU-NIZ.o5m"));
            data = new O5MReader().read(new File("c:\\osm\\RU-N_01.o5m"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        MemoryStorage finalData = data;
        if (data != null){

            data.byTag("highway", o -> {

                //if ((o.getTag("highway", finalData).equals("primary")) & (o.getTag("name", finalData) != null) )
                if (
                        o.getTag("highway", finalData).equals("service") ||
                        o.getTag("highway", finalData).equals("unclassified") ||
                        o.getTag("highway", finalData).equals("residential") ||
                        o.getTag("highway", finalData).equals("tertiary") ||
                        o.getTag("highway", finalData).equals("secondary") ||
                        o.getTag("highway", finalData).equals("primary") ||
                        o.getTag("highway", finalData).equals("motorway") ||
                        o.getTag("highway", finalData).equals("trunk") ||
                        o.getTag("highway", finalData).equals("living_street") ||
                        o.getTag("highway", finalData).equals("motorway_link") ||
                        o.getTag("highway", finalData).equals("trunk_link") ||
                        o.getTag("highway", finalData).equals("primary_link") ||
                        o.getTag("highway", finalData).equals("secondary_link")
                        )
                    if (o instanceof OsmWay) ss.add((OsmWay)o);
               //System.out.println(o.getTag("name", finalData));
            });
        }
        ArrayList<ArrayList> generalListOfSubgraf = new ArrayList<>();      //Массив, куда складываются рабочие подграфы
        ArrayList<TreeSet> generalListOfNodesOfSubgraf = new ArrayList<>(); //Массив рабочих массивов точек
        ArrayList<Long> firstSubgraf = new ArrayList<>();
        TreeSet<Long> firstSetOfNodes = new TreeSet<>();

        long[] longs1;
        TreeSet<Long> tmp1 = new TreeSet<>();    // новый set для точек обрабатываемого вея
        longs1 = ss.get(0).getNodeIds();         // вытаскиваем в массив id точек 1-го вея
        for (long lon: longs1) tmp1.add(lon);    // каждую точку добавляем в set
        generalListOfNodesOfSubgraf.add(tmp1);   // сохраняем set в массив сетов

        for (OsmWay osmWay: ss){
            long[] longs;
            TreeSet<Long> tmp = new TreeSet<>();    // новый set для точек обрабатываемого вея
            longs = osmWay.getNodeIds();         // вытаскиваем в массив id точек 1-го вея
            for (TreeSet set: generalListOfNodesOfSubgraf){ // для каждого сета точек
                boolean i = false;
                for (long Lo: longs){                     // сравниваем точки
                    if (set.contains(Lo)) i = true;
                }
                if (i){                                        // если точка найдена
                    for (long Lo: longs){
                        set.add(Lo);
                    }

                }
            }

        }

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







}
