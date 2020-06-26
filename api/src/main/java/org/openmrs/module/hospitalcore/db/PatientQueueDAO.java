package org.openmrs.module.hospitalcore.db;

import java.util.List;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.api.APIException;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.OpdPatientQueue;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.PatientDrugHistory;
import org.openmrs.module.hospitalcore.model.PatientFamilyHistory;
import org.openmrs.module.hospitalcore.model.PatientMedicalHistory;
import org.openmrs.module.hospitalcore.model.PatientPersonalHistory;
import org.openmrs.module.hospitalcore.model.TriagePatientData;
import org.openmrs.module.hospitalcore.model.TriagePatientQueue;
import org.openmrs.module.hospitalcore.model.TriagePatientQueueLog;

public interface PatientQueueDAO {
	OpdPatientQueue saveOpdPatientQueue(OpdPatientQueue paramOpdPatientQueue) throws DAOException;

	OpdPatientQueue updateOpdPatientQueue(Integer paramInteger, String paramString) throws DAOException;

	PatientMedicalHistory getPatientHistoryByPatientId(Integer paramInteger) throws DAOException;

	PatientDrugHistory getPatientDrugHistoryByPatientId(Integer paramInteger) throws DAOException;

	PatientFamilyHistory getPatientFamilyHistoryByPatientId(Integer paramInteger) throws DAOException;

	PatientPersonalHistory getPatientPersonalHistoryByPatientId(Integer paramInteger) throws DAOException;

	OpdPatientQueue getOpdPatientQueueById(Integer paramInteger) throws DAOException;

	void deleteOpdPatientQueue(OpdPatientQueue paramOpdPatientQueue) throws DAOException;

	List<OpdPatientQueue> listOpdPatientQueue(String paramString1, Integer paramInteger, String paramString2, int paramInt1, int paramInt2) throws DAOException;

	List<TriagePatientQueue> listTriagePatientQueue(String paramString1, Integer paramInteger, String paramString2, int paramInt1, int paramInt2) throws DAOException;

	Integer countOpdPatientQueue(String paramString1, String paramString2, Integer paramInteger, String paramString3) throws DAOException;

	OpdPatientQueueLog saveOpdPatientQueueLog(OpdPatientQueueLog paramOpdPatientQueueLog) throws DAOException;

	OpdPatientQueueLog getOpdPatientQueueLogById(Integer paramInteger) throws DAOException;

	List<OpdPatientQueue> getAllPatientInQueue() throws DAOException;

	OpdPatientQueue getOpdPatientQueue(String paramString, Integer paramInteger) throws DAOException;

	TriagePatientQueue getTriagePatientQueue(String paramString, Integer paramInteger) throws DAOException;

	TriagePatientQueue getTriagePatientQueueById(Integer paramInteger) throws APIException;

	TriagePatientQueue saveTriagePatientQueue(TriagePatientQueue paramTriagePatientQueue) throws DAOException;

	TriagePatientQueueLog saveTriagePatientQueueLog(TriagePatientQueueLog paramTriagePatientQueueLog) throws APIException;

	void deleteTriagePatientQueue(TriagePatientQueue paramTriagePatientQueue) throws APIException;

	TriagePatientData saveTriagePatientData(TriagePatientData paramTriagePatientData) throws APIException;

	PatientMedicalHistory savePatientMedicalHistory(PatientMedicalHistory paramPatientMedicalHistory) throws APIException;

	PatientDrugHistory savePatientDrugHistory(PatientDrugHistory paramPatientDrugHistory) throws APIException;

	PatientPersonalHistory savePatientPersonalHistory(PatientPersonalHistory paramPatientPersonalHistory) throws APIException;

	PatientFamilyHistory savePatientFamilyHistory(PatientFamilyHistory paramPatientFamilyHistory) throws APIException;

	ConceptAnswer getConceptAnswer(Concept paramConcept) throws DAOException;

	Encounter getLastOPDEncounter(Patient paramPatient) throws APIException;

	OpdPatientQueueLog getOpdPatientQueueLogByEncounter(Encounter paramEncounter) throws APIException;

	Obs getObservationByPersonConceptAndEncounter(Person paramPerson, Concept paramConcept, Encounter paramEncounter) throws APIException;

	OpdPatientQueueLog getOpdPatientQueueLog(String paramString, Integer paramInteger) throws DAOException;

	void updatePatientHistoryByPatientId(PatientMedicalHistory paramPatientMedicalHistory) throws DAOException;

	void updatePatientDrugHistoryByPatientId(PatientDrugHistory paramPatientDrugHistory) throws DAOException;

	void updatePatientFamilyHistoryByPatientId(PatientFamilyHistory paramPatientFamilyHistory) throws DAOException;

	void updatePatientPersonalHistoryByPatientId(PatientPersonalHistory paramPatientPersonalHistory) throws DAOException;

	List<Obs> getAllDiagnosis(Integer paramInteger) throws DAOException;

	List<Obs> getAllSymptom(Integer paramInteger) throws DAOException;

	List<Obs> getAllExamination(Integer paramInteger) throws DAOException;

	TriagePatientData updateTriagePatientData(TriagePatientData paramTriagePatientData) throws DAOException;

	List<Obs> getAllUnderlinedCondition(Integer paramInteger) throws DAOException;

	List<Obs> getAllSigns(Integer paramInteger) throws DAOException;

	List<Obs> getAllDifferentialDiagnosis(Integer paramInteger) throws DAOException;

	List<Obs> getAllWorkingDiagnosis(Integer paramInteger) throws DAOException;
}
