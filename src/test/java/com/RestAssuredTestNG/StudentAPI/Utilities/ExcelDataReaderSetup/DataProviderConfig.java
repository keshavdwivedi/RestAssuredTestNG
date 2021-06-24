package com.RestAssuredTestNG.StudentAPI.Utilities.ExcelDataReaderSetup;

import java.io.IOException;

public class DataProviderConfig {

//custom method to fetch call getcelldata() method which fetch data values from excel file.

    public Object[][] fetchSheetData(String workbookpath, String sheetName) {
        ExcelDataReader excelobj = new ExcelDataReader(workbookpath, sheetName);
        int totalrow = excelobj.getRowcount();
        int totalcolumn = excelobj.getColumncount();
        Object[][] data = new Object[totalrow - 1][totalcolumn];
        for (int i = 1; i < totalrow; i++) {
            for (int j = 0; j < totalcolumn; j++) {
                String celldata = null;
                try {
                    celldata = excelobj.getCellData(workbookpath,sheetName, i, j);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.print(celldata + " | ");
                data[i - 1][j] = celldata;
            }
            System.out.println();
        }

        return data;
    }
 }
