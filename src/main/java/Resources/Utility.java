package Resources;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Properties;

public class Utility {
    public static Properties getPropertyFile() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Resources/data.properties");
        prop.load(fis);
        return prop;
    }

    public static Properties setPropertyFile(Properties prop) throws IOException {
        FileOutputStream fs = new FileOutputStream(System.getProperty("user.dir")+"/src/main/java/Resources/data.properties");
        prop.store(fs,"Updated Successfully");
        return prop;
    }

    public static JSONObject getJsonObject(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+path));
        JSONObject jobj = (JSONObject) obj;
        return jobj;
    }
}
