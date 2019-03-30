public class LargRegion {
    public static void main(String[] args) {
        BaseTest baseTest = new BaseTest();
        OsmRegion fin = new OsmRegion(RussianRegion.FIN);
        fin.isolatedSets = baseTest.innerConnectivityTest(fin.O5M_Data);
        baseTest.serializeOsmRegion(fin);
        OsmRegion nor = new OsmRegion(RussianRegion.NORG);
        nor.isolatedSets = baseTest.innerConnectivityTest(nor.O5M_Data);
        baseTest.serializeOsmRegion(nor);

    }
}
