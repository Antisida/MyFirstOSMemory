import java.io.Serializable;

public class SimplifyOsmRegion extends BaseOsmRegion implements Serializable {
    public SimplifyOsmRegion(OsmRegion osmRegion) {
        this.name = osmRegion.name;
        this.ID = osmRegion.ID;
        this.isolatedSets = osmRegion.isolatedSets;
    }
}
