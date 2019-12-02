package com.cy.domain.product.fyj;

import java.io.Serializable;

public class FyjCode implements Serializable{
  
   private static final long serialVersionUID = 1L;
   private Integer id;
   private String sn;
   private String boxNum;
   private String creatime;
   public Integer getId() {
	 return id;
   }
   public void setId(Integer id) {
	 this.id = id;
   }
   public String getSn() {
	 return sn;
   }
   public void setSn(String sn) {
	 this.sn = sn;
   }
   public String getBoxNum() {
	 return boxNum;
   }
   public void setBoxNum(String boxNum) {
	 this.boxNum = boxNum;
   } 
   public String getCreatime() {
	 return creatime;
   }
   public void setCreatime(String creatime) {
	 this.creatime = creatime;
   }
   @Override
   public String toString() {
	 return "FyjCode [id=" + id + ", sn=" + sn + ", boxNum=" + boxNum + ", creatime=" + creatime + "]";
   }
   
}
