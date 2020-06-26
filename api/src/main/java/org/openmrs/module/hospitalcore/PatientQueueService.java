package org.openmrs.module.hospitalcore;

import java.util.List;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.OpdPatientQueue;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.PatientDrugHistory;
import org.openmrs.module.hospitalcore.model.PatientFamilyHistory;
import org.openmrs.module.hospitalcore.model.PatientMedicalHistory;
import org.openmrs.module.hospitalcore.model.PatientPersonalHistory;
import org.openmrs.module.hospitalcore.model.TriagePatientData;
import org.openmrs.module.hospitalcore.model.TriagePatientQueue;
import org.openmrs.module.hospitalcore.model.TriagePatientQueueLog;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PatientQueueService extends OpenmrsService {
	OpdPatientQueue saveOpdPatientQueue(OpdPatientQueue paramOpdPatientQueue) throws APIException;

	OpdPatientQueue updateOpdPatientQueue(Integer paramInteger, String paramString) throws APIException;

	OpdPatientQueue getOpdPatientQueueById(Integer paramInteger) throws APIException;

	PatientMedicalHistory getPatientHistoryByPatientId(Integer paramInteger) throws APIException;

	PatientDrugHistory getPatientDrugHistoryByPatientId(Integer paramInteger) throws APIException;

	PatientFamilyHistory getPatientFamilyHistoryByPatientId(Integer paramInteger) throws APIException;

	PatientPersonalHistory getPatientPersonalHistoryByPatientId(Integer paramInteger) throws APIException;

	void deleteOpdPatientQueue(OpdPatientQueue paramOpdPatientQueue) throws APIException;

	List<OpdPatientQueue> listOpdPatientQueue(String paramString1, Integer paramInteger, String paramString2, int paramInt1, int paramInt2) throws APIException;

	List<TriagePatientQueue> listTriagePatientQueue(String paramString1, Integer paramInteger, String paramString2, int paramInt1, int paramInt2) throws APIException;

	Integer countOpdPatientQueue(String paramString1, String paramString2, Integer paramInteger, String paramString3) throws APIException;

	OpdPatientQueueLog saveOpdPatientQueueLog(OpdPatientQueueLog paramOpdPatientQueueLog) throws APIException;

	OpdPatientQueueLog getOpdPatientQueueLogById(Integer paramInteger) throws APIException;

	List<OpdPatientQueue> getAllPatientInQueue() throws APIException;

	OpdPatientQueueLog copyTo(OpdPatientQueue paramOpdPatientQueue) throws APIException;

	OpdPatientQueue getOpdPatientQueue(String paramString, Integer paramInteger) throws APIException;

	TriagePatientQueue getTriagePatientQueue(String paramString, Integer paramInteger) throws APIException;

	TriagePatientQueue getTriagePatientQueueById(Integer paramInteger) throws APIException;

	TriagePatientQueue saveTriagePatientQueue(TriagePatientQueue paramTriagePatientQueue) throws APIException;

	TriagePatientQueueLog saveTriagePatientQueueLog(TriagePatientQueueLog paramTriagePatientQueueLog) throws APIException;

	void deleteTriagePatientQueue(TriagePatientQueue paramTriagePatientQueue) throws APIException;

	TriagePatientData saveTriagePatientData(TriagePatientData paramTriagePatientData) throws APIException;

	PatientMedicalHistory savePatientMedicalHistory(PatientMedicalHistory paramPatientMedicalHistory) throws APIException;

	PatientDrugHistory savePatientDrugHistory(PatientDrugHistory paramPatientDrugHistory) throws APIException;

	PatientFamilyHistory savePatientFamilyHistory(PatientFamilyHistory paramPatientFamilyHistory) throws APIException;

	PatientPersonalHistory savePatientPersonalHistory(PatientPersonalHistory paramPatientPersonalHistory) throws APIException;

	ConceptAnswer getConceptAnswer(Concept paramConcept) throws APIException;

	Encounter getLastOPDEncounter(Patient paramPatient) throws APIException;

	OpdPatientQueueLog getOpdPatientQueueLogByEncounter(Encounter paramEncounter) throws APIException;

	Obs getObservationByPersonConceptAndEncounter(Person paramPerson, Concept paramConcept, Encounter paramEncounter) throws APIException;

	OpdPatientQueueLog getOpdPatientQueueLog(String paramString, Integer paramInteger) throws APIException;

	void updatePatientHistoryByPatientId(PatientMedicalHistory paramPatientMedicalHistory) throws APIException;

	void updatePatientDrugHistoryByPatientId(PatientDrugHistory paramPatientDrugHistory) throws APIException;

	void updatePatientFamilyHistoryByPatientId(PatientFamilyHistory paramPatientFamilyHistory) throws APIException;

	void updatePatientPersonalHistoryByPatientId(PatientPersonalHistory paramPatientPersonalHistory) throws APIException;

	List<Obs> getAllDiagnosis(Integer paramInteger) throws APIException;

	List<Obs> getAllSymptom(Integer paramInteger) throws APIException;

	TriagePatientData updateTriagePatientData(TriagePatientData paramTriagePatientData) throws APIException;

	List<Obs> getAllExamination(Integer paramInteger) throws APIException;

	List<Obs> getAllUnderlinedCondition(Integer paramInteger) throws APIException;

	List<Obs> getAllSigns(Integer paramInteger) throws APIException;

	List<Obs> getAllDifferentialDiagnosis(Integer paramInteger) throws APIException;

	List<Obs> getAllWorkingDiagnosis(Integer paramInteger) throws APIException;
}
