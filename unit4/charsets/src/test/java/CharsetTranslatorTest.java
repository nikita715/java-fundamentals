import javase04.charsets.CharsetTranslator;
import org.junit.jupiter.api.Test;

class CharsetTranslatorTest {

    CharsetTranslator charsetTranslator = new CharsetTranslator("src/main/resources/UTF8File.txt");

    @Test
    void translateTo() {
        charsetTranslator.translateTo("src/main/resources/UTF16File.txt");
    }

}