package si.leanpay.taf.stepdefinitions.backend;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import si.leanpay.taf.assertions.SessionInfoAssertions;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.stepdefinitions.SpringIntegrationTest;
import si.leanpay.taf.steps.SessionInfoStep;

import java.io.IOException;

/**
 * Session Info Service Step Definition class
 *
 * @author Filip Milicevic
 */
public class SessionInfoStepDefinition extends SpringIntegrationTest {
    @Autowired
    private TestData testData;
    @Autowired
    private SessionInfoStep sessionInfoStep;

    @Given("user prepares session info request$")
    public void prepareSessionInfoRequest() {
        sessionInfoStep.init(testData, baseUrl);
        sessionInfoStep.prepareSessionInfoRequest();
    }

    @When("user sends session-info request")
    public void sendSessionInfoRequest() throws IOException {
        sessionInfoStep.sendSessionInfoRequest(sessionInfoPath);
    }

    @Then("valid session-info response should be returned")
    public void verifySessionInfoResponseCode() {
        SessionInfoAssertions.checkStatusCode(sessionInfoStep.getResponse());
        //SessionInfoAssertions.checkMembershipStatus(sessionInfoStep.getResponse());
        //SessionInfoAssertions.checkAccountVerificationStatus(sessionInfoStep.getResponse());
    }
}
