package com.inetBank.utilities;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

   public static int getRowCount(String xlfile, String xlsheet) throws IOException 
   {
	   fi=new FileInputStream(xlfile);
	   wb= new XSSFWorkbook(fi);
	   ws= wb.getSheet(xlsheet);
	   int rowcount=ws.getLastRowNum();
	   wb.close();
	   fi.close();
	   return rowcount;
   }

    
    public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException
    {
    	 fi=new FileInputStream(xlfile);
  	   wb= new XSSFWorkbook(fi);
  	   ws= wb.getSheet(xlsheet);
  	   row=ws.getRow(rownum);
  	   int cellcount=row.getLastCellNum();
  	   wb.close();
  	   fi.close();
  	   return cellcount;
    }

public static String getCellData(String xlfile, String xlsheet, int rownum,int colnum) throws IOException
    {
   	 fi=new FileInputStream(xlfile);
	   wb= new XSSFWorkbook(fi);
	   ws= wb.getSheet(xlsheet);
	   row=ws.getRow(rownum);
	   cell=row.getCell(colnum);
	   String data;
	   try
	   {
        DataFormatter formatter = new DataFormatter();
     String cellData= formatter.formatCellValue(cell);
     return cellData;
	   }
	   catch(Exception e)
	   {
		   data="";
	   }
	   wb.close();
	   fi.close();
	   return data;
    }

    public static void setCellData(String xlfile, String xlsheet, int rownum,int colnum, String data) throws IOException
    {
    	 fi=new FileInputStream(xlfile);
  	   wb= new XSSFWorkbook(fi);
  	   ws= wb.getSheet(xlsheet);
  	   row =ws.getRow(rownum);
  	   cell=row.createCell(colnum);
  	   cell.setCellValue(data);
  	   fo=new FileOutputStream(xlfile);
  	   wb.write(fo);
       wb.close();
  	   fi.close();
  	   fo.close();
    }

   
    public String getData(String fileName, String sheetName, String columnName, int rowNumber) throws IOException, InvalidFormatException 
    {
        FileInputStream fis = new FileInputStream(fileName);
        Workbook workbook = null;

        if (fileName.endsWith(".xlsx"))
        {
            workbook = new XSSFWorkbook(fis);
        } else if (fileName.endsWith(".xls")) 
        {
            workbook = new HSSFWorkbook(fis);
        } else 
        {
            fis.close();
            throw new IllegalArgumentException("The specified file is not an Excel file");
        }

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) 
        {
            workbook.close();
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in " + fileName);
        }

        Row headerRow = sheet.getRow(0);
        if (headerRow == null)
        {
            workbook.close();
            throw new IllegalArgumentException("No header row found in sheet " + sheetName);
        }
        Map<String, Integer> columnMap = new HashMap<>();
        for (Cell cell : headerRow) 
        {
            columnMap.put(cell.getStringCellValue(), cell.getColumnIndex());
        }

        if (!columnMap.containsKey(columnName)) 
        {
            workbook.close();
            throw new IllegalArgumentException("Column " + columnName + " does not exist in sheet " + sheetName);
        }

        int columnIndex = columnMap.get(columnName);
        Row dataRow = sheet.getRow(rowNumber);

        if (dataRow == null) 
        {
            workbook.close();
            throw new IllegalArgumentException("Row " + rowNumber + " does not exist in sheet " + sheetName);
        }

        Cell cell = dataRow.getCell(columnIndex);
        String cellValue = getCellValue(cell);

        workbook.close();
        return cellValue;
    }

    private static String getCellValue(Cell cell) 
    {
        if (cell == null) 
        {
            return "";
        }

switch (cell.getCellType()) 
{
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) 
                {
                    return cell.getDateCellValue().toString();
                } else 
                {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
            	return "";
                }


	
	
    }
}
