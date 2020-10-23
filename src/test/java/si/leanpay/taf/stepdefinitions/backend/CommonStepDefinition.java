package si.leanpay.taf.stepdefinitions.backend;

import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.stepdefinitions.SpringIntegrationTest;

/**
 * Common Step Definition class
 *
 * @author Filip Milicevic
 */
public class CommonStepDefinition extends SpringIntegrationTest {
    @Autowired
    private TestData testData;

    @Given("Access token used for checkout process: (.*)$")
    public void setAccessToken(String accessToken){
        testData.setAccessToken(accessToken);
    }
}
