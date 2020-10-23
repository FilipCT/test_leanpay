package si.leanpay.taf.stepdefinitions;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

/**
 * Cucumber Runner class
 *
 * @author Filip Milicevic
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber.json"},
        glue = {"si.leanpay.taf"},
        features = {"src/test/resources/features/backend"},
        monochrome = true)
public class CucumberIntegrationTest extends SpringIntegrationTest {

}
