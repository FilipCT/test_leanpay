package si.leanpay.taf.assertions;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditCalcAssertions {

    public static void checkStatusCode(Response response) {
        Assert.assertEquals("Wrong status code: "
                + response.getStatusCode() + " returned!", 200, response.getStatusCode());
    }

    public static void checkAmount(Response response, double amount) {
        Assert.assertEquals(Double.toString(amount),
                response.path("creditCalculations[0].loanAmount.").toString());
    }

    public static void checkNumberOfInstallments(Response response, int installment) {
        Assert.assertEquals( Integer.toString(installment),
                response.path("creditCalculations[0].numberOfInstallments").toString());
    }

    public static void checkErrorCode(Response response, int errorCode, String errorMessage) {
        Assert.assertEquals("Wrong error code: "
                + response.getStatusCode() + " returned!", errorCode, response.getStatusCode());
        Assert.assertEquals("Wrong error message: "
                + response.getHeader("x-leanpaycoreapp-error") + " returned!",
                errorMessage, response.getHeader("x-leanpaycoreapp-error"));
    }
}
