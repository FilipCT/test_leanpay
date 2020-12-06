package si.leanpay.taf.stepdefinitions.backend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import si.leanpay.taf.assertions.TokenRequestAssertions;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.extractor.RequestTokenExtractor;
import si.leanpay.taf.stepdefinitions.SpringIntegrationTest;
import si.leanpay.taf.steps.TokenRequestStep;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Token Request Service Step Definition class
 *
 * @author Filip Milicevic
 */
public class TokenRequestStepDefinition extends SpringIntegrationTest {
    @Autowired
    private TestData testData;
    @Autowired
    private TokenRequestStep tokenRequestStep;

    @And("user prepares request token request")
    public void prepareCheckOutPaymentRequest() throws IOException, ParseException {
        tokenRequestStep.init(testData, baseUrl);
        //tokenRequestStep.prepareTokenRequest();

        tokenRequestStep.prepareTokenRequestDTO("1", BigDecimal.valueOf(300),
            "4", "product01");
    }

    @When("user sends request token request")
    public void sendTokenRequest() throws IOException {
        tokenRequestStep.sendTokenRequest(requestTokenPath);
    }

    @Then("valid request token response should be returned")
    public void checkRequestTokenResponse() {
        TokenRequestAssertions.checkStatusCode(tokenRequestStep.getResponse());
    }

    @And("extract request token from response")
    public void extractRequestToken() {
        testData.setRequestToken(RequestTokenExtractor.extractRequestToken(tokenRequestStep.getResponseToken()));
    }
}
