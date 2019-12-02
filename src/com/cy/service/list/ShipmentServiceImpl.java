package com.cy.service.list;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cy.dao.list.ShipmentDao;
import com.cy.domain.list.Shipment;
import com.cy.domain.list.SnCodeDomain;
import com.cy.utils.CodeImportByExcel;
import com.cy.utils.CyResult;
import com.cy.utils.CyUtil;
import com.cy.utils.UploadUtil;
@Service
public class ShipmentServiceImpl implements ShipmentService{

	@Resource
	private ShipmentDao dao;
	
	
	@Override
	public CyResult getShipmentList(Shipment shipment) {
		CyResult result = new CyResult();
		shipment.setPageSize((shipment.getPage()-1)*shipment.getLimit());
		List<Shipment> list = dao.getShipmentList(shipment);
		if(list != null){
			int count = dao.count(shipment);
			result.setCount(count);
			result.setData(list);
			result.setMsg("查询成功");
			result.setState(0);
			return result;
		}
		
		result.setMsg("查询失败");
		result.setState(1);
		return result;
	}


	@Override
	public CyResult insertShipment(Shipment shipment) {
		CyResult result = new CyResult();
		if(shipment.getSn1()!=null&&shipment.getSn1()!=""){
			Shipment codeList=dao.getShipment(shipment.getSn1());
			if(codeList != null){
				result.setMsg("SN重复!");
				result.setState(1);
				return result;
			}
		}
		shipment.setCreatime(CyUtil.getTime2()); 
			try {
				dao.insertShipment(shipment);
				result.setMsg("新增成功");
				result.setState(0);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("faield");
			}
			 
	}
	@Override
	public CyResult elsInsertShipment(MultipartFile file, Shipment shipment, HttpServletRequest request) throws Exception {
		CyResult result = new CyResult();
		// 文件夹名字
		String folderName = "mac";
		// 接收上传结果
		Map<String, Object> map = null;
		System.out.println("shipment:"+shipment);
		if (file != null&&!file.isEmpty()) {
			map = UploadUtil.upload(file, request, folderName);
			if (!map.get("msg").toString().equals("保存成功")) {
				result.setState(0);
				result.setMsg(map.get("msg") + ",请重新传送!!");
				return result;
			} else {
				String localAppAdd = map.get("path").toString() + File.separator + map.get("filename").toString();
				List<Map<String, String>> list = null;
		       list=CodeImportByExcel.ImportExcelUtils(localAppAdd);
		       List<Shipment> shipmentList=new ArrayList<Shipment>();
		       for (Map<String,String> mapone : list) {
		    	   Shipment code=new Shipment();
		    	   SnCodeDomain snCode = new SnCodeDomain();
		           BeanUtils.populate(snCode, mapone);
		           code.setSn1(snCode.getSN号().replace("'", ""));
		           code.setSn2(snCode.get广电号().replace("'", ""));
		           code.setSn3(snCode.getRJMac地址().replace("'", ""));
		           code.setSn4(snCode.getSTBID().replace("'", ""));
		           code.setSn5(snCode.getWIFIMac地址().replace("'", ""));
		           code.setSn7(snCode.getYSTSN号().replace("'", ""));
		           code.setData1(snCode.getVN().replace("'", ""));
		           code.setData2(snCode.getTN().replace("'", ""));
		           code.setBatch(snCode.getPN().replace("'", ""));
		           if(shipment.getModelCode()==null||"".equals(shipment.getModelCode())){
		        	   code.setModelCode(snCode.getYPRO().replace("'", ""));
		           }else {
		        	   code.setModelCode(shipment.getModelCode());
		           }
		           //code.setModelCode(snCode.getYPRO().replace("'", ""));
		           code.setColor(shipment.getColor());
		           code.setPcode(shipment.getPcode().trim());
		           code.setPnCode(shipment.getPnCode().trim());
		           code.setProcess(shipment.getProcess());
		           code.setPorder(shipment.getPorder());
		           code.setCreatime(CyUtil.getTime());
		          // code.setBatch(shipment.getBatch());
		           code.setCid(shipment.getCid());
		           code.setDeliverTime(shipment.getDeliverTime());
		           code.setHardwareVersion(shipment.getHardwareVersion().trim());
		           code.setSoftwareVersion(shipment.getSoftwareVersion().trim());
		           code.setLicenseTag(shipment.getLicenseTag().trim());
		           code.setMainboardModel(shipment.getMainboardModel().trim());
		           code.setProjectNumber(shipment.getProjectNumber().trim());
		           code.setRegion(shipment.getRegion().trim());
		           if(code.getSn1()!=null&&code.getSn1()!=""){
		   		   List<Shipment> codeList=dao.getShipmentBySn1(code.getSn1());
		   			if(codeList.size()>0){
		   				continue;
		   			}
		   		   }
		           shipmentList.add(code);
		       }
		       if(shipmentList.size()<1){
		    	     result.setState(2);
					 result.setMsg("该Excel表中的SN已完全存在于数据库中，请确认!");
					 return result;
		       }
			    try {

			    	for(Shipment code:shipmentList){
			    		System.err.println("code："+code);
			    	}
			    	 int n = dao.xlsAddShipment(shipmentList);
			    	 result.setState(1);
					 result.setMsg("录入成功!");
					 return result;
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("批量录入失败!");
				}
			}
		}

		result.setState(2);
		result.setMsg("录入失败!");
		return result;
	}
	@Override
	public CyResult deleteShipment(Integer id) {
		CyResult result = new CyResult();
		if (id == null) {
			result.setMsg("未能选定一条列表!");
			result.setState(0);
			return result;
		}
		try {
			dao.deleteShipment(id);
			result.setMsg("success!");
			result.setState(1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error!");
		}
	}

	@Override
	public CyResult updateShipment(Shipment shipment) {
		CyResult result = new CyResult();
		
		try {
			dao.updateShipment(shipment);
			result.setMsg("修改成功！");
			result.setState(0);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error");
		}
	}


	@Override
	public CyResult deleteShipments(String idStr) {
		CyResult result = new CyResult();
		String[] id = idStr.split(",");
		for(int i=0; i<id.length; i++){
			dao.deleteShipment(Integer.valueOf(id[i]));
		}
		result.setMsg("删除成功");
		result.setState(1);
		return result;
	
	}


	

}
