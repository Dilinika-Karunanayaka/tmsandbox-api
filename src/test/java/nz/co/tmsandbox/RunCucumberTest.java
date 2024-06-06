package nz.co.tmsandbox;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("nz.co.tmsandbox")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty," +
                "json:target/cucumber-reports/Cucumber.json," +
                "html:target/cucumber-reports/Cucumber.html," +
                "timeline:target/cucumber-reports/CucumberTimeline")
public class RunCucumberTest {
}