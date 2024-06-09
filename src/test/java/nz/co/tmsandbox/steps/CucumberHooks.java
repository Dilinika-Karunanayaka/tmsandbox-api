package nz.co.tmsandbox.steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import lombok.extern.log4j.Log4j2;
import nz.co.tmsandbox.util.ScenarioContext;

@Log4j2
public class CucumberHooks {

    private final ScenarioContext scenarioContext;

    public CucumberHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @BeforeAll
    public static void beforeAll() {
        log.info("CucumberHooks.BeforeAll");
    }

    @Before()
    public void before() {
        log.info("CucumberHooks.Before");
    }

    @After()
    public void after() {
        log.info("CucumberHooks.After");
    }

    @AfterAll
    public static void afterAll() {
        log.info("CucumberHooks.AfterAll");
    }
}