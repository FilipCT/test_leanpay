package si.leanpay.taf.rqbuilder;

import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

@Data
public class CheckPersonDetailsRQBuilder {
    private RequestSpecification request;
    private JSONObject requestBody;

    public CheckPersonDetailsRQBuilder(String baseUri) {
        request = given().baseUri(baseUri);
    }

    public CheckPersonDetailsRQBuilder addRequestBody(String requestToken) {
        requestBody = new JSONObject();
        requestBody.put("token", requestToken);
        request.body(requestBody.toString());
        return this;
    }

    public CheckPersonDetailsRQBuilder addHeaders(String idToken) {
        request
                .contentType("application/json")
                .header("Authorization", "Bearer " + idToken);
        return this;
    }

    public RequestSpecification build() {
        return request;
    }
}
