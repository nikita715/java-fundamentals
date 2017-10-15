package keywords;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KeyWordsSymbolSearcherTest {

    KeyWordsSymbolSearcher keyWordsSymbolSearcher =
            new KeyWordsSymbolSearcher(
                    Paths.get("src/main/java/serialization/FilmsApp.java").toFile().getAbsolutePath(),
                    Charset.defaultCharset());

    @Test
    void getKeyWords() {
        Arrays.stream(keyWordsSymbolSearcher.getKeyWords().toArray()).forEach(System.out::println);
    }

    @Test
    void extractInto() {
        keyWordsSymbolSearcher.extractInto("src/main/resources/SymbolSearchKeywords.txt");
    }
}