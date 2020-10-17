package si.leanpay.taf.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Utils {
    private String baseURI = "https://lapp.leanpay.si/";
    private String basePath = "vendor/checkout";


    public Response sendGetRequest(String uri){
        RestAssured.baseURI = this.baseURI;
        RestAssured.basePath = this.basePath;

        return given()
                .contentType("application/json")
                .header("application/x-www-form-urlencoded", "")
                .when()
                .get()
                .then()
                .using()
                .extract()
                .response();
    }

    public Response sendPostRequestRequest(String uri, String request){
        RestAssured.baseURI = this.baseURI;
        return given()
                .contentType("application/json")
                .header("application/x-www-form-urlencoded", "")
                .body(request)
                .post(uri);
    }

}
