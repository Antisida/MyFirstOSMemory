package ver2;

import com.koloboke.collect.set.LongSet;
import org.alex73.osmemory.MemoryStorage;
import org.alex73.osmemory.O5MReader;

import java.io.File;
import java.util.ArrayList;

public class OsmRegionConteiner {
    BaseOsmRegion baseOsmRegion;
    MemoryStorage O5M_Data;
    ArrayList<LongSet> fofDeleteSets = new ArrayList<>();

    public OsmRegionConteiner(RussianRegion russianRegion) {
        this.baseOsmRegion = new BaseOsmRegion();
        this.O5M_Data = loadO5Mfile(russianRegion.getPath());
    }

    public BaseOsmRegion getBaseOsmRegion() {
        return baseOsmRegion;
    }

    private MemoryStorage loadO5Mfile(String path){
        MemoryStorage data = null;
        try {
            data = new O5MReader().read(new File(path));
        }catch (Exception e) {
            System.out.println("Файл не найден: " + path);
        }
        return data;
    }
}
