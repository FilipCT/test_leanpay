package si.leanpay.taf.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import si.leanpay.taf.steps.*;

public class CheckOutStepDefinition {
    @Autowired
    private CheckOutStep checkOutStep;

    @Given("as user I want to access checkout page")
    public void prepareCheckOutRequest() {
    }

    @When("user sends checkout request")
    public void sendCheckOutRequest() {
        checkOutStep.init();
        checkOutStep.sendPostRequest("vendor/checkout");
    }

    @Then("valid response should be returned")
    public void valid_response_should_be_returned() {
       //CheckOutAssertion.checkStatusCode(checkOutStep.getResponse());
    }
}
