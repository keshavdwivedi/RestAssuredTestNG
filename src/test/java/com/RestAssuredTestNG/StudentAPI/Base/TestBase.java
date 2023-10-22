package com.RestAssuredTestNG.StudentAPI.Base;

import static io.restassured.RestAssured.*;

import com.RestAssuredTestNG.StudentAPI.Utilities.ExcelDataReaderSetup.StudentAPIDataProviders;
import com.RestAssuredTestNG.StudentAPI.Utils.ApiUtility.ApiUtils;
import com.RestAssuredTestNG.StudentAPI.Utils.EndpointUtility.EndPointUtils;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class TestBase {
    public static Logger logger;
    public static Response response;

    @Test
    public void test1(){

        String api_Key="c2tfdGVzdF81MUl6Tm9XU0ZVZ3lJRjRCcVh5cm13aGtYNzJ5TkdXWWFqUlBGUnBpNmpKbnZhOGhoc2ZDSVJFT2VUV3pMczd0QzAzb25sSEg1OW1TRWdGemIyYVNFNFJwbDAwY1oyUVJ0Q0g=";

              response=  given().baseUri("https://api.stripe.com")
                        .when()
                        .auth().basic(ApiUtils.decodeString(api_Key),"")
                        .get("/v1/balance").then().extract().response();
              response.prettyPrint();

              JsonPath jsonPath=response.jsonPath();
              Map<String, String> map=jsonPath.getMap("available[0]");
              Assert.assertEquals(map.get("amount"),3860);
              Assert.assertTrue(response.getBody().asString().contains("inr"));
    }

    @Test
    public void OperateUser(){

        baseURI= EndPointUtils.getEndpoint("qa");

        HashMap<String,String>RegisterMap=new HashMap<>();
        RegisterMap.put("name","Test007");
        RegisterMap.put("email","test007@mailinator.com");
        RegisterMap.put("password","Lucknow@123");

        HashMap<String,String>LoginMap=new HashMap<>();
        LoginMap.put("email","test007@mailinator.com");
        LoginMap.put("password","Lucknow@123");

        //register user api

               response=given()
                       .contentType(ContentType.JSON)
                .body(RegisterMap)
                .when().post(EndPointUtils.getRegisterEndpoint("qa"))
                .then().log().all().extract().response();

        JsonPath path=new JsonPath(response.asString());

        System.out.println("The id of created user is:-  "+path.get("data.id"));

               //login api for registered user which returns login token for session

        response= given()
                .contentType(ContentType.JSON)
                .body(LoginMap)
                .when().post(EndPointUtils.getLoginEndpoint("qa"))
                .then().log().all().extract().response();

        path=response.getBody().jsonPath();
        String loginToken=path.get("data.token");

        System.out.println("The login token of logged in user is:-  "+loginToken); //extract and save login token

        //using login token and calling API for logged in user

        given().contentType(ContentType.JSON)
                .header("X-Auth-Token",loginToken)
                .when().get(EndPointUtils.getProfileEndpoint("qa"))
                .then().log().all();

        //deleting the user
        given().contentType(ContentType.JSON)
                .header("X-Auth-Token",loginToken)
                .when().delete(EndPointUtils.getDeleteEndpoint("qa"))
                .then().log().all();
    }
}

/*

 public static void initialization()
    {
        try
        {
            property = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/TestResources/Configuration.properties");
            property.load(ip);
        }
        catch (IOException e) {
            e.getMessage();
        }
    }

public static Response getSpecificUsersAPI(){
        response=given().contentType(ContentType.JSON)
                .baseUri(property.getProperty("baseURL"))
                .get(property.getProperty("getSpecificUserCall"));
        return response;
    }

    public static Response createUsersAPI(Users users){
        response=given()
                .contentType("application/json")
                .baseUri(property.getProperty("baseURL"))
                .body(users)
                .post(property.getProperty("createNewUserCall"));
        return response;
    }

    public static Response putUsersAPI(Users users){
        response=given().
                contentType(ContentType.JSON)
                .baseUri(property.getProperty("baseURL"))
                .body(users)
                .put(property.getProperty("putUserCall")+TestBase.studentid);
        return response;
    }

    public static Response deleteUsersAPI(){
        response=given().contentType(ContentType.JSON)
                .baseUri(property.getProperty("baseURL"))
                .delete(property.getProperty("deleteUserCall")+TestBase.studentid);
        return response;
    }

    // jsonpath creation ways:-

    1. using map

     JsonPath jsonPath=response.jsonPath();
              Map<String, String> map=jsonPath.getMap("available[0]");
              Assert.assertEquals(map.get("amount"),0);

     2. using getbody

      JsonPath path=new JsonPath(response.asString());
      path=response.getBody().jsonPath();
      String loginToken=path.get("data.token");
      System.out.println("The login token of logged in user is:-  "+loginToken);

      3. direct from response

      Jsonpath path=response.jsonpath();
      String firstname=path.get("data.firstname");
      System.out.println("The login token of logged in user is:-  "+loginToken);

      original api key :- sk_test_51IzNoWSFUgyIF4BqXyrmwhkX72yNGWYajRPFRpi6jJnva8hhsfCIREOeTWzLs7tC03onlHH59mSEgFzb2aSE4Rpl00cZ2QRtCH

*/
