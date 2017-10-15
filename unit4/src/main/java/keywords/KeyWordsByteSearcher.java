package keywords;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

public class KeyWordsByteSearcher implements KeyWordsSearcher {
    private Map<String, Integer> foundedKeyWords;

    public KeyWordsByteSearcher(String filePath, Charset charset) {
        byte[] bytes = new byte[0];

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            bytes = new byte[bis.available()];
            bytes = bis.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        foundedKeyWords = findKeyWords(new String(bytes, charset));
    }

    @Override
    public Set<String> getKeyWords() {
        return foundedKeyWords.keySet();
    }

    @Override
    public Map<String, Integer> getKeyWordsWithCount() {
        return foundedKeyWords;
    }

    @Override
    public void extractInto(String fileName) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
            for (String keyWord: foundedKeyWords.keySet()) {
                bos.write(String.format(OUTPUT_FORMAT, keyWord, foundedKeyWords.get(keyWord)).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
