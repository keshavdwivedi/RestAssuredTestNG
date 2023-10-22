package com.RestAssuredTestNG.StudentAPI.Utilities.ExcelDataReaderSetup;

import org.testng.annotations.DataProvider;

public class StudentAPIDataProviders {

    DataProviderConfig dataProviderConfig=new DataProviderConfig();

    @DataProvider(name = "CreateUserData")
    public Object[][] createNewUser()
    {

        Object [][] data=dataProviderConfig.fetchSheetData(System.getProperty("user.dir")+"/src/test/resources/TestResources/StudentData.xlsx","Sheet1");
        return data;
    }

    @DataProvider(name = "PutUserData")
    public Object[][] PutExistingUser()
    {

        Object [][] data=dataProviderConfig.fetchSheetData(System.getProperty("user.dir")+"/src/test/resources/TestResources/StudentData.xlsx","Sheet2");
        return data;
    }
}
