package si.leanpay.taf.steps;

import io.restassured.response.Response;
import lombok.Data;
import org.springframework.stereotype.Component;
import si.leanpay.taf.data.VendorData;
import si.leanpay.taf.rqbuilder.CreditCalculationRQBuilder;
import si.leanpay.taf.utils.RestHelper;

import java.io.IOException;

@Component
@Data
public class CreditCalculationStep {
    private RestHelper creditCalcHelper;
    private Response response;
    private VendorData vendorData;
    private String serviceName;
    private CreditCalculationRQBuilder creditCalcRQBuilder;

    public void init(VendorData vendorData, String baseUrl) {
        this.serviceName = "credit-calculation";
        this.vendorData = vendorData;
        this.creditCalcHelper = new RestHelper(serviceName);
        this.creditCalcRQBuilder = new CreditCalculationRQBuilder(baseUrl);
    }

    public void sendCreditCalcPostRequest(String uri) throws IOException {
        response = creditCalcHelper.sendPostRequest(uri, creditCalcRQBuilder.build());
    }

    public void prepareCreditCalcRequest() {
        creditCalcRQBuilder
                .withVendorApiKey(vendorData.getVendorApiKey())
                .withAmount(vendorData.getAmount())
                .withTerm(vendorData.getTerm())
                .withHeaders();
        creditCalcHelper.setRequest(creditCalcRQBuilder.getJsonRequest().toString());
    }
}
