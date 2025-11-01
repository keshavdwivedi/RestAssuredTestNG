package com.RestAssuredTestNG.APITests.Testcases;

import com.RestAssuredTestNG.APITests.APIConfigs.APIMethods.GorestMethods;
import com.RestAssuredTestNG.APITests.APIConfigs.PojoClasses.LoginPOJO;
import com.RestAssuredTestNG.APITests.APIConfigs.PojoClasses.RegisterPOJO;
import com.RestAssuredTestNG.APITests.Base.TestBase;
import com.RestAssuredTestNG.APITests.Utils.ApiUtility.ApiUtils;
import com.RestAssuredTestNG.APITests.Utils.EndpointUtility.Notes_EndPointUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Test4_Notes_expandtesting extends GorestMethods {

//    @BeforeClass
//    public void setUp(){
//
//        TestBase.logger = LogManager.getLogger();
//        TestBase.logger.info("Notes Test case started");
//        RestAssured.baseURI = Notes_EndPointUtils.getEndpoint("qa");
//
//    }
//
//    @AfterClass
//    public void tearDown(){
//        logger.info("Notes Test case ended");
//    }
//
//    @Test(priority = 1,description = "Registering a user")
//    public void Validate_User_Journey(){
//        String RegistrationName= ApiUtils.generateRandomName();
//        String Email= ApiUtils.generateRandomEmail();
//        String Password=ApiUtils.generateRandomPassword();
//
//        RegisterPOJO registerPOJO=new RegisterPOJO(RegistrationName,Email,Password);
//        registerPOJO.SetName(RegistrationName);
//        registerPOJO.SetEmail(Email);
//        registerPOJO.SetPassword(Password);
//
//        response =given()
//                .contentType(ContentType.JSON)
//                .body(registerPOJO)
//                .when().post(Notes_EndPointUtils.getRegisterEndpoint("qa"))
//                .then().log().all().extract().response();
//
//        Assert.assertEquals(response.statusCode(),201);
//        Assert.assertTrue(response.getBody().asString().contains("User account created successfully"));
//        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema/Register.json"));
//
//        JsonPath path=new JsonPath(response.asString());
//
//        System.out.println("The id of created user is:-  "+path.get("data.id"));
//
//        //login api for registered user which returns login token for session
//
//        LoginPOJO loginPOJO=new LoginPOJO(Email,Password);
//        loginPOJO.setEmail(Email);
//        loginPOJO.setPassword(Password);
//
//        response= given()
//                .contentType(ContentType.JSON)
//                .body(loginPOJO)
//                .when().post(Notes_EndPointUtils.getLoginEndpoint("qa"))
//                .then().log().all().extract().response();
//
//        path=response.getBody().jsonPath();
//        String loginToken=path.get("data.token");
//
//        System.out.println("The login token of logged in user is:-  "+loginToken); //extract and save login token
//
//        Assert.assertEquals(response.statusCode(),200);
//        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema/login.json"));
//
//        //using login token and calling API for logged in user
//
//        given().contentType(ContentType.JSON)
//                .header("X-Auth-Token",loginToken)
//                .when().get(Notes_EndPointUtils.getProfileEndpoint("qa"))
//                .then().log().all();
//
//        Assert.assertEquals(response.statusCode(),200);
//        Assert.assertEquals(response.getBody().jsonPath().get("message"),"Login successful");
//
//        //deleting the user
//        given().contentType(ContentType.JSON)
//                .header("X-Auth-Token",loginToken)
//                .when().delete(Notes_EndPointUtils.getDeleteEndpoint("qa"))
//                .then().log().all();
//
//        Assert.assertEquals(response.statusCode(),200);
//    }

    @Test(priority = 1,description = "Complete Notes User Journey using GorestMethods")
    public void TestNotesJourney(ITestContext context) {
        GorestMethods.registerUser();
        String loginToken=GorestMethods.loginUser();
        GorestMethods.updateUser(loginToken);
        GorestMethods.deleteUser(loginToken);
        context.setAttribute("token",loginToken);
    }

}
