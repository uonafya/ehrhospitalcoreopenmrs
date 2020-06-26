package org.openmrs.module.hospitalcore.impl;

import java.util.List;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.PatientQueueService;
import org.openmrs.module.hospitalcore.db.PatientQueueDAO;
import org.openmrs.module.hospitalcore.model.OpdPatientQueue;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.PatientDrugHistory;
import org.openmrs.module.hospitalcore.model.PatientFamilyHistory;
import org.openmrs.module.hospitalcore.model.PatientMedicalHistory;
import org.openmrs.module.hospitalcore.model.PatientPersonalHistory;
import org.openmrs.module.hospitalcore.model.TriagePatientData;
import org.openmrs.module.hospitalcore.model.TriagePatientQueue;
import org.openmrs.module.hospitalcore.model.TriagePatientQueueLog;

public class PatientQueueServiceImpl extends BaseOpenmrsService implements PatientQueueService {
	protected PatientQueueDAO dao;

	public PatientQueueDAO getDao() {
		return this.dao;
	}

	public void setDao(PatientQueueDAO dao) {
		this.dao = dao;
	}

	public OpdPatientQueue saveOpdPatientQueue(OpdPatientQueue opdPatientQueue) throws APIException {
		return this.dao.saveOpdPatientQueue(opdPatientQueue);
	}

	public OpdPatientQueue updateOpdPatientQueue(Integer id, String status) throws APIException {
		return this.dao.updateOpdPatientQueue(id, status);
	}

	public OpdPatientQueue getOpdPatientQueueById(Integer id) throws APIException {
		return this.dao.getOpdPatientQueueById(id);
	}

	public PatientMedicalHistory getPatientHistoryByPatientId(Integer id) throws APIException {
		return this.dao.getPatientHistoryByPatientId(id);
	}

	public PatientDrugHistory getPatientDrugHistoryByPatientId(Integer id) throws APIException {
		return this.dao.getPatientDrugHistoryByPatientId(id);
	}

	public PatientFamilyHistory getPatientFamilyHistoryByPatientId(Integer id) throws APIException {
		return this.dao.getPatientFamilyHistoryByPatientId(id);
	}

	public PatientPersonalHistory getPatientPersonalHistoryByPatientId(Integer id) throws APIException {
		return this.dao.getPatientPersonalHistoryByPatientId(id);
	}

	public void deleteOpdPatientQueue(OpdPatientQueue opdPatientQueue) throws APIException {
		this.dao.deleteOpdPatientQueue(opdPatientQueue);
	}

	public List<OpdPatientQueue> listOpdPatientQueue(String patientName, Integer referralConceptId, String status, int min, int max) throws APIException {
		return this.dao.listOpdPatientQueue(patientName, referralConceptId, status, min, max);
	}

	public List<TriagePatientQueue> listTriagePatientQueue(String patientName, Integer referralConceptId, String status, int min, int max) throws APIException {
		return this.dao.listTriagePatientQueue(patientName, referralConceptId, status, min, max);
	}

	public Integer countOpdPatientQueue(String patientName, String searchType, Integer referralConceptId, String status) throws APIException {
		return this.dao.countOpdPatientQueue(patientName, searchType, referralConceptId, status);
	}

	public OpdPatientQueueLog saveOpdPatientQueueLog(OpdPatientQueueLog opdPatientQueueLog) throws APIException {
		return this.dao.saveOpdPatientQueueLog(opdPatientQueueLog);
	}

	public OpdPatientQueueLog getOpdPatientQueueLogById(Integer id) throws APIException {
		return this.dao.getOpdPatientQueueLogById(id);
	}

	public OpdPatientQueueLog copyTo(OpdPatientQueue opdPatientQueue) {
		OpdPatientQueueLog opdPatientQueueLog = new OpdPatientQueueLog();
		opdPatientQueueLog.setBirthDate(opdPatientQueue.getBirthDate());
		opdPatientQueueLog.setCreatedOn(opdPatientQueue.getCreatedOn());
		opdPatientQueueLog.setOpdConcept(opdPatientQueue.getOpdConcept());
		opdPatientQueueLog.setOpdConceptName(opdPatientQueue.getOpdConceptName());
		opdPatientQueueLog.setPatientIdentifier(opdPatientQueue.getPatientIdentifier());
		opdPatientQueueLog.setPatient(opdPatientQueue.getPatient());
		opdPatientQueueLog.setPatientName(opdPatientQueue.getPatientName());
		opdPatientQueueLog.setReferralConcept(opdPatientQueue.getReferralConcept());
		opdPatientQueueLog.setReferralConceptName(opdPatientQueue.getReferralConceptName());
		opdPatientQueueLog.setSex(opdPatientQueue.getSex());
		opdPatientQueueLog.setStatus(opdPatientQueue.getStatus());
		opdPatientQueueLog.setUser(opdPatientQueue.getUser());
		return opdPatientQueueLog;
	}

	public OpdPatientQueue getOpdPatientQueue(String patientIdentifier, Integer opdConceptId) throws APIException {
		return this.dao.getOpdPatientQueue(patientIdentifier, opdConceptId);
	}

	public TriagePatientQueue getTriagePatientQueue(String patientIdentifier, Integer triageConceptId) throws APIException {
		return this.dao.getTriagePatientQueue(patientIdentifier, triageConceptId);
	}

	public TriagePatientQueue getTriagePatientQueueById(Integer id) throws APIException {
		return this.dao.getTriagePatientQueueById(id);
	}

	public TriagePatientQueue saveTriagePatientQueue(TriagePatientQueue triagePatientQueue) throws APIException {
		return this.dao.saveTriagePatientQueue(triagePatientQueue);
	}

	public TriagePatientQueueLog saveTriagePatientQueueLog(TriagePatientQueueLog triagePatientQueueLog) throws APIException {
		return this.dao.saveTriagePatientQueueLog(triagePatientQueueLog);
	}

	public void deleteTriagePatientQueue(TriagePatientQueue triagePatientQueue) throws APIException {
		this.dao.deleteTriagePatientQueue(triagePatientQueue);
	}

	public TriagePatientData saveTriagePatientData(TriagePatientData triagePatientData) throws APIException {
		return this.dao.saveTriagePatientData(triagePatientData);
	}

	public List<OpdPatientQueue> getAllPatientInQueue() throws APIException {
		return this.dao.getAllPatientInQueue();
	}

	public ConceptAnswer getConceptAnswer(Concept answerConcept) throws APIException {
		return this.dao.getConceptAnswer(answerConcept);
	}

	public Encounter getLastOPDEncounter(Patient patient) {
		return this.dao.getLastOPDEncounter(patient);
	}

	public OpdPatientQueueLog getOpdPatientQueueLogByEncounter(Encounter encounter) {
		return this.dao.getOpdPatientQueueLogByEncounter(encounter);
	}

	public Obs getObservationByPersonConceptAndEncounter(Person person, Concept concept, Encounter encounter) {
		return this.dao.getObservationByPersonConceptAndEncounter(person, concept, encounter);
	}

	public OpdPatientQueueLog getOpdPatientQueueLog(String patientIdentifier, Integer opdConceptId) throws APIException {
		return this.dao.getOpdPatientQueueLog(patientIdentifier, opdConceptId);
	}

	public PatientMedicalHistory savePatientMedicalHistory(PatientMedicalHistory patientMedicalHistory) throws APIException {
		return this.dao.savePatientMedicalHistory(patientMedicalHistory);
	}

	public PatientDrugHistory savePatientDrugHistory(PatientDrugHistory patientDrugHistory) throws APIException {
		return this.dao.savePatientDrugHistory(patientDrugHistory);
	}

	public PatientPersonalHistory savePatientPersonalHistory(PatientPersonalHistory patientPersonalHistory) throws APIException {
		return this.dao.savePatientPersonalHistory(patientPersonalHistory);
	}

	public PatientFamilyHistory savePatientFamilyHistory(PatientFamilyHistory patientFamilyHistory) throws APIException {
		return this.dao.savePatientFamilyHistory(patientFamilyHistory);
	}

	public void updatePatientHistoryByPatientId(PatientMedicalHistory patientMedicalHistory) throws APIException {
		this.dao.updatePatientHistoryByPatientId(patientMedicalHistory);
	}

	public void updatePatientDrugHistoryByPatientId(PatientDrugHistory patientDrugHistory) throws APIException {
		this.dao.updatePatientDrugHistoryByPatientId(patientDrugHistory);
	}

	public void updatePatientFamilyHistoryByPatientId(PatientFamilyHistory patientFamilyHistory) throws APIException {
		this.dao.updatePatientFamilyHistoryByPatientId(patientFamilyHistory);
	}

	public void updatePatientPersonalHistoryByPatientId(PatientPersonalHistory patientPersonalHistory) throws APIException {
		this.dao.updatePatientPersonalHistoryByPatientId(patientPersonalHistory);
	}

	public List<Obs> getAllDiagnosis(Integer personId) throws APIException {
		return this.dao.getAllDiagnosis(personId);
	}

	public List<Obs> getAllSymptom(Integer personId) throws APIException {
		return this.dao.getAllSymptom(personId);
	}

	public TriagePatientData updateTriagePatientData(TriagePatientData triagePatientData) throws APIException {
		return this.dao.updateTriagePatientData(triagePatientData);
	}

	public List<Obs> getAllExamination(Integer personId) throws APIException {
		return this.dao.getAllExamination(personId);
	}

	public List<Obs> getAllUnderlinedCondition(Integer personId) throws APIException {
		return this.dao.getAllUnderlinedCondition(personId);
	}

	public List<Obs> getAllSigns(Integer personId) throws APIException {
		return this.dao.getAllSigns(personId);
	}

	public List<Obs> getAllDifferentialDiagnosis(Integer personId) throws APIException {
		return this.dao.getAllDifferentialDiagnosis(personId);
	}

	public List<Obs> getAllWorkingDiagnosis(Integer personId) throws APIException {
		return this.dao.getAllWorkingDiagnosis(personId);
	}
}
