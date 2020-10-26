package si.leanpay.taf.frontend.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import si.leanpay.taf.data.GeneralConstants;

import java.io.File;

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
    public static void removeDriver() {
        getInstance().getDriver().quit();
        getInstance().driver.remove();
    }

    public static void takeSnapShot(String fileName) throws Exception {
        String fileWithPath = GeneralConstants.WORK_DIR + fileName + ".png";
        TakesScreenshot scrShot = ((TakesScreenshot) getInstance().getDriver());
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
