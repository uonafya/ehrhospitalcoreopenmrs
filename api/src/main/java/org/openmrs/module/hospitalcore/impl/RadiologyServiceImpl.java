package org.openmrs.module.hospitalcore.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.Patient;
import org.openmrs.Role;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.RadiologyService;
import org.openmrs.module.hospitalcore.concept.ConceptNode;
import org.openmrs.module.hospitalcore.concept.TestTree;
import org.openmrs.module.hospitalcore.db.RadiologyDAO;
import org.openmrs.module.hospitalcore.form.RadiologyForm;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.hospitalcore.model.RadiologyTest;
import org.openmrs.module.hospitalcore.template.RadiologyTemplate;
import org.openmrs.module.hospitalcore.util.GlobalPropertyUtil;
import org.openmrs.module.hospitalcore.util.PatientUtils;

public class RadiologyServiceImpl extends BaseOpenmrsService implements RadiologyService {
	protected RadiologyDAO dao;

	public void setDao(RadiologyDAO dao) {
		this.dao = dao;
	}

	public RadiologyForm saveRadiologyForm(RadiologyForm form) {
		return this.dao.saveRadiologyForm(form);
	}

	public RadiologyForm getRadiologyFormById(Integer id) {
		return this.dao.getRadiologyFormById(id);
	}

	public List<RadiologyForm> getAllRadiologyForms() {
		return this.dao.getAllRadiologyForms();
	}

	public void deleteRadiologyForm(RadiologyForm form) {
		this.dao.deleteRadiologyForm(form);
	}

	public List<RadiologyForm> getRadiologyForms(String conceptName) {
		return this.dao.getRadiologyForms(conceptName);
	}

	public RadiologyForm getDefaultForm() {
		Integer formId = GlobalPropertyUtil.getInteger("radiology.form.defaultXRay", Integer.valueOf(0));
		RadiologyForm form = getRadiologyFormById(formId);
		return form;
	}

	public RadiologyDepartment saveRadiologyDepartment(RadiologyDepartment department) {
		return this.dao.saveRadiologyDepartment(department);
	}

	public RadiologyDepartment getRadiologyDepartmentById(Integer id) {
		return this.dao.getRadiologyDepartmentById(id);
	}

	public void deleteRadiologyDepartment(RadiologyDepartment department) {
		this.dao.deleteRadiologyDepartment(department);
	}

	public RadiologyDepartment getCurrentRadiologyDepartment() {
		Set<Role> roles = Context.getAuthenticatedUser().getAllRoles();
		List<RadiologyDepartment> departments = new ArrayList<RadiologyDepartment>();
		for (Role role : roles) {
			RadiologyDepartment department = this.dao.getRadiologyDepartmentByRole(role);
			if (department != null)
				departments.add(department);
		}
		if (!departments.isEmpty())
			return departments.get(0);
		return null;
	}

	public List<Order> getOrders(Date startDate, String phrase, Set<Concept> tests, int page) throws ParseException {
		Integer radiologyOrderTypeId = GlobalPropertyUtil.getInteger("billing.radiologyOrderType", Integer.valueOf(8));
		OrderType orderType = Context.getOrderService().getOrderType(radiologyOrderTypeId);
		List<Patient> patients = null;
		if (!StringUtils.isBlank(phrase))
			patients = Context.getPatientService().getPatients(phrase);
		List<Order> orders = this.dao.getOrders(startDate, orderType, tests, patients, page, GlobalPropertyUtil.getInteger("radiology.pagesize", Integer.valueOf(20)).intValue());
		return orders;
	}

	public Integer countOrders(Date startDate, String phrase, Set<Concept> tests) throws ParseException {
		Integer radiologyOrderTypeId = GlobalPropertyUtil.getInteger("billing.radiologyOrderType", Integer.valueOf(8));
		OrderType orderType = Context.getOrderService().getOrderType(radiologyOrderTypeId);
		List<Patient> patients = Context.getPatientService().getPatients(phrase);
		return this.dao.countOrders(startDate, orderType, tests, patients);
	}

	public RadiologyTest saveRadiologyTest(RadiologyTest test) {
		return this.dao.saveRadiologyTest(test);
	}

	public RadiologyTest getRadiologyTestById(Integer id) {
		return this.dao.getRadiologyTestById(id);
	}

	public List<RadiologyTest> getAllRadiologyTests() {
		return this.dao.getAllRadiologyTests();
	}

	public void deleteRadiologyTest(RadiologyTest test) {
		this.dao.deleteRadiologyTest(test);
	}

	public Integer acceptTest(Order order) {
		RadiologyTest test = this.dao.getRadiologyTestByOrder(order);
		if (test == null) {
			test = new RadiologyTest();
			test.setOrder(order);
			test.setPatient(order.getPatient());
			test.setConcept(order.getConcept());
			test.setCreator(Context.getAuthenticatedUser());
			test.setDate(new Date());
			test.setStatus("accepted");
			List<RadiologyForm> forms = this.dao.getRadiologyForms(order.getConcept().getName().getName());
			if (CollectionUtils.isEmpty(forms)) {
				test.setForm(getDefaultForm());
			} else {
				test.setForm(forms.get(0));
			}
			RadiologyTest acceptedTest = this.dao.saveRadiologyTest(test);
			return acceptedTest.getId();
		}
		return Integer.valueOf(-1);
	}

	public String unacceptTest(RadiologyTest test) {
		if (test != null) {
			if (test.getStatus().equalsIgnoreCase("accepted")) {
				this.dao.deleteRadiologyTest(test);
				return "success";
			}
		} else {
			return "not found";
		}
		return null;
	}

	public String rescheduleTest(Order order, Date rescheduledDate) {
		if (!order.getDiscontinued().booleanValue()) {
			RadiologyTest test = getRadiologyTestByOrder(order);
			if (test != null) {
				if (test.getStatus().equalsIgnoreCase("accepted")) {
					order.setStartDate(rescheduledDate);
					order.setChangedBy(Context.getAuthenticatedUser());
					order.setDateChanged(new Date());
					deleteRadiologyTest(test);
					Context.getOrderService().saveOrder(order);
					return "success";
				}
				return test.getStatus();
			}
			order.setStartDate(rescheduledDate);
			order.setChangedBy(Context.getAuthenticatedUser());
			order.setDateChanged(new Date());
			Context.getOrderService().saveOrder(order);
			return "success";
		}
		return "entered";
	}

	public RadiologyTest getRadiologyTestByOrder(Order order) {
		return this.dao.getRadiologyTestByOrder(order);
	}

	public List<RadiologyTest> getAcceptedRadiologyTests(Date date, String phrase, Set<Concept> allowableTests, int page) throws ParseException {
		List<Patient> patients = null;
		if (!StringUtils.isBlank(phrase))
			patients = Context.getPatientService().getPatients(phrase);
		return this.dao.getRadiologyTests(date, "accepted", allowableTests, patients, page, GlobalPropertyUtil.getInteger("radiology.pagesize", Integer.valueOf(20)).intValue());
	}

	public Integer countAcceptedRadiologyTests(Date date, String phrase, Set<Concept> allowableTests) throws ParseException {
		List<Patient> patients = null;
		if (!StringUtils.isBlank(phrase))
			patients = Context.getPatientService().getPatients(phrase);
		return this.dao.countRadiologyTests(date, "accepted", allowableTests, patients);
	}

	public List<RadiologyTest> getCompletedRadiologyTests(Date date, String phrase, Set<Concept> allowableTests, int page) throws ParseException {
		List<Patient> patients = null;
		if (!StringUtils.isBlank(phrase))
			patients = Context.getPatientService().getPatients(phrase);
		List<RadiologyTest> tests = this.dao.getRadiologyTestsByDiscontinuedDate(date, allowableTests, patients, page, GlobalPropertyUtil.getInteger("radiology.pagesize", Integer.valueOf(20)).intValue());
		return tests;
	}

	public Integer countCompletedRadiologyTests(Date date, String phrase, Set<Concept> allowableTests) throws ParseException {
		List<Patient> patients = null;
		if (!StringUtils.isBlank(phrase))
			patients = Context.getPatientService().getPatients(phrase);
		return this.dao.countRadiologyTestsByDiscontinuedDate(date, allowableTests, patients);
	}

	public List<RadiologyTest> getAllRadiologyTestsByDate(Date date, String phrase, Concept investigation) throws ParseException {
		List<RadiologyTest> tests = this.dao.getRadiologyTestsByDate(date);
		return filterRadiologyTests(tests, phrase, investigation);
	}

	private List<RadiologyTest> filterRadiologyTests(List<RadiologyTest> tests, String phrase, Concept investigation) {
		List<RadiologyTest> patientFilteredOrders = new ArrayList<RadiologyTest>();
		for (RadiologyTest test : tests) {
			Patient patient = test.getPatient();
			String fullname = PatientUtils.getFullName(patient).toLowerCase();
			String identifier = patient.getPatientIdentifier().getIdentifier().toLowerCase();
			phrase = phrase.toLowerCase();
			if (fullname.contains(phrase) || identifier.contains(phrase))
				patientFilteredOrders.add(test);
		}
		List<RadiologyTest> investigationFilteredOrders = new ArrayList<RadiologyTest>();
		if (investigation != null) {
			TestTree tree = new TestTree(investigation);
			Set<ConceptNode> nodes = tree.getRootNode().getChildNodes();
			List<ConceptNode> leafNodeList = new ArrayList<ConceptNode>();
			for (ConceptNode nod : nodes) {
				Set<ConceptNode> leafNode = nod.getChildNodes();
				if (nod.getChildNodes().size() != 0) {
					leafNodeList.addAll(leafNode);
					continue;
				}
				leafNodeList.add(nod);
			}
			for (RadiologyTest test : patientFilteredOrders) {
				ConceptNode node = new ConceptNode(test.getConcept());
				if (leafNodeList.contains(node))
					investigationFilteredOrders.add(test);
			}
		} else {
			investigationFilteredOrders = patientFilteredOrders;
		}
		return investigationFilteredOrders;
	}

	public String completeTest(RadiologyTest test) {
		if (test.getStatus() != null && (
				test.getStatus().equalsIgnoreCase("accepted") || test.getStatus().equalsIgnoreCase("completed"))) {
			Order order = test.getOrder();
			order.setDiscontinued(Boolean.valueOf(true));
			order.setDiscontinuedDate(new Date());
			order.setDiscontinuedBy(Context.getAuthenticatedUser());
			Context.getOrderService().saveOrder(order);
			test.setStatus("completed");
			saveRadiologyTest(test);
			return "success";
		}
		return "not accepted";
	}

	public List<RadiologyTest> getRadiologyTestsByDateAndPatient(Date date, Patient patient) throws ParseException {
		List<RadiologyTest> tests = this.dao.getRadiologyTestsByDateAndPatient(date, patient);
		return tests;
	}

	public void createConceptsForXrayDefaultForm() {
		this.dao.createConceptsForXrayDefaultForm();
	}

	public RadiologyTest getRadiologyTest(Encounter ecnounter) {
		return this.dao.getRadiologyTest(ecnounter);
	}

	public RadiologyTemplate getRadiologyTemplate(Integer id) {
		return this.dao.getRadiologyTemplate(id);
	}

	public RadiologyTemplate saveRadiologyTemplate(RadiologyTemplate template) {
		return this.dao.saveRadiologyTemplate(template);
	}

	public List<RadiologyTemplate> getAllRadiologyTemplates() {
		return this.dao.getAllRadiologyTemplates();
	}

	public void deleteRadiologyTemplate(RadiologyTemplate template) {
		this.dao.deleteRadiologyTemplate(template);
	}

	public List<RadiologyTemplate> getRadiologyTemplates(Concept concept) {
		return this.dao.getRadiologyTemplates(concept);
	}

	public List<RadiologyTest> getCompletedRadiologyTestsByPatient(Patient patient) {
		return this.dao.getCompletedRadiologyTestsByPatient(patient);
	}
}
