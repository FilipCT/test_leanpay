package si.leanpay.taf.stepdefinitions.backend;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import si.leanpay.taf.assertions.CheckPersonDetailsAssertions;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.stepdefinitions.SpringIntegrationTest;
import si.leanpay.taf.steps.CheckPersonDetailsStep;

import java.io.IOException;

/**
 * Check Person Details Service Step Definition class
 *
 * @author Filip Milicevic
 */
public class CheckPersonDetailsStepDefinition extends SpringIntegrationTest {
    @Autowired
    private TestData testData;
    @Autowired
    private CheckPersonDetailsStep checkPersonDetailsStep;

    @Given("user prepares check personal details request")
    public void prepareCheckPersonDetailsRequest() {
        checkPersonDetailsStep.init(testData, baseUrl);
        checkPersonDetailsStep.prepareCheckPersonDetailsRequest();
    }

    @When("user sends check personal details request")
    public void sendCheckPersonDetailsRequest() throws IOException {
        checkPersonDetailsStep.sendCheckoutRequest(checkPersonDetailsPath);
    }

    @Then("valid check personal details response should be returned")
    public void verifyCheckPersonDetailsResponseCode() {
        CheckPersonDetailsAssertions.checkStatusCode(checkPersonDetailsStep.getResponse());
    }

}
