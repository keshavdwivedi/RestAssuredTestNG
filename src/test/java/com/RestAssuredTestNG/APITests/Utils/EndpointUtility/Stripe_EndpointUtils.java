package com.RestAssuredTestNG.APITests.Utils.EndpointUtility;

import java.util.HashMap;
import java.util.Map;

public class Stripe_EndpointUtils {
    static Map<String,Object> jsonData=new HashMap<>();

    public static String GetStripeEndpoint(String env){
        jsonData= com.RestAssuredTestNG.APITests.Utils.JsonReader.ReadJson.getJsonDataAsMap("EnvFiles/"+env+"/StripeQA.json");
        return (String) jsonData.get("baseURI");
    }

    public static String GetStripe_products_Endpoint(String env){
        jsonData= com.RestAssuredTestNG.APITests.Utils.JsonReader.ReadJson.getJsonDataAsMap("EnvFiles/"+env+"/StripeQA.json");
        return (String) jsonData.get("GetAllProducts");
    }

    public static String GetStripe_AccountBalance_Endpoint(String env){
        jsonData= com.RestAssuredTestNG.APITests.Utils.JsonReader.ReadJson.getJsonDataAsMap("EnvFiles/"+env+"/StripeQA.json");
        return (String) jsonData.get("GetBalance");
    }

    public static String GetStripe_BalanceTransactions_Endpoint(String env){
        jsonData= com.RestAssuredTestNG.APITests.Utils.JsonReader.ReadJson.getJsonDataAsMap("EnvFiles/"+env+"/StripeQA.json");
        return (String) jsonData.get("GetBalanceTransactions");
    }



}
