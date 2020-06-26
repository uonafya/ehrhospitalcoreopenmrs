package org.openmrs.module.hospitalcore.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptDatatype;
import org.openmrs.ConceptDescription;
import org.openmrs.ConceptMap;
import org.openmrs.ConceptName;
import org.openmrs.ConceptNameTag;
import org.openmrs.ConceptSource;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.GlobalProperty;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.User;
import org.openmrs.api.APIException;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.concept.ConceptModel;
import org.openmrs.module.hospitalcore.concept.Mapping;
import org.openmrs.module.hospitalcore.concept.Synonym;
import org.openmrs.module.hospitalcore.db.HospitalCoreDAO;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientSearch;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HospitalCoreServiceImpl extends BaseOpenmrsService implements HospitalCoreService {
	private Log log = LogFactory.getLog(getClass());

	protected HospitalCoreDAO dao;

	public List<Obs> listObsGroup(Integer personId, Integer conceptId, Integer min, Integer max) throws APIException {
		return this.dao.listObsGroup(personId, conceptId, min, max);
	}

	private Concept insertConcept(ConceptService conceptService, String dataTypeName, String conceptClassName, String concept) {
		try {
			ConceptDatatype datatype = Context.getConceptService().getConceptDatatypeByName(dataTypeName);
			ConceptClass conceptClass = conceptService.getConceptClassByName(conceptClassName);
			Concept con = conceptService.getConcept(concept);
			if (con == null) {
				con = new Concept();
				ConceptName name = new ConceptName(concept, Context.getLocale());
				con.addName(name);
				con.setDatatype(datatype);
				con.setConceptClass(conceptClass);
				return conceptService.saveConcept(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void creatConceptQuestionAndAnswer(ConceptService conceptService, User user, String conceptParent, String... conceptChild) {
		Concept concept = conceptService.getConcept(conceptParent);
		if (concept == null)
			insertConcept(conceptService, "Coded", "Question", conceptParent);
		if (concept != null) {
			for (String hn : conceptChild)
				insertHospital(conceptService, hn);
			addConceptAnswers(concept, conceptChild, user);
		}
	}

	private void addConceptAnswers(Concept concept, String[] answerNames, User creator) {
		Set<Integer> currentAnswerIds = new HashSet<Integer>();
		for (ConceptAnswer answer : concept.getAnswers())
			currentAnswerIds.add(answer.getAnswerConcept().getConceptId());
		boolean changed = false;
		for (String answerName : answerNames) {
			Concept answer = Context.getConceptService().getConcept(answerName);
			if (!currentAnswerIds.contains(answer.getConceptId())) {
				changed = true;
				ConceptAnswer conceptAnswer = new ConceptAnswer(answer);
				conceptAnswer.setCreator(creator);
				concept.addAnswer(conceptAnswer);
			}
		}
		if (changed)
			Context.getConceptService().saveConcept(concept);
	}

	private Concept insertHospital(ConceptService conceptService, String hospitalName) {
		try {
			ConceptDatatype datatype = Context.getConceptService().getConceptDatatypeByName("N/A");
			ConceptClass conceptClass = conceptService.getConceptClassByName("Misc");
			Concept con = conceptService.getConceptByName(hospitalName);
			if (con == null) {
				con = new Concept();
				ConceptName name = new ConceptName(hospitalName, Context.getLocale());
				con.addName(name);
				con.setDatatype(datatype);
				con.setConceptClass(conceptClass);
				return conceptService.saveConcept(con);
			}
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public EncounterType insertEncounterTypeByKey(String type) throws APIException {
		EncounterType encounterType = null;
		try {
			GlobalProperty gp = Context.getAdministrationService().getGlobalPropertyObject(type);
			encounterType = Context.getEncounterService().getEncounterType(gp.getPropertyValue());
			if (encounterType == null) {
				encounterType = new EncounterType(gp.getPropertyValue(), "");
				encounterType = Context.getEncounterService().saveEncounterType(encounterType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encounterType;
	}

	public Concept insertConceptUnlessExist(String dataTypeName, String conceptClassName, String conceptName) throws APIException {
		Concept con = null;
		try {
			ConceptService conceptService = Context.getConceptService();
			ConceptDatatype datatype = Context.getConceptService().getConceptDatatypeByName(dataTypeName);
			ConceptClass conceptClass = conceptService.getConceptClassByName(conceptClassName);
			con = conceptService.getConceptByName(conceptName);
			if (con == null) {
				con = new Concept();
				ConceptName name = new ConceptName(conceptName, Context.getLocale());
				con.addName(name);
				con.setDatatype(datatype);
				con.setConceptClass(conceptClass);
				Concept ccccc = conceptService.saveConcept(con);
				return ccccc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public Obs createObsGroup(Patient patient, String properyKey) {
		String opdVisitConceptName = Context.getAdministrationService().getGlobalProperty(properyKey);
		if (!StringUtils.isBlank(opdVisitConceptName)) {
			Concept concept = insertConceptUnlessExist("N/A", "Misc", opdVisitConceptName);
			Obs obs = new Obs();
			obs.setPatient(patient);
			obs.setConcept(concept);
			obs.setDateCreated(new Date());
			obs.setObsDatetime(new Date());
			obs.setLocation(new Location(Integer.valueOf(1)));
			return Context.getObsService().saveObs(obs, "Global obs for " + patient.getPersonName().getGivenName());
		}
		return null;
	}

	public Obs getObsGroup(Patient patient) {
		String name = Context.getAdministrationService().getGlobalProperty("hospitalcore.obsGroup");
		Obs obs = null;
		try {
			Concept concept = Context.getConceptService().getConceptByName(name);
			List<Obs> obses = listObsGroup(patient.getPersonId(), concept.getConceptId(), Integer.valueOf(0), Integer.valueOf(1));
			obs = CollectionUtils.isEmpty(obses) ? null : obses.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obs;
	}

	public Obs getObsGroupCurrentDate(Integer personId) throws APIException {
		String name = Context.getAdministrationService().getGlobalProperty("hospitalcore.obsGroup");
		Concept concept = Context.getConceptService().getConceptByName(name);
		return this.dao.getObsGroupCurrentDate(personId, concept.getConceptId());
	}

	public HospitalCoreDAO getDao() {
		return this.dao;
	}

	public void setDao(HospitalCoreDAO dao) {
		this.dao = dao;
	}

	public void insertSynonym(Concept concept, String name) {
		Locale loc = new Locale("en");
		ConceptName conceptName = new ConceptName(name, loc);
		ConceptNameTag tag = Context.getConceptService().getConceptNameTagByName("synonym");
		conceptName.addTag(tag);
		conceptName.setDateCreated(new Date());
		conceptName.setCreator(Context.getAuthenticatedUser());
		concept.addName(conceptName);
		Context.getConceptService().saveConcept(concept);
	}

	public void insertMapping(Concept concept, String sourceName, String sourceCode) {
		ConceptSource conceptSource = Context.getConceptService().getConceptSourceByName(sourceName);
		List<ConceptMap> conceptMaps = new ArrayList<ConceptMap>();
		conceptMaps.addAll(concept.getConceptMappings());
		boolean found = false;
		for (ConceptMap cm : concept.getConceptMappings()) {
			if (cm.getSource().equals(conceptSource) &&
					cm.getSourceCode().equalsIgnoreCase(sourceCode)) {
				found = true;
				break;
			}
		}
		if (!found) {
			ConceptMap conceptMap = new ConceptMap();
			conceptMap.setConcept(concept);
			conceptMap.setSource(conceptSource);
			conceptMap.setSourceCode(sourceCode);
			conceptMap.setDateCreated(new Date());
			conceptMap.setCreator(Context.getAuthenticatedUser());
			concept.addConceptMapping(conceptMap);
			Context.getConceptService().saveConcept(concept);
		}
	}

	public Concept insertConcept(String dataTypeName, String conceptClassName, String name, String shortname, String description) throws APIException {
		Concept con = null;
		try {
			ConceptService conceptService = Context.getConceptService();
			con = conceptService.getConceptByName(name);
			if (con == null) {
				con = new Concept();
				Locale loc = new Locale("en");
				con = addName(con, name.toUpperCase(), loc);
				con = addName(con, shortname.toUpperCase(), loc);
				ConceptDescription conceptDescription = new ConceptDescription(description, loc);
				con.addDescription(conceptDescription);
				ConceptDatatype conceptDatatype = Context.getConceptService().getConceptDatatypeByName(dataTypeName);
				con.setDatatype(conceptDatatype);
				ConceptClass conceptClass = conceptService.getConceptClassByName(conceptClassName);
				con.setConceptClass(conceptClass);
				return conceptService.saveConcept(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	private Concept addName(Concept concept, String name, Locale loc) {
		if (!StringUtils.isBlank(name)) {
			ConceptName conceptName = new ConceptName(name, loc);
			conceptName.setDateCreated(new Date());
			conceptName.setCreator(Context.getAuthenticatedUser());
			concept.addName(conceptName);
		}
		return concept;
	}

	public Integer importConcepts(InputStream diagnosisStream, InputStream mappingStream, InputStream synonymStream) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		Integer diagnosisNo = Integer.valueOf(0);
		Set<ConceptModel> concepts = new HashSet<ConceptModel>();
		Set<Mapping> mapping = new HashSet<Mapping>();
		Set<Synonym> synonym = new HashSet<Synonym>();
		if (diagnosisStream != null)
			concepts = parseDiagnosis(diagnosisStream);
		if (mappingStream != null)
			mapping = parseMapping(mappingStream);
		if (synonymStream != null)
			synonym = parseSynonym(synonymStream);
		List<ConceptModel> conceptModels = merge(concepts, mapping, synonym);
		System.out.println("NUMBER OF CONCEPTS + " + conceptModels.size());
		diagnosisNo = this.dao.buildConcepts(conceptModels);
		return diagnosisNo;
	}

	private Set<ConceptModel> parseDiagnosis(InputStream stream) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		Set<ConceptModel> concepts = new TreeSet<ConceptModel>();
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(stream);
		NodeList rows = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < rows.getLength(); i++) {
			Node row = rows.item(i);
			if (row.getNodeName().equalsIgnoreCase("row")) {
				ConceptModel cm = new ConceptModel();
				NodeList fields = row.getChildNodes();
				for (int j = 0; j < fields.getLength(); j++) {
					Node field = fields.item(j);
					NamedNodeMap attributes = field.getAttributes();
					if (field.getNodeName().equalsIgnoreCase("field")) {
						String type = attributes.getNamedItem("name").getTextContent();
						String value = field.getTextContent();
						if ("name".equalsIgnoreCase(type))
							cm.setName(value);
						if ("description".equalsIgnoreCase(type))
							cm.setDescription(value);
					}
				}
				concepts.add(cm);
			}
		}
		return concepts;
	}

	private Set<Mapping> parseMapping(InputStream stream) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		Set<Mapping> mappings = new TreeSet<Mapping>();
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(stream);
		NodeList rows = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < rows.getLength(); i++) {
			Node row = rows.item(i);
			if (row.getNodeName().equalsIgnoreCase("row")) {
				Mapping cm = new Mapping();
				NodeList fields = row.getChildNodes();
				for (int j = 0; j < fields.getLength(); j++) {
					Node field = fields.item(j);
					NamedNodeMap attributes = field.getAttributes();
					if (field.getNodeName().equalsIgnoreCase("field")) {
						String type = attributes.getNamedItem("name").getTextContent();
						String value = field.getTextContent();
						if ("name".equalsIgnoreCase(type))
							cm.setName(value);
						if ("source_code".equalsIgnoreCase(type))
							cm.setSourceCode(value);
						if ("source_name".equalsIgnoreCase(type))
							cm.setSource(value);
					}
				}
				mappings.add(cm);
			}
		}
		return mappings;
	}

	private Set<Synonym> parseSynonym(InputStream stream) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		Set<Synonym> synnonyms = new TreeSet<Synonym>();
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(stream);
		NodeList rows = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < rows.getLength(); i++) {
			Node row = rows.item(i);
			if (row.getNodeName().equalsIgnoreCase("row")) {
				Synonym cm = new Synonym();
				NodeList fields = row.getChildNodes();
				for (int j = 0; j < fields.getLength(); j++) {
					Node field = fields.item(j);
					NamedNodeMap attributes = field.getAttributes();
					if (field.getNodeName().equalsIgnoreCase("field")) {
						String type = attributes.getNamedItem("name").getTextContent();
						String value = field.getTextContent();
						if ("concept".equalsIgnoreCase(type))
							cm.setName(value);
						if ("synonym".equalsIgnoreCase(type))
							cm.setSynonym(value);
					}
				}
				synnonyms.add(cm);
			}
		}
		return synnonyms;
	}

	private List<ConceptModel> merge(Set<ConceptModel> conceptSet, Set<Mapping> mappingSet, Set<Synonym> synonymSet) {
		List<ConceptModel> conceptList = new ArrayList<ConceptModel>();
		conceptList.addAll(conceptSet);
		for (Mapping mapping : mappingSet) {
			int index = indexOf(conceptList, mapping.getName());
			if (index >= 0) {
				ConceptModel concept = conceptList.get(index);
				concept.getMappings().add(mapping);
			}
		}
		for (Synonym synonym : synonymSet) {
			int index = indexOf(conceptList, synonym.getName());
			if (index >= 0) {
				ConceptModel concept = conceptList.get(index);
				concept.getSynonyms().add(synonym.getSynonym());
			}
		}
		return conceptList;
	}

	private int indexOf(List<ConceptModel> conceptList, String name) {
		ConceptModel concept = new ConceptModel();
		concept.setName(name);
		return Collections.binarySearch((List)conceptList, concept);
	}

	public List<Patient> searchPatient(String nameOrIdentifier, String gender, int age, int rangeAge, String date, int rangeDay, String relativeName) throws APIException {
		return this.dao.searchPatient(nameOrIdentifier, gender, age, rangeAge, date, rangeDay, relativeName);
	}

	public List<Patient> searchPatient(String nameOrIdentifier, String gender, int age, int rangeAge, String lastDayOfVisit, int lastVisit, String relativeName, String maritalStatus, String phoneNumber, String nationalId, String fileNumber) throws APIException {
		return this.dao.searchPatient(nameOrIdentifier, gender, age, rangeAge, lastDayOfVisit, lastVisit, relativeName, maritalStatus, phoneNumber, nationalId, fileNumber);
	}

	public List<Patient> searchPatient(String hql) {
		return this.dao.searchPatient(hql);
	}

	public BigInteger getPatientSearchResultCount(String hql) {
		return this.dao.getPatientSearchResultCount(hql);
	}

	public List<PersonAttribute> getPersonAttributes(Integer patientId) {
		return this.dao.getPersonAttributes(patientId);
	}

	public Encounter getLastVisitEncounter(Patient patient, List<EncounterType> types) {
		return this.dao.getLastVisitEncounter(patient, types);
	}

	public CoreForm saveCoreForm(CoreForm form) {
		return this.dao.saveCoreForm(form);
	}

	public CoreForm getCoreForm(Integer id) {
		return this.dao.getCoreForm(id);
	}

	public List<CoreForm> getCoreForms(String conceptName) {
		return this.dao.getCoreForms(conceptName);
	}

	public List<CoreForm> getCoreForms() {
		return this.dao.getCoreForms();
	}

	public void deleteCoreForm(CoreForm form) {
		this.dao.deleteCoreForm(form);
	}

	public PatientSearch savePatientSearch(PatientSearch patientSearch) {
		return this.dao.savePatientSearch(patientSearch);
	}

	public Date getLastVisitTime(Patient patientID) {
		return this.dao.getLastVisitTime(patientID);
	}

	public PatientSearch getPatientByPatientId(int patientId) {
		return this.dao.getPatientByPatientId(patientId);
	}

	public PatientSearch getPatient(int patientID) {
		return this.dao.getPatient(patientID);
	}

	public List<Obs> getObsByEncounterAndConcept(Encounter encounter, Concept concept) {
		return this.dao.getObsByEncounterAndConcept(encounter, concept);
	}

	public PersonAddress getPersonAddress(Person person) {
		return this.dao.getPersonAddress(person);
	}

	public OpdTestOrder getOpdTestOrder(Integer opdOrderId) {
		return this.dao.getOpdTestOrder(opdOrderId);
	}

	public PersonAttributeType getPersonAttributeTypeByName(String attributeName) {
		return this.dao.getPersonAttributeTypeByName(attributeName);
	}

	public Obs getObs(Person person, Encounter encounter) {
		return this.dao.getObs(person, encounter);
	}

	public String getPatientType(Patient patientId) {
		return this.dao.getPatientType(patientId);
	}

	public List<Obs> getObsInstanceForDiagnosis(Encounter encounter, Concept concept) {
		return this.dao.getObsInstanceForDiagnosis(encounter, concept);
	}
}
