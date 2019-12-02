package com.cy.utils;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.springframework.util.DigestUtils;


public class CyUtil {
	
	public final static String salt="lrm";
	/**
	 * 获取当前时间字符串
	 *  如:2017-1-10 15:54:10
	 * @return
	 */
	public static String getTime(){
		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time =sdf.format(date); 
		return time;
	}
	
	public static String getTime1(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String getTime2(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static String getTime3(){
		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		String time =sdf.format(date); 
		return time;
	}
	public static String getTime4(){
		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
		String time =sdf.format(date); 
		return time;
	}
	//计算两个时间的时间差
	public static Long parseTime(String timestr1,String timestr2) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		Long time1=sdf.parse(timestr1).getTime();
		Long time2=sdf.parse(timestr2).getTime();
		Long time;
		if(time1>time2){
			time=time1-time2;
		}else{
			time=time2-time1;
		}
		
		return time;
	}
	
	/**
	 * 获取传入值括号内的信息 如 s="品质组长审核(A01005)" 则是要获取A01005
	 * @param s
	 * @return
	 */
	public static String getString(String s){
		int startNum =s.indexOf("(");
		int endNum =s.lastIndexOf(")");
		String result =s.substring(startNum+1, endNum);
		return result;
	}
	
	public static String returnNewStr(String item){
		String[]ss =item.split("\\(");
		return ss[0];
	}
	
	/**
	 * 将传进来的字符串 如品质组长审核(A01005) 拆分成两个长度的字符串数组 品质组长审核 和A01005
	 * @param item
	 * @return
	 */
	public static String[] getList(String item){
		String[] ss =item.split("\\(");
		ss[1] =ss[1].replaceAll("\\)","");
		return ss;
	}

	/**
	 * 将传入的连个字符串按照指定格式拼接 如 品质部 和 A01001  则拼接成 :品质部(A01001)
	 * @param stepName
	 * @param depName
	 * @return
	 */
	public static String  getNewString(String stepName,String depName){
		StringBuffer sb1 =new StringBuffer(stepName);
		StringBuffer sb2 =new StringBuffer(depName);
		StringBuffer sb3 =sb1.append("(").append(depName).append(")");
		String result =sb3.toString();
		return sb3.toString();
	}
	
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	} 
	
	/**
	 * 名生成
	 */
	public static String getName() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//long millis = System.nanoTime();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = millis + String.format("%03d", end3);
		
		return str;
	}
	
	public static String getMD5(String str) {
        String base = str +"/"+salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
 

}
