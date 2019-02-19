import org.alex73.osmemory.*;
import java.io.File;

public class ConnectivityTest {
    MemoryStorage data;

    {
        try {
            data = new O5MReader().read(new File("tmp/belarus-updated.o5m"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
