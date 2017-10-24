package props;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PropertiesReaderTest {

    @Test
    public void createClassTest() {
        String path = "/Users/nikstepmac/IdeaProjects/Новая папка/java-fundamentals-master/unit5/src/main/resources/pr.properties";
        PropertiesReader propertiesReader = new PropertiesReader(path);
    }

    @Test
    public void getPropertyTest() {
        String path = "/Users/nikstepmac/IdeaProjects/Новая папка/java-fundamentals-master/unit5/src/main/resources/pr.properties";
        PropertiesReader propertiesReader = new PropertiesReader(path);
        assertThat(propertiesReader.getProperty("qwe"), is("dsf"));
    }
}