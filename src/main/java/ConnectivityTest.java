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
            //  data = new O5MReader().read(new File("e:\\osmtmp\\RU-NIZ.o5m"));
            data = new O5MReader().read(new File("c:\\osm\\RU-N_01.o5m"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<OsmWay> ss = connectivityTest.dataToWays(data);
        connectivityTest.connectivityMethod(ss);

        // алгоритм поиска подграфов: елсли найден подграф с точкой, заносим его индекс из Ареейлиста в массив и ищем дальше.
        // если сли найден еще один подграф с точкой заносим его индекс в массив и т.д
        // объединяем найденные подграфы в один и удаляем ненужные
    }

    void connectivityMethod(ArrayList<OsmWay> inputWays){
        long st = System.nanoTime();
        ArrayList<ArrayList> generalListOfSubgraf = new ArrayList<>();      //Массив 1 уровня для подграфор, подграфы тоже массивы
        ArrayList<TreeSet<Long>> generalListOfNodesOfSubgraf = new ArrayList<>(); //Массив 1 уровня для Set`ов точек, по индексу они соответствуют соответствующим массивам в
        ArrayList<OsmWay> firstSubgraf = new ArrayList<>();                     //для первого вея
        ArrayList<Integer> tempListForMergeSubgraf = new ArrayList<>(); //массивы для индексов объединямых субграфов и их точек
        for (OsmWay osmWay: inputWays){              // берем попорядку веи из массива
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

                for (TreeSet<Long> set : generalListOfNodesOfSubgraf) { // для каждого сета точек, которые уже находятся
                    for (long Lo : nodeIDs) {// сравниваем точки
                        if (set.contains(Lo)) {
                            if (!tempListForMergeSubgraf.contains(generalListOfNodesOfSubgraf.indexOf(set)))
                            tempListForMergeSubgraf.add(generalListOfNodesOfSubgraf.indexOf(set));  // если точка найдена добавляем индекс сета в массив для объединения
                        }
                    }
                }

                // если есть что объединять то объединяме
                if (tempListForMergeSubgraf.size() > 0) {
                    int indexFforMerge = tempListForMergeSubgraf.get(0);
                    //добавляем точки к сету точек
                   // добавляем точки вея к первому набору точек, в котором совпадение обнаружилось
                    TreeSet<Long> tmpSet = generalListOfNodesOfSubgraf.get(indexFforMerge);
                    for (long LL : nodeIDs) {
                        tmpSet.add(LL);
                    }
                    generalListOfNodesOfSubgraf.remove(indexFforMerge);
                    generalListOfNodesOfSubgraf.add(indexFforMerge, tmpSet);

                    TreeSet<Long> tmpSetFMerge = new TreeSet<>();
                    for (Integer integer : tempListForMergeSubgraf) { // добавляем все субграфы с совпадениями
                        for (Long LONG: generalListOfNodesOfSubgraf.get(integer)){
                            tmpSetFMerge.add(LONG);
                        }
                    }

                    for (int integer1 = tempListForMergeSubgraf.size() - 1; integer1 > -1; integer1--){
                        int iii = integer1;
                        int nSub = tempListForMergeSubgraf.get(iii);
                            generalListOfNodesOfSubgraf.remove(nSub);
                    }
                    generalListOfNodesOfSubgraf.add(tmpSetFMerge);
                }
                // или, если нечего объединять, добавить новый субграф
                else {
                    TreeSet<Long> tmp = new TreeSet<>();    // новый set для точек обрабатываемого вея
                    for (long LL : nodeIDs) tmp.add(LL);  //добавляем в новый сет точки
                    generalListOfNodesOfSubgraf.add(tmp);   // добавляем новы сет точек в главный массив точек
                }
            }
        }
        long fn = System.nanoTime();
        System.out.println("-----------------------");
        System.out.println("Время:" + (fn - st)/1000 + "сек");
        System.err.println("Количество графов: " + generalListOfNodesOfSubgraf.size());
        System.out.println("-----------------------");
        int size1 = 0;
        for (TreeSet<Long> set: generalListOfNodesOfSubgraf){
            System.out.println("Размер графа: " +set.size());
            size1 =size1+ set.size();
            if (set.size() < 2000){
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
                            //o.getTag("highway", data).equals("service") ||
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
                            o.getTag("highway", data).equals("tertiary_link") ||
                            o.getTag("highway", data).equals("secondary_link")
                    )
                if (o instanceof OsmWay) ss.add((OsmWay) o);
        });
        return ss;
    }

}
