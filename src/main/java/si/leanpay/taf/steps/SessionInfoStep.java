package si.leanpay.taf.steps;

import io.restassured.response.Response;
import lombok.Data;
import org.springframework.stereotype.Component;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.rqbuilder.SessionInfoRQBuilder;
import si.leanpay.taf.utils.RestHelper;

import java.io.IOException;

@Component
@Data
public class SessionInfoStep {
    private TestData testData;
    private RestHelper sessionInfoHelper;
    private Response response;
    private String serviceName;
    private SessionInfoRQBuilder sessionInfoRQBuilder;

    public void init(TestData testData, String baseUrl) {
        this.testData = testData;
        this.serviceName = "session-info";
        this.sessionInfoHelper = new RestHelper(serviceName);
        this.sessionInfoRQBuilder = new SessionInfoRQBuilder(baseUrl);
    }

    public void sendSessionInfoRequest(String uri) throws IOException {
        response = sessionInfoHelper.sendGetRequest(uri, sessionInfoRQBuilder.build());
    }

    public void prepareSessionInfoRequest(){
        sessionInfoRQBuilder.addHeaders(testData.getIdToken());
    }
}
