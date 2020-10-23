package si.leanpay.taf.rqbuilder;

import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

@Data
public class PaymentRQBuilder {
    private RequestSpecification request;
    private JSONObject requestBody;

    public PaymentRQBuilder(String baseUri){
        request = given().baseUri(baseUri);
    }

    public PaymentRQBuilder addRequestBody(String requestToken){
        requestBody = new JSONObject();
        requestBody.put("token", requestToken);
        request.body(requestBody.toString());
        return this;
    }

    public PaymentRQBuilder addHeaders(){
        request.contentType("application/json")
                .accept("application/json");
        return this;
    }

    public RequestSpecification build(){
        return request;
    }
}
