package ver2;

public class Tests {
    static BaseOsmRegion[] innerTestCompletedRegions = new BaseOsmRegion[100];
    static boolean[] outerTestCompletedRegions = new boolean[100];
    static BaseOsmRegion[] finalDatas = new BaseOsmRegion[100];

    boolean isInnerTestComplete(RussianRegion russianRegion) {
        if (innerTestCompletedRegions[russianRegion.id] != null) return true;
        return false;
    }
}
