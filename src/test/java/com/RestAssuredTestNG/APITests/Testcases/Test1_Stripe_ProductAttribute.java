package com.RestAssuredTestNG.APITests.Testcases;

import com.RestAssuredTestNG.APITests.APIConfigs.APIMethods.StripeMethods;
import com.RestAssuredTestNG.APITests.Base.TestBase;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class Test1_Stripe_ProductAttribute extends StripeMethods {


    @Test(priority = 1,description = "Validation of product attributes")
    public void validateGetProduct_ResponseText(){

            response= StripeMethods.getProductList();

        JsonPath jsonPath=response.jsonPath();

        List<String> AccountTxnIds = jsonPath.getList("data.id");
        List<String> defaultAmounts = jsonPath.getList("data.amount");

        for(int i=0;i<AccountTxnIds.size();i++){
           if (AccountTxnIds.get(i).contains("txn_3QQs0PSFUgyIF4Bq0oQdLJwu")){
               Assert.assertEquals(defaultAmounts.get(i),"2000");
           }
        }

        Assert.assertTrue(response.getBody().asString().contains("Gold Special"));

        // Print all results
        System.out.println("Account transaction IDs: " + AccountTxnIds);
        System.out.println("default Prices: " + defaultAmounts);
        TestBase.logger.info("Account Transactions validation verified successfully");

    }

    @Test(priority = 2,description = "Validating the status code")
    public void validateGetProduct_StatusCode(ITestContext context){
        assertThat(response.statusCode(), equalTo(200));
        TestBase.logger.info("status code verified successfully");
    }

    @Test(priority = 3,description = "Validating the response time")
    public void validateGetProduct_ResponseTime(){
        assertThat(response.getTime()<5000, is(true));
        TestBase.logger.info("response time verified successfully");
    }

    @Test(priority = 4,description = "Validating the json schema response")
    public void validateGetProduct_ResponseSchema(){
         response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema/StripeProductList.json"));
        TestBase.logger.info("response schema verified successfully");
    }

    @Test(priority = 5,description = "Validating the response Headers")
    public void validateGetProduct_ResponseHeaders(){

        //checking single header
        assertThat(response.getHeader("Content-Type"), Matchers.equalTo("application/json"));

        //checking multiple headers using switch
        Headers headersList = response.getHeaders();
        for(Header header: headersList){
            System.out.println(header.getName() + " : " + header.getValue());

            switch (header.getName()){

                    case "Content-Type":
                    assertThat(header.getValue(), Matchers.equalTo("application/json"));
                    break;

                    case "Content-Encoding":
                    assertThat(header.getValue(), Matchers.equalTo("gzip"));
                    break;

                    case "Content-Length":
                    assertThat(header.getValue(), Matchers.equalTo("2154"));

            }
        }
    }

}
