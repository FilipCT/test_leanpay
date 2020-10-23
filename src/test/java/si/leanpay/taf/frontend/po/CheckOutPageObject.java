package si.leanpay.taf.frontend.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import si.leanpay.taf.data.FinancialData;
import si.leanpay.taf.data.PersonalData;
import si.leanpay.taf.frontend.utils.DriverFactory;

import java.util.List;

/**
 * CheckOut Page Object class
 *
 * @author Filip Milicevic
 */
@Component
public class CheckOutPageObject extends AbstractPage {
    WebDriver driver;
    @FindBy(partialLinkText = "Leanpay")
    public List<WebElement> buyWthLeanPayBtn;

    public void init() {
        this.driver = DriverFactory.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    public void clickBuyWithLeanpayButton(int itemNumber) {
        buyWthLeanPayBtn.get(itemNumber - 1).click();
    }

    public WebElement getTabTitle() {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), 5);
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//span[contains(text(), 'Kakšen obrok ti najbolj ustreza?')]")));
    }

    public void selectInstallmentPlan(String month) {
        if (month.contains("12")) {
            driver.findElement(By.xpath(".//label[@id='term-option-6']")).click();
        } else {
            driver.findElement(By.xpath(".//label[@id='term-option-5']")).click();
        }
    }

    public void selectPaymentDate(int day) {
        driver.findElement(By.xpath(".//span[contains(text(),'" + day + "')]"));
    }

    public void selectFromDropDownCountry(String country) {
        getDropDownById("countryCode").click();
        chooseOptionFromDropDown(country).click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        getInputById("phoneNumber").sendKeys(phoneNumber);
    }

    public void clickConfirmationButton() {
        DriverFactory.getInstance().getDriver()
                .findElement(By.xpath("//button[contains(text(), 'NADALJUJ')]")).click();
    }

    public void enterPinCode(String pinCode) {
        String[] codes = pinCode.split("");
        for (int i = 0; i < codes.length; i++) {
            WebElement input = DriverFactory.getInstance().getDriver()
                    .findElement(By.xpath("//input[@id='pinCode" + (i + 1) + "']"));
            input.click();
            input.sendKeys(codes[i]);
        }

        if (DriverFactory.getInstance().getDriver()
                .findElement(By.xpath("//button[contains(text(), 'NADALJUJ')]")).isDisplayed()) {
            clickConfirmationButton();
        }
    }

    public void enterConfirmPersonalSecurityPin(String pinCode) {
        String[] codes = pinCode.split("");

        for (int i = 0; i < codes.length; i++) {
            WebElement input = driver
                    .findElement(By.xpath("//lp-two-fa-pin-set[1]/div[2]/form[1]/div[1]/" +
                            "lp-two-fa-pin-input[1]/form[1]/div[1]/input[" + (i + 1) + "]"));
            input.sendKeys(codes[i]);
        }
        for (int i = 0; i < codes.length; i++) {
            WebElement input = driver
                    .findElement(By.xpath("//lp-two-fa-pin-set[1]/div[2]/form[1]/div[2]/" +
                            "lp-two-fa-pin-input[1]/form[1]/div[1]/input[" + (i + 1) + "]"));
            input.sendKeys(codes[i]);
        }
    }

    public void enterPersonalSecurityPin(String pinCode) {
        String[] codes = pinCode.split("");

        for (int i = 0; i < codes.length; i++) {
            WebElement input = driver
                    .findElement(By.xpath("//lp-checkout-contract[1]/div[1]/div[1]/div[2]/div[3]/form[1]/div[4]/" +
                            "lp-two-fa-pin-input[1]/form[1]/div[1]/input[" + (i + 1) + "]"));
            input.sendKeys(codes[i]);
        }
    }

    public void populatePersonalData(PersonalData personalData) {
        getInputById("firstName").sendKeys(personalData.getFirstName());
        getInputById("lastName").sendKeys(personalData.getLastName());
        getInputById("email").sendKeys(personalData.getEmail());

        getDropDownById("postZip").click();
        chooseOptionFromDropDown(personalData.getPostCode()).click();

        getDropDownById("street").click();
        chooseOptionFromDropDown(personalData.getStreet()).click();

        getInputById("houseNumber").sendKeys(personalData.getHouseNumber());

        getDropDownById("settlement").click();
        chooseOptionFromDropDown(personalData.getPlaceOfBirth()).click();

        getDropDownById("gender").click();
        chooseOptionFromDropDown(personalData.getGender()).click();

        getInputById("birthDate").sendKeys(personalData.getDateOfBirth());
        getInputById("placeOfBirth").sendKeys(personalData.getPlaceOfBirth());

        getDropDownById("countryOfBirthCodebook").click();
        chooseOptionFromDropDown(personalData.getCountryOfBirth()).click();

        getInputById("taxNumber").sendKeys(personalData.getTaxNumber());

        clickCheckBoxById("citizenshipOfSlovenia");
        clickCheckBoxById("termsAndPrivacy");
    }

    public void populateFinancialData(FinancialData financialData) {
        clickXpath("//lp-financial-details[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/lp-yes-no[1]/div[1]/div[2]");
        clickXpath("//lp-financial-details[1]/div[1]/div[1]/form[1]/div[3]/div[1]/div[2]/lp-yes-no[1]/div[1]/div[2]");
        clickXpath("//lp-financial-details[1]/div[1]/div[1]/form[1]/div[4]/div[1]/div[2]/lp-yes-no[1]/div[1]/div[1]");
        clickXpath("//lp-financial-details[1]/div[1]/div[1]/form[1]/div[5]/div[1]/div[2]/lp-yes-no[1]/div[1]/div[2]");

        getInputById("income").sendKeys(financialData.getMonthlyEarnings());
        getInputById("monthlyCreditExpense").sendKeys(financialData.getCreditLiabilities());

        clickXpath("//lp-financial-details[1]/div[1]/div[1]/form[1]/div[8]/div[1]/div[1]/lp-yes-no[1]/div[1]/div[2]");
        clickXpath("//lp-financial-details[1]/div[1]/div[1]/form[1]/div[9]/div[1]/div[1]/lp-yes-no[1]/div[1]/div[2]");
        clickXpath("//lp-financial-details[1]/div[1]/div[1]/form[1]/div[10]/div[1]/div[1]/lp-yes-no[1]/div[1]/div[2]");

        clickXpath("//lp-financial-details[1]/div[1]/div[1]/form[1]/div[11]/ng-select[1]/div[1]/div[1]/div[1]");
        chooseOptionFromDropDown(financialData.getTypeOfEmployment()).click();

        getXpathByText("div", "Izberi število").click();
        getXpathByText("li", "2").click();

        getInputById("bankAccountNumber").sendKeys(financialData.getTransactionNumber());
    }

    public void approveCreditAgreement(String securityPin) {
        clickCheckBoxById("CONTRACT");
        clickCheckBoxById("SEPA_DIRECT_DEBIT");
        clickXpath("//lp-checkout-contract[1]/div[1]/div[1]/div[2]/div[3]/form[1]/div[3]/div[1]/div[2]/label[1]/input[1]");
        enterPersonalSecurityPin(securityPin);
        clickXpath("//lp-checkout-contract[1]/div[1]/div[1]/div[2]/div[4]/lp-loading-button[1]/button[1]");
    }
}
