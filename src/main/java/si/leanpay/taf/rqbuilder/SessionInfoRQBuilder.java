package si.leanpay.taf.rqbuilder;

import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

@Data
public class SessionInfoRQBuilder {
    private RequestSpecification request;
    private JSONObject requestBody;

    public SessionInfoRQBuilder(String baseUri) {
        request = given().baseUri(baseUri);
    }

    public SessionInfoRQBuilder addHeaders(String idToken) {
        request
                .contentType("application/json")
                .header("Authorization", "Bearer " + idToken);
        return this;
    }

    public RequestSpecification build() {
        return request;
    }
}
