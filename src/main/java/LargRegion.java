public class LargRegion {
    public static void main(String[] args) {
        BaseTest baseTest = new BaseTest();
//        ArrayList<HashSet<Long>> arrayList = new ArrayList<>();
//        OsmRegion nn = new OsmRegion(RussianRegion.SEVASTOPOL);
//        nn.isolatedSets = baseTest.innerConnectivityTest(nn.O5M_Data);
//        for (LongSet longs: nn.isolatedSets){
//            System.out.println(longs);
//        }
//        System.out.println("-----------");
//        for (LongSet longs: nn.isolatedSets){
//            HashSet<Long> set = new HashSet<>(longs);
//            System.out.println(set);
//            arrayList.add(set);
//        }
//        for (HashSet<Long> longs1: arrayList){
//            System.out.println(longs1);
//        }
//        OsmRegion nn = new OsmRegion(RussianRegion.SEVASTOPOL);
//        nn.isolatedSets = baseTest.innerConnectivityTest(nn.O5M_Data);
//        baseTest.serializeOsmRegion(nn);
        OsmRegion nor = new OsmRegion(RussianRegion.NORG);
        nor.isolatedSets = baseTest.innerConnectivityTest(nor.O5M_Data);
        nor.O5M_Data = null;
        baseTest.serializeOsmRegion(nor);
        nor.isolatedSets = null;

        nor = null;

       /* OsmRegion fin = new OsmRegion(RussianRegion.SEVASTOPOL);
        fin.isolatedSets = baseTest.innerConnectivityTest(fin.O5M_Data);
        fin.O5M_Data = null;
        baseTest.serializeOsmRegion(fin);
        fin.isolatedSets = null;
        fin = null;
        System.gc();
*/


    }
}
