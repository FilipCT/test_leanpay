package si.leanpay.taf.steps;

import io.restassured.response.Response;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.rqbuilder.CheckPersonDetailsRQBuilder;
import si.leanpay.taf.utils.RestHelper;

import java.io.IOException;

@Log4j2
@Component
@Data
public class CheckPersonDetailsStep {
    private TestData testData;
    private RestHelper checkPersonDetailsHelper;
    private Response response;
    private String serviceName;
    private CheckPersonDetailsRQBuilder checkPersonDetailsRQBuilder;

    public void init(TestData testData, String baseUrl) {
        this.testData = testData;
        this.serviceName = "check-personal-details";
        this.checkPersonDetailsHelper = new RestHelper(serviceName);
        checkPersonDetailsRQBuilder = new CheckPersonDetailsRQBuilder(baseUrl);
    }

    public void sendCheckoutRequest(String uri) throws IOException {
        response = checkPersonDetailsHelper.sendPostRequest(uri, checkPersonDetailsRQBuilder.build());
    }

    public void prepareCheckPersonDetailsRequest() {
        checkPersonDetailsRQBuilder
                .addHeaders(testData.getIdToken())
                .addRequestBody(testData.getRequestToken());
        checkPersonDetailsHelper.setRequest(checkPersonDetailsRQBuilder.getRequestBody().toString());
    }
}
