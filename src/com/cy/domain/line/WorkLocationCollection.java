package com.cy.domain.line;

import java.io.Serializable;
import java.util.List;

public class WorkLocationCollection implements Serializable {


	private static final long serialVersionUID = 1L;
	
	List<WorkLocation> locationList;

	public List<WorkLocation> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<WorkLocation> locationList) {
		this.locationList = locationList;
	}
	
	
	
	
	

}
