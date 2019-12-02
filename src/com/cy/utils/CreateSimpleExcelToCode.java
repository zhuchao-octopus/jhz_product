package com.cy.utils;


import java.io.FileOutputStream;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cy.domain.list.ProductCode;

  
public class CreateSimpleExcelToCode  {  
    
  
    public static  String createExcel(List list) throws Exception  
    {  
        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("条码表一");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("出货日期");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("生产日期");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("产品型号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("YstSN");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);  
        cell.setCellValue("SN");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);  
        cell.setCellValue("终端串号");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);  
        cell.setCellValue("WIFI mac");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);  
        cell.setCellValue("RJ mac");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);  
        cell.setCellValue("移动设备号");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);  
        cell.setCellValue("生产批次");  
        cell.setCellStyle(style);
        cell = row.createCell((short)10);  
        cell.setCellValue("VN");  
        cell.setCellStyle(style);
        cell = row.createCell((short)11);  
        cell.setCellValue("TN");  
        cell.setCellStyle(style);
        cell = row.createCell((short)12);  
        cell.setCellValue("软件版本号");  
        cell.setCellStyle(style);
        cell = row.createCell((short)13);  
        cell.setCellValue("硬件版本号");  
        cell.setCellStyle(style);
        cell = row.createCell((short)14);  
        cell.setCellValue("箱号");  
        cell.setCellStyle(style);
        cell = row.createCell((short)15);  
        cell.setCellValue("出货地点");  
        cell.setCellStyle(style);
        cell = row.createCell((short)16);  
        cell.setCellValue("运营商");  
        cell.setCellStyle(style);
        
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
       
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            ProductCode code = (ProductCode) list.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(code.getDeliverTime());  
            row.createCell((short) 1).setCellValue(code.getCreatime());  
            row.createCell((short) 2).setCellValue(code.getModelCode());  
            row.createCell((short) 3).setCellValue(code.getSn7());
            row.createCell((short) 4).setCellValue(code.getSn1());
            row.createCell((short) 5).setCellValue(code.getSn2());
            row.createCell((short) 6).setCellValue(code.getSn5());
            row.createCell((short) 7).setCellValue(code.getSn3());
            row.createCell((short) 8).setCellValue(code.getSn4());
            row.createCell((short) 9).setCellValue(code.getBatch());
            row.createCell((short) 10).setCellValue(code.getData1());
            row.createCell((short) 11).setCellValue(code.getData2());
            row.createCell((short) 12).setCellValue(code.getSoftwareVersion());
            row.createCell((short) 13).setCellValue(code.getHardwareVersion());
            row.createCell((short) 14).setCellValue(code.getSn6());
            row.createCell((short) 15).setCellValue(code.getRegion());
            row.createCell((short) 16).setCellValue(code.getLicenseTag());
            
            
        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
        	String fileName=CyUtil.getName();
        	 FileOutputStream fout = new FileOutputStream(UrlAddress.folder+fileName+".xlsx");

             wb.write(fout);  
             fout.close(); 
           
             return UrlAddress.urlString+fileName+".xlsx";

           
        }  
        catch (Exception e)  
        {  
            e.printStackTrace(); 
            throw new RuntimeException("文件导出错误！");
        }  
    }  
}  
