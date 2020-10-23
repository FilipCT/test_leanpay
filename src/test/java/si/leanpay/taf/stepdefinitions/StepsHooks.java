package si.leanpay.taf.stepdefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import si.leanpay.taf.data.GeneralConstants;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.frontend.utils.DriverFactory;
import si.leanpay.taf.utils.Info;

import java.io.File;
import java.io.IOException;

/**
 * Cucumber Step Hooks class
 *
 * @author Filip Milicevic
 */
public class StepsHooks extends SpringIntegrationTest {
    @Autowired
    private TestData testData;
    private static boolean isScenarioDefined = false;


    @Before
    public void beforeScenario(Scenario scenario) throws IOException {
        FileUtils.deleteDirectory(new File(GeneralConstants.WORK_DIR));
        FileUtils.forceMkdir(new File(GeneralConstants.WORK_DIR));

        String currentFeatureName;
        try {
            currentFeatureName = scenario.getId().split("---")[0].toUpperCase();
        } catch (NullPointerException e) {
            currentFeatureName = scenario.getId();
        }

        if (!currentFeatureName.equals(Info.getFeatureName())) {
            Info.setScenarioName("");
        }
        if (Info.getScenarioName().equals(scenario.getName())) {
            Info.incExampleNumber();
        } else {
            if (!isScenarioDefined) {
                Info.incScenarioNumber();
            }
            Info.resetExampleNumber();
        }
        Info.setFeatureName(currentFeatureName);
        Info.setScenarioName(scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        String scenarioName = Info.getScenarioName();
        String scenarioNumberFromName;
        String folderNamePattern;
        String currentMessageReportDirPath;

        try {
            scenarioNumberFromName = scenarioName.trim().replaceAll(":", "");
        } catch (NullPointerException npe) {
            scenarioNumberFromName = scenarioName;
        }

        folderNamePattern = "./messageStorage/Scenario_%s/example_%s";
        currentMessageReportDirPath = String.format(folderNamePattern, scenarioNumberFromName,
                Info.getExampleNumber());

        FileUtils.copyDirectory(new File(GeneralConstants.WORK_DIR), new File(currentMessageReportDirPath));

        if(testData.isWebDriverStarted()){
            DriverFactory.getInstance().getDriver().quit();
        }

    }
}
