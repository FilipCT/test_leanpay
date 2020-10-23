package si.leanpay.taf.rqbuilder;

import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

@Data
public class RequestPinRQBuilder {
    private RequestSpecification request;
    private JSONObject requestBody;

    public RequestPinRQBuilder(String baseUri) {
        request = given().baseUri(baseUri);
    }

    public RequestPinRQBuilder addRequestBody(String requestToken, String phoneNumber) {
        requestBody = new JSONObject();
        requestBody.put("phoneNumber", phoneNumber);
        requestBody.put("token", requestToken);
        request.body(requestBody.toString());
        return this;
    }

    public RequestPinRQBuilder addHeaders() {
        request.contentType("application/json")
                .accept("application/json");
        return this;
    }

    public RequestSpecification build() {
        return request;
    }
}
