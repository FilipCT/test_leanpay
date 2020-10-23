package si.leanpay.taf.stepdefinitions.backend;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import si.leanpay.taf.assertions.RequestPinAssertions;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.stepdefinitions.SpringIntegrationTest;
import si.leanpay.taf.steps.RequestPinStep;

import java.io.IOException;

/**
 * Request Pin Service Step Definition class
 *
 * @author Filip Milicevic
 */
public class RequestPinStepDefinition extends SpringIntegrationTest {
    @Autowired
    private TestData testData;
    @Autowired
    private RequestPinStep requestPinStep;

    @Given("user prepares request-pin request using phoneNumber: (.*)$")
    public void prepareRequestPinRequest(String phoneNumber) {
        testData.setPhoneNumber(phoneNumber);
        requestPinStep.init(testData, baseUrl);
        requestPinStep.prepareRequestPinRequest();
    }

    @When("user sends request-pin request")
    public void sendRequestPinRequest() throws IOException {
        requestPinStep.sendRequestPinRequest(requestPinPath);
    }

    @Then("valid request-pin response should be returned")
    public void verifyRequestPinResponseCode() {
        RequestPinAssertions.checkStatusCode(requestPinStep.getResponse());
    }
}
