package com.RestAssuredTestNG.StudentAPI.Testcases;

import com.RestAssuredTestNG.StudentAPI.APICollection.StudentAPICollection;
import com.RestAssuredTestNG.StudentAPI.Base.TestBase;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class TC002_GetSpecifcUsers extends TestBase {

    @BeforeClass
    public void setUp(){
        logger.info("Get specific users test has been started");
        TestBase.initialization();

        response= StudentAPICollection.getSpecificUsersAPI();

        response.prettyPrint();

    }

    @AfterClass
    public void tearDown(){
        logger.info("Get specific users test has been completed");
    }

    @Test(priority = 1)
    public void GetSpecificUsers_validateResponseCode(){
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("Response code validated is==> "+response.getStatusCode());
    }

    @Test(priority = 2)
    public void GetSpecificUsers_validateResponseTime(){
        if (response.getTime()>8000)
            logger.warn("Response time is greater than 8000");

        Assert.assertTrue(response.getTime()<8000);
        logger.info("Response time validated is==> "+response.getTime());
    }

    @Test(priority = 3)
    public void GetSpecificUsers_validateStatusLine(){
        logger.info("Status Line is ==> " + response.statusLine());
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    }

    @Test(priority = 4)
    public void GetSpecificUsers_validateContentType(){
        String contentType=response.contentType();
        logger.info("Content type is==> " + contentType);
        Assert.assertTrue(contentType.equalsIgnoreCase("application/json; charset=utf-8"));
    }

    @Test(priority = 5)
    public void GetSpecificUsers_validateContentLength(){
        String contentLength = response.header("Content-Length");
        logger.info("Content Length is==>" +contentLength);

        if(Integer.parseInt(contentLength)<50)
            logger.warn("Content Length is less than 50");

        Assert.assertTrue(Integer.parseInt(contentLength)>50);
    }



}
