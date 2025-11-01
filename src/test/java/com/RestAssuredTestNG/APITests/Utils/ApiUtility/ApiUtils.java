package com.RestAssuredTestNG.APITests.Utils.ApiUtility;

import java.util.Base64;
import java.util.UUID;

public class ApiUtils {

    // Base64 encoding and decoding methods

    public static String decodeString(String encodedString){
        byte[] bytes = Base64.getDecoder().decode(encodedString);
        return new String(bytes);
    }

    //Random data generation methods

    public static String generateRandomName() {
        return "User" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generateRandomEmail() {
        return "user" + UUID.randomUUID().toString().substring(0, 8) + "@mailinator.com";
    }

    public static String generateRandomPassword() {
        return "Pass@" + UUID.randomUUID().toString().substring(0, 8);
    }

}
