package si.leanpay.taf.stepdefinitions.backend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import si.leanpay.taf.assertions.PaymentAssertions;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.stepdefinitions.SpringIntegrationTest;
import si.leanpay.taf.steps.PaymentStep;

import java.io.IOException;

/**
 * Payment Service Step Definition class
 *
 * @author Filip Milicevic
 */
public class PaymentStepDefinition extends SpringIntegrationTest {
    @Autowired
    private TestData testData;
    @Autowired
    private PaymentStep paymentStep;

    @And("user prepares checkout payment request")
    public void prepareCheckOutPaymentRequest() {
        paymentStep.init(testData, baseUrl);
        paymentStep.preparePaymentRequest();
    }

    @When("user send checkout payment request")
    public void sendCheckOutPaymentRequest() throws IOException {
        paymentStep.sendPaymentPostRequest(checkoutPaymentPath);
    }

    @Then("valid payment response should be returned")
    public void checkPaymentResponse() {
        PaymentAssertions.checkStatusCode(paymentStep.getResponse());
    }
}
