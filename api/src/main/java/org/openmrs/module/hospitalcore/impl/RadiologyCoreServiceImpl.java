package org.openmrs.module.hospitalcore.impl;

import java.util.List;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.RadiologyCoreService;
import org.openmrs.module.hospitalcore.db.RadiologyCoreDAO;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;

public class RadiologyCoreServiceImpl extends BaseOpenmrsService implements RadiologyCoreService {
	protected RadiologyCoreDAO dao;

	public void setDao(RadiologyCoreDAO dao) {
		this.dao = dao;
	}

	public List<RadiologyDepartment> getAllRadiologyDepartments() {
		return this.dao.getAllRadiologyDepartments();
	}
}
