package org.openmrs.module.hospitalcore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.openmrs.Concept;
import org.openmrs.ConceptWord;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Order;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.RadiologyService;
import org.openmrs.module.hospitalcore.concept.TestTree;
import org.openmrs.module.hospitalcore.form.RadiologyForm;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.hospitalcore.model.RadiologyTest;
import org.springframework.ui.Model;

public class RadiologyUtil {
	private static Map<Concept, String> conceptNames = new HashMap<Concept, String>();

	private static Concept xrayConcept = null;

	private static Set<Concept> xrayConcepts = null;

	public static List<TestModel> generateModelsFromOrders(List<Order> orders, Map<Concept, Set<Concept>> testTreeMap) {
		List<TestModel> models = new ArrayList<TestModel>();
		for (Order order : orders) {
			TestModel tm = generateModel(order, testTreeMap);
			models.add(tm);
		}
		return models;
	}

	public static List<TestModel> generateModelsFromTests(List<RadiologyTest> tests, Map<Concept, Set<Concept>> testTreeMap) {
		List<TestModel> models = new ArrayList<TestModel>();
		for (RadiologyTest test : tests) {
			TestModel tm = generateModel(test, testTreeMap);
			models.add(tm);
		}
		return models;
	}

	public static TestModel generateModel(Order order, Map<Concept, Set<Concept>> testTreeMap) {
		RadiologyService rs = (RadiologyService)Context.getService(RadiologyService.class);
		RadiologyTest test = rs.getRadiologyTestByOrder(order);
		return generateModel(order, test, testTreeMap);
	}

	public static TestModel generateModel(RadiologyTest test, Map<Concept, Set<Concept>> testTreeMap) {
		return generateModel(test.getOrder(), test, testTreeMap);
	}

	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	public static Date parseDate(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(dateStr);
	}

	private static Set<Concept> getAllInvestigations() {
		RadiologyService rs = (RadiologyService)Context.getService(RadiologyService.class);
		RadiologyDepartment department = rs.getCurrentRadiologyDepartment();
		if (department != null) {
			Set<Concept> investigations = department.getInvestigations();
			return investigations;
		}
		return null;
	}

	public static List<TestTree> getAllTestTrees() {
		List<TestTree> trees = new ArrayList<TestTree>();
		Set<Concept> concepts = getAllInvestigations();
		for (Concept concept : concepts) {
			TestTree tree = new TestTree(concept);
			trees.add(tree);
		}
		return trees;
	}

	private static TestModel generateModel(Order order, RadiologyTest test, Map<Concept, Set<Concept>> testTreeMap) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		TestModel tm = new TestModel();
		tm.setStartDate(sdf.format(order.getStartDate()));
		tm.setPatientIdentifier(order.getPatient().getPatientIdentifier().getIdentifier());
		tm.setPatientName(PatientUtils.getFullName(order.getPatient()));
		tm.setGender(order.getPatient().getGender());
		tm.setAge(PatientUtils.estimateAge(order.getPatient()));
		tm.setTestName(order.getConcept().getName().getName());
		tm.setOrderId(order.getOrderId());
		if (getXrayConcepts(testTreeMap).contains(order.getConcept())) {
			tm.setXray(Boolean.valueOf(true));
		} else {
			tm.setXray(Boolean.valueOf(false));
		}
		if (test != null) {
			tm.setStatus(test.getStatus());
			tm.setTestId(test.getId());
			if (test.getForm() != null)
				tm.setGivenFormId(test.getForm().getId());
			if (test.getEncounter() != null)
				tm.setGivenEncounterId(test.getEncounter().getEncounterId());
			tm.setAcceptedDate(sdf.format(test.getDate()));
		} else {
			tm.setStatus(null);
		}
		if (testTreeMap != null)
			for (Concept investigationConcept : testTreeMap.keySet()) {
				Set<Concept> set = testTreeMap.get(investigationConcept);
				if (set.contains(order.getConcept()))
					tm.setInvestigation(getConceptName(investigationConcept));
			}
		return tm;
	}

	public static Concept searchConcept(String name) {
		Concept concept = Context.getConceptService().getConcept(name);
		if (concept != null)
			return concept;
		List<ConceptWord> cws = Context.getConceptService().findConcepts(name, new Locale("en"), false);
		if (!cws.isEmpty())
			return ((ConceptWord)cws.get(0)).getConcept();
		return null;
	}

	private static Concept getXrayConcept(Map<Concept, Set<Concept>> testTreeMap) {
		if (xrayConcept != null)
			return xrayConcept;
		for (Concept concept : testTreeMap.keySet()) {
			Concept investigation = Context.getConceptService().getConcept(concept.getConceptId());
			if (investigation.getName().getName().toLowerCase().contains("x-ray")) {
				xrayConcept = investigation;
				return xrayConcept;
			}
		}
		return null;
	}

	private static Set<Concept> getXrayConcepts(Map<Concept, Set<Concept>> testTreeMap) {
		if (xrayConcepts != null)
			return xrayConcepts;
		Concept xrayConcept = getXrayConcept(testTreeMap);
		xrayConcepts = testTreeMap.get(xrayConcept);
		return xrayConcepts;
	}

	public static void generateDataFromEncounter(Model model, Encounter encounter, RadiologyForm form) {
		if (encounter != null) {
			List<String> inputNames = new ArrayList<String>();
			List<String> inputValues = new ArrayList<String>();
			for (Obs obs : encounter.getAllObs()) {
				inputNames.add(obs.getConcept().getName().getName());
				inputValues.add(getObsValue(obs));
			}
			model.addAttribute("inputNames", inputNames);
			model.addAttribute("inputValues", inputValues);
			model.addAttribute("inputLength", Integer.valueOf(inputValues.size()));
		}
	}

	private static String getObsValue(Obs obs) {
		Concept concept = obs.getConcept();
		if (concept.getDatatype().getName().equalsIgnoreCase("Text"))
			return obs.getValueText();
		if (concept.getDatatype().getName().equalsIgnoreCase("Numeric"))
			return obs.getValueNumeric().toString();
		if (concept.getDatatype().getName().equalsIgnoreCase("Coded"))
			return obs.getValueCoded().getName().getName();
		return null;
	}

	public static String getConceptName(Concept searchConcept) {
		if (conceptNames.containsKey(searchConcept))
			return conceptNames.get(searchConcept);
		Concept concept = Context.getConceptService().getConcept(searchConcept.getConceptId());
		conceptNames.put(searchConcept, concept.getName().getName());
		return conceptNames.get(searchConcept);
	}
}
