package si.leanpay.taf.frontend.po;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import si.leanpay.taf.frontend.utils.DriverFactory;

/**
 * Page Object abstract class - common PO methods
 *
 * @author Filip Milicevic
 */
public abstract class AbstractPage {

    public void navigate(final String value) {
        DriverFactory.getInstance().getDriver().navigate().to(value);
    }

    public WebElement getDropDownById(String id) {
        return DriverFactory.getInstance().getDriver().findElement(By.xpath("//ng-select[@id='" + id + "']"));
    }

    public WebElement chooseOptionFromDropDown(String option) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), 10);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//li[contains(text(),'" + option + "')]"))).isDisplayed();
        return DriverFactory.getInstance().getDriver().findElement(By.xpath("//li[contains(text(),'" + option + "')]"));
    }

    public boolean checkPageTitleByText(String text) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), 10);
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h3[contains(text(), '" + text + "')]"))).isDisplayed();
    }

    public WebElement getInputById(String id) {
        WebElement input = DriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@id='" + id + "']"));
        input.clear();
        return input;
    }

    public void clickCheckBoxById(String id) {
        getInputById(id).click();
    }

    public WebElement getXpathByText(String tag, String text) {
        return DriverFactory.getInstance().getDriver().findElement(By.xpath("//" + tag + "[contains(text(),'" + text + "')]"));
    }

    public void clickXpath(final String value) {
        DriverFactory.getInstance().getDriver()
                .findElement(By.xpath(value)).click();
    }

    public WebElement returnElementByXpath(final String value) {
        return DriverFactory.getInstance().getDriver()
                .findElement(By.xpath(value));
    }

    public void clickCss(final String value) {
        DriverFactory.getInstance().getDriver()
                .findElement(By.cssSelector("//*[contains(text(), '" + value + "')]")).click();
    }

    protected void clickIdJs(final String id) {
        final WebElement button = DriverFactory.getInstance().getDriver().findElement(By.id(id));
        final JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance()
                .getDriver();
        executor.executeScript("arguments[0].click();", button);
    }

    protected void clickXpathJs(final String value) {
        final WebElement button = DriverFactory.getInstance().getDriver()
                .findElement(By.xpath("//a[contains(text(), '" + value + "')]"));
        final JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance()
                .getDriver();
        executor.executeScript("arguments[0].click();", button);
    }

    public void quitDriver() {
        DriverFactory.getInstance().getDriver().quit();
    }

}
