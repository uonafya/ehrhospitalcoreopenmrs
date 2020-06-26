package org.openmrs.module.hospitalcore.db.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
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

public class HibernatePatientQueueDAO implements PatientQueueDAO {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	SimpleDateFormat formatterExt = new SimpleDateFormat("dd/MM/yyyy");

	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public OpdPatientQueue saveOpdPatientQueue(OpdPatientQueue opdPatientQueue) throws DAOException {
		return (OpdPatientQueue)this.sessionFactory.getCurrentSession().merge(opdPatientQueue);
	}

	public OpdPatientQueue updateOpdPatientQueue(Integer id, String status) throws DAOException {
		OpdPatientQueue opdPatientQueue = getOpdPatientQueueById(id);
		opdPatientQueue.setStatus(status);
		return (OpdPatientQueue)this.sessionFactory.getCurrentSession().merge(opdPatientQueue);
	}

	public OpdPatientQueue getOpdPatientQueueById(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdPatientQueue.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		OpdPatientQueue opdPatientQueue = (OpdPatientQueue)criteria.uniqueResult();
		return opdPatientQueue;
	}

	public PatientMedicalHistory getPatientHistoryByPatientId(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientMedicalHistory.class);
		criteria.add((Criterion)Restrictions.eq("patientId", id));
		PatientMedicalHistory patientMedicalHistory = (PatientMedicalHistory)criteria.uniqueResult();
		return patientMedicalHistory;
	}

	public PatientDrugHistory getPatientDrugHistoryByPatientId(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientDrugHistory.class);
		criteria.add((Criterion)Restrictions.eq("patientId", id));
		PatientDrugHistory patientDrugHistory = (PatientDrugHistory)criteria.uniqueResult();
		return patientDrugHistory;
	}

	public PatientFamilyHistory getPatientFamilyHistoryByPatientId(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientFamilyHistory.class);
		criteria.add((Criterion)Restrictions.eq("patientId", id));
		PatientFamilyHistory patientFamilyHistory = (PatientFamilyHistory)criteria.uniqueResult();
		return patientFamilyHistory;
	}

	public PatientPersonalHistory getPatientPersonalHistoryByPatientId(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientPersonalHistory.class);
		criteria.add((Criterion)Restrictions.eq("patientId", id));
		PatientPersonalHistory patientPersonalHistory = (PatientPersonalHistory)criteria.uniqueResult();
		return patientPersonalHistory;
	}

	public OpdPatientQueue getOpdPatientQueue(String patientIdentifier, Integer opdConceptId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdPatientQueue.class, "queue").createAlias("queue.opdConcept", "opdConcept");
		criteria.add((Criterion)Restrictions.eq("queue.patientIdentifier", patientIdentifier));
		criteria.add((Criterion)Restrictions.eq("opdConcept.conceptId", opdConceptId));
		String date = this.formatterExt.format(new Date());
		String startFromDate = date + " 00:00:00";
		String endFromDate = date + " 23:59:59";
		try {
			criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("queue.createdOn", this.formatter.parse(startFromDate)), (Criterion)Restrictions.le("queue.createdOn", this.formatter.parse(endFromDate))));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.addOrder(Order.desc("queue.createdOn"));
		List<OpdPatientQueue> list = criteria.list();
		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}

	public void deleteOpdPatientQueue(OpdPatientQueue opdPatientQueue) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(opdPatientQueue);
	}

	public List<OpdPatientQueue> listOpdPatientQueue(String searchText, Integer conceptId, String status, int min, int max) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdPatientQueue.class, "opdPatientQueue");
		if (!StringUtils.isBlank(searchText))
			criteria.add((Criterion)Restrictions.or((Criterion)Restrictions.like("opdPatientQueue.patientIdentifier", "%" + searchText + "%"), (Criterion)Restrictions.like("opdPatientQueue.patientName", "%" + searchText + "%")));
		if (conceptId != null && conceptId.intValue() > 0) {
			criteria.createAlias("opdPatientQueue.opdConcept", "opdConcept");
			criteria.add((Criterion)Restrictions.eq("opdConcept.conceptId", conceptId));
		}
		if (!StringUtils.isBlank(status))
			criteria.add((Criterion)Restrictions.eq("opdPatientQueue.status", status));
		String date = this.formatterExt.format(new Date());
		String startFromDate = date + " 00:00:00";
		String endFromDate = date + " 23:59:59";
		try {
			criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("opdPatientQueue.createdOn", this.formatter.parse(startFromDate)), (Criterion)Restrictions.le("opdPatientQueue.createdOn", this.formatter.parse(endFromDate))));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.addOrder(Order.asc("opdPatientQueue.createdOn"));
		if (max > 0)
			criteria.setFirstResult(min).setMaxResults(max);
		List<OpdPatientQueue> list = criteria.list();
		return list;
	}

	public Integer countOpdPatientQueue(String patientName, String searchType, Integer conceptId, String status) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdPatientQueue.class, "opdPatientQueue");
		if (!StringUtils.isBlank(patientName))
			criteria.add((Criterion)Restrictions.like("opdPatientQueue.patientName", "%" + patientName + "%"));
		if (conceptId != null) {
			criteria.createAlias("opdPatientQueue.opdConcept", "opdConcept");
			criteria.add((Criterion)Restrictions.eq("opdConcept.conceptId", conceptId));
		}
		if (!StringUtils.isBlank(status))
			criteria.add((Criterion)Restrictions.eq("opdPatientQueue.status", status));
		String date = this.formatterExt.format(new Date());
		String startFromDate = date + " 00:00:00";
		String endFromDate = date + " 23:59:59";
		try {
			criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("opdPatientQueue.createdOn", this.formatter.parse(startFromDate)), (Criterion)Restrictions.le("opdPatientQueue.createdOn", this.formatter.parse(endFromDate))));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return Integer.valueOf((rs != null) ? rs.intValue() : 0);
	}

	public OpdPatientQueueLog saveOpdPatientQueueLog(OpdPatientQueueLog opdPatientQueueLog) throws DAOException {
		return (OpdPatientQueueLog)this.sessionFactory.getCurrentSession().merge(opdPatientQueueLog);
	}

	public OpdPatientQueueLog getOpdPatientQueueLogById(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdPatientQueueLog.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		OpdPatientQueueLog opdPatientQueueLog = (OpdPatientQueueLog)criteria.uniqueResult();
		return opdPatientQueueLog;
	}

	public List<OpdPatientQueue> getAllPatientInQueue() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdPatientQueue.class, "opdPatientQueue");
		String date = this.formatterExt.format(new Date());
		String startFromDate = date + " 00:00:00";
		try {
			criteria.add((Criterion)Restrictions.lt("opdPatientQueue.createdOn", this.formatter.parse(startFromDate)));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		return criteria.list();
	}

	public TriagePatientQueue saveTriagePatientQueue(TriagePatientQueue triagePatientQueue) throws DAOException {
		return (TriagePatientQueue)this.sessionFactory.getCurrentSession().merge(triagePatientQueue);
	}

	public TriagePatientQueue getTriagePatientQueue(String patientIdentifier, Integer triageConceptId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(TriagePatientQueue.class, "queue").createAlias("queue.triageConcept", "triageConcept");
		criteria.add((Criterion)Restrictions.eq("queue.patientIdentifier", patientIdentifier));
		criteria.add((Criterion)Restrictions.eq("triageConcept.conceptId", triageConceptId));
		String date = this.formatterExt.format(new Date());
		String startFromDate = date + " 00:00:00";
		String endFromDate = date + " 23:59:59";
		try {
			criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("queue.createdOn", this.formatter.parse(startFromDate)), (Criterion)Restrictions.le("queue.createdOn", this.formatter.parse(endFromDate))));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.addOrder(Order.desc("queue.createdOn"));
		List<TriagePatientQueue> list = criteria.list();
		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}

	public TriagePatientQueue getTriagePatientQueueById(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(TriagePatientQueue.class, "queue");
		criteria.add((Criterion)Restrictions.eq("queue.id", id));
		return (TriagePatientQueue)criteria.uniqueResult();
	}

	public List<TriagePatientQueue> listTriagePatientQueue(String searchText, Integer conceptId, String status, int min, int max) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(TriagePatientQueue.class, "triagePatientQueue");
		if (!StringUtils.isBlank(searchText))
			criteria.add((Criterion)Restrictions.or((Criterion)Restrictions.like("triagePatientQueue.patientIdentifier", "%" + searchText + "%"), (Criterion)Restrictions.like("triagePatientQueue.patientName", "%" + searchText + "%")));
		if (conceptId != null && conceptId.intValue() > 0) {
			criteria.createAlias("triagePatientQueue.triageConcept", "triageConcept");
			criteria.add((Criterion)Restrictions.eq("triageConcept.conceptId", conceptId));
		}
		if (!StringUtils.isBlank(status))
			criteria.add((Criterion)Restrictions.eq("triagePatientQueue.status", status));
		String date = this.formatterExt.format(new Date());
		String startFromDate = date + " 00:00:00";
		String endFromDate = date + " 23:59:59";
		try {
			criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("triagePatientQueue.createdOn", this.formatter.parse(startFromDate)), (Criterion)Restrictions.le("triagePatientQueue.createdOn", this.formatter.parse(endFromDate))));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.addOrder(Order.asc("triagePatientQueue.createdOn"));
		if (max > 0)
			criteria.setFirstResult(min).setMaxResults(max);
		List<TriagePatientQueue> list = criteria.list();
		return list;
	}

	public TriagePatientQueueLog saveTriagePatientQueueLog(TriagePatientQueueLog triagePatientQueueLog) throws DAOException {
		return (TriagePatientQueueLog)this.sessionFactory.getCurrentSession().merge(triagePatientQueueLog);
	}

	public void deleteTriagePatientQueue(TriagePatientQueue triagePatientQueue) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(triagePatientQueue);
	}

	public TriagePatientData saveTriagePatientData(TriagePatientData triagePatientData) throws DAOException {
		return (TriagePatientData)this.sessionFactory.getCurrentSession().merge(triagePatientData);
	}

	public PatientMedicalHistory savePatientMedicalHistory(PatientMedicalHistory patientMedicalHistory) throws DAOException {
		return (PatientMedicalHistory)this.sessionFactory.getCurrentSession().merge(patientMedicalHistory);
	}

	public PatientDrugHistory savePatientDrugHistory(PatientDrugHistory patientDrugHistory) throws DAOException {
		return (PatientDrugHistory)this.sessionFactory.getCurrentSession().merge(patientDrugHistory);
	}

	public PatientFamilyHistory savePatientFamilyHistory(PatientFamilyHistory patientFamilyHistory) throws DAOException {
		return (PatientFamilyHistory)this.sessionFactory.getCurrentSession().merge(patientFamilyHistory);
	}

	public PatientPersonalHistory savePatientPersonalHistory(PatientPersonalHistory patientPersonalHistory) throws DAOException {
		return (PatientPersonalHistory)this.sessionFactory.getCurrentSession().merge(patientPersonalHistory);
	}

	public ConceptAnswer getConceptAnswer(Concept answerConcept) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ConceptAnswer.class);
		criteria.add((Criterion)Restrictions.eq("answerConcept", answerConcept));
		return (ConceptAnswer)criteria.uniqueResult();
	}

	public Encounter getLastOPDEncounter(Patient patient) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Encounter.class, "encounter").createAlias("encounter.encounterType", "encounterType");
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		criteria.add((Criterion)Restrictions.eq("encounterType.name", "OPDENCOUNTER"));
		criteria.addOrder(Order.desc("dateCreated"));
		criteria.setMaxResults(1);
		return (Encounter)criteria.uniqueResult();
	}

	public OpdPatientQueueLog getOpdPatientQueueLogByEncounter(Encounter encounter) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdPatientQueueLog.class);
		criteria.add((Criterion)Restrictions.eq("encounter", encounter));
		return (OpdPatientQueueLog)criteria.uniqueResult();
	}

	public Obs getObservationByPersonConceptAndEncounter(Person person, Concept concept, Encounter encounter) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class);
		criteria.add((Criterion)Restrictions.eq("person", person));
		criteria.add((Criterion)Restrictions.eq("concept", concept));
		criteria.add((Criterion)Restrictions.eq("encounter", encounter));
		criteria.addOrder(Order.desc("dateCreated"));
		criteria.setMaxResults(1);
		return (Obs)criteria.uniqueResult();
	}

	public OpdPatientQueueLog getOpdPatientQueueLog(String patientIdentifier, Integer opdConceptId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdPatientQueueLog.class, "queue").createAlias("queue.opdConcept", "opdConcept");
		criteria.add((Criterion)Restrictions.eq("queue.patientIdentifier", patientIdentifier));
		criteria.add((Criterion)Restrictions.eq("queue.visitOutCome", "admit"));
		criteria.addOrder(Order.desc("queue.createdOn"));
		criteria.setMaxResults(1);
		return (OpdPatientQueueLog)criteria.uniqueResult();
	}

	public void updatePatientHistoryByPatientId(PatientMedicalHistory patientMedicalHistory) throws DAOException {
		this.sessionFactory.getCurrentSession().merge(patientMedicalHistory);
	}

	public void updatePatientDrugHistoryByPatientId(PatientDrugHistory patientDrugHistory) throws DAOException {
		this.sessionFactory.getCurrentSession().merge(patientDrugHistory);
	}

	public void updatePatientFamilyHistoryByPatientId(PatientFamilyHistory patientFamilyHistory) throws DAOException {
		this.sessionFactory.getCurrentSession().merge(patientFamilyHistory);
	}

	public void updatePatientPersonalHistoryByPatientId(PatientPersonalHistory patientPersonalHistory) throws DAOException {
		this.sessionFactory.getCurrentSession().merge(patientPersonalHistory);
	}

	public List<Obs> getAllDiagnosis(Integer personId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class, "obs");
		String toDdate = this.formatter1.format(new Date());
		Date date1 = new Date();
		Date oldDate = new Date(date1.getTime() - TimeUnit.HOURS.toMillis(24L));
		String fromDate = this.formatter1.format(oldDate);
		try {
			criteria.add((Criterion)Restrictions.lt("obs.obsDatetime", this.formatter1.parse(toDdate)));
			criteria.add((Criterion)Restrictions.gt("obs.obsDatetime", this.formatter1.parse(fromDate)));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add((Criterion)Restrictions.eq("obs.personId", personId));
		criteria.add((Criterion)Restrictions.eq("obs.concept", Context.getConceptService().getConcept("PROVISIONAL DIAGNOSIS")));
		return criteria.list();
	}

	public List<Obs> getAllSymptom(Integer personId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class, "obs");
		String toDdate = this.formatter1.format(new Date());
		Date date1 = new Date();
		Date oldDate = new Date(date1.getTime() - TimeUnit.HOURS.toMillis(24L));
		String fromDate = this.formatter1.format(oldDate);
		try {
			criteria.add((Criterion)Restrictions.lt("obs.obsDatetime", this.formatter1.parse(toDdate)));
			criteria.add((Criterion)Restrictions.gt("obs.obsDatetime", this.formatter1.parse(fromDate)));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add((Criterion)Restrictions.eq("obs.personId", personId));
		criteria.add((Criterion)Restrictions.eq("obs.concept", Context.getConceptService().getConcept("SYMPTOM")));
		return criteria.list();
	}

	public TriagePatientData updateTriagePatientData(TriagePatientData triagePatientData) throws DAOException {
		return (TriagePatientData)this.sessionFactory.getCurrentSession().merge(triagePatientData);
	}

	public List<Obs> getAllExamination(Integer personId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class, "obs");
		String toDdate = this.formatter1.format(new Date());
		Date date1 = new Date();
		Date oldDate = new Date(date1.getTime() - TimeUnit.HOURS.toMillis(24L));
		String fromDate = this.formatter1.format(oldDate);
		try {
			criteria.add((Criterion)Restrictions.lt("obs.obsDatetime", this.formatter1.parse(toDdate)));
			criteria.add((Criterion)Restrictions.gt("obs.obsDatetime", this.formatter1.parse(fromDate)));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add((Criterion)Restrictions.eq("obs.personId", personId));
		criteria.add((Criterion)Restrictions.eq("obs.concept", Context.getConceptService().getConcept("EXAMINATION")));
		return criteria.list();
	}

	public List<Obs> getAllUnderlinedCondition(Integer personId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class, "obs");
		String toDdate = this.formatter1.format(new Date());
		Date date1 = new Date();
		Date oldDate = new Date(date1.getTime() - TimeUnit.HOURS.toMillis(24L));
		String fromDate = this.formatter1.format(oldDate);
		try {
			criteria.add((Criterion)Restrictions.lt("obs.obsDatetime", this.formatter1.parse(toDdate)));
			criteria.add((Criterion)Restrictions.gt("obs.obsDatetime", this.formatter1.parse(fromDate)));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add((Criterion)Restrictions.eq("obs.personId", personId));
		criteria.add((Criterion)Restrictions.eq("obs.concept", Context.getConceptService().getConcept("UNDERLINED CONDITION")));
		return criteria.list();
	}

	public List<Obs> getAllSigns(Integer personId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class, "obs");
		String toDdate = this.formatter1.format(new Date());
		Date date1 = new Date();
		Date oldDate = new Date(date1.getTime() - TimeUnit.HOURS.toMillis(24L));
		String fromDate = this.formatter1.format(oldDate);
		try {
			criteria.add((Criterion)Restrictions.lt("obs.obsDatetime", this.formatter1.parse(toDdate)));
			criteria.add((Criterion)Restrictions.gt("obs.obsDatetime", this.formatter1.parse(fromDate)));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add((Criterion)Restrictions.eq("obs.personId", personId));
		criteria.add((Criterion)Restrictions.eq("obs.concept", Context.getConceptService().getConcept("SIGNS")));
		return criteria.list();
	}

	public List<Obs> getAllDifferentialDiagnosis(Integer personId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class, "obs");
		String toDdate = this.formatter1.format(new Date());
		Date date1 = new Date();
		Date oldDate = new Date(date1.getTime() - TimeUnit.HOURS.toMillis(24L));
		String fromDate = this.formatter1.format(oldDate);
		try {
			criteria.add((Criterion)Restrictions.lt("obs.obsDatetime", this.formatter1.parse(toDdate)));
			criteria.add((Criterion)Restrictions.gt("obs.obsDatetime", this.formatter1.parse(fromDate)));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add((Criterion)Restrictions.eq("obs.personId", personId));
		criteria.add((Criterion)Restrictions.eq("obs.concept", Context.getConceptService().getConcept("DIFFERENTIAL DIAGNOSIS")));
		return criteria.list();
	}

	public List<Obs> getAllWorkingDiagnosis(Integer personId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class, "obs");
		String toDdate = this.formatter1.format(new Date());
		Date date1 = new Date();
		Date oldDate = new Date(date1.getTime() - TimeUnit.HOURS.toMillis(24L));
		String fromDate = this.formatter1.format(oldDate);
		try {
			criteria.add((Criterion)Restrictions.lt("obs.obsDatetime", this.formatter1.parse(toDdate)));
			criteria.add((Criterion)Restrictions.gt("obs.obsDatetime", this.formatter1.parse(fromDate)));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add((Criterion)Restrictions.eq("obs.personId", personId));
		criteria.add((Criterion)Restrictions.eq("obs.concept", Context.getConceptService().getConcept("WORKING DIAGNOSIS")));
		return criteria.list();
	}
}
