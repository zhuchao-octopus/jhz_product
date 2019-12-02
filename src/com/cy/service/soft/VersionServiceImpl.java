package com.cy.service.soft;

import java.util.List;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cy.dao.soft.VersionDao;
import com.cy.domain.soft.SoftVersion;
import com.cy.utils.CyResult;
@Service
public class VersionServiceImpl implements VersionService {
    
	
	@Resource
	private VersionDao versionDao;
	@Override
	public CyResult loadLastVersion(SoftVersion version) {
		CyResult result=new CyResult();
		List<SoftVersion> list=versionDao.loadMaxSoftByName(version);
	    if(list!=null && list.size()>0){
	    	Double maxVersion=list.get(0).getSoft_version();
	    	if(version.getSoft_version()<maxVersion){
	    		result.setCode(1);
	    		result.setData(list.get(0));
	    		result.setMsg("the new version  is OK!");
	    		return result;
	    	}
	    }	
	    result.setCode(0);
		result.setMsg("there is no last version！");
		return result;
	}
	
	@Override
	public CyResult loadAllVersion(SoftVersion version) {
		CyResult result=new CyResult();
		version.setPageSize((version.getPage()-1)*version.getLimit());
		List<SoftVersion> list=versionDao.loadSoftWare(version);
		int count=versionDao.loadSoftWareCount(version);
		result.setCode(0);
		result.setCount(count);
		result.setData(list);
		return result;
	}
	
	@Override
	public CyResult addSoftware(SoftVersion version) {
		CyResult result=new CyResult();
		
		try {
			versionDao.addSoftware(version);
			result.setCode(0);
			result.setMsg("success!");
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("增加失败!");
		}
	}

	@Override
	public CyResult deleteVersion(SoftVersion version) {
        CyResult result=new CyResult();
		try {
			versionDao.deleteVersion(version);
			result.setState(1);
			result.setMsg("success!");
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("增加失败!");
		}
	}

	@Override
	public CyResult editVersion(SoftVersion version) {
		 CyResult result=new CyResult();
			try {
				versionDao.editVersion(version);
				result.setState(1);
				result.setMsg("success!");
	            return result;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("修改失败!");
			}
		
	}
	
	
	
	
	
	

}
