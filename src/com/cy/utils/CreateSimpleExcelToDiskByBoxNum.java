package com.cy.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 
  
public class CreateSimpleExcelToDiskByBoxNum  {  
    
  
    public static  String createExcel(List<String> list,OutputStream os,String fileName) throws Exception  
    {  
//        // 第一步，创建一个webbook，对应一个Excel文件  
//        HSSFWorkbook wb = new HSSFWorkbook();  
//        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
//        HSSFSheet sheet = wb.createSheet("条码表一");  
//        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
//        HSSFRow row = sheet.createRow((int) 0);  
//        // 第四步，创建单元格，并设置值表头 设置表头居中  
//        HSSFCellStyle style = wb.createCellStyle();  
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        
        
        //OutputStream os=null;
      //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet1= workbook.createSheet("First Sheet",0);
        
        
        
//        HSSFCell cell = row.createCell((short) 0);  
//        cell.setCellValue("PCBA_SN");  
//        cell.setCellStyle(style);
//        cell = row.createCell((short) 1);  
//        cell.setCellValue("彩盒SN");  
//        cell.setCellStyle(style);
//        cell = row.createCell((short) 2);  
//        cell.setCellValue("箱号");  
//        cell.setCellStyle(style);
//       
//       System.out.println(list);
//       HSSFCell cell =row.createCell((short)0);
//       cell.setCellValue(list.get(0)+"");  
//       cell.setCellStyle(style);
//       for(int i=1;i<list.size();i++){
//    	   cell=row.createCell((short)i);
//    	   cell.setCellValue(list.get(i)+"");
//    	   cell.setCellStyle(style);
//       }
//        HSSFCell cell = row.createCell((short)0);
//        cell.setCellValue("箱号");
//        cell.setCellStyle(style);
////        for (int i = 0; i < list.size(); i++) {
////			String str = String.format("%03d", i).toString();
////			 cell=row.createCell((short)i);
////	     	 cell.setCellValue(str);
////	    	 cell.setCellStyle(style);
////		}
        
//        for(int i=0;i<2;i++){
//        	cell=row.createCell((short)i);
//        	cell.setCellValue(list.get(i));
//        	cell.setCellStyle(style);
//        }
        
        
        
//        for(int i=0;i<list.size()-2;i=i+2){
//        	
//        }
        
        
//        String[] number={"00","01","02","03","04","05","06","07","08","09","10","11",
//        		"12","13","14","15","16","17","18","19","20"};
//        String.format("%05d", n++)
//        HSSFCell cell =row.createCell((short)0);
//        cell.setCellValue("箱号");
//        cell.setCellStyle(style);
//        for(int i=1;i<number.length;i++){
//           cell=row.createCell((short)i);
//     	   cell.setCellValue(number[i]);
//    	   cell.setCellStyle(style);
//        }
//        Label A=new Label(0,0,list.get(0));
//        sheet1.addCell(A);
//        Label B=new Label(1,0,list.get(1));
//        sheet1.addCell(B);
        
        for(int i=0;i<32;i++){
        	Label A=new Label(i,0,String.valueOf(i));
        	sheet1.addCell(A);
        }
        
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
       
//        row = sheet.createRow(1);  
//        for (int i = 0; i <list.size(); i++)  
//        {  
//            //row = sheet.createRow((int) i + 1);  
//           
//            // 第四步，创建单元格，并设置值  
//           
//            row.createCell((short) i).setCellValue(list.get(i)+"");                                                       
//        }  
{
        	
        	for(int i=0;i<list.size();i=i+1){
        		//row=sheet.createRow((short)i-1);
        		Label lable=new Label(i,1,list.get(i));
        		sheet1.addCell(lable);   	
            }
    		
    	}
        
        
        
        
        
        
        
        // 第六步，将文件存到指定位置  
        try  
        {  
        	

            //FileOutputStream fout = new FileOutputStream(UrlAddress.folder+fileName+".xls"); 
            
            workbook.write();  
            workbook.close();
            os.close(); 
            return UrlAddress.urlString+fileName+".xls";
            

        }  
        catch (Exception e)  
        { 
            e.printStackTrace(); 
            throw new RuntimeException("文件导出错误！");
        }  
        
        
        
        
        
        
    }  
}  
