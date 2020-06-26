package org.openmrs.module.hospitalcore.db;

import java.util.Date;
import java.util.List;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
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

public interface PatientDashboardDAO {
	List<Order> getOrders(List<Concept> paramList, Patient paramPatient, Location paramLocation, Date paramDate) throws DAOException;

	List<Concept> searchConceptsByNameAndClass(String paramString, ConceptClass paramConceptClass) throws DAOException;

	List<Encounter> getEncounter(Patient paramPatient, Location paramLocation, EncounterType paramEncounterType, String paramString) throws DAOException;

	Department createDepartment(Department paramDepartment) throws DAOException;

	void removeDepartment(Department paramDepartment) throws DAOException;

	Department getDepartmentById(Integer paramInteger) throws DAOException;

	Department getDepartmentByWard(Integer paramInteger) throws DAOException;

	List<Department> listDepartment(Boolean paramBoolean) throws DAOException;

	Department getDepartmentByName(String paramString) throws DAOException;

	DepartmentConcept createDepartmentConcept(DepartmentConcept paramDepartmentConcept) throws DAOException;

	DepartmentConcept getByDepartmentAndConcept(Integer paramInteger1, Integer paramInteger2) throws DAOException;

	DepartmentConcept getById(Integer paramInteger) throws DAOException;

	void removeDepartmentConcept(DepartmentConcept paramDepartmentConcept) throws DAOException;

	List<DepartmentConcept> listByDepartment(Integer paramInteger1, Integer paramInteger2) throws DAOException;

	List<Concept> listByDepartmentByWard(Integer paramInteger1, Integer paramInteger2) throws DAOException;

	List<Concept> searchInvestigation(String paramString) throws DAOException;

	OpdTestOrder saveOrUpdateOpdOrder(OpdTestOrder paramOpdTestOrder) throws DAOException;

	OpdDrugOrder saveOrUpdateOpdDrugOrder(OpdDrugOrder paramOpdDrugOrder) throws DAOException;

	List<InventoryDrug> findDrug(String paramString) throws DAOException;

	Symptom saveSymptom(Symptom paramSymptom) throws DAOException;

	Question saveQuestion(Question paramQuestion) throws DAOException;

	Answer saveAnswer(Answer paramAnswer) throws DAOException;

	OpdPatientQueueLog getOpdPatientQueueLog(Encounter paramEncounter) throws DAOException;

	List<Symptom> getSymptom(Encounter paramEncounter) throws DAOException;

	List<Question> getQuestion(Symptom paramSymptom) throws DAOException;

	Answer getAnswer(Question paramQuestion) throws DAOException;

	List<OpdDrugOrder> getOpdDrugOrder(Encounter paramEncounter) throws DAOException;

	TriagePatientData getTriagePatientData(Integer paramInteger) throws DAOException;

	TriagePatientData getTriagePatientDataFromEncounter(Integer paramInteger) throws DAOException;

	Examination saveExamination(Examination paramExamination) throws DAOException;

	List<Examination> getExamination(Encounter paramEncounter) throws DAOException;

	List<Question> getQuestion(Examination paramExamination) throws DAOException;
}
