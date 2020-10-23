package si.leanpay.taf.steps;

import io.restassured.response.Response;
import lombok.Data;
import org.springframework.stereotype.Component;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.rqbuilder.PaymentRQBuilder;
import si.leanpay.taf.utils.RestHelper;

import java.io.IOException;

@Component
@Data
public class PaymentStep {
    private RestHelper paymentHelper;
    private Response response;
    private TestData testData;
    private String serviceName;
    private PaymentRQBuilder paymentRQBuilder;

    public void init(TestData testData, String baseUrl) {
        this.serviceName = "payment-data";
        this.testData = testData;
        this.paymentHelper = new RestHelper(serviceName);
        this.paymentRQBuilder = new PaymentRQBuilder(baseUrl);
    }

    public void sendPaymentPostRequest(String uri) throws IOException {
        response = paymentHelper.sendPostRequest(uri, paymentRQBuilder.build());
    }

    public void preparePaymentRequest(){
        paymentRQBuilder
                .addRequestBody(testData.getRequestToken())
                .addHeaders();
        paymentHelper.setRequest(paymentRQBuilder.getRequestBody().toString());
    }
}
