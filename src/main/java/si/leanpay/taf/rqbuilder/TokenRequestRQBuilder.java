package si.leanpay.taf.rqbuilder;

import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import si.leanpay.taf.data.VendorData;
import si.leanpay.taf.leanpay.core.dto.VendorApiRequest;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@Data
public class TokenRequestRQBuilder {
    private RequestSpecification request;
    private String requestBody;

    public TokenRequestRQBuilder(String baseUri) {
        request = given().baseUri(baseUri);
    }

    public TokenRequestRQBuilder addRequestBody(String jsonTemplateLocation) throws IOException, ParseException {
        UUID uuid = UUID.randomUUID();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(jsonTemplateLocation));
        JSONObject jsonObject = (JSONObject) obj;
        jsonObject.remove("vendorTransactionId");
        jsonObject.put("vendorTransactionId", uuid.toString());
        request.body(jsonObject.toJSONString());
        this.requestBody = jsonObject.toJSONString();
        return this;
    }

    public TokenRequestRQBuilder addRequestBodyObject(String surrogateId, BigDecimal amount, String vendorId,
        String vendorProductCode){
        UUID uuid = UUID.randomUUID();
        VendorApiRequest vendorApiRequest = new VendorApiRequest();

        vendorApiRequest.setVendorApiKey(vendorId);
        vendorApiRequest.setVendorTransactionId(uuid.toString());
        vendorApiRequest.setAmount(amount);
        vendorApiRequest.setSuccessUrl("/vendor-mock#success");
        vendorApiRequest.setErrorUrl("/vendor-mock#error");
        vendorApiRequest.setVendorFirstName("Janez111");
        vendorApiRequest.setVendorLastName("Novak");
        vendorApiRequest.setVendorAddress("Teslova ulica");
        vendorApiRequest.setVendorZip("1000");
        vendorApiRequest.setVendorCity("Ljubljana");
        vendorApiRequest.setLanguage("sl");
        vendorApiRequest.setVendorEmail("test@emal.com");
        request.body(vendorApiRequest);
        return this;
    }

    public TokenRequestRQBuilder addRequestBodyTDD(VendorData vendorData, String jsonTemplateLocation) throws IOException, ParseException {
        UUID uuid = UUID.randomUUID();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(jsonTemplateLocation));
        JSONObject jsonObject = (JSONObject) obj;
        jsonObject.remove("vendorTransactionId");
        jsonObject.put("vendorTransactionId", uuid.toString());
        jsonObject.put("vendorFirstName", vendorData.getVendorFirstName());
        jsonObject.put("vendorLastName", vendorData.getVendorLastName());
        jsonObject.put("vendorEmail", vendorData.getVendorEmail());
        request.body(jsonObject.toJSONString());
        this.requestBody = jsonObject.toJSONString();
        return this;
    }

    public TokenRequestRQBuilder addHeaders() {
        request.contentType("application/json")
                .accept("application/json");
        return this;
    }

    public RequestSpecification build() {
        return request;
    }
}
