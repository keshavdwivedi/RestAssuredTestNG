package com.RestAssuredTestNG.APITests.Testcases;

import com.RestAssuredTestNG.APITests.APIConfigs.APIMethods.StripeMethods;
import com.RestAssuredTestNG.APITests.Base.TestBase;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Test3_Stripe_AccountBalanceTransactions extends StripeMethods {

    @Test(priority = 1,description = "Validation of account balance Transactions attributes")
    public void validateAccountBalanceTxn_ResponseText(){

            response= StripeMethods.getAccountBalanceTransactions();

        JsonPath jsonPath=response.jsonPath();

        List<String> Acc_Balance_Transactions = jsonPath.getList("data.id");
        List<String> defaultPrices = jsonPath.getList("data.amount");

        System.out.println("Actual Balance transactions are " + Acc_Balance_Transactions);
        System.out.println("default Price Amounts are " + defaultPrices);

    }

    @Test(priority = 2,description = "Validating the status code")
    public void validateAccountBalanceTxn_StatusCode(){
        assertThat(response.statusCode(), equalTo(200));
        TestBase.logger.info("status code verified successfully");
    }

    @Test(priority = 3,description = "Validating the response time")
    public void validateAccountBalanceTxn_ResponseTime(){
        assertThat(response.getTime()<5000, is(true));
        TestBase.logger.info("response time verified successfully");
    }

    @Test(priority = 4,description = "Validating the json schema response")
    public void validateAccountBalanceTxn_ResponseSchema(){
         response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema/StripeAccountBalanceTxns.json"));
        TestBase.logger.info("response schema verified successfully");
    }

    @Test(priority = 5,description = "Validating the response Headers")
    public void validateAccountBalanceTxn_ResponseHeaders(){

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
                    assertThat(header.getValue(), Matchers.equalTo("3553"));

            }
        }
    }

}
