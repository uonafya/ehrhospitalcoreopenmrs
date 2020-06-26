package org.openmrs.module.hospitalcore;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.module.hospitalcore.model.Answer;
import org.openmrs.module.hospitalcore.model.Department;
import org.openmrs.module.hospitalcore.model.DepartmentConcept;
import org.openmrs.module.hospitalcore.model.Examination;
import org.openmrs.module.hospitalcore.model.InventoryDrug;
import org.openmrs.module.hospitalcore.model.OpdDrugOrder;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.Question;
import org.openmrs.module.hospitalcore.model.Symptom;
import org.openmrs.module.hospitalcore.model.TriagePatientData;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PatientDashboardService {
	List<Concept> searchSymptom(String paramString) throws APIException;

	List<Concept> searchExamination(String paramString) throws APIException;

	List<Concept> searchDiagnosis(String paramString) throws APIException;

	List<Concept> searchProcedure(String paramString) throws APIException;

	List<Order> getOrders(List<Concept> paramList, Patient paramPatient, Location paramLocation, Date paramDate) throws APIException;

	List<Concept> getAnswers(Concept paramConcept) throws APIException;

	List<Encounter> getEncounter(Patient paramPatient, Location paramLocation, EncounterType paramEncounterType, String paramString) throws APIException;

	Set<Concept> listDiagnosisByOpd(Integer paramInteger) throws APIException;

	Department createDepartment(Department paramDepartment) throws APIException;

	void removeDepartment(Department paramDepartment) throws APIException;

	Department getDepartmentById(Integer paramInteger) throws APIException;

	Department getDepartmentByWard(Integer paramInteger) throws APIException;

	List<Department> listDepartment(Boolean paramBoolean) throws APIException;

	Department getDepartmentByName(String paramString) throws APIException;

	DepartmentConcept createDepartmentConcept(DepartmentConcept paramDepartmentConcept) throws APIException;

	DepartmentConcept getByDepartmentAndConcept(Integer paramInteger1, Integer paramInteger2) throws APIException;

	DepartmentConcept getById(Integer paramInteger) throws APIException;

	void removeDepartmentConcept(DepartmentConcept paramDepartmentConcept) throws APIException;

	List<DepartmentConcept> listByDepartment(Integer paramInteger1, Integer paramInteger2) throws APIException;

	List<Concept> listByDepartmentByWard(Integer paramInteger1, Integer paramInteger2) throws APIException;

	List<Concept> searchInvestigation(String paramString) throws APIException;

	OpdTestOrder saveOrUpdateOpdOrder(OpdTestOrder paramOpdTestOrder) throws APIException;

	List<Concept> searchDrug(String paramString) throws APIException;

	OpdDrugOrder saveOrUpdateOpdDrugOrder(OpdDrugOrder paramOpdDrugOrder) throws APIException;

	List<InventoryDrug> findDrug(String paramString) throws APIException;

	Symptom saveSymptom(Symptom paramSymptom) throws APIException;

	Question saveQuestion(Question paramQuestion) throws APIException;

	Answer saveAnswer(Answer paramAnswer) throws APIException;

	OpdPatientQueueLog getOpdPatientQueueLog(Encounter paramEncounter) throws APIException;

	List<Symptom> getSymptom(Encounter paramEncounter) throws APIException;

	List<Question> getQuestion(Symptom paramSymptom) throws APIException;

	Answer getAnswer(Question paramQuestion) throws APIException;

	List<OpdDrugOrder> getOpdDrugOrder(Encounter paramEncounter) throws APIException;

	TriagePatientData getTriagePatientData(Integer paramInteger) throws APIException;

	TriagePatientData getTriagePatientDataFromEncounter(Integer paramInteger) throws APIException;

	Examination saveExamination(Examination paramExamination) throws APIException;

	List<Examination> getExamination(Encounter paramEncounter) throws APIException;

	List<Question> getQuestion(Examination paramExamination) throws APIException;

	List<Concept> searchUnderLinedCondition(String paramString) throws APIException;

	List<Concept> searchSigns(String paramString) throws APIException;

	List<Concept> searchDifferentialDiagnosis(String paramString) throws APIException;

	List<Concept> searchWorkingDiagnosis(String paramString) throws APIException;
}
