package org.openmrs.module.hospitalcore;

import java.util.List;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.RadiologyTest;

public interface RadiologyCommonService extends OpenmrsService {
	List<RadiologyTest> getAllTest(Patient paramPatient) throws APIException;

	ConceptAnswer getConceptAnswer(Concept paramConcept) throws APIException;

	List<RadiologyTest> getAllTest(Patient paramPatient, String paramString) throws APIException;

	List<RadiologyTest> getAllTest(Patient paramPatient, String paramString, Concept paramConcept) throws APIException;

	List<RadiologyTest> getAllSubTest(Patient paramPatient, String paramString, Concept paramConcept) throws APIException;
}
