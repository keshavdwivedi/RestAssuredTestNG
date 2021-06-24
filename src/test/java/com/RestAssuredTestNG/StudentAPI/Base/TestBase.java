package com.RestAssuredTestNG.StudentAPI.Base;

import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public static Properties property;
    public static Logger logger;
    public static Response response;
    public static String studentid="4";

    public TestBase()
    {
        logger = Logger.getLogger(this.getClass());
    }

    public static void initialization()
    {
        try
        {
            property = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/TestResources/Configuration.properties");
            property.load(ip);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
