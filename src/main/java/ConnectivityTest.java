import org.alex73.osmemory.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConnectivityTest {
    public static void main(String[] args) {
        MemoryStorage data = null;
        MemoryStorage data1 = null;
        List<IOsmObject> ss = new ArrayList<>();
        try {
             data = new O5MReader().read(new File("e:\\osmtmp\\RU-NIZ.o5m"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        MemoryStorage finalData = data;
        if (data != null){

            data.byTag("highway", o -> {

                //if ((o.getTag("highway", finalData).equals("primary")) & (o.getTag("name", finalData) != null) )
                if (o.getTag("highway", finalData).equals("primary"))
                    ss.add(o);
               //System.out.println(o.getTag("name", finalData));
            });
        }

        for (short i = 0;  i < 100; i++) {
            // if (osmObject.getTag((short)0x2).equals()) {
            //if (osmObject.getTag("name", finalData).equals("Кузбасская улица")) {
            if (ss.get(1).getTag(i)!=null)
            System.out.println(ss.get(1).getTag(i));
        }




    }







}
