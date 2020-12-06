package si.leanpay.taf.extractor;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import si.leanpay.taf.leanpay.core.domain.VendorToken;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestTokenExtractor {

    public static String extractRequestToken(VendorToken response){
        
        return response.getToken();
       // return response.path("token").toString();
    }
}
