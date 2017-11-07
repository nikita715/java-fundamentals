package javase07.sync1;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public abstract class TransfersLoader {
    private static final Pattern pattern = Pattern.compile("from:(\\d+) to:(\\d+) (\\d+)");

    TransfersLoader(Path path) throws FileNotFoundException {
        checkPath(path);
    }

    public abstract List<Transfer> getTransfers();

    void checkPath(Path path) throws FileNotFoundException {
        if (!path.toFile().exists() || path.toFile().isDirectory()) {
            throw new FileNotFoundException("File not found");
        }
    }
}
