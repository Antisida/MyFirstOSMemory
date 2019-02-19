import org.alex73.osmemory.*;
import java.io.File;

public class ConnectivityTest {
    public static void main(String[] args) {
        try {
            MemoryStorage data = new O5MReader().read(new File("e:\\osmtmp\\RU-NIZ.o5m"));
        } catch (Exception e) {
            e.printStackTrace();
        }





    }







}
