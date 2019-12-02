package com.cy.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Demo1 {
	String[] data={"acbd3","acb456","acb789","ccb2fd","ccb23fd","ccb23dd"};
	public  List<List<String>> test11(){
		List<List<String>> finalList=new ArrayList<List<String>>();
		HashSet<String> hs=new HashSet<String>();
	    for(int i=0;i<data.length;i++){
		    if(data[i].length()>3){
			   hs.add(data[i].substring(0,3));
			}
	    }
		for(String d:hs){
			 List<String> list=new ArrayList<String>();
			 for(int j=0;j<data.length;j++){
				 
			    if(data[j].startsWith(d)){
				   list.add(data[j]);
			    }
			    
			 }
			 finalList.add(list);
		} 
	     return finalList; 
    }
		
	public static void main(String[] args) {
		Demo1 demo=new Demo1();
        List<List<String>> list=demo.test11();
        System.out.println(list);
	}	
	
	
	
	
	
	
	
}
