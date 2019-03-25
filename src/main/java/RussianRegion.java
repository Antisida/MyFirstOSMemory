enum RussianRegion {
    NN(52, new int[]{12, 13, 21, 33, 37, 43, 44, 62}, "z:\\osmtmp\\RU-NIZ.o5m" ),
    VLADIMIR(33, new int[]{37, 50, 52, 62, 76}, "z:\\osmtmp\\RU-VLA.o5m" ),
    IVANOVO(37, new int[]{33, 52, 44, 76}, "z:\\osmtmp\\RU-IVA.o5m" ),
    KOSTROMA(44, new int[]{33, 37, 35, 43, 52, 76}, "z:\\osmtmp\\RU-KOS.o5m" ),
    KIROV(43, new int[]{11, 12, 16, 18, 29, 35, 44, 52, 59}, "z:\\osmtmp\\RU-KIR.o5m" ),
    YOSHKAR_OLA(12, new int[]{16, 21, 43, 52}, "z:\\osmtmp\\RU-ME.o5m" ),
    CHEBOKSARY(21, new int[]{12, 13, 16, 52, 73}, "z:\\osmtmp\\RU-CU.o5m" ),
    SARANSK(13, new int[]{21, 52, 58, 62, 73}, "z:\\osmtmp\\RU-MO.o5m" ),
    RYAZAN(62, new int[]{13, 33, 48, 50, 52, 58, 68, 71}, "z:\\osmtmp\\RU-RYA.o5m" ),
    KAZAN(16, new int[]{2, 12, 18, 21, 43, 56, 63, 73}, "z:\\osmtmp\\RU-TA.o5m" ),
    YLYANOVSK(73, new int[]{13, 16, 21, 58, 63, 64}, "z:\\osmtmp\\RU-ULY.o5m" ),
    PENZA(58, new int[]{13, 62, 64, 68, 73}, "z:\\osmtmp\\RU-PNZ.o5m" ),
    VOLOGDA(35, new int[]{10, 29, 43, 44, 47, 53, 69, 76}, "z:\\osmtmp\\RU-VLG.o5m" ),
    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),

//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),
//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),
//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),
//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),
//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),
//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),
//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),
//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),
//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),
//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),
//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" ),
//    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m" )
;
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
