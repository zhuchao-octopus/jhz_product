package com.cy.service.line;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.dao.line.Station_test_statusDao;
import com.cy.domain.list.ProductCode;
import com.cy.domain.station_test_status.Station_test_status;
import com.cy.utils.CyResult;
@Service("/stationTestService")
public class StationTestServiceImpl implements StationTestService {
	
	@Resource
	private Station_test_statusDao testDao;

	@Override
	public CyResult loadTestStatus() {
		CyResult result=new CyResult();
		List<Station_test_status> list=testDao.findSn1BySn1AndPname();
		for(Station_test_status demo:list){
			String process=demo.getProduct_name();
			String sn1=demo.getProductCode_sn1();
			ProductCode code=new ProductCode();
			code.setSn1(sn1);
			code.setProcess(process);
			List<ProductCode> codeList=testDao.findCodeByDemo(code);
			if(codeList!=null&&codeList.size()>0){
				demo.setState(true);
			}else{
				demo.setState(false);
			}
			System.out.println("demo的值："+demo);
		}
		
		result.setData(list);
		result.setState(1);
		result.setMsg("数据加载成功!");
		return result;
	}

}
