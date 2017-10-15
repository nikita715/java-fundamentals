package keywords;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface KeyWordsSearcher {

    String OUTPUT_FORMAT = "%s - %d\n";

    Set<String> getKeyWords();

    Map<String, Integer> getKeyWordsWithCount();

    void extractInto(String fileName);

    default Map<String, Integer> findKeyWords(String text) {
        KeyWordsService keyWordsService = new KeyWordsService();
        Map<String, Integer> foundedKeyWords = new HashMap<>();

        Matcher matcher = Pattern.compile("\\w+").matcher(text);

        while (matcher.find()) {
            String foundedWord = matcher.group();

            if (keyWordsService.isKeyWord(foundedWord)) {
                if (foundedKeyWords.containsKey(foundedWord)) {
                    foundedKeyWords.put(foundedWord, foundedKeyWords.get(foundedWord) + 1);
                } else {
                    foundedKeyWords.put(foundedWord, 1);
                }
            }
        }

        return foundedKeyWords;
    }
}
