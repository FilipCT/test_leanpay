package si.leanpay.taf.steps;

import io.restassured.response.Response;
import lombok.Data;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import si.leanpay.taf.data.GeneralConstants;
import si.leanpay.taf.data.TestData;
import si.leanpay.taf.data.VendorData;
import si.leanpay.taf.rqbuilder.TokenRequestRQBuilder;
import si.leanpay.taf.utils.RestHelper;

import java.io.IOException;

@Component
@Data
public class TokenRequestStep {
    private RestHelper tokenRequestHelper;
    private Response response;
    private TestData testData;
    private VendorData vendorData;
    private String serviceName;
    private TokenRequestRQBuilder tokenRequestRQBuilder;

    public void init(TestData testData, String baseUrl) {
        this.serviceName = "request-token";
        this.testData = testData;
        this.tokenRequestHelper = new RestHelper(serviceName);
        this.tokenRequestRQBuilder = new TokenRequestRQBuilder(baseUrl);
    }

    public void initTDD(VendorData vendorData, String baseUrl) {
        this.serviceName = "request-token";
        this.vendorData = vendorData;
        this.tokenRequestHelper = new RestHelper(serviceName);
        this.tokenRequestRQBuilder = new TokenRequestRQBuilder(baseUrl);
    }

    public void sendTokenRequest(String uri) throws IOException {
        response = tokenRequestHelper.sendPostRequest(uri, tokenRequestRQBuilder.build());
    }

    public void prepareTokenRequest() throws IOException, ParseException {
        String jsonTemplateLocation = GeneralConstants.JSON_TEMPLATES_DIR + "request-token-rq.json";
        tokenRequestRQBuilder
                .addRequestBody(jsonTemplateLocation)
                .addHeaders();
        tokenRequestHelper.setRequest(tokenRequestRQBuilder.getRequestBody());
    }

    public void prepareTokenRequestTDD(VendorData vendorData) throws IOException, ParseException {
        String jsonTemplateLocation = GeneralConstants.JSON_TEMPLATES_DIR + "request-token-rq.json";
        tokenRequestRQBuilder
                .addRequestBodyTDD(vendorData, jsonTemplateLocation)
                .addHeaders();
        tokenRequestHelper.setRequest(tokenRequestRQBuilder.getRequestBody());
    }
}