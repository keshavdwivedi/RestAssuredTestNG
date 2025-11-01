package com.RestAssuredTestNG.APITests.Utils.JsonReader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ReadJson {

    private static ObjectMapper objectMapper=new ObjectMapper();
    public static Map<String,Object>getJsonDataAsMap(String jsonFileName) {

        String completeJsonPath=System.getProperty("user.dir")+"/src/test/resources/TestResources/"+jsonFileName;

        Map<String,Object>objectData= null;
        try {
            objectData = objectMapper.readValue(new File(completeJsonPath),new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return objectData;
    }
}
