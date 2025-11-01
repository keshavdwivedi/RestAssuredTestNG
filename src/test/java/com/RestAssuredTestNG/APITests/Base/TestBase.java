package com.RestAssuredTestNG.APITests.Base;

import com.RestAssuredTestNG.APITests.Utils.ApiUtility.ApiUtils;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;

public class TestBase {
    public static Logger logger;
    public static Response response;

    private static final String api_Key="c2tfdGVzdF81MUl6Tm9XU0ZVZ3lJRjRCcVh5cm13aGtYNzJ5TkdXWWFqUlBGUnBpNmpKbnZhOGhoc2ZDSVJFT2VUV3pMczd0QzAzb25sSEg1OW1TRWdGemIyYVNFNFJwbDAwY1oyUVJ0Q0g=";

    public static final String getApi_Key(){
        return ApiUtils.decodeString(api_Key);
    }

//    @Test
//    public void test1(){
//
//        String api_Key="c2tfdGVzdF81MUl6Tm9XU0ZVZ3lJRjRCcVh5cm13aGtYNzJ5TkdXWWFqUlBGUnBpNmpKbnZhOGhoc2ZDSVJFT2VUV3pMczd0QzAzb25sSEg1OW1TRWdGemIyYVNFNFJwbDAwY1oyUVJ0Q0g=";
//
//             baseURI="https://api.stripe.com";
//
//              response=  given()
//                        .when()
//                        .auth().basic(ApiUtils.decodeString(api_Key),"")
//                        .get("/v1/products").then().log().all().extract().response();
//              response.prettyPrint();
//
//              JsonPath jsonPath=response.jsonPath();
//              Map<String, String> map=jsonPath.getMap("data[0]");
//              Assert.assertTrue(map.get("id").contains("prod_"));
//              Assert.assertTrue(response.getBody().asString().contains("Silver Special"));
//    }

   /* @Test
    public void OperateUser(){

        baseURI= EndPointUtils.getEndpoint("qa");


       String RegistrationName=ApiUtils.generateRandomName();
        String Email=ApiUtils.generateRandomEmail();
       String Password=ApiUtils.generateRandomPassword();
//
//
//
//        using map to create json body
//
//        HashMap<String,String>RegisterMap=new HashMap<>();
//        RegisterMap.put("name",RegistrationName);
//        RegisterMap.put("email",Email);
//        RegisterMap.put("password",Password);
//
//        HashMap<String,String>LoginMap=new HashMap<>();
//        LoginMap.put("email",Email);
//        LoginMap.put("password",Password);
//
//
//        //register user api
//
       RegisterPOJO registerPOJO=new RegisterPOJO(RegistrationName,Email,Password);
       registerPOJO.SetName(RegistrationName);
       registerPOJO.SetEmail(Email);
        registerPOJO.SetPassword(Password);

             response=given()
                     .contentType(ContentType.JSON)
              .body(registerPOJO)
               .when().post(EndPointUtils.getRegisterEndpoint("qa"))
                .then().log().all().extract().response();

              Assert.assertEquals(response.statusCode(),201);
             Assert.assertTrue(response.getBody().asString().contains("User account created successfully"));
              response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema/Register.json"));

       JsonPath path=new JsonPath(response.asString());

       System.out.println("The id of created user is:-  "+path.get("data.id"));

        //login api for registered user which returns login token for session

        LoginPOJO loginPOJO=new LoginPOJO(Email,Password);
        loginPOJO.setEmail(Email);
        loginPOJO.setPassword(Password);
                     response= given()
                     .contentType(ContentType.JSON)
               .body(loginPOJO)
               .when().post(EndPointUtils.getLoginEndpoint("qa"))
              .then().log().all().extract().response();

                     path=response.getBody().jsonPath();
                    String loginToken=path.get("data.token");

       System.out.println("The login token of logged in user is:-  "+loginToken); //extract and save login token

           Assert.assertEquals(response.statusCode(),200);
          response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema/login.json"));

        //using login token and calling API for logged in user
                given().contentType(ContentType.JSON)
               .header("X-Auth-Token",loginToken)
               .when().get(EndPointUtils.getProfileEndpoint("qa"))
                .then().log().all();
            Assert.assertEquals(response.statusCode(),200);
            Assert.assertEquals(response.getBody().jsonPath().get("message"),"Login successful");

//        //deleting the user
         given().contentType(ContentType.JSON)
                 .header("X-Auth-Token",loginToken)
                .when().delete(EndPointUtils.getDeleteEndpoint("qa"))
              .then().log().all();

        Assert.assertEquals(response.statusCode(),200);
   }  */
}

//
// public static void initialization()
//    {
//        try
//        {
//            property = new Properties();
//            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/TestResources/Configuration.properties");
//            property.load(ip);
//        }
//        catch (IOException e) {
//            e.getMessage();
//        }
//    }
//
//public static Response getSpecificUsersAPI(){
//        response=given().contentType(ContentType.JSON)
//                .baseUri(property.getProperty("baseURL"))
//                .get(property.getProperty("getSpecificUserCall"));
//        return response;
//    }
//
//    public static Response createUsersAPI(Users users){
//        response=given()
//                .contentType("application/json")
//                .baseUri(property.getProperty("baseURL"))
//                .body(users)
//                .post(property.getProperty("createNewUserCall"));
//        return response;
//    }
//
//    public static Response putUsersAPI(Users users){
//        response=given().
//                contentType(ContentType.JSON)
//                .baseUri(property.getProperty("baseURL"))
//                .body(users)
//                .put(property.getProperty("putUserCall")+TestBase.studentid);
//        return response;
//    }
//
//    public static Response deleteUsersAPI(){
//        response=given().contentType(ContentType.JSON)
//                .baseUri(property.getProperty("baseURL"))
//                .delete(property.getProperty("deleteUserCall")+TestBase.studentid);
//        return response;
//    }

    // jsonpath creation ways:-

    /*1. using map

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
