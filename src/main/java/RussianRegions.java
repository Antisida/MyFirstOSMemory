import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class RussianRegions {
    private static ArrayList<OsmRegion> allRegions = new ArrayList<>(
            Arrays.asList(
            new OsmRegion(
                    "NN",
                    52,
                    new int[]{12, 13, 21, 33, 37, 43, 44, 62},
                    "e:\\osmtmp\\RU-NIZ.o5m"),
            new OsmRegion(
                    "VLADIMIR",
                    33,
                    new int[]{37, 50, 52, 62, 76},
                    "e:\\osmtmp\\RU-NIZ.o5m"),
            new OsmRegion(
                    "IVANOVO",
                    37,
                    new int[]{33, 50, 52, 44},
                    "e:\\osmtmp\\RU-NIZ.o5m"),
            new OsmRegion(
                    "KOSTROMA",
                    44,
                    new int[]{33, 35, 43, 52, 76},
                    "e:\\osmtmp\\RU-NIZ.o5m"),
            new OsmRegion(
                    "KIROV",
                    43,
                    new int[]{11, 12, 16, 18, 29, 35, 44, 52, 59},
                    "e:\\osmtmp\\RU-NIZ.o5m"),
            new OsmRegion(
                    "YOSHKAR_OLA",
                    12,
                    new int[]{16, 21, 43, 52},
                    "e:\\osmtmp\\RU-NIZ.o5m"),
            new OsmRegion(
                    "CHEBOKSARY",
                    21,
                    new int[]{12, 13, 16, 52, 73},
                    "e:\\osmtmp\\RU-NIZ.o5m"),
            new OsmRegion(
                    "SARANSK",
                    13,
                    new int[]{21, 52, 58, 62, 73},
                    "e:\\osmtmp\\RU-NIZ.o5m"),
            new OsmRegion(
                    "RYAZAN",
                    13,
                    new int[]{13, 33, 48, 50, 52, 58, 68, 71},
                    "e:\\osmtmp\\RU-NIZ.o5m")
    ));

    static public ArrayList<OsmRegion> getAllRegions() {
       return allRegions;
    }
}
