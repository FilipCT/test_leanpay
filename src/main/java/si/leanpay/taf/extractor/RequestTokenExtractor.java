package si.leanpay.taf.extractor;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestTokenExtractor {

    public static String extractRequestToken(Response response){
        return response.path("token").toString();
    }
}
