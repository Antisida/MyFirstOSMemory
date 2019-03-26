
import com.koloboke.collect.set.LongSet;

import java.util.ArrayList;


public class ReadyData {
    String nameRegion;
    ArrayList<LongSet> finalIsolatedSets;// для данных после окончательного теста связности


    void printRD(){
        System.out.println("------------" + nameRegion);
        for (LongSet longs: finalIsolatedSets){
            System.out.print("Размер графа: "+ longs.size() + " ==>> ");
            if(longs.size() < 1500) {
                for (long l : longs) {
                    System.out.print(l + " ");
                }
            }
            System.out.println();
        }
    }
    //LongSet outSet;

}
