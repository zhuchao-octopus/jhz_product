package com.cy.utils;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcel {
	
	 public static List<Map<String, String>> ImportExcelUtils(String filePath,String columns[] ){
		 Workbook wb =null;
	        Sheet sheet = null;
	        Row row = null;
	        List<Map<String, String>> list =  new ArrayList<Map<String,String>>();
	        String cellData = null;
	        //String filePath = "D:\\UEG-Playstore Package Name.xlsx";
	        //String columns[] = {"appClassName","appClassPackageName"};
	        wb = readExcel(filePath);
	        if(wb != null){
	        	for(int m=0;m<wb.getNumberOfSheets();m++){
	            //获取第一个sheet
	            sheet = wb.getSheetAt(m);
	            //获取最大行数
	            int rownum = sheet.getPhysicalNumberOfRows();
	            System.err.println(rownum);
	            //获取第一行
	            row = sheet.getRow(0);
	            //获取最大列数
	            int colnum=0;
	            try {
	            	 colnum = row.getPhysicalNumberOfCells();
				} catch (NullPointerException e) {
					continue;
				}
	           
	            System.err.println(colnum);
	            for (int i = 1; i<rownum; i++) {
	                Map<String, String> map = new LinkedHashMap<String,String>();
	                row = sheet.getRow(i);
	                if(row !=null){
	                    for (int j=0;j<colnum;j++){
	                        cellData = (String) getCellFormatValue(row.getCell(j));
	                        map.put(columns[j], cellData);
	                    }
	                }else{
	                    break;
	                }
	                list.add(map);
	             }
	        	}
	        }
	        	
	       return list;
	    
		 
	 }
	 
	 //读取excel
	    public static  Workbook readExcel(String filePath){
	        Workbook wb = null;
	        if(filePath==null){
	            return null;
	        }
	        String extString = filePath.substring(filePath.lastIndexOf("."));
	        InputStream is = null;
	        try {
	            is = new FileInputStream(filePath);
	            if(".xls".equals(extString)){
	                return wb = new HSSFWorkbook(is);
	            }else if(".xlsx".equals(extString)){
	                return wb = new XSSFWorkbook(is);
	            }else{
	                return wb = null;
	            }
	            
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return wb;
	    }
	 
	 
	 
	 
	 public static  Object getCellFormatValue(Cell cell){
	        Object cellValue = null;
	        if(cell!=null){
	            //判断cell类型
	            switch(cell.getCellType()){
	            case Cell.CELL_TYPE_NUMERIC:{
	                cellValue = String.valueOf(cell.getNumericCellValue());
	                break;
	            }
	            case Cell.CELL_TYPE_FORMULA:{
	                //判断cell是否为日期格式
	                if(DateUtil.isCellDateFormatted(cell)){
	                    //转换为日期格式YYYY-mm-dd
	                    cellValue = cell.getDateCellValue();
	                }else{
	                    //数字
	                    cellValue = String.valueOf(cell.getNumericCellValue());
	                }
	                break;
	            }
	            case Cell.CELL_TYPE_STRING:{
	                cellValue = cell.getRichStringCellValue().getString();
	                break;
	            }
	            default:
	                cellValue = "";
	            }
	        }else{
	            cellValue = "";
	        }
	        return cellValue;
	    }

}
