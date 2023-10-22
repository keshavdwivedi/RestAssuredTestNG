package com.RestAssuredTestNG.StudentAPI.Utils.ApiUtility;

import java.util.Base64;

public class ApiUtils {

    public static String decodeString(String encodedString){
        byte[] bytes = Base64.getDecoder().decode(encodedString);
        return new String(bytes);
    }
}
