package si.leanpay.taf.stepdefinitions.frontend;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import si.leanpay.taf.data.FinancialData;
import si.leanpay.taf.data.PersonalData;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.frontend.po.CheckOutPageObject;
import si.leanpay.taf.stepdefinitions.SpringIntegrationTest;
import si.leanpay.taf.utils.DataHelper;

import java.util.List;

/**
 * Checkout Page Step Definition class
 *
 * @author Filip Milicevic
 */
public class CheckOutPageStepDefinition extends SpringIntegrationTest {
    @Autowired
    public TestData testData;
    @Autowired
    public PersonalData personalData;
    @Autowired
    public FinancialData financialData;
    @Autowired
    public CheckOutPageObject checkOutPageObject;

    @Given("user access checkout page")
    public void accessCheckoutPage() {
        testData.setWebDriverStarted(true);
        checkOutPageObject.init();
        checkOutPageObject.navigate(basePage);
    }

    @Given("user chooses item {int} from the list to buy")
    public void chooseItemToBuy(int itemNumber) {
        testData.setItemNumber(itemNumber);
    }

    @When("user clicks BuyWithLeanPay button")
    public void clickBuyWithLeanPay() {
        checkOutPageObject.clickBuyWithLeanpayButton(testData.getItemNumber());
    }

    @Then("valid checkout page is opened")
    public void checkIfCheckoutPageIsOpened() {
        Assert.assertEquals("Kakšen obrok ti najbolj ustreza?", checkOutPageObject.getTabTitle().getText());
    }

    @And("user chooses installment option: (.*), (.*)$")
    public void chooseInstallmentOption(String installmentPlan, String paymentDate) {
        testData.setInstallmentPlan(installmentPlan);
        testData.setPaymentDate(paymentDate);
        checkOutPageObject.selectInstallmentPlan(testData.getInstallmentPlan());
        checkOutPageObject.selectPaymentDate(Integer.parseInt(testData.getPaymentDate().split("\\.")[0]));
    }

    @When("user clicks on confirm button$")
    public void clickConfirmButton() {
        checkOutPageObject.clickConfirmationButton();
    }

    @Then("valid page for providing phone number is opened")
    public void checkIfPhonePageIsOpened() {
        Assert.assertTrue(checkOutPageObject.checkPageTitleByText("Številka mobilnega telefona"));
    }

    @And("user selects (.*) country code$")
    public void clickConfirmButton(String countryCode) {
        checkOutPageObject.selectFromDropDownCountry(countryCode);
    }

    @And("user enters phone number: (.*) and clicks confirmation button$")
    public void enterPhoneNumber(String phoneNumber) {
        Faker faker = new Faker();
        checkOutPageObject.enterPhoneNumber(phoneNumber + faker.number().digits(11));
        checkOutPageObject.clickConfirmationButton();
    }

    @And("user enters pin: (.*) and clicks confirmation button$")
    public void enterPinCode(String pinCode) {
        checkOutPageObject.enterPinCode(pinCode);
    }

    @And("valid page for providing pin code is opened")
    public void checkIfPinCodePageIsOpened() {
        Assert.assertTrue(checkOutPageObject.checkPageTitleByText("Verifikacijska koda"));
    }

    @And("valid page for providing personal data is opened")
    public void checkIfPersonalDataPageIsOpened() {
        Assert.assertTrue(checkOutPageObject.checkPageTitleByText("Osebni podatki"));
    }

    @And("user enters personal data and clicks on confirmation button")
    public void enterPersonalData(DataTable dataTable) {
        List<String> personDataList = dataTable.row(1);
        DataHelper.savePersonalData(personDataList, personalData);
        checkOutPageObject.populatePersonalData(personalData);
        checkOutPageObject.clickConfirmationButton();
    }

    @And("valid page for providing personal security code is opened")
    public void checkIfSecurityCodePageIsOpened() {
        Assert.assertTrue(checkOutPageObject.checkPageTitleByText("Določi svojo osebno varnostno kodo"));
    }

    @And("user enters personal security pin: (.*) and clicks confirmation button$")
    public void enterSecurityPinCode(String pinCode) throws InterruptedException {
        testData.setSecurityPin(pinCode);
        checkOutPageObject.enterConfirmPersonalSecurityPin(pinCode);
        checkOutPageObject.clickConfirmationButton();
    }

    @And("valid page for providing financial data is opened")
    public void checkIfFinancialDataPageIsOpened() {
        Assert.assertTrue(checkOutPageObject.checkPageTitleByText("Finančni podatki"));
    }

    @And("user enters financial data$")
    public void enterFinancialData(DataTable dataTable) {
        List<String> financialDataList = dataTable.row(1);
        DataHelper.saveFinancialData(financialDataList, financialData);
        checkOutPageObject.populateFinancialData(financialData);
        checkOutPageObject.clickConfirmationButton();
    }

    @And("valid page for reviewing financial data is opened")
    public void checkIfReviewFinancePageIsOpened() {
        Assert.assertTrue(checkOutPageObject.checkPageTitleByText("Pregled finančnih podatkov"));
    }

    @And("user reviews financial data and click on confirmation button")
    public void reviewFinanceDataAndContinue() {
        checkOutPageObject.confirmFinancialData();
    }

    @And("check if the correct credit amount is approved")
    public void checkCreditAmount() {
        Assert.assertTrue(checkOutPageObject.checkPageTitleByText("150"));
        checkOutPageObject.confirmCreditAmountData();
    }

    @And("check if the credit agreement page is opened")
    public void checkCreditAgreementPageIsOpened() {
        Assert.assertTrue(checkOutPageObject.checkPageTitleByText("Kreditna pogodba"));
    }

    @And("page for announcing successful credit agreement is opened")
    public void checkSuccessCreditAgreementPageIsOpened() {
        Assert.assertTrue(checkOutPageObject.checkPageTitleByText("Čestitke"));
    }

    @And("user confirms credit agreement")
    public void confirmCreditAgreement() throws InterruptedException {
        checkOutPageObject.approveCreditAgreement(testData.getSecurityPin());
    }

}
