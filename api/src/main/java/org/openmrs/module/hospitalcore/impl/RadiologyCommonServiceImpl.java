package org.openmrs.module.hospitalcore.impl;

import java.util.List;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.RadiologyCommonService;
import org.openmrs.module.hospitalcore.db.RadiologyCommonDAO;
import org.openmrs.module.hospitalcore.model.RadiologyTest;

public class RadiologyCommonServiceImpl extends BaseOpenmrsService implements RadiologyCommonService {
	protected RadiologyCommonDAO dao;

	public void setDao(RadiologyCommonDAO dao) {
		this.dao = dao;
	}

	public List<RadiologyTest> getAllTest(Patient patient) throws APIException {
		return this.dao.getAllTest(patient);
	}

	public ConceptAnswer getConceptAnswer(Concept concept) throws APIException {
		return this.dao.getConceptAnswer(concept);
	}

	public List<RadiologyTest> getAllTest(Patient patient, String date) throws APIException {
		return this.dao.getAllTest(patient, date);
	}

	public List<RadiologyTest> getAllTest(Patient patient, String date, Concept concept) throws APIException {
		return this.dao.getAllTest(patient, date, concept);
	}

	public List<RadiologyTest> getAllSubTest(Patient patient, String date, Concept concept) throws APIException {
		return this.dao.getAllSubTest(patient, date, concept);
	}
}
