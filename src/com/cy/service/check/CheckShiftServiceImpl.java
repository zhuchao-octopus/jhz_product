package com.cy.service.check;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.dao.check.CheckShiftDao;
import com.cy.domain.check.CheckShift;
import com.cy.utils.CyResult;
@Service
public class CheckShiftServiceImpl implements CheckShiftService {

	@Resource
	private CheckShiftDao shiftDao;
	
	@Override
	public CyResult getShift(CheckShift shift) {
		CyResult result = new CyResult();
		List<CheckShift> list = shiftDao.getShift(shift);
		if(list.size() < 1 || list == null){
			result.setMsg("无轮班列表信息!");
			result.setState(0);
			return result;
		}
		int num = shiftDao.count(shift);
		
		result.setMsg("轮班列表信息加载成功!");
		result.setState(1);
		result.setCount(num);
		result.setData(list);
		return result;
		
	}

	@Override
	public CyResult insert(CheckShift shift) {
		CyResult result = new CyResult();
		CheckShift checkShift = shiftDao.getShiftByType(shift.getShiftType());
		if(checkShift != null){
			result.setMsg("轮班名称存在，请重新输入！");
			result.setState(2);
			return result;
		}
		
		if(shift.getShiftType() == null || shift.getShiftType().trim().isEmpty()){
			result.setMsg("轮班名称不能为空");
			result.setState(3);
			return result;
		}
		
		shift.setStatus(1);
		int num = shiftDao.insert(shift);
		if(num < 1){
			result.setMsg("插入失败");
			result.setState(0);
			return result;
		}
		
		result.setMsg("插入成功");
		result.setState(1);
		result.setData(num);
		return result;
	}

	@Override
	public CyResult deleteShift(String id) {
			
			CyResult result = new CyResult();
			
			shiftDao.deleteShift(Integer.valueOf(id));
			result.setMsg("刪除成功");
			result.setState(1);
			return result;

	
	}

	@Override
	public CyResult deleteBatchShift(String strId) {
		CyResult result = new CyResult();
		String[] id = strId.split(",");
		for(int i=0; i<id.length; i++){
			shiftDao.deleteBatchShift(Integer.valueOf(id[i]));
		}
		result.setMsg("删除成功");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult update(CheckShift shift) {
		CyResult result = new CyResult();
		int num = shiftDao.update(shift);
		if(num > 0){
			result.setMsg("修改成功");
			result.setState(1);
			return result;
		}
		result.setMsg("修改失败");
		result.setState(0);
		return result;
	}

}
