package com.RestAssuredTestNG.StudentAPI.Testcases;

import com.RestAssuredTestNG.StudentAPI.APICollection.StudentAPICollection;
import com.RestAssuredTestNG.StudentAPI.Base.TestBase;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC005_DeleteUser extends TestBase {

    @BeforeClass
    public void setUp(){
        logger.info("Delete users test has been started");
        TestBase.initialization();
        response= StudentAPICollection.deleteUsersAPI();
    }

    @AfterClass
    public void tearDown(){
        logger.info("Delete users test has been completed");
    }

    @Test(priority = 2)
    public void DeleteUser_validateResponseCode(){
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("Response code validated is==> "+response.getStatusCode());
    }

    @Test(priority = 3)
    public void DeleteUser_validateResponseTime(){
        if (response.getTime()>8000)
            logger.warn("Response time is greater than 8000");

        Assert.assertTrue(response.getTime()<8000);
        logger.info("Response time validated is==> "+response.getTime());
    }

    @Test(priority = 4)
    public void DeleteUser_validateStatusLine(){
        logger.info("Status Line is ==> " + response.statusLine());
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    }

    @Test(priority = 5)
    public void DeleteUser_validateContentType(){
        String contentType=response.contentType();
        logger.info("Content type is==> " + contentType);
        Assert.assertTrue(contentType.equalsIgnoreCase("application/json; charset=utf-8"));
    }
}

