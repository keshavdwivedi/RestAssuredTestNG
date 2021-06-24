package com.RestAssuredTestNG.StudentAPI.Testcases;

import com.RestAssuredTestNG.StudentAPI.APICollection.StudentAPICollection;
import com.RestAssuredTestNG.StudentAPI.Base.TestBase;
import com.RestAssuredTestNG.StudentAPI.Utilities.ExcelDataReaderSetup.StudentAPIDataProviders;
import com.RestAssuredTestNG.StudentAPI.Utilities.PojoClasses.Users;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC004_PutSpecificUser extends TestBase {

    @BeforeClass
    public void setUp(){
        logger.info("Put specific users test has been started");
        TestBase.initialization();
    }

    @AfterClass
    public void tearDown(){
        logger.info("Put specific users test has been completed");
    }

    @Test(priority = 1,dataProvider = "PutUserData",dataProviderClass = StudentAPIDataProviders.class)
    public void PutUserDetails(String firstName,String lastName,String subjectid){

        Users users=new Users();
        users.setFirstName(firstName);
        users.setLastName(lastName);
        users.setSubjectid(subjectid);

        response= StudentAPICollection.putUsersAPI(users);

        response.prettyPrint();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void PutUser_validateResponseCode(){
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("Response code validated is==> "+response.getStatusCode());
    }

    @Test(priority = 3)
    public void PutUser_validateResponseTime(){
        if (response.getTime()>8000)
            logger.warn("Response time is greater than 8000");

        Assert.assertTrue(response.getTime()<8000);
        logger.info("Response time validated is==> "+response.getTime());
    }

    @Test(priority = 4)
    public void PutUser_validateStatusLine(){
        logger.info("Status Line is ==> " + response.statusLine());
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    }

    @Test(priority = 5)
    public void PutUser_validateContentType(){
        String contentType=response.contentType();
        logger.info("Content type is==> " + contentType);
        Assert.assertTrue(contentType.equalsIgnoreCase("application/json; charset=utf-8"));
    }

    @Test(priority = 6)
    public void PutUser_validateContentLength(){
        String contentLength = response.header("Content-Length");
        logger.info("Content Length is==>" +contentLength);

        if(Integer.parseInt(contentLength)<50)
            logger.warn("Content Length is less than 50");

        Assert.assertTrue(Integer.parseInt(contentLength)>50);
    }

}
