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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Test2_Stripe_Accountbalance extends StripeMethods {

    @Test(priority = 1,description = "Validation of account balance attributes")
    public void validateAccountBalance_ResponseText(){


            response= StripeMethods.getAccountBalance();

        JsonPath jsonPath=response.jsonPath();

        List<Integer>AccountBalanceList=jsonPath.getList("available.amount");

        int accountBalance =jsonPath.getInt("available[0].amount");
        int pendingAmount=jsonPath.getInt("pending[0].amount");
        int refundAvailableAmount=jsonPath.getInt("refund_and_dispute_prefunding.available[0].amount");

        for (Integer integer : AccountBalanceList) {
            if (integer != null) {
                Assert.assertTrue(AccountBalanceList.contains(-4400));
            }
        }

        Assert.assertTrue(response.getBody().asString().contains("inr"));

        System.out.println("Actual Balance is " + accountBalance);
        System.out.println("Pending Amount is " + pendingAmount);
        System.out.println("Refund Available Amount is " + refundAvailableAmount);

    }

    @Test(priority = 2,description = "Validating the status code")
    public void validateAccountBalance_StatusCode(ITestContext context){
       // response= (io.restassured.response.Response) context.getAttribute("response");
        assertThat(response.statusCode(), equalTo(200));
        //Assert.assertEquals(response.getStatusCode(),200);
        TestBase.logger.info("status code verified successfully");
    }

    @Test(priority = 3,description = "Validating the response time")
    public void validateAccountBalance_ResponseTime(){
        //Assert.assertTrue(response.getTime()<=5000);
        assertThat(response.getTime()<5000, is(true));
        TestBase.logger.info("response time verified successfully");
    }

    @Test(priority = 4,description = "Validating the json schema response")
    public void validateAccountBalance_ResponseSchema(){
         response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema/StripeAccountBalance.json"));
        TestBase.logger.info("response schema verified successfully");
    }

    @Test(priority = 5,description = "Validating the response Headers")
    public void validateAccountBalance_ResponseHeaders(){

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
                    assertThat(header.getValue(), Matchers.equalTo("525"));

            }
        }
    }

}
