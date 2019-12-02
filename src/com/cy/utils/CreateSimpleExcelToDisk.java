package com.cy.utils;
import java.io.FileOutputStream;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cy.domain.list.Binding_code;
import com.cy.domain.list.ProductCode;
import com.cy.domain.produce.tv.TvProduce;
import com.cy.domain.product.fyj.FyjCode;
import com.cy.utils.CyUtil;
  
public class CreateSimpleExcelToDisk  {  
    
	//zqt条码列表批量导出
    public static  String createExcel(List<ProductCode> list,Map<Integer, String> map) throws Exception  
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
  
        HSSFCell cell ; 
        for (int i = 0; i < map.size(); i++) {
        	cell = row.createCell((short) i);
			cell.setCellValue(map.get(i));
			cell.setCellStyle(style);
			
		}
      
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            ProductCode code = list.get(i);  
            // 第四步，创建单元格，并设置值  
            for (int j = 0; j < map.size(); j++) {
				switch (map.get(j)) {
				case "条码ID":
		            row.createCell((short) j).setCellValue(code.getPid());
					break;
				case "型号":
		            row.createCell((short) j).setCellValue(code.getModelCode());
					break;
				case "料号":
		            row.createCell((short) j).setCellValue(code.getPnCode());
					break;
				case "SN1":
		            row.createCell((short) j).setCellValue(code.getSn1());
					break;
				case "SN2":
		            row.createCell((short) j).setCellValue(code.getSn2());
					break;
				case "SN3":
		            row.createCell((short) j).setCellValue(code.getSn3());
					break;
				case "SN4":
		            row.createCell((short) j).setCellValue(code.getSn4());
					break;
				case "SN5":
		            row.createCell((short) j).setCellValue(code.getSn5());
					break;
				case "SN6":
		            row.createCell((short) j).setCellValue(code.getSn6());
					break;
				case "SN7":
		            row.createCell((short) j).setCellValue(code.getSn7());
					break;
				case "颜色":
					row. createCell((short) j).setCellValue(code.getColor());
					break;
				default:
					break;
				}
			}
            
        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
        	String fileName=CyUtil.getName();

        	 FileOutputStream fout = new FileOutputStream(UrlAddress.folder+fileName+".xls");

             wb.write(fout);  
             fout.close(); 
             
             return UrlAddress.urlString+fileName+".xls";
        }  
        catch (Exception e)  
        {  
            e.printStackTrace(); 
            throw new RuntimeException("文件导出错误！");
        }  
    }  
    
    
    
    
    
    
    
    
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
        cell.setCellValue("条码ID");  
        cell.setCellStyle(style);  
        
        cell = row.createCell((short) 1);  
        cell.setCellValue("SN");  
        cell.setCellStyle(style); 
        
        cell = row.createCell((short) 2);  
        cell.setCellValue("箱號");  
        cell.setCellStyle(style);  

        cell = row.createCell((short) 3);  
        cell.setCellValue("生產時間");  
        cell.setCellStyle(style);

       
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
       
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            FyjCode code = (FyjCode) list.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue((Integer) code.getId());  
            row.createCell((short) 1).setCellValue(code.getSn());  
            row.createCell((short) 2).setCellValue(code.getBoxNum());  
            row.createCell((short) 3).setCellValue(code.getCreatime());
           
            
            
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
    
    
    public static  String createFunctionExcel(List<TvProduce> list) throws Exception  
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
        cell.setCellValue("MAC");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("MAC是否已录");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("设备ID");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("HDMI");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);  
        cell.setCellValue("音频");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);  
        cell.setCellValue("USB接口1");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);  
        cell.setCellValue("USB接口2");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);  
        cell.setCellValue("USB接口3");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);  
        cell.setCellValue("USB接口4");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);  
        cell.setCellValue("砧板号");  
        cell.setCellStyle(style);
        cell = row.createCell((short)10);  
        cell.setCellValue("TF卡");  
        cell.setCellStyle(style);
        cell = row.createCell((short)11);  
        cell.setCellValue("网卡");  
        cell.setCellStyle(style);
        cell = row.createCell((short)12);  
        cell.setCellValue("wifi");  
        cell.setCellStyle(style);
        cell = row.createCell((short)13);  
        cell.setCellValue("LED");  
        cell.setCellStyle(style);
        cell = row.createCell((short)14);  
        cell.setCellValue("遥控");  
        cell.setCellStyle(style);
        cell = row.createCell((short)15);  
        cell.setCellValue("蓝牙");  
        cell.setCellStyle(style);
        cell = row.createCell((short)16);  
        cell.setCellValue("存储容量");  
        cell.setCellStyle(style);
        cell = row.createCell((short)17);  
        cell.setCellValue("软件版本");  
        cell.setCellStyle(style);
        cell = row.createCell((short)18);  
        cell.setCellValue("硬件版本");  
        cell.setCellStyle(style);
        cell = row.createCell((short)19);  
        cell.setCellValue("设备型号");  
        cell.setCellStyle(style);
        cell = row.createCell((short)20);  
        cell.setCellValue("SN");  
        cell.setCellStyle(style);
        cell = row.createCell((short)21);  
        cell.setCellValue("老化时间");  
        cell.setCellStyle(style);
        
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            TvProduce tv = list.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(tv.getMac());  
            System.out.println(tv);
//            if(tv.getTv_mac_status()==0){
//            	row.createCell((short)1).setCellValue("未录");  
//            }else if(tv.getTv_mac_status()==1){
//                row.createCell((short)1).setCellValue("已录");  
//            }else if(tv.getTv_mac_status()==null){
//            	row.createCell((short)1).setCellValue("未知");  
//            }
            row.createCell((short) 2).setCellValue(tv.getTv_product_id());  
            row.createCell((short) 3).setCellValue(tv.getTest_hdmi());  
            row.createCell((short) 4).setCellValue(tv.getTv_av());
            
            row.createCell((short) 5).setCellValue(tv.getTv_usb_interface_01());  
            row.createCell((short) 6).setCellValue(tv.getTv_usb_interface_02());  
            row.createCell((short) 7).setCellValue(tv.getTv_usb_interface_03());
            row.createCell((short) 8).setCellValue(tv.getTv_usb_interface_04());  
            row.createCell((short) 9).setCellValue(tv.getTv_tfcard());  
            row.createCell((short) 10).setCellValue(tv.getTv_ethernet_info());  
            row.createCell((short) 11).setCellValue(tv.getTv_wifi_info());
            row.createCell((short) 12).setCellValue(tv.getTv_led());  
            row.createCell((short) 13).setCellValue(tv.getTv_control());  
            row.createCell((short) 14).setCellValue(tv.getTv_bluetooth());  
            row.createCell((short) 15).setCellValue(tv.getTv_storage_capacity());
            row.createCell((short) 16).setCellValue(tv.getTv_software_version());  
            row.createCell((short) 17).setCellValue(tv.getTv_hardware_version());  
            row.createCell((short) 18).setCellValue(tv.getTv_equiment_model());  
            row.createCell((short) 19).setCellValue(tv.getTv_sn_code());
            row.createCell((short) 20).setCellValue(tv.getTv_burnin_time());    
        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
        	String fileName=CyUtil.getName();

        	 FileOutputStream fout = new FileOutputStream(UrlAddress.folder+fileName+".xls");

             wb.write(fout);  
             fout.close(); 
             
             return UrlAddress.urlString+fileName+".xls";
        }  
        catch (Exception e)  
        {  
            e.printStackTrace(); 
            throw new RuntimeException("文件导出错误！");
        }  
    }
}