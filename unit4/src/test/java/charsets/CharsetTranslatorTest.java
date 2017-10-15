package charsets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharsetTranslatorTest {

    CharsetTranslator charsetTranslator = new CharsetTranslator("src/main/resources/UTF8File.txt");

    @Test
    void translateTo() {
        charsetTranslator.translateTo("src/main/resources/UTF16File.txt");
    }

}