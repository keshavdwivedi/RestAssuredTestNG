package com.RestAssuredTestNG.StudentAPI.Utilities.ExcelDataReaderSetup;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelDataReader {

    XSSFWorkbook wb = null;
    XSSFSheet sheet = null;
    XSSFRow row = null;
    XSSFCell cell = null;
    String excelfilePath;
    FileInputStream fis = null;
    FileOutputStream fo;

    public ExcelDataReader(String filePath,String sheetName) {
        this.excelfilePath = filePath;
        try {
            fis = new FileInputStream(filePath);
            wb = new XSSFWorkbook(fis);
            sheet = wb.getSheet(sheetName);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
     }

    //this method is used to get count of total rows

    public int getRowcount() {

        int row = sheet.getLastRowNum() + 1;
        return row;
    }

    //this method is used to get count of total columns

    public int getColumncount() {

        int columncount = 0;
        try {
            row = sheet.getRow(0);
            columncount = row.getLastCellNum();

        } catch (Exception e) {
            System.out.println("the error occurred is as " + e.getMessage());
            e.printStackTrace();
        }
        return columncount;
    }

    public String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
    {
        fis=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fis);
        sheet=wb.getSheet(xlsheet);
        row=sheet.getRow(rownum);
        cell=row.getCell(colnum);
        String data;
        try
        {
            DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
        }
        catch (Exception e)
        {
            data="";
        }
        wb.close();
        fis.close();
        return data;
    }



    public void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
    {
        fis=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fis);
        sheet=wb.getSheet(xlsheet);
        row=sheet.getRow(rownum);
        cell=row.createCell(colnum);
        cell.setCellValue(data);
        fo=new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fis.close();
        fo.close();
    }
}
