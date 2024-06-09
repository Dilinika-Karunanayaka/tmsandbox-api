package nz.co.tmsandbox.util;

import io.restassured.RestAssured;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class APIManager {
    public static void setBaseURI() {
        RestAssured.baseURI = ConfigManager.getConfig("baseURI");
        setProxy();
    }

    private static void setProxy() {
        if (ConfigManager.getConfig("proxy.enabled").equals("true")) {
            String proxyHost = ConfigManager.getConfig("proxy.host");
            int proxyPort = Integer.parseInt(ConfigManager.getConfig("proxy.port"));
            String proxyScheme = ConfigManager.getConfig("proxy.scheme");
            log.info("Setting proxy: " + proxyScheme + " " + proxyHost + " " + proxyPort);
            RestAssured.proxy(proxyHost, proxyPort, proxyScheme);
        }
    }
}