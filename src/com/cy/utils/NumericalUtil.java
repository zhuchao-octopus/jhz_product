package com.cy.utils;

import java.util.Stack;
public class NumericalUtil {  
	    private static final String C_CODES_STRING = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	    //private static final String C_CODES_STRING = "0123456789ABCDEF";
	    /*** 
	     * 将10进制转换为42进制 
	     * @param intVal 
	     * @return 
	     */  
	    public static String int2CodeString(int intVal)  {  
	        return int2CodeString(intVal, C_CODES_STRING.length());  
	    }  
	    /*** 
	     * 将10进制转换为任意进制 
	     * @param intVal 
	     * @param base <=42 
	     * @return 
	     */  
	    public static String int2CodeString(long intVal,int base)  {  
	          
	        int w_code_len = C_CODES_STRING.length();  
	        if (base >w_code_len){  
	            return null;  
	        }  
	        StringBuilder sb = new StringBuilder();  
	        Stack<String> s=new Stack<String>();  
	        while (intVal!=0){  
	            s.push(C_CODES_STRING.charAt((int)(intVal%base))+"");  
	            intVal/=base;  
	        }  
	        while (!s.empty()){  
	            sb.append(s.pop());  
	        }  
	        return sb.length()==0?"0":sb.toString();  
	    }  
	    /*** 
	     *  任何进制转换, 
	     * @param s 
	     * @param srcBase s的进制 
	     * @param destBase 要转换为的进制 
	     * @return 
	     */  
	    public static String BaseConvert(String s,int srcBase,int destBase){    
	        if(srcBase == destBase){    
	            return s;    
	        }             
	        char[] chars = s.toCharArray();    
	        int len = chars.length;    
	        if(destBase != 10){//目标进制不是十进制 先转化为十进制    
	            s = BaseConvert(s,srcBase,10);    
	        }else{    
	            long n = 0;    
	            for(int i = len - 1; i >=0; i--){    
	                n+=C_CODES_STRING.indexOf(chars[i])*Math.pow(srcBase, len - i - 1);    
	            }    
	            return String.valueOf(n);    
	        }    
	        return int2CodeString(Integer.valueOf(s),destBase);    
	    }    
	  
	    public static void main(String[] args){
	    	System.out.println(BaseConvert("10",16,10));
	    }
	    
	    
	}  


