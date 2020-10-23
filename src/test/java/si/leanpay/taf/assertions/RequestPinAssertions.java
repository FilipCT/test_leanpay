package si.leanpay.taf.assertions;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestPinAssertions {

    public static void checkStatusCode(Response response) {
        Assert.assertEquals("Wrong status code: "
                + response.getStatusCode() + " returned!", 200, response.getStatusCode());
    }
}
