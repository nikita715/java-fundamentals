package javase06.propsmap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class PropertiesReader {
    private Map<String, String> properties;
    private PrintStream printStream = new PrintStream(System.out);

    public PropertiesReader(String path) {
        setFile(path);
    }

    public void setFile(String path) {
        try (FileInputStream input = new FileInputStream(path)) {
            Properties p = new Properties();
            p.load(input);
            properties = new HashMap<>();
            p.forEach((key, value) -> properties.put((String) key, (String) value));
        } catch (IOException e) {
            printStream.println("Unable to read this file");
        }
    }

    public String getProperty(String key) {
        try {
            if (properties == null) {
                throw new FileNotFoundException();
            }
            if (properties.containsKey(key)) {
                return properties.get(key);
            } else {
                throw new PropertyNotFoundException();
            }
        } catch (PropertyNotFoundException e) {
            printStream.println(e.getMessage());
        } catch (FileNotFoundException e) {
            printStream.println("File not defined");
        }
        return "";
    }
}
