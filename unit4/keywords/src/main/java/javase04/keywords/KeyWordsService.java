package javase04.keywords;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KeyWordsService {
    private Set<String> keyWordsSet;
    private static final String[] keyWordsArray = new String[] {
            "abstract", "continue", "for", "new", "switch",
            "assert", "default", "goto", "package", "synchronized",
            "boolean", "do", "if", "private", "this",
            "break", "double", "implements", "protected", "throw",
            "byte", "else", "import", "public", "throws",
            "case", "enum", "instanceof", "return", "transient",
            "catch", "extends", "int", "short", "try",
            "char", "final", "interface", "static", "void",
            "class", "finally", "long", "strictfp", "volatile",
            "const", "float", "native", "super", "while"
    };

    public KeyWordsService() {
        keyWordsSet = new HashSet<>(Arrays.asList(keyWordsArray));
    }

    public boolean isKeyWord(String string) {
        return keyWordsSet.contains(string);
    }
}
