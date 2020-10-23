package si.leanpay.taf.stepdefinitions.backend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import si.leanpay.taf.assertions.AuthenticateAssertions;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.extractor.AuthenticateExtractor;
import si.leanpay.taf.stepdefinitions.SpringIntegrationTest;
import si.leanpay.taf.steps.AuthenticateStep;

import java.io.IOException;

/**
 * Authenticate Service Step Definition class
 *
 * @author Filip Milicevic
 */
public class AuthenticateStepDefinition extends SpringIntegrationTest {
    @Autowired
    private TestData testData;
    @Autowired
    private AuthenticateStep authenticateStep;

    @Given("user prepares authenticate request using data (.*),(.*)$")
    public void prepareRequestPinRequest(String userName, String password) {
        testData.setUserName(userName);
        testData.setPassword(password);
        authenticateStep.init(testData, baseUrl);
        authenticateStep.prepareAuthenticateRequest();
    }

    @When("user sends authenticate request")
    public void sendAuthenticateRequest() throws IOException {
        authenticateStep.sendAuthenticateRequest(authenticatePath);
    }

    @Then("valid authenticate response should be returned")
    public void verifyAuthenticateResponseCode() {
        AuthenticateAssertions.checkStatusCode(authenticateStep.getResponse());
    }

    @And("extract idToken from authenticate response")
    public void extractIdToken() {
        testData.setIdToken(AuthenticateExtractor.extractIdToken(authenticateStep.getResponse()));
    }
}
