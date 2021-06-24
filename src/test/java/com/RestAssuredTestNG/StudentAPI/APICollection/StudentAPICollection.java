package com.RestAssuredTestNG.StudentAPI.APICollection;

import com.RestAssuredTestNG.StudentAPI.Base.TestBase;
import com.RestAssuredTestNG.StudentAPI.Utilities.PojoClasses.Users;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StudentAPICollection extends TestBase {

    public static Response getAllusersAPI(){
        response=given().contentType(ContentType.JSON).baseUri(property.getProperty("baseURL")).get(property.getProperty("getAllUsersCall"));
        return response;
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

}
