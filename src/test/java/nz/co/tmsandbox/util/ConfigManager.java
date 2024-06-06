package nz.co.tmsandbox.util;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ConfigManager {

    private static final Properties config = new Properties();

    static {
        log.info("ConfigManager.static");
        try {
            config.load(ConfigManager.class.getClassLoader().getResourceAsStream("config.properties"));
            log.info("env=" + config.getProperty("env"));
        } catch (IOException e) {
            log.error(e);
        }
    }

    public static String getConfig(String key) {
        String value = config.getProperty(key);
        log.info("ConfigManager.getConfig(" + key + ") => " + value);
        return value;
    }
}