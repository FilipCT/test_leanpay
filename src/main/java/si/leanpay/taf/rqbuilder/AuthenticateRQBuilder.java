package si.leanpay.taf.rqbuilder;

import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

@Data
public class AuthenticateRQBuilder {
    private RequestSpecification request;
    private JSONObject requestBody;

    public AuthenticateRQBuilder(String baseUri){
        request = given().baseUri(baseUri);
    }

    public AuthenticateRQBuilder addRequestBody(String requestToken, String userName, String password){
        requestBody = new JSONObject();
        requestBody.put("token", requestToken);
        requestBody.put("username", userName);
        requestBody.put("password", password);
        request.body(requestBody.toString());
        return this;
    }

    public AuthenticateRQBuilder addHeaders(){
        request.contentType("application/json")
                .accept("application/json");
        return this;
    }

    public RequestSpecification build(){
        return request;
    }
}
