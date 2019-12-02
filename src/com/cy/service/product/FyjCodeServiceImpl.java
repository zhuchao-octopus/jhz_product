package com.cy.service.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dao.product.fyj.FyjCodeDao;
import com.cy.domain.product.fyj.FyjCode;
import com.cy.utils.CreateSimpleExcelToDisk;
import com.cy.utils.CyResult;
@Transactional
@Service
public class FyjCodeServiceImpl implements FyjCodeService {
	
	
	@Resource
    private FyjCodeDao codeDao;
	
	@Override
	public CyResult exportByBoxNum(String boxNum) throws Exception {
		CyResult result=new CyResult();
		if(boxNum==null||boxNum.trim().isEmpty()){
			result.setMsg("箱號不能爲空！");
			result.setState(0);
			return result;
		}
		FyjCode code=new FyjCode();
		code.setBoxNum(boxNum);
		List<FyjCode> list=codeDao.findAll(code);
		if(list==null||list.size()==0){
			result.setMsg("數據爲空，請檢查箱號是否有誤！");
			result.setState(2);
			return result;
		}
		String url=CreateSimpleExcelToDisk.createExcel(list);
		result.setMsg("導出成功！");
		result.setData(url);
		result.setState(1);
		return result;
	}
    
	/**
	 * 根据箱号首尾批量导出
	 * @throws Exception 
	 */
	@Override
	public CyResult exportToBoxNums(String boxNum1, String boxNum2) throws Exception {
		CyResult result=new CyResult();
		if(boxNum1==null||boxNum1.trim().isEmpty()||boxNum2==null||boxNum2.trim().isEmpty()){
			result.setMsg("箱号不能为空！");
			result.setState(0);
			return result;
		}
		String str1=boxNum1.substring(0,9);
		System.out.println(str1);
		String str2=boxNum2.substring(0,9);
		if(!str1.equals(str2)){
			result.setMsg("两箱不属于同一个号段，请检查箱号！");
			result.setState(2);
			return result;
		}
	
		int num1=Integer.parseInt(boxNum1.substring(9));
		System.out.println(num1);
		int num2=Integer.parseInt(boxNum2.substring(9));
		List<FyjCode> list=new ArrayList<FyjCode>();
		for(int i=num1;i<=num2;i++){
			StringBuffer str=new StringBuffer();
			str.append(str1+String.format("%04d",i));
			FyjCode code=new FyjCode();
			code.setBoxNum(str.toString());
			List<FyjCode> codeList=codeDao.findAll(code);
			for(int j=0;j<codeList.size();j++){
				System.out.println(codeList.get(j));
				list.add(codeList.get(j));
			}
		}
		String downLoadUrl=CreateSimpleExcelToDisk.createExcel(list);
		result.setMsg("文件导出成功！");
		result.setState(1);
		result.setData(downLoadUrl);
		return result;
	}
	
	/**
	 * 查询或者加载数据
	 */
	public CyResult loadCode(FyjCode code){
		CyResult result=new CyResult();
		List<FyjCode> list=codeDao.findAll(code);
		if(list==null||list.size()<1){
			result.setMsg("数据为空,请核实！");
			result.setState(0);
			return result;
		}
		result.setData(list);
		result.setMsg("加載產品列表成功!");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult deleteBySn(FyjCode code) {
		CyResult result=new CyResult();
		if(code.getSn()==null||code.getSn().trim().isEmpty()){
			result.setMsg("sn码不能为空！");
			result.setState(0);
			return result;
		}
		List<FyjCode> list=codeDao.findAll(code);
		if(list==null||list.size()<1){
			result.setMsg("sn码不存在");
			result.setState(2);
			return result;
		}
		int n=codeDao.deleteBySn(code);
		if(n!=1){
			throw new RuntimeException("删除失败");
		}
		result.setMsg("删除成功！");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult deleteByBoxNum(FyjCode code) {
		CyResult result=new CyResult();
		if(code.getBoxNum()==null||code.getBoxNum().trim().isEmpty()){
			result.setMsg("箱号不能为空！");
			result.setState(0);
			return result;
		}
		List<FyjCode> list=codeDao.findAll(code);
		if(list==null||list.size()<1){
			result.setMsg("箱号不存在");
			result.setState(2);
			return result;
		}
		int n=codeDao.deleteByBoxNum(code);
		if(n!=list.size()){
			throw new RuntimeException("删除失败");
		}
		result.setMsg("删除成功！");
		result.setState(1);
		return result;
	}
	
}
