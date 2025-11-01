package com.RestAssuredTestNG.APITests.APIConfigs.APIMethods;

import com.RestAssuredTestNG.APITests.Base.TestBase;
import com.RestAssuredTestNG.APITests.Utils.EndpointUtility.Stripe_EndpointUtils;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class StripeMethods extends TestBase {

   //public static String api_Key="c2tfdGVzdF81MUl6Tm9XU0ZVZ3lJRjRCcVh5cm13aGtYNzJ5TkdXWWFqUlBGUnBpNmpKbnZhOGhoc2ZDSVJFT2VUV3pMczd0QzAzb25sSEg1OW1TRWdGemIyYVNFNFJwbDAwY1oyUVJ0Q0g=";

    @BeforeClass
    public void setUp() {
        TestBase.logger = LogManager.getLogger();
        TestBase.logger.info("Test case started");
        baseURI= Stripe_EndpointUtils.GetStripeEndpoint("qa");
    }

    @AfterClass
    public void tearDown(){
        logger.info("Test case ended");
    }

    public static Response getProductList(){
        response=given()
                .when()
                .auth().basic(TestBase.getApi_Key(),"")
                .get(Stripe_EndpointUtils.GetStripe_products_Endpoint("qa")).then().log().all().extract().response();
        return response;
    }

    public static Response getproductDetail(String productId){
        response=given()
                .when()
                .auth().basic(TestBase.getApi_Key(),"")
                .get("v1/products/"+productId).then().log().all().extract().response();
        return response;
    }

    public static Response getAccountBalance(){
        response= given().when().auth().basic(TestBase.getApi_Key(), "")
                .get(Stripe_EndpointUtils.GetStripe_AccountBalance_Endpoint("qa"))
                .then().log().all().extract().response();
        return response;
    }

    public static Response getAccountBalanceTransactions(){
        response= given().when().auth().basic(TestBase.getApi_Key(), "")
                .get(Stripe_EndpointUtils.GetStripe_BalanceTransactions_Endpoint("qa"))
                .then().log().all().extract().response();
        return response;
    }
}
