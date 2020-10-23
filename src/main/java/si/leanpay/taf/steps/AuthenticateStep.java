package si.leanpay.taf.steps;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.springframework.stereotype.Component;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.rqbuilder.AuthenticateRQBuilder;
import si.leanpay.taf.utils.RestHelper;

import java.io.IOException;

@Component
@Data
public class AuthenticateStep {
    private TestData testData;
    private RestHelper authenticateHelper;
    private Response response;
    private RequestSpecification request;
    private AuthenticateRQBuilder authenticateRQBuilder;

    public void init(TestData testData, String baseUrl) {
        this.testData = testData;
        this.authenticateHelper = new RestHelper("authenticate");
        this.authenticateRQBuilder = new AuthenticateRQBuilder(baseUrl);
    }

    public void sendAuthenticateRequest(String uri) throws IOException {
        response = authenticateHelper.sendPostRequest(uri, authenticateRQBuilder.build());
    }

    public void prepareAuthenticateRequest() {
        authenticateRQBuilder.addRequestBody(testData.getRequestToken(), testData.getUserName(), testData.getPassword())
                .addHeaders();
        authenticateHelper.setRequest(authenticateRQBuilder.getRequestBody().toString());
    }
}
