package org.openmrs.module.hospitalcore.db.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
//import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.Patient;
import org.openmrs.Role;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.db.RadiologyDAO;
import org.openmrs.module.hospitalcore.form.RadiologyForm;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.hospitalcore.model.RadiologyTest;
import org.openmrs.module.hospitalcore.template.RadiologyTemplate;
import org.springframework.transaction.annotation.Transactional;

public class HibernateRadiologyDAO implements RadiologyDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public RadiologyForm saveRadiologyForm(RadiologyForm form) {
		return (RadiologyForm)this.sessionFactory.getCurrentSession().merge(form);
	}

	public RadiologyForm getRadiologyFormById(Integer id) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyForm.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		return (RadiologyForm)criteria.uniqueResult();
	}

	public List<RadiologyForm> getRadiologyForms(String conceptName) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyForm.class);
		criteria.add((Criterion)Restrictions.eq("conceptName", conceptName));
		return criteria.list();
	}

	public List<RadiologyForm> getAllRadiologyForms() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyForm.class);
		return criteria.list();
	}

	public void deleteRadiologyForm(RadiologyForm form) {
		this.sessionFactory.getCurrentSession().delete(form);
	}

	public RadiologyDepartment saveRadiologyDepartment(RadiologyDepartment department) {
		return (RadiologyDepartment)this.sessionFactory.getCurrentSession().merge(department);
	}

	public RadiologyDepartment getRadiologyDepartmentById(Integer id) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyDepartment.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		return (RadiologyDepartment)criteria.uniqueResult();
	}

	public void deleteRadiologyDepartment(RadiologyDepartment department) {
		this.sessionFactory.getCurrentSession().delete(department);
	}

	public RadiologyDepartment getRadiologyDepartmentByRole(Role role) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyDepartment.class);
		criteria.add((Criterion)Restrictions.eq("role", role));
		return (RadiologyDepartment)criteria.uniqueResult();
	}

	public Integer countOrders(Date orderStartDate, OrderType orderType, Set<Concept> tests, List<Patient> patients) throws ParseException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Order.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(orderStartDate) + " 00:00:00";
		String endDate = sdf.format(orderStartDate) + " 23:59:59";
		criteria.add((Criterion)Restrictions.eq("orderType", orderType));
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		criteria.add(Expression.between("startDate", dateTimeFormatter.parse(startDate), dateTimeFormatter.parse(endDate)));
		criteria.add((Criterion)Restrictions.eq("discontinued", Boolean.valueOf(false)));
		criteria.add(Restrictions.in("concept", tests));
		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return Integer.valueOf((rs != null) ? rs.intValue() : 0);
	}

	public List<Order> getOrders(Date orderStartDate, OrderType orderType, Set<Concept> tests, List<Patient> patients, int page, int pageSize) throws ParseException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Order.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(orderStartDate) + " 00:00:00";
		String endDate = sdf.format(orderStartDate) + " 23:59:59";
		criteria.add((Criterion)Restrictions.eq("orderType", orderType));
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		criteria.add(Expression.between("startDate", dateTimeFormatter.parse(startDate), dateTimeFormatter.parse(endDate)));
		criteria.add((Criterion)Restrictions.eq("discontinued", Boolean.valueOf(false)));
		criteria.add(Restrictions.in("concept", tests));
		criteria.add(Restrictions.isNull("dateVoided"));
		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		criteria.addOrder(org.hibernate.criterion.Order.asc("startDate"));
		int firstResult = (page - 1) * pageSize;
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

	public RadiologyTest saveRadiologyTest(RadiologyTest test) {
		return (RadiologyTest)this.sessionFactory.getCurrentSession().merge(test);
	}

	public RadiologyTest getRadiologyTestById(Integer id) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		return (RadiologyTest)criteria.uniqueResult();
	}

	public void deleteRadiologyTest(RadiologyTest test) {
		this.sessionFactory.getCurrentSession().delete(test);
	}

	public List<RadiologyTest> getAllRadiologyTests() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		return criteria.list();
	}

	public RadiologyTest getRadiologyTestByOrder(Order order) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		criteria.add((Criterion)Restrictions.eq("order", order));
		return (RadiologyTest)criteria.uniqueResult();
	}

	public List<RadiologyTest> getRadiologyTestsByDateAndStatus(Date date, String status) throws ParseException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		criteria.add(Expression.between("date", dateTimeFormatter.parse(startDate), dateTimeFormatter.parse(endDate)));
		criteria.add((Criterion)Restrictions.eq("status", status));
		return criteria.list();
	}

	public List<RadiologyTest> getRadiologyTests(Date date, String status, Set<Concept> concepts, List<Patient> patients, int page, int pageSize) throws ParseException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		criteria.add(Expression.between("date", dateTimeFormatter.parse(startDate), dateTimeFormatter.parse(endDate)));
		criteria.add((Criterion)Restrictions.eq("status", status));
		criteria.add(Restrictions.in("concept", concepts));
		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		int firstResult = (page - 1) * pageSize;
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

	public Integer countRadiologyTests(Date date, String status, Set<Concept> concepts, List<Patient> patients) throws ParseException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		criteria.add(Expression.between("date", dateTimeFormatter.parse(startDate), dateTimeFormatter.parse(endDate)));
		criteria.add((Criterion)Restrictions.eq("status", status));
		criteria.add(Restrictions.in("concept", concepts));
		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return Integer.valueOf((rs != null) ? rs.intValue() : 0);
	}

	public List<RadiologyTest> getRadiologyTestsByDate(Date date) throws ParseException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		criteria.add(Expression.between("date", dateTimeFormatter.parse(startDate), dateTimeFormatter.parse(endDate)));
		return criteria.list();
	}

	public List<RadiologyTest> getRadiologyTestsByDiscontinuedDate(Date date, Set<Concept> concepts, List<Patient> patients, int page, int pageSize) throws ParseException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		Criteria orderCriteria = criteria.createCriteria("order");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		orderCriteria.add(Expression.between("discontinuedDate", dateTimeFormatter.parse(startDate), dateTimeFormatter.parse(endDate)));
		criteria.add(Restrictions.in("concept", concepts));
		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		int firstResult = (page - 1) * pageSize;
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

	public Integer countRadiologyTestsByDiscontinuedDate(Date date, Set<Concept> concepts, List<Patient> patients) throws ParseException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		Criteria orderCriteria = criteria.createCriteria("order");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		orderCriteria.add(Expression.between("discontinuedDate", dateTimeFormatter.parse(startDate), dateTimeFormatter.parse(endDate)));
		criteria.add(Restrictions.in("concept", concepts));
		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return Integer.valueOf((rs != null) ? rs.intValue() : 0);
	}

	public List<RadiologyTest> getRadiologyTestsByDateAndPatient(Date date, Patient patient) throws ParseException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		Criteria orderCriteria = criteria.createCriteria("order");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		orderCriteria.add(Expression.between("discontinuedDate", dateTimeFormatter.parse(startDate), dateTimeFormatter.parse(endDate)));
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		return criteria.list();
	}

	@Transactional(readOnly = true)
	public void createConceptsForXrayDefaultForm() {
		HospitalCoreService hcs = (HospitalCoreService)Context.getService(HospitalCoreService.class);
		hcs.insertConcept("Coded", "Question", "RADIOLOGY XRAY DEFAULT FORM REPORT STATUS", "", "Radiology default x-ray form report status");
		hcs.insertConcept("Text", "Misc", "RADIOLOGY XRAY DEFAULT FORM NOTE", "", "Radiology default x-ray form report note");
		hcs.insertConcept("N/A", "Misc", "RADIOLOGY XRAY DEFAULT FORM FILM GIVEN", "", "Radiology default x-ray form film given");
		hcs.insertConcept("N/A", "Misc", "RADIOLOGY XRAY DEFAULT FORM FILM NOT GIVEN", "", "Radiology default x-ray form film not given");
	}

	public RadiologyTest getRadiologyTest(Encounter ecnounter) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		criteria.add((Criterion)Restrictions.eq("encounter", ecnounter));
		return (RadiologyTest)criteria.uniqueResult();
	}

	public List<RadiologyTemplate> getAllRadiologyTemplates() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTemplate.class);
		return criteria.list();
	}

	public RadiologyTemplate getRadiologyTemplate(Integer id) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTemplate.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		return (RadiologyTemplate)criteria.uniqueResult();
	}

	public RadiologyTemplate saveRadiologyTemplate(RadiologyTemplate template) {
		return (RadiologyTemplate)this.sessionFactory.getCurrentSession().merge(template);
	}

	public void deleteRadiologyTemplate(RadiologyTemplate template) {
		this.sessionFactory.getCurrentSession().delete(template);
	}

	public List<RadiologyTemplate> getRadiologyTemplates(Concept concept) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTemplate.class);
		Criteria testCriteria = criteria.createCriteria("tests");
		testCriteria.add((Criterion)Restrictions.eq("conceptId", concept.getConceptId()));
		return criteria.list();
	}

	public List<RadiologyTest> getCompletedRadiologyTestsByPatient(Patient patient) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(RadiologyTest.class);
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		return criteria.list();
	}
}
