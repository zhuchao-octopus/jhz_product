package com.cy.service.check;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.dao.check.CheckResourceDao;
import com.cy.domain.check.CheckView;
import com.cy.utils.CyResult;
@Service
public class CheckResourceServiceImpl implements CheckResourceService {

	@Resource
	private CheckResourceDao dao;

	@Override
	public CyResult getCheckByCodeAndDate(String workerCode, String attendanceStartDate, String attendanceEndDate) {
		CyResult result = new CyResult();
		List<CheckView> list = dao.getCheckByCodeAndDate(workerCode, attendanceStartDate, attendanceEndDate);
		if(list == null && list.size() < 1){
			result.setMsg("无考勤信息");
			result.setState(1);
			return result;
		}
		result.setMsg("查询成功");
		result.setState(0);
		result.setData(list);
		return result;
	}
	
	


}
