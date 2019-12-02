package com.cy.service.line;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dao.line.daily_production_listDao;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.daily_production_list;
import com.cy.utils.CyResult;

@Service("daily_production_listServier")
@Transactional(rollbackFor = Exception.class)
public class daily_production_listServierImpl implements daily_production_listServier{
	@Resource
	private daily_production_listDao production_listDao;

	@Override
	public CyResult findAllDaily(Integer page, Integer limit) throws ParseException {
		CyResult result = new CyResult();
		if(page==null){
			page=1;
		}
		int pageSize = (page-1)*limit;
		daily_production_list production_list = new daily_production_list();
		production_list.setLimit(limit);
		production_list.setPageSize(pageSize);
		List<daily_production_list> list = production_listDao.loadAllDaily(production_list);
		if (list == null || list.size() < 1) {
			result.setMsg("无数据!");
			result.setState(0);
			return result;
		}
		int count = production_listDao.CountLine();
		result.setCount(count);
		result.setMsg("加载生产线表成功");
		result.setState(1);
		result.setData(list);
		return result;
	}

	@Override
	public CyResult addDailyProduction(daily_production_list dpls) {
		CyResult result=new CyResult();
		try {
			production_listDao.addDailyProduction(dpls);
			result.setState(1);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("异常,请检查网络!");
			result.setState(3);
			return result;
		}
		
	}

	@Override
	public CyResult delDailyProduction(daily_production_list dpl) {
		CyResult result = new CyResult();
		production_listDao.delDailyProduction(dpl);
		result.setMsg("删除成功!");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updateDailyProduction(daily_production_list dpl) {
		CyResult result = new CyResult();
		production_listDao.updateDailyProduction(dpl);
		result.setMsg("修改成功");
		result.setState(1);
		return result;
	}

	


	
	

	
}
