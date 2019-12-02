package com.cy.dao.soft;

import java.util.List;

import com.cy.domain.soft.SoftVersion;

public interface VersionDao {

	List<SoftVersion> loadMaxSoftByName(SoftVersion version);

	List<SoftVersion> loadSoftWare(SoftVersion version);

	int loadSoftWareCount(SoftVersion version);

	int  addSoftware(SoftVersion version);

	void deleteVersion(SoftVersion version);

	void editVersion(SoftVersion version);

	

}
