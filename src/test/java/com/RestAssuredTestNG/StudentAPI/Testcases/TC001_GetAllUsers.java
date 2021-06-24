package com.RestAssuredTestNG.StudentAPI.Testcases;

import com.RestAssuredTestNG.StudentAPI.APICollection.StudentAPICollection;
import com.RestAssuredTestNG.StudentAPI.Base.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001_GetAllUsers extends TestBase {


    @BeforeClass
    public void setUp(){

        logger.info("Get all users test has been started");
        TestBase.initialization();

        response= StudentAPICollection.getAllusersAPI();
        response.prettyPrint();
    }

    @AfterClass
    public void tearDown(){
        logger.info("Get all users test has been completed");
    }

    @Test(priority = 1)
    public void GetAllUsers_validateResponseCode(){
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("Response code validated is "+response.getStatusCode());
    }

    @Test(priority = 2)
    public void GetAllUsers_validateResponseTime(){
       if (response.getTime()>8000)
           logger.warn("Response time is greater than 8000");

           Assert.assertTrue(response.getTime()<8000);
           logger.info("Response time is==> "+response.getTime());
    }

    @Test(priority = 3)
    public void GetAllUsers_validateStatusLine(){
        logger.info("Status Line is ==> " + response.statusLine());
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    }

    @Test(priority = 4)
    public void GetAllUsers_validateContentType(){
        String contentType=response.contentType();
        logger.info("Content type is==> " + contentType);
        Assert.assertTrue(contentType.equalsIgnoreCase("application/json; charset=utf-8"));
    }

    @Test(priority = 5)
    public void GetAllUsers_validateContentLength(){
        String contentLength = response.header("Content-Length");
        logger.info("Content Length is==>" +contentLength);

        if(Integer.parseInt(contentLength)<50)
            logger.warn("Content Length is less than 50");

        Assert.assertTrue(Integer.parseInt(contentLength)>50);
    }

}
