package javase07.sync1;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class SynchronizedTransfersLoaderTest {
    @Test
    public void getTransfers() throws Exception {
        Path path = Paths.get("/Users/nikstepmac/IdeaProjects/java-fundamentals-master/unit7/sync1/src/main/resources/transfer.txt");
        TransfersLoader transfersLoader = new SynchronizedTransfersLoader(path);
        for (Transfer transfer : transfersLoader.getTransfers()) {
            System.out.println(transfer.toString());
        }
    }
}