package si.leanpay.taf.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import si.leanpay.taf.data.GeneralConstants;

import java.io.File;
import java.io.IOException;

@Data
@Log4j2
public class RestHelper {
    private static int index;
    String request;
    Response response;
    String serviceName;

    public RestHelper(String serviceName) {
        this.serviceName = serviceName;
    }

    public Response sendGetRequest(String uri, RequestSpecification requestSpecification) throws IOException {
        index++;

        response = requestSpecification
                .when()
                .log()
                .all()
                .get(uri);

        saveDocumentToFile(response.prettyPrint(), GeneralConstants.WORK_DIR + index + "_" +
                serviceName + "RS" + ".json");
        return response;
    }

    public Response sendPostRequest(String uri, RequestSpecification requestSpecification) throws IOException {
        index++;
        saveDocumentToFile(request, GeneralConstants.WORK_DIR + index + "_" +
                serviceName + "RQ" + ".json");

        response = requestSpecification
                .when()
                .log()
                .all()
                .post(uri);

        saveDocumentToFile(response.prettyPrint(), GeneralConstants.WORK_DIR + index + "_" +
                serviceName + "RS" + ".json");
        return response;
    }

    public void saveDocumentToFile(String content, String fileNameWithPath) throws IOException {
        File file = new File(fileNameWithPath);
        FileUtils.writeStringToFile(file, content, "ISO-8859-1");
    }
}
