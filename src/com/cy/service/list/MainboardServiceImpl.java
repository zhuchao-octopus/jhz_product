package com.cy.service.list;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dao.list.MainboardDao;
import com.cy.domain.list.Mainboard;
import com.cy.utils.CyResult;

@Service("MainboardService")
@Transactional
public class MainboardServiceImpl implements MainboardService{
	
	@Resource
	private MainboardDao mainboardDao;

	@Override
	public CyResult selectMainboardList(Integer page , Integer limit,Mainboard mainboard) {
		CyResult result = new CyResult();
		if (page == null) {
			page = 1;
		}
		int pageSize = (page - 1) * limit;
		mainboard.setLimit(limit);
		mainboard.setPageSize(pageSize);
		List<Mainboard> list = mainboardDao.selectMainboardListAll(mainboard);
		if (list == null || list.size() < 1) {
			result.setMsg("无产品列表信息!");
			return result;
		}
		int num = mainboardDao.count(mainboard);
		result.setMsg("加载产品列表成功");
		result.setCount(num);
		result.setData(list);
		return result;
	}

	@Override
	public CyResult MainboardDel(Mainboard mainboard) {
		CyResult result = new CyResult();
		mainboardDao.MainboardDelAll(mainboard);
		result.setMsg("删除成功！");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult insertMainboard(Mainboard mainboard) {
		CyResult result = new CyResult();
		int num = mainboardDao.insertMainboardAll(mainboard);
		result.setMsg("添加成功！");
		result.setState(0);
		result.setData(num);
		return result;
	}

	@Override
	public CyResult updateMainboard(Mainboard mainboard) {
		CyResult result = new CyResult();
		int num = mainboardDao.updateMainboardAll(mainboard);
		result.setMsg("编辑成功！");
		result.setState(0);
		result.setData(num);
		return result;
	}

}
