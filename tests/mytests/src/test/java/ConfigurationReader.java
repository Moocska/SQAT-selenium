import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.Properties;


//Using configuration file - 6 point
public class ConfigurationReader {
    Properties properties;

    public Properties loadConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("Config.properties")) {
            properties = new Properties();
            if (input == null) {
                throw new FileNotFoundException("Config.properties file not found in classpath");
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}
