package javase06.propsmap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class PropertiesReader {
    private Properties properties = new Properties();
    private PrintStream printStream = new PrintStream(System.out);

    public PropertiesReader(String path) {
        try (FileInputStream input = new FileInputStream(path)) {
            properties.load(input);
        } catch (IOException e) {
            printStream.println("Unable to read this file");
        }
    }

    public String getProperty(String key) {
        try {
            if (properties.containsKey(key)) {
                return properties.getProperty(key);
            } else {
                throw new PropertyNotFoundException();
            }
        } catch (PropertyNotFoundException e) {
            printStream.println(e.getMessage());
        }
        return "";
    }
}
