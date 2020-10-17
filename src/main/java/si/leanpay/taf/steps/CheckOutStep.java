package si.leanpay.taf.steps;
import io.restassured.response.Response;

import org.springframework.stereotype.Component;
import si.leanpay.taf.utils.Utils;

@Component
public class CheckOutStep {
    private Utils utils;
    private Response response;

    public void init(){
        this.utils = new Utils();
    }

    public void  sendGetRequest(String uri) {
        this.response = utils.sendGetRequest(uri);
    }

    public void sendPostRequest(String uri) {
        utils.sendPostRequestRequest(uri, prepareRequest());
    }

    public String prepareRequest(){
        return "{\n" +
                "  \"token\": \"f864505b7a5346cfbf7cbc40e9f49bbf\"\n" +
                "}";
    }

    public Response getResponse() {
        return response;
    }
}
