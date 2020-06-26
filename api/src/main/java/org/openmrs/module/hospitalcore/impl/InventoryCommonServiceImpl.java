package org.openmrs.module.hospitalcore.impl;

import java.util.List;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.InventoryCommonService;
import org.openmrs.module.hospitalcore.db.InventoryCommonDAO;
import org.openmrs.module.hospitalcore.model.InventoryDrug;
import org.openmrs.module.hospitalcore.model.InventoryDrugFormulation;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatient;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatientDetail;

public class InventoryCommonServiceImpl extends BaseOpenmrsService implements InventoryCommonService {
	protected InventoryCommonDAO dao;

	public void setDao(InventoryCommonDAO dao) {
		this.dao = dao;
	}

	public List<InventoryStoreDrugPatient> getAllIssueDateByPatientId(Patient patient) throws APIException {
		return this.dao.getAllIssueDateByPatientId(patient);
	}

	public List<InventoryStoreDrugPatient> getDeatilOfInventoryStoreDrugPatient(Patient patient, String date) throws APIException {
		return this.dao.getDeatilOfInventoryStoreDrugPatient(patient, date);
	}

	public List<InventoryStoreDrugPatientDetail> getDrugDetailOfPatient(InventoryStoreDrugPatient isdpd) throws APIException {
		return this.dao.getDrugDetailOfPatient(isdpd);
	}

	public InventoryDrug getDrugByName(String name) throws APIException {
		return this.dao.getDrugByName(name);
	}

	public List<Concept> getDrugFrequency() throws APIException {
		return this.dao.getDrugFrequency();
	}

	public InventoryDrugFormulation getDrugFormulationById(Integer id) throws APIException {
		return this.dao.getDrugFormulationById(id);
	}
}
