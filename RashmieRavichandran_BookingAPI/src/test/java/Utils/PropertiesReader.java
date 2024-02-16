package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class PropertiesReader {

    private Properties prop = new Properties();
    private InputStream inputStream = null;

    public PropertiesReader() {
        try {
            String propertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/url.properties";
            inputStream = new FileInputStream(propertiesFilePath);
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEndPointUrl(String endpoint) {
        return prop.getProperty(endpoint);
    }
    public String getValues(String value) {
        return prop.getProperty(value);
    }

}