package javase.unit7.sync2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParallelPropertiesReader {

    private static Pattern propertyKeyValuePattern = Pattern.compile("([^=]+)=([^=]+)");
    private static final PropertiesContainer container = new PropertiesContainer();

    private Properties properties;

    public static ParallelPropertiesReader of(String propertiesPath) {
        Path path = Paths.get(propertiesPath);
        ParallelPropertiesReader parallelPropertiesReader = new ParallelPropertiesReader();
        parallelPropertiesReader.properties = tryToLoadProperties(path);
        return parallelPropertiesReader;
    }

    public String get(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    private static Properties tryToLoadProperties(Path path) {
        Properties properties;
        boolean isLoaded = false;
        boolean isLoading = false;

        synchronized (container) {
            if (container.isLoading(path)) {
                properties = container.getLoadingProperties(path);
                isLoading = true;
            } else if (container.isLoaded(path)) {
                properties = container.getProperties(path);
                isLoaded = true;
            } else {
                properties = new Properties();
                container.addLoading(path, properties);
            }
        }

        if (isLoading) {
            waitForLoading(path, properties);
        } else if (!isLoaded) {
            loadProperties(path, properties);
        }

        return properties;
    }

    private static void waitForLoading(Path path, Properties properties) {
        boolean isLoaded = false;
        while (!isLoaded) {
            try {
                synchronized (properties) {
                    properties.wait(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (container) {
                isLoaded = container.isLoaded(path);
            }
        }
    }

    private static void loadProperties(Path path, Properties properties) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = propertyKeyValuePattern.matcher(line);

                if (matcher.matches()) {
                    String key = matcher.group(1).trim();
                    String value = matcher.group(2).trim();
                    if (!key.isEmpty() && !value.isEmpty()) {
                        properties.put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        synchronized (container) {
            container.setLoaded(path, properties);
        }
    }

    private static class PropertiesContainer {
        private Map<Path, Properties> loadedProperties = new HashMap<>();
        private Map<Path, Properties> loadingProperties = new HashMap<>();

        public boolean isLoaded(Path path) {
            return loadedProperties.containsKey(path);
        }

        public boolean isLoading(Path path) {
            return loadingProperties.containsKey(path);
        }

        public Properties getProperties(Path path) {
            return loadedProperties.get(path);
        }

        public Properties getLoadingProperties(Path path) {
            return loadingProperties.get(path);
        }

        public void setLoaded(Path path, Properties properties) {
            if (isLoading(path)) {
                loadingProperties.remove(path);
                loadedProperties.put(path, properties);

                synchronized (properties) {
                    properties.notifyAll();
                }
            }
        }

        public void addLoading(Path path, Properties properties) {
            loadingProperties.put(path, properties);
        }

        public static void main(String[] args) {
            String path = "/Users/nikstepmac/IdeaProjects/Новая папка/java-fundamentals-master/unit5/src/main/resources/pr.properties";
            ParallelPropertiesReader reader = ParallelPropertiesReader.of(path);
            System.out.println(reader.get("qwe"));
        }
    }
}
