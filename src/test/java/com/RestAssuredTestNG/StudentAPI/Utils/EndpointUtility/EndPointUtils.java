package com.RestAssuredTestNG.StudentAPI.Utils.EndpointUtility;

import com.RestAssuredTestNG.StudentAPI.Utils.JsonReader.ReadJson;
import java.util.HashMap;
import java.util.Map;

public class EndPointUtils {

    static Map<String,Object> jsonData=new HashMap<>();

    public static String getEndpoint(String env){

        jsonData= ReadJson.getJsonDataAsMap("EnvFiles/"+env+"/UAPortalQA.json");
        return (String) jsonData.get("baseURI");
    }

    public static String getRegisterEndpoint(String env){

        jsonData= ReadJson.getJsonDataAsMap("EnvFiles/"+env+"/UAPortalQA.json");
        return (String) jsonData.get("registerUser");
    }

    public static String getProfileEndpoint(String env){
        jsonData= ReadJson.getJsonDataAsMap("EnvFiles/"+env+"/UAPortalQA.json");
        return (String) jsonData.get("profileUser");
    }

    public static String getLoginEndpoint(String env){
        jsonData=ReadJson.getJsonDataAsMap("EnvFiles/"+env+"/UAPortalQA.json");
        return(String) jsonData.get("loginUser");
    }

    public static String getDeleteEndpoint(String env){
        jsonData=ReadJson.getJsonDataAsMap("EnvFiles/"+env+"/UAPortalQA.json");
        return(String) jsonData.get("deleteUser");
    }
}
