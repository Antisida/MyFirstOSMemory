enum RussianRegion {
    MURMANSK(51, new int[]{10, 101, 102}, "z:\\osmtmp\\RU-MUR.o5m", true ),
    PETROZAVODSK(10, new int[]{29, 35, 47, 51, 102}, "z:\\osmtmp\\RU-KR.o5m", true ),
    SPB(47, new int[]{10, 35, 53, 60, 102, 110}, "z:\\osmtmp\\RU-LEN.o5m", true ),
    ARHANGELSK(29, new int[]{10, 11, 35, 43, 83}, "z:\\osmtmp\\RU-ARK.o5m", true ),


    NN(52, new int[]{12, 13, 21, 33, 37, 43, 44, 62}, "z:\\osmtmp\\RU-NIZ.o5m", true),
    VLADIMIR(33, new int[]{37, 50, 52, 62, 76}, "z:\\osmtmp\\RU-VLA.o5m", true),
    IVANOVO(37, new int[]{33, 52, 44, 76}, "z:\\osmtmp\\RU-IVA.o5m", true),
    KOSTROMA(44, new int[]{33, 37, 35, 43, 52, 76}, "z:\\osmtmp\\RU-KOS.o5m", true),
    KIROV(43, new int[]{11, 12, 16, 18, 29, 35, 44, 52, 59}, "z:\\osmtmp\\RU-KIR.o5m", true ),
    YOSHKAR_OLA(12, new int[]{16, 21, 43, 52}, "z:\\osmtmp\\RU-ME.o5m", true ),
    CHEBOKSARY(21, new int[]{12, 13, 16, 52, 73}, "z:\\osmtmp\\RU-CU.o5m", true ),
    SARANSK(13, new int[]{21, 52, 58, 62, 73}, "z:\\osmtmp\\RU-MO.o5m", true ),
    RYAZAN(62, new int[]{13, 33, 48, 50, 52, 58, 68, 71}, "z:\\osmtmp\\RU-RYA.o5m", true ),

    KAZAN(16, new int[]{2, 12, 18, 21, 43, 56, 63, 73}, "z:\\osmtmp\\RU-TA.o5m", true ),
    YLYANOVSK(73, new int[]{13, 16, 21, 58, 63, 64}, "z:\\osmtmp\\RU-ULY.o5m", true ),
    PENZA(58, new int[]{13, 62, 64, 68, 73}, "z:\\osmtmp\\RU-PNZ.o5m", true ),
    VOLOGDA(35, new int[]{10, 29, 43, 44, 47, 53, 69, 76}, "z:\\osmtmp\\RU-VLG.o5m", true ),
    YAROSLAVL(76, new int[]{33, 35, 37, 44, 50, 69}, "z:\\osmtmp\\RU-YAR.o5m", true ),





    PSKOV(60, new int[]{47, 53, 67, 69, 110, 111, 112}, "z:\\osmtmp\\RU-PSK.o5m", true ),
    TVER(69, new int[]{35, 50, 53, 60, 67, 76}, "z:\\osmtmp\\RU-TVE.o5m", true ),
    NOVGOROD(53, new int[]{35, 47, 60, 69}, "z:\\osmtmp\\RU-NGR.o5m", true ),
    SMOLENSK(67, new int[]{32, 40, 50, 60, 69, 112, 113}, "z:\\osmtmp\\RU-SMO.o5m", true ),
    MOSCOW(50, new int[]{33, 40, 62, 67, 69, 71, 76}, "z:\\osmtmp\\RU-MOS.o5m", true ),
    KALUGA(40, new int[]{32, 50, 57, 67, 71}, "z:\\osmtmp\\RU-KLU.o5m", true ),
    BRYANSK(32, new int[]{32, 46, 57, 67, 113, 114, 115, 116}, "z:\\osmtmp\\RU-BRY.o5m", true ),
    TULA(71, new int[]{40, 48, 50, 57 ,62}, "z:\\osmtmp\\RU-TUL.o5m", true ),
    OREL(57, new int[]{32, 40, 46, 48, 71}, "z:\\osmtmp\\RU-ORL.o5m", true ),

    KURSK(46, new int[]{31, 32, 36, 48, 57, 115}, "z:\\osmtmp\\RU-KRS.o5m", true ),
    BELGOROD(31, new int[]{36, 46, 116, 117, 118}, "z:\\osmtmp\\RU-BEL.o5m", true ),
    LIPETSK(48, new int[]{36, 46, 57, 62, 68, 71}, "z:\\osmtmp\\RU-LIP.o5m", true ),
    TAMBOV(68, new int[]{36, 48, 58, 62, 64, }, "z:\\osmtmp\\RU-TAM.o5m", true ),
    VORONEZH(36, new int[]{31, 34, 46, 48, 61, 64, 68, 118}, "z:\\osmtmp\\RU-VOR.o5m", true ),
    SARATOV(64, new int[]{34, 36, 58, 63, 68, 73, 131}, "z:\\osmtmp\\RU-SAR.o5m", true ),
    VOLGOGRAD(34, new int[]{8, 30, 36, 61, 64, 131 }, "z:\\osmtmp\\RU-VGG.o5m", true ),
    ASTRAHAN(30, new int[]{8, 34, 131}, "z:\\osmtmp\\RU-AST.o5m", true ),
    ELISTA(8, new int[]{5, 26, 30, 34, 61}, "z:\\osmtmp\\RU-KL.o5m", true ),

    ROSTOV(61, new int[]{8, 23, 26, 34, 36, 118, 119}, "z:\\osmtmp\\RU-ROS.o5m", true ),
    SEVASTOPOL(92, new int[]{82}, "z:\\osmtmp\\RU-SEV.o5m", true ),
    SIMFEROPOL(82, new int[]{92, 23, 120}, "z:\\osmtmp\\RU-CR.o5m", true ),
    KRASNODAR(23, new int[]{9, 26, 61, 82, 121}, "z:\\osmtmp\\RU-KDA.o5m", true ),
    STAVROPOL(26, new int[]{5, 7, 8, 9, 15, 20, 23, 61}, "z:\\osmtmp\\RU-STA.o5m", true ),
    CHERKESSK(9, new int[]{7, 23, 26, 121 }, "z:\\osmtmp\\RU-KC.o5m", true ),
    NALCHIK(7, new int[]{9, 15, 26, 121}, "z:\\osmtmp\\RU-KB.o5m", true ),
    VLADIKAVKAZ(15, new int[]{6, 7, 20, 26, 121}, "z:\\osmtmp\\RU-SE.o5m", true ),
    NAZRAN(6, new int[]{15, 20, 121}, "z:\\osmtmp\\RU-IN.o5m", true ),

    GROZNY(20, new int[]{5, 6, 15, 26, 121}, "z:\\osmtmp\\RU-CE.o5m", true ),
    MAHACHKALA(5, new int[]{8, 20, 26, 121, 122}, "z:\\osmtmp\\RU-DA.o5m", true ),
    SAMARA(63, new int[]{16, 56, 64, 73, 131}, "z:\\osmtmp\\RU-SAM.o5m", true ),
    ORENBURG(56, new int[]{2, 16, 63, 64, 74, 131}, "z:\\osmtmp\\RU-ORE.o5m", true ),
    UFA(2, new int[]{16, 18, 56, 59, 66, 74}, "z:\\osmtmp\\RU-BA.o5m", true ),
    IZHEVSK(18, new int[]{2, 16, 43, 59}, "z:\\osmtmp\\RU-UD.o5m", true ),
    PERM(59, new int[]{2, 11, 18, 43, 66}, "z:\\osmtmp\\RU-PER.o5m", true ),
    SYKTYVKAR(11, new int[]{29, 43, 59, 66, 83, 86, 89}, "z:\\osmtmp\\RU-KO.o5m", true ),
    NARYAN_MAR(83, new int[]{11, 29, 89}, "z:\\osmtmp\\RU-NEN.o5m", true ),

    //пограничные регионы
    HANTY_MAN(86, new int[]{11, 24, 55, 66, 70, 72, 89}, "z:\\osmtmp\\RU-KHM.o5m", false ),
    YAMAL(89, new int[]{11, 24, 83, 86}, "z:\\osmtmp\\RU-YAN.o5m", false ),
    EKATERINBURG(66, new int[]{2, 11, 45, 59, 72, 74, 86 }, "z:\\osmtmp\\RU-SVE.o5m", false),
    CHELYABINSK(74, new int[]{2, 45, 56, 66, 131}, "z:\\osmtmp\\RU-CHE.o5m", false ),

    NORG(101, new int[]{51}, "z:\\osmtmp\\norway-latest.o5m", false),
    FIN(102, new int[]{10, 47, 51}, "z:\\osmtmp\\finland-latest.o5m", false),

    EST(110, new int[]{47, 60}, "z:\\osmtmp\\estonia-latest.o5m", false),
    LAT(111, new int[]{60}, "z:\\osmtmp\\latvia-latest.o5m", false),

    BY_VITEBSK(112, new int[]{60, 67}, "z:\\osmtmp\\latvia-latest.o5m", false),
    BY_MOGILEV(113, new int[]{32, 67}, "z:\\osmtmp\\latvia-latest.o5m", false),
    BY_GOMEL(114, new int[]{32}, "z:\\osmtmp\\latvia-latest.o5m", false),

    UA_CHERNIGOV(115, new int[]{32}, "z:\\osmtmp\\UA-74.o5m", false),
    UA_SUMY(116, new int[]{31, 32, 46}, "z:\\osmtmp\\UA-59.o5m", false),
    UA_KHARKOV(117, new int[]{31}, "z:\\osmtmp\\UA-63.o5m", false),
    UA_LUGANSK(118, new int[]{31, 36, 61}, "z:\\osmtmp\\UA-09.o5m", false),
    UA_DONETSK(119, new int[]{61}, "z:\\osmtmp\\UA-14.o5m", false),
    UA_HERSON(120, new int[]{92}, "z:\\osmtmp\\UA-65.o5m", false),

    GRU(121, new int[]{5, 6, 7, 9, 15, 20, 23}, "z:\\osmtmp\\GE.o5m", false),
    AZER(122, new int[]{5}, "z:\\osmtmp\\AZ.o5m", false),

    KAZZ(131, new int[]{30, 34, 56, 63, 64}, "z:\\osmtmp\\KZ.o5m", false)
;

    String path;
    int[] neighbors;
    int id;
    boolean isRussian;

    RussianRegion(int id, int[] neighbors, String path, boolean isRussian) {
        this.path = path;
        this.neighbors = neighbors;
        this.id = id;
        this.isRussian = isRussian;
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

