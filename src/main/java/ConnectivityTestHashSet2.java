import org.alex73.osmemory.MemoryStorage;
import org.alex73.osmemory.O5MReader;
import org.alex73.osmemory.OsmNode;
import org.alex73.osmemory.OsmWay;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class ConnectivityTestHashSet2 {


    MemoryStorage data = null;
    private static long timeF;
    private static long timeFind;
    private static long delta = 0L;
    private static long timeFindSumm = 0L;
    private static long timeMerge = 0;
    private static long timeMergeSumm = 0;
    private static long counter = 0;

    public static void main(String[] args) {
        ConnectivityTestHashSet2 connectivityTestHashSet = new ConnectivityTestHashSet2();
        MemoryStorage data = null;
        try {
            // data = new O5MReader().read(new File("e:\\osmtmp\\RU-SEV.o5m"));
            //data = new O5MReader().read(new File("e:\\osmtmp\\RU-NIZ.o5m"));
            data = new O5MReader().read(new File("c:\\osm\\RU-N_01.o5m"));
          //  data = new O5MReader().read(new File("c:\\osm\\RU-KOS.o5m"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<OsmWay> ss = connectivityTestHashSet.dataToWays(data);
        connectivityTestHashSet.createAdjList(ss);

        // алгоритм поиска подграфов: елсли найден подграф с точкой, заносим его индекс из Ареейлиста в массив и ищем дальше.
        // если сли найден еще один подграф с точкой заносим его индекс в массив и т.д
        // объединяем найденные подграфы в один и удаляем ненужные
    }

    private void connectivityMethod(ArrayList<OsmWay> inputWays) {
        long st = System.nanoTime();
        timeF = System.nanoTime();

        ArrayList<ArrayList> generalListOfSubgraf = new ArrayList<>();      //Массив 1 уровня для подграфор, подграфы тоже массивы
        ArrayList<HashSet<Long>> generalListOfNodesOfSubgraf = new ArrayList<>(); //Массив 1 уровня для Set`ов точек, по индексу они соответствуют соответствующим массивам в
        ArrayList<OsmWay> firstSubgraf = new ArrayList<>();                     //для первого вея
        ArrayList<Integer> tempListForMergeSubgraf = new ArrayList<>(); //массивы для индексов объединямых субграфов и их точек
        for (OsmWay osmWay : inputWays) {              // берем попорядку веи из массива

            counter++;

            long[] nodeIDs;
            nodeIDs = osmWay.getNodeIds();         // вытаскиваем в массив id точек 1-го вея
            if (generalListOfNodesOfSubgraf.isEmpty()) {    // если главный массив пуст, то добавляем туда первый сет
                HashSet<Long> tmp1 = new HashSet<>();    // новый set для точек обрабатываемого вея
                for (long nID : nodeIDs) tmp1.add(nID);    // каждую точку добавляем в set
                generalListOfNodesOfSubgraf.add(tmp1);   // сохраняем set точек первого вея в массив сетов
                System.err.println("ПЕРВЫЙ ГРАФ ДОБАВЛЕН");
            } else {
                tempListForMergeSubgraf.clear();
                timeFind = System.nanoTime();
                for (HashSet<Long> set : generalListOfNodesOfSubgraf) { // для каждого сета точек, которые уже находятся
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

                    int indexFforMerge = tempListForMergeSubgraf.get(0);
                    //добавляем точки к сету точек
                    // добавляем точки вея к первому набору точек, в котором совпадение обнаружилось
                    HashSet<Long> tmpSet = generalListOfNodesOfSubgraf.get(indexFforMerge);
                    for (long LL : nodeIDs) {
                        tmpSet.add(LL);
                    }
                    generalListOfNodesOfSubgraf.remove(indexFforMerge);
                    generalListOfNodesOfSubgraf.add(indexFforMerge, tmpSet);

                    HashSet<Long> tmpSetFMerge = new HashSet<>();

//                    for (Integer integer : tempListForMergeSubgraf) { // добавляем все субграфы с совпадениями
//                        for (Long LONG: generalListOfNodesOfSubgraf.get(integer)){
//                            tmpSetFMerge.add(LONG);
//                        }
//                    }


                    for (Integer integer : tempListForMergeSubgraf) { // добавляем все субграфы с совпадениями
                        tmpSetFMerge.addAll(generalListOfNodesOfSubgraf.get(integer));
                    }

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
                    HashSet<Long> tmp = new HashSet<>();    // новый set для точек обрабатываемого вея
                    for (long LL : nodeIDs) tmp.add(LL);  //добавляем в новый сет точки
                    generalListOfNodesOfSubgraf.add(tmp);   // добавляем новы сет точек в главный массив точек
                }
            }

            if (counter % 10_000 == 0) {
                delta = (System.nanoTime()) - timeF;
                System.err.println("Среднее за 10_000 = " + delta / 10_000_000_000F);
                System.out.println("размер = " + generalListOfNodesOfSubgraf.size());
                System.out.println("Среднее время поиска = " + timeFindSumm / 10_000_000_000F);
                System.out.println("Среднее время объединения = " + timeMergeSumm / 10_000_000_000F);
                timeF = System.nanoTime();
                timeFindSumm = 0;
                timeMergeSumm = 0;
            }
        }

        delta = (System.nanoTime()) - timeF;
        System.err.println("Среднее за 10_000 = " + delta / 10_000_000_000F);
        System.out.println("размер = " + generalListOfNodesOfSubgraf.size());
        System.out.println("Среднее время поиска = " + timeFindSumm / 10_000_000_000F);
        System.out.println("Среднее время объединения = " + timeMergeSumm / 10_000_000_000F);


        long fn = System.nanoTime();
        System.out.println("-----------------------");
        System.out.println("Время:" + (fn - st) / 1000_000_000 + "сек");
        System.err.println("Количество графов: " + generalListOfNodesOfSubgraf.size());
        System.out.println("-----------------------");
        int size1 = 0;
        for (HashSet<Long> set : generalListOfNodesOfSubgraf) {
            System.out.println("Размер графа: " + set.size());
            size1 = size1 + set.size();
            if (set.size() < 2000) {
                for (Long Lpo : set) {
                    System.out.print(Lpo + " ");
                }
                System.out.println();
            }
        }
        System.out.println("all = " + size1);
    }

    public HashMap<Long, SimpleMarkedNode> createAdjList(ArrayList<OsmWay> osmWays) {

        HashMap<Long, SimpleMarkedNode> adjList = new HashMap<>();

        for (OsmWay osmWay : osmWays) {
            getAdjSetForWay(osmWay, adjList);
        }

        System.out.println(adjList.size());
        System.out.println(adjList.get(580334994L));

        return adjList;
    }

    public HashMap<Long, SimpleMarkedNode> getAdjSetForWay(OsmWay osmWay, HashMap<Long, SimpleMarkedNode> adjSet) {

        if (osmWay.getNodeIds().length < 2) {
            System.out.println("!");
            return adjSet;
        }

        long[] nodes = osmWay.getNodeIds();
        int i = 0;
        while (i < nodes.length) {
            //  long node = nodes[i];
            //создаем ноду с id и с ссылкой на вей, к которому она относится
            HashSet<Long> hs = new HashSet<>();
            if (i > 0 & i < nodes.length - 1) {
//                System.out.println("0-0");
                hs.add(nodes[i - 1]);
                hs.add(nodes[i + 1]);
//                System.out.println(hs);
                adjSet.merge(nodes[i], new SimpleMarkedNode(nodes[i], hs, osmWay.getId()),
                        (old, newv) -> {
                            old.getNeighbour().addAll(newv.getNeighbour());
                            return old;
                        });

            }
            if (i == 0) {
//                System.out.println("0");
                hs.add(nodes[i + 1]);
//                System.out.println(hs);
                adjSet.merge(nodes[0], new SimpleMarkedNode(nodes[0], hs, osmWay.getId()),
                        (old, newv) -> {
                            old.getNeighbour().addAll(newv.getNeighbour());
                            return old;
                        });
                //   return adjSet;
            }
            if (i == nodes.length - 1) {
//                System.out.println("end");
                hs.add(nodes[i - 1]);
//                System.out.println(nodes[nodes.length - 1]);
//                System.out.println(hs);
                adjSet.merge(nodes[nodes.length - 1], new SimpleMarkedNode(nodes[nodes.length - 1], hs,  osmWay.getId()),
                        (old, newv) -> {
                            old.getNeighbour().addAll(newv.getNeighbour());
                            return old;
                        });
                //  return adjSet;
            }

            i++;
        }
        return adjSet;
    }

//    HashSet<SimpleMarkedNode> connTest(HashMap<SimpleMarkedNode, HashSet<Long>> adjSet) {
//        while (adjSet.keySet().stream().anyMatch(k -> !k.isVisited())) {
//            long l = adjSet.keySet().stream().filter(k -> !k.isVisited()).findFirst().map(d -> d.) get().getId();
//            Stack<Long> stack = new Stack<>();
//            stack.push(l);
//        }
//        return null;
//    }


    /*  Метод возвращающий ArrayList всех значащих веев */
    private ArrayList<OsmWay> dataToWays(MemoryStorage data) {
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
                            o.getTag("highway", data).equals("tertiary_link") ||
                            o.getTag("highway", data).equals("secondary_link")
            )
                if (o instanceof OsmWay) ss.add((OsmWay) o);
        });
        return ss;
    }

}
