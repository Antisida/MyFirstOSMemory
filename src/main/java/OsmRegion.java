
import com.koloboke.collect.set.LongSet;
import org.alex73.osmemory.MemoryStorage;
import org.alex73.osmemory.O5MReader;

import java.io.File;
import java.util.ArrayList;

public class OsmRegion extends BaseOsmRegion{
    //String name;
    //int ID;
    MemoryStorage O5M_Data;
    int[] neighbors;
    private String path;
    //ArrayList<LongSet> isolatedSets; //для данных после теста связности этого региона
    ArrayList<LongSet> fofDeleteSets = new ArrayList<>();
    ReadyData outData;// для данных после окончательного теста связности


    public OsmRegion(int ID) {
        boolean notFind = true;
        for (RussianRegion russianRegion: RussianRegion.values()) {
            if (russianRegion.id == ID) {
                notFind = false;
                this.name = russianRegion.name();
                this.ID = russianRegion.getId();
                this.neighbors = russianRegion.getNeighbors();
                this.path = russianRegion.getPath();
                this.outData = new ReadyData();
                this.outData.nameRegion = russianRegion.name();
                loadO5Mfile(russianRegion.getPath());
            }
        }
        if (notFind) System.out.println("Ошибка: Регион по ID не найден..." + ID);
    }

    private void loadO5Mfile(String path){
        try {
            this.O5M_Data = new O5MReader().read(new File(path));
        }catch (Exception e) {
            System.out.println("Файл не найден: " + path);
        }
    }

    boolean isUseful(){
        System.out.println("Проверка на isUseful " + this.name);
        boolean result = false;
        for (int idNeb: this.neighbors) {
            System.out.println("Сосед " + idNeb +" = " + BaseTest.outerTestCompletedRegions[idNeb]);
            if (BaseTest.outerTestCompletedRegions[idNeb] != true) {
                System.out.println(" =>> тест не пройден");
                result = true;
                break;
            }
        }
//        if (this.ID ==13) result = false; //убрать
//        if (this.ID == 44) result = false;
        if (!result) System.out.println(this.name + " isUseful!!!!");
        return result;
    }
}
