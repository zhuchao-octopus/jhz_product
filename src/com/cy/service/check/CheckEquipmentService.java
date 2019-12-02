package com.cy.service.check;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cy.dao.check.CheckEquipmentDao;
import com.cy.domain.check.CheckEquipment;
import com.cy.domain.line.ProductWorker;
import com.cy.utils.CyResult;
import com.cy.utils.ImportExcel;
import com.cy.utils.UploadUtil;

@Service
public class CheckEquipmentService {

	@Resource
	private CheckEquipmentDao equipmentDao;
	/**
	 * 后台
	 * @param equipment
	 * @return
	 */
	public CyResult getEquipment(CheckEquipment equipment){
		CyResult result = new CyResult();
		equipment.setPageSize((equipment.getPage()-1)*equipment.getLimit());
		List<CheckEquipment> list = equipmentDao.getEquipmentList(equipment);
		
		if(list == null  || list.size()<1){
			result.setMsg("无设备列表信息!");
			result.setState(0);
			return result;
		}
		
		int count = equipmentDao.count(equipment);
		
		result.setMsg("设备列表加载成功!");
		result.setCount(count);                          
		result.setState(1);
		result.setData(list);
		return result;
	}
	public List<CheckEquipment> getEquipmentList(CheckEquipment equipment){
		List<CheckEquipment> list = equipmentDao.getEquipmentList(equipment);
		return list;
	}
	
	
	public CyResult insertEquipment(CheckEquipment equipment){
		CyResult result = new CyResult();
		if(equipment.getMac1()==null||equipment.getMac1().trim().isEmpty()){
			result.setMsg("Mac地址不能为空");
			result.setState(3);
			return result;
		}
		
		CheckEquipment equipmentMac1 = equipmentDao.findEquipmentByMac1(equipment.getMac1());
		if(equipmentMac1 != null){
			result.setMsg("mac地址存在,不可重复录入！");
			result.setState(2);
			return result;
		}
		
		equipment.setWay(1);
		equipment.setStatus(1);
		int num = equipmentDao.insertEquipment(equipment);
		if(num != 1){
			result.setMsg("新增设备失败！");
			result.setState(1);
			return result;
		}
		result.setMsg("新增设备成功！");
		result.setState(0);
		return result;
	}
	
	
	public CyResult insertEquipmentList(MultipartFile file, CheckEquipment equipment, HttpServletRequest request) throws Exception{
		CyResult result = new CyResult();
		// 文件夹名字
		String folderName = "mac";
		// 接收上传结果
		Map<String, Object> map = null;
		if (file != null&&!file.isEmpty()) {
			map = UploadUtil.upload(file, request, folderName);
			if (!map.get("msg").toString().equals("保存成功")) {
				result.setState(0);
				result.setMsg(map.get("msg") + ",请重新传送!!");
				return result;
			} else {
				String localAppAdd = map.get("path").toString() + File.separator + map.get("filename").toString();
				System.out.println("路径:"+localAppAdd);
				List<Map<String, String>> list = null;
			    String colums[]={"sn1","mac1"};//这个是表头  你可以定义成其他的 但是 必须要让对象知每个表头和对象是如何对应的 
			    list=ImportExcel.ImportExcelUtils(localAppAdd, colums);//这里是工具类  解析Excel文档的 
				List<CheckEquipment> listcode = new ArrayList<CheckEquipment>();
				for(Map<String, String> mapone:list){
					CheckEquipment codeOne=new CheckEquipment();
					//BeanUtils.populate(codeOne, mapone);
					BeanUtils.populate(codeOne, mapone);
					System.out.println(codeOne);
					if(("".equals(codeOne.getMac1().trim()))){
						 continue;
					 }
					 String sss=codeOne.getMac1().substring(0,2)+":"+codeOne.getMac1().substring(2,4)+":"+codeOne.getMac1().substring(4,6)+":"+codeOne.getMac1().substring(6,8)+":"+codeOne.getMac1().substring(8,10)+":"+codeOne.getMac1().substring(10);
					 //TvCode codeOne=new TvCode();
					 codeOne.setMac1(sss);
					 codeOne.setSn1(codeOne.getSn1());
					 codeOne.setWay(1);
					 CheckEquipment codeTwo=equipmentDao.findEquipmentByMac1(codeOne.getMac1());
					 if(codeTwo!=null){
						 continue;
					 }
					 
					 codeOne.setMac1(sss);
					 codeOne.setSn1(codeOne.getSn1());
					 codeOne.setStatus(1);
					 codeOne.setWay(1);
					 listcode.add(codeOne);
					 //System.out.println(code);
				}	 
				System.out.println(listcode.size());
				if(listcode.size()==0){
					result.setState(3);
				    result.setMsg("文件数据完全重复,请确认!");
				    result.setData(equipment);
				    return result;
				}
				
				try{
				    equipmentDao.insertEquipmentLists(listcode);
				}catch (Exception e) {
				     e.printStackTrace();
				     throw new RuntimeException("录入失败!");
				}
			      result.setState(1);
			      result.setMsg("录入成功");
			      result.setData(equipment);
			      return result;
			}
			
		}
		
		result.setState(2);
	    result.setMsg("录入失败");
	    result.setData(equipment);
	    return result;
		
	}
	
	public CyResult deleteEquipment(String id) {
			
			CyResult result = new CyResult();
			
			equipmentDao.deleteEquipment(Integer.valueOf(id));
			result.setMsg("刪除成功");
			result.setState(1);
			return result;
		}

	
	public CyResult deleteBatchEquipment(String strId) {
		CyResult result = new CyResult();
		String[] id = strId.split(",");
		for(int i=0; i<id.length; i++){
			equipmentDao.deleteBatchEquipment(Integer.valueOf(id[i]));
		}
		result.setMsg("删除成功");
		result.setState(1);
		return result;
	}
	
	public CyResult updateEquipment(List<CheckEquipment> equipmentList){
		CyResult result = new CyResult();
		for (CheckEquipment checkEquipment : equipmentList) {
			equipmentDao.updateEquipment(checkEquipment);
		}
		
		result.setMsg("修改成功");
		result.setState(0);
		return result;
	}
	
	public CyResult updateBatchEquipment(String strId,String status){
		List<CheckEquipment> equipmentList = new ArrayList<CheckEquipment>();
		for (String id : strId.split(",")) {
			CheckEquipment equipment = new CheckEquipment();
			equipment.setId(Integer.parseInt(id));
			equipment.setStatus(Integer.parseInt(status));
			equipmentList.add(equipment);
		}
		return updateEquipment(equipmentList);
	}
	
}
