package org.openmrs.module.hospitalcore.db.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.db.PatientDashboardDAO;
import org.openmrs.module.hospitalcore.model.Answer;
import org.openmrs.module.hospitalcore.model.BillableService;
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

public class HibernatePatientDashboardDAO implements PatientDashboardDAO {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	protected final Log log = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Order> getOrders(List<Concept> concepts, Patient patient, Location location, Date orderStartDate) throws DAOException {
		String hql = "from Order as o where o.voided = 0 and ";
		if (concepts != null && concepts.size() > 0) {
			hql = hql + " o.concept in (:conceptIds) ";
			if (patient != null || location != null || orderStartDate != null)
				hql = hql + " and ";
		}
		if (patient != null) {
			hql = hql + " o.patient = (:patient)";
			if (location != null || orderStartDate != null)
				hql = hql + " and ";
		}
		if (location != null) {
			hql = hql + " o.encounter.location = :location";
			if (orderStartDate != null)
				hql = hql + " and ";
		}
		if (orderStartDate != null) {
			SimpleDateFormat sdf = Context.getDateFormat();
			String startDate = sdf.format(orderStartDate) + " 00:00:00";
			String endDate = sdf.format(orderStartDate) + " 23:59:59";
			hql = hql + " ((o.startDate is null and o.encounter.encounterDatetime BETWEEN '" + startDate + "' AND '" + endDate + "' ) OR (    o.startDate is not null and  o.startDate  BETWEEN '" + startDate + "' AND '" + endDate + "'  )  )";
		}
		hql = hql + " order by o.startDate asc";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (concepts != null && concepts.size() > 0)
			query.setParameterList("conceptIds", concepts);
		if (patient != null)
			query.setParameter("patient", patient);
		if (location != null)
			query.setParameter("location", location);
		return query.list();
	}

	public List<Concept> searchConceptsByNameAndClass(String text, ConceptClass clazz) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Concept.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add((Criterion)Expression.eq("retired", Boolean.valueOf(false)));
		criteria.add((Criterion)Restrictions.eq("conceptClass", clazz));
		if (StringUtils.isNotBlank(text)) {
			criteria.createAlias("names", "names");
			criteria.add((Criterion)Expression.like("names.name", text, MatchMode.ANYWHERE));
		}
		return criteria.list();
	}

	public List<Encounter> getEncounter(Patient patient, Location location, EncounterType encType, String date) throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Encounter.class);
		crit.add((Criterion)Expression.eq("patient", patient));
		if (location != null && location.getLocationId() != null)
			crit.add((Criterion)Expression.eq("location", location));
		if (StringUtils.isNotBlank(date)) {
			String startDate = "";
			String endDate = "";
			if ("recent".equalsIgnoreCase(date)) {
				SimpleDateFormat formatter = Context.getDateFormat();
				Calendar cal = Calendar.getInstance();
				String today = Context.getDateFormat().format(cal.getTime());
				cal.set(5, cal.get(5) - 1);
				date = formatter.format(cal.getTime());
				startDate = date + " 00:00:00";
				endDate = today + " 23:59:59";
			} else {
				startDate = date + " 00:00:00";
				endDate = date + " 23:59:59";
			}
			try {
				crit.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("encounterDatetime", this.formatter.parseObject(startDate)), (Criterion)Restrictions.le("encounterDatetime", this.formatter.parseObject(endDate))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		crit.add((Criterion)Expression.eq("voided", Boolean.valueOf(false)));
		crit.add((Criterion)Expression.eq("encounterType", encType));
		crit.addOrder(org.hibernate.criterion.Order.desc("encounterDatetime"));
		return crit.list();
	}

	public Department createDepartment(Department department) throws DAOException {
		return (Department)this.sessionFactory.getCurrentSession().merge(department);
	}

	public void removeDepartment(Department department) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(department);
	}

	public Department getDepartmentById(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Department.class, "department");
		criteria.add((Criterion)Restrictions.eq("department.id", id));
		Department r = (Department)criteria.uniqueResult();
		return r;
	}

	public Department getDepartmentByName(String name) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Department.class, "department");
		criteria.add((Criterion)Restrictions.eq("department.name", name));
		criteria.setFirstResult(0).setMaxResults(1);
		List<Department> list = criteria.list();
		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}

	public Department getDepartmentByWard(Integer wardId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Department.class, "department");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createCriteria("department.wards", 1).add((Criterion)Restrictions.eq("id", wardId));
		criteria.setFirstResult(0).setMaxResults(1);
		List<Department> list = criteria.list();
		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}

	public List<Department> listDepartment(Boolean retired) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Department.class, "department");
		if (retired != null)
			criteria.add((Criterion)Restrictions.eq("department.retired", retired));
		List<Department> list = criteria.list();
		return list;
	}

	public DepartmentConcept createDepartmentConcept(DepartmentConcept departmentConcept) throws DAOException {
		return (DepartmentConcept)this.sessionFactory.getCurrentSession().merge(departmentConcept);
	}

	public DepartmentConcept getByDepartmentAndConcept(Integer departmentId, Integer conceptId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(DepartmentConcept.class, "departmentConcept");
		criteria.add((Criterion)Restrictions.eq("departmentConcept.department.id", departmentId)).add((Criterion)Restrictions.eq("departmentConcept.concept.id", conceptId));
		criteria.setFirstResult(0).setMaxResults(1);
		List<DepartmentConcept> list = criteria.list();
		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}

	public DepartmentConcept getById(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(DepartmentConcept.class, "departmentConcept");
		criteria.add((Criterion)Restrictions.eq("departmentConcept.id", id));
		criteria.setFirstResult(0).setMaxResults(1);
		DepartmentConcept r = (DepartmentConcept)criteria.uniqueResult();
		return r;
	}

	public void removeDepartmentConcept(DepartmentConcept departmentConcept) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(departmentConcept);
	}

	public List<DepartmentConcept> listByDepartment(Integer departmentId, Integer typeConcept) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(DepartmentConcept.class, "departmentConcept");
		criteria.add((Criterion)Restrictions.eq("departmentConcept.department.id", departmentId)).add((Criterion)Restrictions.eq("departmentConcept.typeConcept", typeConcept));
		List<DepartmentConcept> list = criteria.list();
		return list;
	}

	public List<Concept> listByDepartmentByWard(Integer wardId, Integer typeConcept) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(DepartmentConcept.class, "departmentConcept").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("departmentConcept.department", "department").add((Criterion)Restrictions.eq("departmentConcept.typeConcept", typeConcept)).add((Criterion)Restrictions.eq("department.retired", Boolean.valueOf(false)));
		criteria.createCriteria("department.wards", 1).add((Criterion)Restrictions.eq("id", wardId));
		List<DepartmentConcept> list = criteria.list();
		if (CollectionUtils.isNotEmpty(list)) {
			List<Concept> listConcept = new ArrayList<Concept>();
			for (DepartmentConcept dC : list)
				listConcept.add(dC.getConcept());
			return listConcept;
		}
		return null;
	}

	public List<Concept> searchInvestigation(String text) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Concept.class);
		List<BillableService> allBillableServices = ((BillingService)Context.getService(BillingService.class)).getAllServices();
		List<Integer> billableServiceConceptIds = new ArrayList<Integer>();
		for (BillableService service : allBillableServices)
			billableServiceConceptIds.add(service.getConceptId());
		ConceptClass cct = Context.getConceptService().getConceptClassByName("Test");
		ConceptClass ccl = Context.getConceptService().getConceptClassByName("LabSet");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add((Criterion)Restrictions.eq("retired", Boolean.valueOf(false)));
		criteria.add(Restrictions.in("conceptId", billableServiceConceptIds));
		SimpleExpression simpleExpression1 = Restrictions.eq("conceptClass", cct);
		SimpleExpression simpleExpression2 = Restrictions.eq("conceptClass", ccl);
		LogicalExpression orExp = Restrictions.or((Criterion)simpleExpression1, (Criterion)simpleExpression2);
		criteria.add((Criterion)orExp);
		if (StringUtils.isNotBlank(text)) {
			criteria.createAlias("names", "names");
			criteria.add((Criterion)Restrictions.like("names.name", text, MatchMode.ANYWHERE));
		}
		return criteria.list();
	}

	public OpdTestOrder saveOrUpdateOpdOrder(OpdTestOrder opdTestOrder) throws DAOException {
		this.sessionFactory.getCurrentSession().saveOrUpdate(opdTestOrder);
		return opdTestOrder;
	}

	public OpdDrugOrder saveOrUpdateOpdDrugOrder(OpdDrugOrder opdDrugOrder) throws DAOException {
		this.sessionFactory.getCurrentSession().saveOrUpdate(opdDrugOrder);
		return opdDrugOrder;
	}

	public List<InventoryDrug> findDrug(String name) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(InventoryDrug.class, "drug");
		if (!StringUtils.isBlank(name))
			criteria.add((Criterion)Restrictions.like("drug.name", "%" + name + "%"));
		List<InventoryDrug> l = criteria.list();
		return l;
	}

	public Symptom saveSymptom(Symptom symptom) throws DAOException {
		return (Symptom)this.sessionFactory.getCurrentSession().merge(symptom);
	}

	public Question saveQuestion(Question question) throws DAOException {
		return (Question)this.sessionFactory.getCurrentSession().merge(question);
	}

	public Answer saveAnswer(Answer answer) throws DAOException {
		return (Answer)this.sessionFactory.getCurrentSession().merge(answer);
	}

	public OpdPatientQueueLog getOpdPatientQueueLog(Encounter encounter) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdPatientQueueLog.class);
		criteria.add((Criterion)Restrictions.like("encounter", encounter));
		return (OpdPatientQueueLog)criteria.uniqueResult();
	}

	public List<Symptom> getSymptom(Encounter encounter) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Symptom.class);
		criteria.add((Criterion)Restrictions.like("encounter", encounter));
		return criteria.list();
	}

	public List<Question> getQuestion(Symptom symptom) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Question.class);
		criteria.add((Criterion)Restrictions.like("symptom", symptom));
		return criteria.list();
	}

	public Answer getAnswer(Question question) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Answer.class);
		criteria.add((Criterion)Restrictions.like("question", question));
		return (Answer)criteria.uniqueResult();
	}

	public List<OpdDrugOrder> getOpdDrugOrder(Encounter encounter) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdDrugOrder.class);
		criteria.add((Criterion)Restrictions.like("encounter", encounter));
		return criteria.list();
	}

	public TriagePatientData getTriagePatientData(Integer triageDataId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(TriagePatientData.class);
		criteria.add((Criterion)Restrictions.like("id", triageDataId));
		return (TriagePatientData)criteria.uniqueResult();
	}

	public TriagePatientData getTriagePatientDataFromEncounter(Integer encounterOpd) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(TriagePatientData.class);
		criteria.add((Criterion)Restrictions.like("encounterOpd", encounterOpd));
		return (TriagePatientData)criteria.uniqueResult();
	}

	public Examination saveExamination(Examination examination) throws DAOException {
		return (Examination)this.sessionFactory.getCurrentSession().merge(examination);
	}

	public List<Examination> getExamination(Encounter encounters) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Examination.class);
		criteria.add((Criterion)Restrictions.like("encounters", encounters));
		return criteria.list();
	}

	public List<Question> getQuestion(Examination examination) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Question.class);
		criteria.add((Criterion)Restrictions.like("examination", examination));
		return criteria.list();
	}
}
