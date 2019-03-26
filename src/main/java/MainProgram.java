
public class MainProgram {
    public static void main(String[] args) {
        BaseTest baseTest = new BaseTest();




        baseTest.aggregeteTest(52);
        baseTest.aggregeteTest(12);
        baseTest.aggregeteTest(44);
        baseTest.aggregeteTest(21);
        baseTest.aggregeteTest(37);

        baseTest.aggregeteTest(13);

        for(ReadyData readyData: BaseTest.resultList){
            if (readyData != null) {
                System.out.println("Печатаем итоги...");
                readyData.printRD();
            }
        }


    }
}
