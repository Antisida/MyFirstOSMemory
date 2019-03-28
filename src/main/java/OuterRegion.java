import javax.swing.*;

public enum OuterRegion {
    NORG
    FIN
    EST,
    LAT,
    BELARUS,
    UKR,
    GRU,
    AZER,
    String path;
    int[] neighbors;
    int id;

    RussianRegion(int id, int[] neighbors, String path) {
        this.path = path;
        this.neighbors = neighbors;
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public int[] getNeighbors() {
        return neighbors;
    }

    public int getId() {
        return id;
    }
}
