import com.koloboke.collect.set.LongSet;

import java.util.ArrayList;

public class BaseOsmRegion {
    String name;
    int ID;
    ArrayList<LongSet> isolatedSets; //для данных после теста связности этого региона

    public BaseOsmRegion() {
        this.name = null;
        this.ID = 0;
        this.isolatedSets = null;
    }
}
