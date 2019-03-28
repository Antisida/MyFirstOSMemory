package ver2;

import com.koloboke.collect.set.LongSet;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseOsmRegion implements Serializable {
    String name;
    int ID;
    ArrayList<LongSet> isolatedSets; //для данных после теста связности этого региона


}
