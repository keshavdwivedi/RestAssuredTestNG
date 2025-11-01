package com.RestAssuredTestNG.APITests.APIConfigs.APIMethods;

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
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class GorestMethods extends TestBase {

    @BeforeClass
    public void setUp(){

        TestBase.logger = LogManager.getLogger();
        TestBase.logger.info("Test notes Test case started");
        RestAssured.baseURI = Notes_EndPointUtils.getEndpoint("qa");

    }

    @AfterClass
    public void tearDown(){
        logger.info("Test notes Test case ended");
    }

    private final String registrationName = ApiUtils.generateRandomName();
    private final String email = ApiUtils.generateRandomEmail();
    private final String password = ApiUtils.generateRandomPassword();

    private static RegisterPOJO registerPOJO;
    private static LoginPOJO loginPOJO;


    public static String registerUser() {
        initRegistrationPojo();
        response = given()
                .contentType(ContentType.JSON)
                .body(registerPOJO)
                .when().post(Notes_EndPointUtils.getRegisterEndpoint("qa"))
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(), 201, "Expected 201 on user creation");
        Assert.assertTrue(response.getBody().asString().contains("User account created successfully"));
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema/Register.json"));

        JsonPath jsonPath = new JsonPath(response.asString());
        String id = jsonPath.getString("data.id");
        Assert.assertNotNull(id, "Created user id should not be null");
        System.out.println("The id of created user is: " + id);
        return id;
    }



    public static String loginUser() {
        initLoginPojo();
        response = given()
                .contentType(ContentType.JSON)
                .body(loginPOJO)
                .when().post(Notes_EndPointUtils.getLoginEndpoint("qa"))
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(), 200, "Expected 200 on login");
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema/login.json"));

        JsonPath path = response.jsonPath();
        String token = path.getString("data.token");
        Assert.assertNotNull(token, "Login token should not be null");
        System.out.println("The login token of logged in user is: " + token);
        return token;
    }


    public static void updateUser(String loginToken) {
        Assert.assertNotNull(loginToken, "loginToken must not be null for updateUser");
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Auth-Token", loginToken)
                .when().get(Notes_EndPointUtils.getProfileEndpoint("qa"))
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(), 200, "Expected 200 on profile fetch");
        Assert.assertEquals(response.getBody().jsonPath().getString("message"), "Login successful");
    }

    public static void deleteUser(String loginToken) {
        Assert.assertNotNull(loginToken, "loginToken must not be null for deleteUser");
        response = given()
                .contentType(ContentType.JSON)
                .header("X-Auth-Token", loginToken)
                .when().delete(Notes_EndPointUtils.getDeleteEndpoint("qa"))
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(), 200, "Expected 200 on delete");
    }

    private static void initRegistrationPojo() {
        // Use the same ApiUtils generator in this class; keep values consistent per instance if needed.
        // If external tests expect different values, consider making these parameters.
        registerPOJO = new RegisterPOJO(new GorestMethods().registrationName,new GorestMethods().email,new GorestMethods().password);
        registerPOJO.SetName(new GorestMethods().registrationName);
        registerPOJO.SetEmail(new GorestMethods().email);
        registerPOJO.SetPassword(new GorestMethods().password);

    }

    private static void initLoginPojo() {
        loginPOJO = new LoginPOJO(new GorestMethods().email, new GorestMethods().password);
        loginPOJO.setEmail(new GorestMethods().email);
        loginPOJO.setPassword(new GorestMethods().password);
    }

}
