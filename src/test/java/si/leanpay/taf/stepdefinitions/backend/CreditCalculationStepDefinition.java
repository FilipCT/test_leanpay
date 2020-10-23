package si.leanpay.taf.stepdefinitions.backend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import si.leanpay.taf.assertions.CreditCalcAssertions;
import si.leanpay.taf.data.VendorData;
import si.leanpay.taf.stepdefinitions.SpringIntegrationTest;
import si.leanpay.taf.steps.CreditCalculationStep;

import java.io.IOException;

/**
 * Credit Calculation Service Step Definition class
 *
 * @author Filip Milicevic
 */
public class CreditCalculationStepDefinition extends SpringIntegrationTest {
    @Autowired
    private VendorData vendorData;
    @Autowired
    private CreditCalculationStep creditCalcStep;

    @And("user prepares credit calculation request with data: {string},{double},{int}")
    public void prepareCreditCalcRequest(String vendorApiKey, double amount, int term) {
        vendorData.setVendorApiKey(vendorApiKey);
        vendorData.setAmount(amount);
        vendorData.setTerm(term);
        creditCalcStep.init(vendorData, baseUrl);
        creditCalcStep.prepareCreditCalcRequest();
    }

    @When("user sends credit calculation request")
    public void sendCreditCalcRequest() throws IOException {
        creditCalcStep.sendCreditCalcPostRequest(creditCalcPath);
    }

    @Then("valid credit calculation response should be returned")
    public void checkPaymentResponse() {
        CreditCalcAssertions.checkStatusCode(creditCalcStep.getResponse());
        CreditCalcAssertions.checkAmount(creditCalcStep.getResponse(), vendorData.getAmount());
        CreditCalcAssertions.checkNumberOfInstallments(creditCalcStep.getResponse(), vendorData.getTerm());
    }

    @Then("valid error code {int} and error message {string} should be returned")
    public void checkErrorCodeMessage(int errorCode, String errorMessage) {
        CreditCalcAssertions.checkErrorCode(creditCalcStep.getResponse(), errorCode, errorMessage);
    }
}
