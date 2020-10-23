package si.leanpay.taf.extractor;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticateExtractor {

    public static String extractIdToken(Response response){
        return response.path("idToken");
    }
}
