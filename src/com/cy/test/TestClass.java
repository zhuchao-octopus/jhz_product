package com.cy.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sound.sampled.AudioFormat.Encoding;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cy.dao.check.CheckDao;
import com.cy.dao.check.NewCheckDao;
import com.cy.dao.line.ProductWorkerDao;
import com.cy.dao.line.WorkLineDao;
import com.cy.dao.produce.tv.ProduceTvDao;
import com.cy.dao.user.PermissionDao;
import com.cy.domain.check.NewCheck;
import com.cy.domain.line.LevelThree;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkLocation;
import com.cy.domain.produce.tv.OrderList;
import com.cy.service.line.WorkLineService;
import com.cy.utils.CyResult;

public class TestClass {
	
	ApplicationContext ac;
	
	@Before
	public void init(){
		ac=new ClassPathXmlApplicationContext("spring-mvc.xml","spring-mybatis.xml");
		System.out.println(ac);
	}
	
	
    
	@Test
	public void testloadLine(){
		WorkLineDao dao=ac.getBean("workLineDao",WorkLineDao.class);
		WorkLine line=new WorkLine();
		line.setLid(5);
		List<WorkLocation> list=dao.loadline(line);
		for(WorkLocation location:list){
		   System.out.println(location);
		}
	}
	
	
	
	
	
	
	
    @Test
    public void testfindWorkerByCode(){
    	CheckDao checkDao=ac.getBean("checkDao", CheckDao.class);
    	WorkLine line=new WorkLine();
    	line.setLineCode("C");
    	List<WorkLocation> list=checkDao.findWorkerByCode(line);
    	for(WorkLocation w:list){
    		System.out.println(w);
    	}
    }
    
    @Test
    public void testAddWorker(){
    	ProductWorkerDao dao=ac.getBean("productWorkerDao",ProductWorkerDao.class);
    	
    	for(int i=1;i<=10;i++){
    		ProductWorker worker=new ProductWorker();
    		worker.setWorkerCode("00000"+i);
    		worker.setWorkerPrice(16.0);
    		worker.setWorkerName("张三"+i);
    		worker.setEstate(0);
    	    int n=dao.addWorker(worker);
    	}
    }
    
    
    @Test
    public void testString() throws ParseException{
    	String time2="10:29:57";
    	SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
    	long date2=sdf.parse(time2).getTime();
    	String time1="10:40:78";
    	long date1=sdf.parse(time1).getTime();
    	System.out.println(date1-date2);
    }
    
    @Test
    public void testString1(){
    	System.out.println(null==null);
    }
    
    @Test
    public void testPrice(){
    	ProductWorker pw = new ProductWorker();
    	pw.setWorkerPrice(15.5);
    	System.out.println(pw.getWorkerPrice());
    	pw.setWorkerHolidayPay(pw.getWorkerPrice()*1.5);
    	System.out.println(pw.getWorkerHolidayPay());
    	
    }
    
    @Test
    public void testCheck(){
    	NewCheckDao newDao=ac.getBean("newCheckDao",NewCheckDao.class);
    	NewCheck check=new NewCheck();
    	ProductWorker worker=new ProductWorker();
    	worker.setWorkerCode("002230");
    	WorkLine line=new WorkLine();
    	line.setLineCode("B");
    	ProductDetails pd=new ProductDetails();
    	pd.setPid(29);
    	check.setNowDate("2017-12-26");
    	check.setWorker(worker);
    	check.setLine(line);
    	check.setProduct(pd);
    	List<NewCheck> list=newDao.findNewCheckByWorkerCode(check);
    	System.out.println(222);
    	for(NewCheck l:list){
    		System.out.println(l);
    	}
    }
    
    
    @Test
    public void testDemo(){
    	//java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
    	System.out.println(new java.text.DecimalFormat("#.00").format(0.0015926));
    }
    
    
    @Test
    public void testNewCheck(){
    	
    	String[] strlist={"aaa123","aaa147","aaa258","bbb159","bbb258","ccc456","nnn589","ccc589","nnn236"};
    	int i = 0,j = 0,k = 0;
    	List strcplist=new ArrayList<>();
    	String[] tmp;
    	
    	//char a[i]
    	//String strlen;
    	for(i = 0; i < strlist.length; i++)
    	{
    		for(j = i+1; j < strlist.length; j++)
    		{
    			if(!strlist[i].substring(0,3).equals(strlist[j].substring(0,3)))
    			{
    				
    				strcplist.add(strlist[i]);
    				
    			}
    			if(strlist[i].substring(0,3).equals(strlist[j].substring(0,3)))
    			{   
    				
    				System.out.println(strlist[i]);
    				strcplist.add(strlist[i]);
    				    				
    			}
    		}
    		
    	}
    	System.out.println(strcplist);
    	//strlist[0].substring(0,3);
    }
    
    @Test
    public  void test11(){
    	int[] str={1,1,2,3,5,8,13,21,34,55,89,144};
    	int he=0;
    	for(int i=0;i<str.length;i++){
    		he+=str[i];
    	}
    	System.out.println(he);
        int sum=0;
    	for(int i=1;i<=12;i++){
    		sum+=f(i);
    	}
    	System.out.println(sum);
    }
    
    
    public int f(int n){
    	if(n==1||n==2){
    		return 1;
    	}else{
    		return f(n-1)+f(n-2);
    	}
    }
    
    
    @Test
    public void testDemoOne(){
    	
    }
    
    @Test
    public void testchuihuo(){
    	ProduceTvDao pdao = ac.getBean("produceTvDao", ProduceTvDao.class);
    	int id=75;
    	System.out.println(id);
    	List<OrderList> lian = pdao.loadchuhuodang(id);
    	for(OrderList orderList : lian){
    		System.err.println(orderList);
    	}
    }
    
    @Test
    public void testInsertLevel(){
    	PermissionDao dao=ac.getBean("permissionDao", PermissionDao.class);
    	LevelThree level=new LevelThree();
    	level.setName("审批");
    	level.setValue("rjc_approval");
    	System.out.println(dao.addLevelThree(level));
    	
    }
    

    
}  



