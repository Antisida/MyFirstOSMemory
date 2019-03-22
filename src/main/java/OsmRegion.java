import org.alex73.osmemory.MemoryStorage;

public class OsmRegion {
    String name;
    int ID;
    MemoryStorage O5M_Data;
    int[] neighbors;
    String path;

    public OsmRegion(String name, int ID, int[] neighbors, String path) {
        this.name = name;
        this.ID = ID;
        this.neighbors = neighbors;
        this.path = path;
    }





}
