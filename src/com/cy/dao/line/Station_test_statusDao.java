package com.cy.dao.line;

import java.util.List;

import com.cy.domain.list.ProductCode;
import com.cy.domain.station_test_status.Station_test_status;

public interface Station_test_statusDao {
	
	public List<Station_test_status> findSn1BySn1AndPname();
	
	public List<ProductCode> findCodeByDemo(ProductCode code);
	
}
