package si.leanpay.taf.rqbuilder;

import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

@Data
public class CreditCalculationRQBuilder {
    private RequestSpecification request;
    private JSONObject jsonRequest;

    public CreditCalculationRQBuilder(String baseUri) {
        request = given().baseUri(baseUri);
        jsonRequest = new JSONObject();
    }

    public CreditCalculationRQBuilder withVendorApiKey(String vendorApiKey) {
        jsonRequest.put("vendorApiKey", vendorApiKey);
        return this;
    }

    public CreditCalculationRQBuilder withAmount(double amount) {
        jsonRequest.put("amount", amount);
        return this;
    }

    public CreditCalculationRQBuilder withTerm(int term) {
        jsonRequest.put("term", term);
        return this;
    }

    public CreditCalculationRQBuilder withHeaders() {
        request.contentType("application/json")
                .accept("application/json");
        return this;
    }

    public RequestSpecification build() {
        request.body(jsonRequest.toString());
        return request;
    }
}
