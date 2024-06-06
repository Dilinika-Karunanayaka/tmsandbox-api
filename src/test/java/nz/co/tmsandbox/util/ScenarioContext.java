package nz.co.tmsandbox.util;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class ScenarioContext {

    private Map<String, String> map = new HashMap<>();

    public ScenarioContext() {
        log.info("ScenarioContext()");
    }

    public void put(String key, String value) {
        log.info("put(" + key + ", " + value + ")");
        map.put(key, value);
    }

    public String get(String key) {
        String value = map.get(key);
        log.info("get(" + key + ") => " + value);
        return value;
    }
}