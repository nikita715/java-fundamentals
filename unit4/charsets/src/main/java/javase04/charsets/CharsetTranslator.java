package javase04.charsets;

import java.io.*;
import java.nio.charset.Charset;

public class CharsetTranslator {
    private Charset charsetUTF8 = Charset.forName("UTF-8");
    private Charset charsetUTF16 = Charset.forName("UTF-16");
    private String inputFilePath;

    public CharsetTranslator(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public void translateTo(String outputFilePath) {
        try(BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputFilePath), charsetUTF16));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFilePath), charsetUTF8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (reader.ready()) {
                    writer.write(line);
                    writer.write("\n");
                } else {
                    writer.write(line);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
