package com.cy.utils;

public class ImeiUtils {
	/** 
     * 通过imei的前14位获取完整的imei(15位) 
     * @param imeiString 
     * @return 
     */  
    public static String getImeiBy14(String imeiString) {
        String retVal = null;
          
        char[] imeiChar=imeiString.toCharArray();
        int resultInt=0;
        for (int i = 0; i < imeiChar.length; i++) {
            int a=Integer.parseInt(String.valueOf(imeiChar[i]));
            i++;
            final int temp=Integer.parseInt(String.valueOf(imeiChar[i]))*2;
            final int b=temp<10?temp:temp-9;
            resultInt+=a+b;
        }
        resultInt%=10;
        resultInt = resultInt==0?0:10-resultInt;
        retVal = imeiString+resultInt;
        //System.out.println("imei:"+imeiString+resultInt);  
          
        return retVal;  
    }  
}
