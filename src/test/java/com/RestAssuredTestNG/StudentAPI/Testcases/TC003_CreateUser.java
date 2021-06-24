package com.RestAssuredTestNG.StudentAPI.Testcases;

import com.RestAssuredTestNG.StudentAPI.APICollection.StudentAPICollection;
import com.RestAssuredTestNG.StudentAPI.Base.TestBase;
import com.RestAssuredTestNG.StudentAPI.Utilities.ExcelDataReaderSetup.StudentAPIDataProviders;
import com.RestAssuredTestNG.StudentAPI.Utilities.PojoClasses.Users;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC003_CreateUser extends TestBase{

    @BeforeClass
    public void setUp(){
        logger.info("Create users test has been started");
        TestBase.initialization();
    }

    @AfterClass
    public void tearDown(){
        logger.info("Create users test has been completed");
    }

    @Test(priority = 1,dataProvider = "CreateUserData",dataProviderClass = StudentAPIDataProviders.class)
    public void CreateNewUserRequest(String firstname,String lastname,String subjectid){

        Users users=new Users();
        users.setFirstName(firstname);
        users.setLastName(lastname);
        users.setSubjectid(subjectid);

        response= StudentAPICollection.createUsersAPI(users);

        response.prettyPrint();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void CreateUser_validateStatusCode(){
        Assert.assertEquals(response.getStatusCode(),201);
        logger.info("Response code validated is==> "+response.getStatusCode());
    }

    @Test(priority = 3)
    public void CreateUser_validateStatusLine(){
        logger.info("Status Line is ==> " + response.statusLine());
        Assert.assertTrue(response.getStatusLine().contains("HTTP/1.1 201"));
    }

    @Test(priority = 4)
    public void CreateUser_validateContentType(){
        String contentType=response.contentType();
        logger.info("Content type is==> " + contentType);
        Assert.assertTrue(contentType.equalsIgnoreCase("application/json; charset=utf-8"));
    }

    @Test(priority = 5)
    public void CreateUser_validateContentLength(){
        String contentLength = response.header("Content-Length");
        logger.info("Content Length is==>" +contentLength);

        if(Integer.parseInt(contentLength)<50)
            logger.warn("Content Length is less than 50");

        Assert.assertTrue(Integer.parseInt(contentLength)>50);
    }
}
