package si.leanpay.taf.frontend.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverFactory {
    static {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
    }

    private static final DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }

    ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(ChromeDriver::new);

    public WebDriver getDriver() {
        return driver.get();
    }

    // Quits the driver and closes the browser
    public void removeDriver() {
        driver.get().quit();
        driver.remove();
    }
}
