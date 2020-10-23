package si.leanpay.taf.stepdefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import si.leanpay.taf.LeanpayApplication;

/**
 * SpringBoot Test/Configuration class
 *
 * @author Filip Milicevic
 */
@CucumberContextConfiguration
@SpringBootTest(classes = LeanpayApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class SpringIntegrationTest {

    protected RequestSpecification request;
    protected Response response;

    @Value("${base.url}")
    protected String baseUrl;

    @Value("${request.token.path}")
    protected String requestTokenPath;

    @Value("${checkout.payment.path}")
    protected String checkoutPaymentPath;

    @Value("${checkout.request.pin.path}")
    protected String requestPinPath;

    @Value("${authenticate.path}")
    protected String authenticatePath;

    @Value("${session.info.path}")
    protected String sessionInfoPath;

    @Value("${base.page}")
    protected String basePage;

    @Value("${check.person.details.path}")
    protected String checkPersonDetailsPath;

    @Value("${credit.calculation.path}")
    protected String creditCalcPath;

}
