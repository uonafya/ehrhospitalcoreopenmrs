package org.openmrs.module.hospitalcore.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptClass;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientDashboardService;
import org.openmrs.module.hospitalcore.db.PatientDashboardDAO;
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

public class PatientDashboardServiceImpl implements PatientDashboardService {
	protected PatientDashboardDAO dao;

	public void setDao(PatientDashboardDAO dao) {
		this.dao = dao;
	}

	public List<Concept> searchSymptom(String text) throws APIException {
		ConceptClass cc = Context.getConceptService().getConceptClassByName("Symptom");
		return this.dao.searchConceptsByNameAndClass(text, cc);
	}

	public List<Concept> searchExamination(String text) throws APIException {
		ConceptClass cc = Context.getConceptService().getConceptClassByName("EXAMINATION");
		return this.dao.searchConceptsByNameAndClass(text, cc);
	}

	public List<Concept> searchDiagnosis(String text) throws APIException {
		ConceptClass cc = Context.getConceptService().getConceptClassByName("Diagnosis");
		return this.dao.searchConceptsByNameAndClass(text, cc);
	}

	public List<Concept> getAnswers(Concept labSet) throws APIException {
		List<Concept> conceptList = new ArrayList<Concept>();
		if (labSet.getDatatype().isCoded() &&
				!labSet.getAnswers().isEmpty()) {
			List<ConceptAnswer> conceptAnswers = new ArrayList<ConceptAnswer>(labSet.getAnswers());
			for (int count = 0; count < conceptAnswers.size(); count++) {
				Concept conceptAnsName = ((ConceptAnswer)conceptAnswers.get(count)).getAnswerConcept();
				conceptList.add(conceptAnsName);
			}
		}
		return conceptList;
	}

	public List<Order> getOrders(List<Concept> concepts, Patient patient, Location location, Date orderStartDate) throws APIException {
		return this.dao.getOrders(concepts, patient, location, orderStartDate);
	}

	public List<Concept> searchProcedure(String text) throws APIException {
		ConceptClass cc = Context.getConceptService().getConceptClassByName("Procedure");
		return this.dao.searchConceptsByNameAndClass(text, cc);
	}

	public List<Encounter> getEncounter(Patient p, Location loc, EncounterType encType, String date) {
		return this.dao.getEncounter(p, loc, encType, date);
	}

	public Set<Concept> listDiagnosisByOpd(Integer opdConcept) throws APIException {
		Set<Concept> listDiagnosis = new HashSet<Concept>();
		Concept concept = Context.getConceptService().getConcept(opdConcept);
		if (concept != null && concept.getAnswers() != null && !concept.getAnswers().isEmpty()) {
			Concept diagnosisC = null;
			for (ConceptAnswer c : concept.getAnswers()) {
				if ("diagnosis".equalsIgnoreCase(c.getAnswerConcept().getConceptClass().getName())) {
					diagnosisC = c.getAnswerConcept();
					break;
				}
			}
			if (diagnosisC == null)
				return null;
			if (diagnosisC.getAnswers() != null && !diagnosisC.getAnswers().isEmpty())
				for (ConceptAnswer c : diagnosisC.getAnswers()) {
					if (c.getAnswerConcept() != null && c.getAnswerConcept().getAnswers() != null && !c.getAnswerConcept().getAnswers().isEmpty()) {
						for (ConceptAnswer cInner : c.getAnswerConcept().getAnswers()) {
							if (cInner.getAnswerConcept().getConceptClass() != null && "diagnosis".equalsIgnoreCase(cInner.getAnswerConcept().getConceptClass().getName()))
								listDiagnosis.add(cInner.getAnswerConcept());
						}
						continue;
					}
					if (c.getAnswerConcept().getConceptClass() != null && "diagnosis".equalsIgnoreCase(c.getAnswerConcept().getConceptClass().getName()))
						listDiagnosis.add(c.getAnswerConcept());
				}
		}
		return listDiagnosis;
	}

	public Department createDepartment(Department department) throws APIException {
		return this.dao.createDepartment(department);
	}

	public void removeDepartment(Department department) throws APIException {
		this.dao.removeDepartment(department);
	}

	public Department getDepartmentById(Integer id) throws APIException {
		return this.dao.getDepartmentById(id);
	}

	public Department getDepartmentByWard(Integer wardId) throws APIException {
		return this.dao.getDepartmentByWard(wardId);
	}

	public List<Department> listDepartment(Boolean retired) throws APIException {
		return this.dao.listDepartment(retired);
	}

	public Department getDepartmentByName(String name) throws APIException {
		return this.dao.getDepartmentByName(name);
	}

	public DepartmentConcept createDepartmentConcept(DepartmentConcept departmentConcept) throws APIException {
		return this.dao.createDepartmentConcept(departmentConcept);
	}

	public DepartmentConcept getByDepartmentAndConcept(Integer departmentId, Integer conceptId) throws APIException {
		return this.dao.getByDepartmentAndConcept(departmentId, conceptId);
	}

	public DepartmentConcept getById(Integer id) throws APIException {
		return this.dao.getById(id);
	}

	public void removeDepartmentConcept(DepartmentConcept departmentConcept) throws APIException {
		this.dao.removeDepartmentConcept(departmentConcept);
	}

	public List<DepartmentConcept> listByDepartment(Integer departmentId, Integer typeConcept) throws APIException {
		return this.dao.listByDepartment(departmentId, typeConcept);
	}

	public List<Concept> listByDepartmentByWard(Integer wardId, Integer typeConcept) throws APIException {
		return this.dao.listByDepartmentByWard(wardId, typeConcept);
	}

	public List<Concept> searchInvestigation(String text) throws APIException {
		return this.dao.searchInvestigation(text);
	}

	public OpdTestOrder saveOrUpdateOpdOrder(OpdTestOrder opdTestOrder) throws APIException {
		return this.dao.saveOrUpdateOpdOrder(opdTestOrder);
	}

	public List<Concept> searchDrug(String text) throws APIException {
		ConceptClass cc = Context.getConceptService().getConceptClassByName("Drug");
		return this.dao.searchConceptsByNameAndClass(text, cc);
	}

	public OpdDrugOrder saveOrUpdateOpdDrugOrder(OpdDrugOrder opdDrugOrder) throws APIException {
		return this.dao.saveOrUpdateOpdDrugOrder(opdDrugOrder);
	}

	public List<InventoryDrug> findDrug(String name) throws APIException {
		return this.dao.findDrug(name);
	}

	public Symptom saveSymptom(Symptom symptom) throws APIException {
		return this.dao.saveSymptom(symptom);
	}

	public Question saveQuestion(Question question) throws APIException {
		return this.dao.saveQuestion(question);
	}

	public Answer saveAnswer(Answer answer) throws APIException {
		return this.dao.saveAnswer(answer);
	}

	public OpdPatientQueueLog getOpdPatientQueueLog(Encounter encounter) {
		return this.dao.getOpdPatientQueueLog(encounter);
	}

	public List<Symptom> getSymptom(Encounter encounter) {
		return this.dao.getSymptom(encounter);
	}

	public List<Question> getQuestion(Symptom symptom) {
		return this.dao.getQuestion(symptom);
	}

	public Answer getAnswer(Question question) {
		return this.dao.getAnswer(question);
	}

	public List<OpdDrugOrder> getOpdDrugOrder(Encounter encounter) {
		return this.dao.getOpdDrugOrder(encounter);
	}

	public TriagePatientData getTriagePatientData(Integer triageDataId) {
		return this.dao.getTriagePatientData(triageDataId);
	}

	public TriagePatientData getTriagePatientDataFromEncounter(Integer encounterOpd) {
		return this.dao.getTriagePatientDataFromEncounter(encounterOpd);
	}

	public Examination saveExamination(Examination examination) throws APIException {
		return this.dao.saveExamination(examination);
	}

	public List<Examination> getExamination(Encounter encounters) throws APIException {
		return this.dao.getExamination(encounters);
	}

	public List<Question> getQuestion(Examination examination) throws APIException {
		return this.dao.getQuestion(examination);
	}

	public List<Concept> searchUnderLinedCondition(String text) throws APIException {
		ConceptClass cc = Context.getConceptService().getConceptClassByName("Diagnosis");
		return this.dao.searchConceptsByNameAndClass(text, cc);
	}

	public List<Concept> searchSigns(String text) throws APIException {
		ConceptClass cc = Context.getConceptService().getConceptClassByName("Diagnosis");
		return this.dao.searchConceptsByNameAndClass(text, cc);
	}

	public List<Concept> searchDifferentialDiagnosis(String text) throws APIException {
		ConceptClass cc = Context.getConceptService().getConceptClassByName("Diagnosis");
		return this.dao.searchConceptsByNameAndClass(text, cc);
	}

	public List<Concept> searchWorkingDiagnosis(String text) throws APIException {
		ConceptClass cc = Context.getConceptService().getConceptClassByName("Diagnosis");
		return this.dao.searchConceptsByNameAndClass(text, cc);
	}
}
