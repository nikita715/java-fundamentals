package javase07.sync1;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ConcurrentTransferLoaderTest {
    @Test
    public void getTransfers() throws Exception {
        Path path = Paths.get("/Users/nikstepmac/IdeaProjects/java-fundamentals-master/unit7/sync1/src/main/resources/transfer.txt");
        TransfersLoader transfersLoader = new ConcurrentTransferLoader(path);
        for (Transfer transfer : transfersLoader.getTransfers()) {
            System.out.println(transfer.toString());
        }
    }

}