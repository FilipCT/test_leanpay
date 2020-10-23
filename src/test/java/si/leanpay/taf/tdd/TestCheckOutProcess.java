package si.leanpay.taf.tdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import si.leanpay.taf.LeanpayApplication;
import si.leanpay.taf.assertions.TokenRequestAssertions;
import si.leanpay.taf.data.VendorData;
import si.leanpay.taf.steps.TokenRequestStep;

import java.io.FileNotFoundException;

import static si.leanpay.taf.utils.DataHelper.mapDataFromCSVToObject;

/**
 * POC for TestNG and TDD
 *
 * @author Filip Milicevic
 */
@SpringBootTest(classes = LeanpayApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestCheckOutProcess extends AbstractTestNGSpringContextTests {
    @Autowired
    private TokenRequestStep tokenRequestStep;

    @DataProvider(name = "Data")
    public Object[][] provideTestData() throws FileNotFoundException {
        return mapDataFromCSVToObject(VendorData.class, "CheckOutProcess");
    }

    @Test(dataProvider = "Data")
    public void basicEndToEndCheckoutProcessFlow(Object data) throws Exception {
        VendorData vendorData = (VendorData) data;
        String baseUrl = "https://sapp.leanpay.si";
        //Sending Request Token request
        tokenRequestStep.initTDD(vendorData, baseUrl);
        tokenRequestStep.prepareTokenRequestTDD(vendorData);
        tokenRequestStep.sendTokenRequest("/vendor/token");
        TokenRequestAssertions.checkStatusCode(tokenRequestStep.getResponse());
    }
}
