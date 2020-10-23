package si.leanpay.taf.steps;

import io.restassured.response.Response;
import lombok.Data;
import org.springframework.stereotype.Component;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.rqbuilder.RequestPinRQBuilder;
import si.leanpay.taf.utils.RestHelper;

import java.io.IOException;

@Component
@Data
public class RequestPinStep {
    private TestData testData;
    private RestHelper requestPinHelper;
    private Response response;
    private String serviceName;
    private RequestPinRQBuilder requestPinRQBuilder;

    public void init(TestData testData, String baseUrl) {
        this.serviceName = "request-pin";
        this.requestPinHelper = new RestHelper(serviceName);
        this.testData = testData;
        this.requestPinRQBuilder = new RequestPinRQBuilder(baseUrl);
    }

    public void sendRequestPinRequest(String uri) throws IOException {
        response = requestPinHelper.sendPostRequest(uri, requestPinRQBuilder.build());
    }

    public void prepareRequestPinRequest(){
        requestPinRQBuilder
                .addRequestBody(testData.getRequestToken(), testData.getPhoneNumber())
                .addHeaders();
        requestPinHelper.setRequest(requestPinRQBuilder.getRequestBody().toString());
    }
}
