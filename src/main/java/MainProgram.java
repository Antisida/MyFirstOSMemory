import com.koloboke.collect.set.LongSet;
//todo казахстан два раза обрабатывается
public class MainProgram {
    public static void main(String[] args) {
        BaseTest baseTest = new BaseTest();
//        OsmRegion osmRegion = baseTest.deSerializeOsmRegion(RussianRegion.SEVASTOPOL);
//        for (LongSet longs: osmRegion.isolatedSets){
//            System.out.println(longs);
//        }
        for (RussianRegion russianRegion: RussianRegion.values()){
            //if (!russianRegion.isRussian) continue;
            baseTest.aggregeteTest(russianRegion);
        }

     /* OsmRegion osmRegion = baseTest.deSerializeOsmRegion(RussianRegion.NORG);
      for (LongSet longs: osmRegion.isolatedSets){
          System.out.println(osmRegion.name);
          System.out.println(osmRegion.isolatedSets.size());
          System.out.println(longs.size());
          //System.out.println(longs);
      }*/
//        baseTest.aggregeteTest(29);
//        baseTest.aggregeteTest(11);
//        baseTest.aggregeteTest(35);
//        baseTest.aggregeteTest(76);
//        baseTest.aggregeteTest(69);
//        baseTest.aggregeteTest(40);
//        baseTest.aggregeteTest(71);
//        baseTest.aggregeteTest(57);
//        baseTest.aggregeteTest(48);
//        baseTest.aggregeteTest(68);
//        baseTest.aggregeteTest(62);
//        baseTest.aggregeteTest(58);
//        baseTest.aggregeteTest(13);
//        baseTest.aggregeteTest(52);
//        baseTest.aggregeteTest(33);
//
//
//        baseTest.aggregeteTest(37);
//        baseTest.aggregeteTest(44);
//        baseTest.aggregeteTest(43);
//        baseTest.aggregeteTest(59);
//        baseTest.aggregeteTest(2);
//
//        baseTest.aggregeteTest(16);
//        baseTest.aggregeteTest(18);
//        baseTest.aggregeteTest(12);
//        baseTest.aggregeteTest(21);
//        baseTest.aggregeteTest(73);

        System.out.println("Почему-то остались в innerTestCompletedRegions: ");
        for (OsmRegion osmRegion: BaseTest.innerTestCompletedRegions){
            if(osmRegion != null){
                System.out.println(osmRegion.name);
            }
        }

        for(ReadyData readyData: BaseTest.finalDatas){
            if (readyData != null) {
                System.out.println("Печатаем итоги...");
                readyData.printRD();
            }
        }


    }
}
