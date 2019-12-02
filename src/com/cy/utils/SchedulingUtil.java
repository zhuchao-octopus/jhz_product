package com.cy.utils;

import com.cy.dao.list.ProductListDao;
import com.cy.domain.list.ProductCode;

public class SchedulingUtil {
	private static ProductListDao dao = SpringContextHolder.getBean(ProductListDao.class);
	
	public CyResult wxs(String strsn1,String strsn2,ProductCode pc){
		CyResult result = new CyResult();
		if(pc.getSn1()==null||pc.getSn1().trim().isEmpty()||pc.getPcode()==null||pc.getPcode().trim().isEmpty()){
			result.setMsg("列表数据不全！");
			result.setState(0);
			return result;
		}
		if (strsn1.length()!=16||strsn2.length()!=16) {
			result.setMsg("SN1输入位数有误!请重新输入");
			result.setState(0);
			return result;
		}
		
		int n = Integer.parseInt(strsn1.substring(strsn1.length()-5, strsn1.length()));
		int m = 1 + Integer.parseInt(strsn2.substring(strsn1.length()-5, strsn1.length()));
		int j = 0;
		String sst = null;
		if(m<n){
			result.setMsg("录入的格式出现错误");
			result.setState(0);
			return result;
		}
		for (int i = n; i < m; i++) {
			StringBuffer sb = new StringBuffer(strsn1.substring(0, 11));
			sst = sb.append(String.format("%05d",i)).toString();
			ProductCode pp = dao.loadProductCodeBySN1(sst);
			if (pp!=null){
				System.out.println(sst);
				result.setMsg("SN1码已存在！");
				result.setState(0);
				continue;
			}
			pc.setSn1(sst);
		    j=dao.addProductCode(pc);
		}
		if(j!=1){
			//throw new RuntimeException("新增列表失败!");
			result.setMsg("新增列表失败!");
			result.setState(0);
			return result;
		}
		result.setMsg("新增列表成功!");
		result.setState(1);
		return result;
		
	}
	
}
