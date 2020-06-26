package org.openmrs.module.hospitalcore;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.User;
import org.openmrs.api.APIException;
import org.openmrs.api.ConceptService;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientSearch;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

@Transactional
public interface HospitalCoreService extends OpenmrsService {
	List<Obs> listObsGroup(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4) throws APIException;

	EncounterType insertEncounterTypeByKey(String paramString) throws APIException;

	void creatConceptQuestionAndAnswer(ConceptService paramConceptService, User paramUser, String paramString, String... paramVarArgs) throws APIException;

	Obs createObsGroup(Patient paramPatient, String paramString);

	Concept insertConceptUnlessExist(String paramString1, String paramString2, String paramString3) throws APIException;

	Obs getObsGroup(Patient paramPatient);

	Obs getObsGroupCurrentDate(Integer paramInteger) throws APIException;

	void insertSynonym(Concept paramConcept, String paramString);

	void insertMapping(Concept paramConcept, String paramString1, String paramString2);

	Concept insertConcept(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) throws APIException;

	Integer importConcepts(InputStream paramInputStream1, InputStream paramInputStream2, InputStream paramInputStream3) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException;

	List<Patient> searchPatient(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, int paramInt3, String paramString4) throws APIException;

	List<Patient> searchPatient(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, int paramInt3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8) throws APIException;

	List<Patient> searchPatient(String paramString);

	BigInteger getPatientSearchResultCount(String paramString);

	List<PersonAttribute> getPersonAttributes(Integer paramInteger);

	Encounter getLastVisitEncounter(Patient paramPatient, List<EncounterType> paramList);

	CoreForm saveCoreForm(CoreForm paramCoreForm);

	CoreForm getCoreForm(Integer paramInteger);

	List<CoreForm> getCoreForms(String paramString);

	List<CoreForm> getCoreForms();

	void deleteCoreForm(CoreForm paramCoreForm);

	PatientSearch savePatientSearch(PatientSearch paramPatientSearch);

	Date getLastVisitTime(Patient paramPatient);

	PatientSearch getPatientByPatientId(int paramInt);

	PatientSearch getPatient(int paramInt);

	List<Obs> getObsByEncounterAndConcept(Encounter paramEncounter, Concept paramConcept);

	PersonAddress getPersonAddress(Person paramPerson);

	OpdTestOrder getOpdTestOrder(Integer paramInteger);

	PersonAttributeType getPersonAttributeTypeByName(String paramString) throws APIException;

	Obs getObs(Person paramPerson, Encounter paramEncounter) throws APIException;

	String getPatientType(Patient paramPatient) throws APIException;

	List<Obs> getObsInstanceForDiagnosis(Encounter paramEncounter, Concept paramConcept) throws APIException;
}
