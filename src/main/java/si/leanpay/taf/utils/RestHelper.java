package si.leanpay.taf.utils;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.WriterOutputStream;
import org.json.JSONObject;

import si.leanpay.taf.data.GeneralConstants;
import si.leanpay.taf.leanpay.core.domain.VendorToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

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

	public <T> T sendPostRequestDTO(String uri, RequestSpecification requestSpecification, Class<T> cls) throws IOException {
		index++;
		saveDocumentToFile((FilterableRequestSpecification) requestSpecification,
			GeneralConstants.WORK_DIR + index + "_" + serviceName + "RQ" + ".json");

		T response = requestSpecification
			.filter(new RestAssuredRequestFilter())
			.when()
			.post(uri).as(cls);

		saveDocumentToFile(new JSONObject(response).toString(), GeneralConstants.WORK_DIR + index + "_" +
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
		saveDocumentToFile(response.toString(), GeneralConstants.WORK_DIR + index + "_" +
			serviceName + "RS" + ".json");
		return response;
	}

	public void saveDocumentToFile(String content, String fileNameWithPath) throws IOException {
		File file = new File(fileNameWithPath);
		FileUtils.writeStringToFile(file, content, "ISO-8859-1");
	}

	public void saveDocumentToFile(FilterableRequestSpecification requestSpec,
		String fileNameWithPath) throws IOException {
		RequestPrinter.print(requestSpec, requestSpec.getMethod(), requestSpec.getURI(),
			LogDetail.BODY, new PrintStream(new FileOutputStream(fileNameWithPath, true)), true);
	}
}
