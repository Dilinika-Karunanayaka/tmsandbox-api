package nz.co.tmsandbox.util;

import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class APIManager {
    public static void setBaseURI() {
        RestAssured.baseURI = ConfigManager.getConfig("baseURI");

        String consumerKey = "68943E510F0C0256D0A7A3A86375F8EE";
        String consumerSecret = "18D97F33A1C6D4A55992CEF7012649E3";
        String accessToken = "8C8044A5ACCBE1BE34C97F7E5B133765";
        String accessTokenSecret = "CEE90AFA62C0A5004B85E1A79FCA2544";
        RestAssured.authentication = RestAssured.oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret, OAuthSignature.HEADER);

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