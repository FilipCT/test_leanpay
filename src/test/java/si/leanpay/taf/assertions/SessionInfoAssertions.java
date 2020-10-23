package si.leanpay.taf.assertions;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionInfoAssertions {

    public static void checkStatusCode(Response response) {
        Assert.assertEquals("Wrong status code: "
                + response.getStatusCode() + " returned!", 200, response.getStatusCode());
    }

    public static void checkMembershipStatus(Response response) {
        Assert.assertEquals("Membership status not approved",
                "APPROVED", response.jsonPath().getString("membershipStatus"));

    }

    public static void checkAccountVerificationStatus(Response response) {
        Assert.assertEquals("Account status not verified",
                "VERIFIED", response.jsonPath().getString("accountVerificationStatus"));
    }
}
