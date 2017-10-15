package keywords;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Arrays;

class KeyWordsByteSearcherTest {

    KeyWordsByteSearcher keyWordsByteSearcher =
            new KeyWordsByteSearcher(
                    Paths.get("src/main/java/serialization/FilmsApp.java").toFile().getAbsolutePath(),
                    Charset.defaultCharset());

    @Test
    void getKeyWords() {
        Arrays.stream(keyWordsByteSearcher.getKeyWords().toArray()).forEach(System.out::println);
    }

    @Test
    void extractInto() {
        keyWordsByteSearcher.extractInto("src/main/resources/ByteSearchKeywords.txt");
    }
}