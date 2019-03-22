import java.util.ArrayList;

public class BaseTest {
    static ArrayList<OsmRegion> list = RussianRegions.getAllRegions();

    public static void main(String[] args) {
        for (OsmRegion osmRegion: list) System.out.println(osmRegion.name);
    }
}
