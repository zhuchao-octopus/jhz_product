package com.cy.service.soft;

import com.cy.domain.soft.SoftVersion;
import com.cy.utils.CyResult;

public interface VersionService {

	CyResult loadLastVersion(SoftVersion version);

	CyResult loadAllVersion(SoftVersion version);

	CyResult addSoftware(SoftVersion version);

	CyResult deleteVersion(SoftVersion version);

	CyResult editVersion(SoftVersion version);

}
