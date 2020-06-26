package org.openmrs.module.hospitalcore.db;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.concept.ConceptModel;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientSearch;

public interface HospitalCoreDAO {
	List<Obs> listObsGroup(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4) throws DAOException;

	Obs getObsGroupCurrentDate(Integer paramInteger1, Integer paramInteger2) throws DAOException;

	Integer buildConcepts(List<ConceptModel> paramList);

	List<Patient> searchPatient(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, int paramInt3, String paramString4) throws DAOException;

	List<Patient> searchPatient(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, int paramInt3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8) throws DAOException;

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

	PersonAttributeType getPersonAttributeTypeByName(String paramString) throws DAOException;

	Obs getObs(Person paramPerson, Encounter paramEncounter) throws DAOException;

	String getPatientType(Patient paramPatient) throws DAOException;

	List<Obs> getObsInstanceForDiagnosis(Encounter paramEncounter, Concept paramConcept) throws DAOException;
}
