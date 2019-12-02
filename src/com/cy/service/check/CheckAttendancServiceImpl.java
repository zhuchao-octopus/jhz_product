package com.cy.service.check;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.dao.check.CheckAttendancDao;
import com.cy.dao.check.CheckResourceDao;
import com.cy.dao.check.CheckShiftDao;
import com.cy.domain.check.CheckAttendanc;
import com.cy.domain.check.CheckShift;
import com.cy.utils.CyResult;
@Service
public class CheckAttendancServiceImpl implements CheckAttendancService {

	@Resource
	private CheckAttendancDao dao;
	@Resource
	private CheckResourceDao rdao;

	@Override
	public CyResult getAttendanc(CheckAttendanc attendanc) {
		CyResult result = new CyResult();
		List<CheckAttendanc> list = dao.getCheckAttendanc(attendanc);
		
		
		if(list != null  || list.size() > 1){
			int count = dao.getCount(attendanc);
			result.setMsg("考勤列表加载成功!");
			result.setCount(count);                          
			result.setState(1);
			result.setData(list);
			return result;
		}
		
		
		
		
		result.setMsg("无考勤列表信息!");
		result.setState(0);
		return result;
		
	}


	@Override
	public CyResult insert(CheckAttendanc attendanc) {
		return null;
	}

	@Override
	public CyResult update(CheckAttendanc attendanc) {
		CyResult result  = new CyResult();
		int num = dao.update(attendanc);
		if(num < 1){
			result.setMsg("修改失败");
			result.setState(1);
			return result;
		}
		
		
		result.setMsg("修改成功");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult delete(String id) {
			CyResult result = new CyResult();
			
			dao.delete(Integer.valueOf(id));
			result.setMsg("刪除成功");
			result.setState(1);
			return result;
	
	}

	@Override
	public CyResult deleteBatch(String id) {

		CyResult result = new CyResult();
		String[] id1 = id.split(",");
		for(int i=0; i<id1.length; i++){
			dao.deleteBatch(Integer.valueOf(id1[i]));
		}
		result.setMsg("删除成功");
		result.setState(1);
		return result;
	}
	

}
