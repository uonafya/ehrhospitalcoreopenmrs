package org.openmrs.module.hospitalcore.db;

import java.util.List;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.RadiologyTest;

public interface RadiologyCommonDAO {
	List<RadiologyTest> getAllTest(Patient paramPatient) throws DAOException;

	ConceptAnswer getConceptAnswer(Concept paramConcept) throws DAOException;

	List<RadiologyTest> getAllTest(Patient paramPatient, String paramString) throws DAOException;

	List<RadiologyTest> getAllTest(Patient paramPatient, String paramString, Concept paramConcept) throws DAOException;

	List<RadiologyTest> getAllSubTest(Patient paramPatient, String paramString, Concept paramConcept) throws DAOException;
}
