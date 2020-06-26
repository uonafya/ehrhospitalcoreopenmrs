package org.openmrs.module.hospitalcore.db.hibernate;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.PersonName;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.concept.ConceptModel;
import org.openmrs.module.hospitalcore.concept.Mapping;
import org.openmrs.module.hospitalcore.db.HospitalCoreDAO;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientSearch;
import org.openmrs.module.hospitalcore.util.DateUtils;
import org.openmrs.module.hospitalcore.util.HospitalCoreConstants;

public class HibernateHospitalCoreDAO implements HospitalCoreDAO {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	SimpleDateFormat formatterExt = new SimpleDateFormat("dd/MM/yyyy");

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Obs> listObsGroup(Integer personId, Integer conceptId, Integer min, Integer max) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class, "obs").add((Criterion)Restrictions.eq("obs.person.personId", personId)).add((Criterion)Restrictions.eq("obs.concept.conceptId", conceptId)).add(Restrictions.isNull("obs.obsGroup")).addOrder(Order.desc("obs.dateCreated"));
		if (max.intValue() > 0)
			criteria.setFirstResult(min.intValue()).setMaxResults(max.intValue());
		List<Obs> list = criteria.list();
		return list;
	}

	public Obs getObsGroupCurrentDate(Integer personId, Integer conceptId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class, "obs").add((Criterion)Restrictions.eq("obs.person.personId", personId)).add((Criterion)Restrictions.eq("obs.concept.conceptId", conceptId)).add(Restrictions.isNull("obs.obsGroup"));
		String date = this.formatterExt.format(new Date());
		String startFromDate = date + " 00:00:00";
		String endFromDate = date + " 23:59:59";
		try {
			criteria.add((Criterion)Restrictions.and((Criterion)Restrictions.ge("obs.dateCreated", this.formatter.parse(startFromDate)), (Criterion)Restrictions.le("obs.dateCreated", this.formatter.parse(endFromDate))));
		} catch (Exception e) {
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		List<Obs> list = criteria.list();
		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}

	public Integer buildConcepts(List<ConceptModel> conceptModels) {
		HospitalCoreService hcs = (HospitalCoreService)Context.getService(HospitalCoreService.class);
		Session session = this.sessionFactory.getCurrentSession();
		Integer diagnosisNo = Integer.valueOf(0);
		for (int i = 0; i < conceptModels.size(); i++) {
			ConceptModel conceptModel = conceptModels.get(i);
			Concept concept = hcs.insertConcept(conceptModel.getConceptDatatype(), conceptModel.getConceptClass(), conceptModel.getName(), "", conceptModel.getDescription());
			System.out.println("concept ==> " + concept.getId());
			for (String synonym : conceptModel.getSynonyms())
				hcs.insertSynonym(concept, synonym);
			for (Mapping mapping : conceptModel.getMappings())
				hcs.insertMapping(concept, mapping.getSource(), mapping.getSourceCode());
			if (i % 20 == 0) {
				session.flush();
				session.clear();
				System.out.println("Imported " + (i + 1) + " diagnosis (" + (i / conceptModels.size() * 100) + "%)");
			}
			Integer integer1 = diagnosisNo, integer2 = diagnosisNo = Integer.valueOf(diagnosisNo.intValue() + 1);
		}
		return diagnosisNo;
	}

	public List<Patient> searchPatient(String nameOrIdentifier, String gender, int age, int rangeAge, String date, int rangeDay, String relativeName) throws DAOException {
		List<Patient> patients = new Vector<Patient>();
		String hql = "SELECT DISTINCT p.patient_id,pi.identifier,pn.given_name ,pn.middle_name ,pn.family_name ,ps.gender,ps.birthdate ,EXTRACT(YEAR FROM (FROM_DAYS(DATEDIFF(NOW(),ps.birthdate)))) age,pn.person_name_id FROM patient p INNER JOIN person ps ON p.patient_id = ps.person_id INNER JOIN patient_identifier pi ON p.patient_id = pi.patient_id INNER JOIN person_name pn ON p.patient_id = pn.person_id INNER JOIN person_attribute pa ON p.patient_id= pa.person_id INNER JOIN person_attribute_type pat ON pa.person_attribute_type_id = pat.person_attribute_type_id WHERE (pi.identifier like '%" + nameOrIdentifier + "%' " + "OR pn.given_name like '" + nameOrIdentifier + "%' " + "OR pn.middle_name like '" + nameOrIdentifier + "%' " + "OR pn.family_name like '" + nameOrIdentifier + "%') ";
		if (StringUtils.isNotBlank(gender))
			hql = hql + " AND ps.gender = '" + gender + "' ";
		if (StringUtils.isNotBlank(relativeName))
			hql = hql + " AND pat.person_attribute_type_id = " + HospitalCoreConstants.NEXT_OF_KIN_PERSON_ATTRIBUTE_ID + " AND pa.value like '%" + relativeName + "%' ";
		if (StringUtils.isNotBlank(date)) {
			String startDate = DateUtils.getDateFromRange(date, -rangeDay) + " 00:00:00";
			String endtDate = DateUtils.getDateFromRange(date, rangeDay) + " 23:59:59";
			hql = hql + " AND ps.birthdate BETWEEN '" + startDate + "' AND '" + endtDate + "' ";
		}
		if (age > 0)
			hql = hql + " AND EXTRACT(YEAR FROM (FROM_DAYS(DATEDIFF(NOW(),ps.birthdate)))) >=" + (age - rangeAge) + " AND EXTRACT(YEAR FROM (FROM_DAYS(DATEDIFF(NOW(),ps.birthdate)))) <= " + (age + rangeAge) + " ";
		hql = hql + " ORDER BY p.patient_id ASC";
		SQLQuery sQLQuery = this.sessionFactory.getCurrentSession().createSQLQuery(hql);
		List l = sQLQuery.list();
		if (CollectionUtils.isNotEmpty(l))
			for (Object obj : l) {
				Object[] obss = (Object[])obj;
				if (obss != null && obss.length > 0) {
					Person person = new Person((Integer)obss[0]);
					PersonName personName = new PersonName((Integer)obss[8]);
					personName.setGivenName((String)obss[2]);
					personName.setMiddleName((String)obss[3]);
					personName.setFamilyName((String)obss[4]);
					personName.setPerson(person);
					Set<PersonName> names = new HashSet<PersonName>();
					names.add(personName);
					person.setNames(names);
					Patient patient = new Patient(person);
					PatientIdentifier patientIdentifier = new PatientIdentifier();
					patientIdentifier.setPatient(patient);
					patientIdentifier.setIdentifier((String)obss[1]);
					Set<PatientIdentifier> identifier = new HashSet<PatientIdentifier>();
					identifier.add(patientIdentifier);
					patient.setIdentifiers(identifier);
					patient.setGender((String)obss[5]);
					patient.setBirthdate((Date)obss[6]);
					patients.add(patient);
				}
			}
		return patients;
	}

	public List<Patient> searchPatient(String nameOrIdentifier, String gender, int age, int rangeAge, String lastDayOfVisit, int lastVisit, String relativeName, String maritalStatus, String phoneNumber, String nationalId, String fileNumber) throws DAOException {
		List<Patient> patients = new Vector<Patient>();
		String[] searchSplit = nameOrIdentifier.split("\\s+");
		String hql = "SELECT DISTINCT p.patient_id,pi.identifier,pn.given_name ,pn.middle_name ,pn.family_name ,ps.gender,ps.birthdate,pse.dead,pse.admitted, pse.person_name_id ,EXTRACT(YEAR FROM (FROM_DAYS(DATEDIFF(NOW(),ps.birthdate)))) age FROM patient p INNER JOIN person ps ON p.patient_id = ps.person_id INNER JOIN patient_identifier pi ON p.patient_id = pi.patient_id INNER JOIN person_name pn ON p.patient_id = pn.person_id INNER JOIN person_attribute pa ON p.patient_id= pa.person_id INNER JOIN person_attribute_type pat ON pa.person_attribute_type_id = pat.person_attribute_type_id INNER JOIN patient_search pse ON p.patient_id = pse.patient_id INNER JOIN encounter en ON p.patient_id = en.patient_id WHERE (pi.identifier like '%" + nameOrIdentifier + "%' ";
		if (searchSplit.length == 1) {
			for (String s : searchSplit)
				hql = hql + "OR pn.given_name like '%" + s + "%' " + "OR pn.middle_name like '%" + s + "%' " + "OR pn.family_name like '%" + s + "%' ";
		} else if (searchSplit.length == 2) {
			hql = hql + "OR pn.given_name like '%" + searchSplit[0] + "%' " + "AND (pn.middle_name like '%" + searchSplit[1] + "%' " + "OR pn.family_name like '%" + searchSplit[1] + "%') ";
		} else if (searchSplit.length == 3) {
			hql = hql + "OR pn.given_name like '%" + searchSplit[0] + "%' " + "AND pn.middle_name like '%" + searchSplit[1] + "%' " + "AND pn.family_name like '%" + searchSplit[2] + "%'";
		}
		hql = hql + ") ";
		if (StringUtils.isNotBlank(gender))
			hql = hql + " AND ps.gender = '" + gender + "' ";
		if (StringUtils.isNotBlank(relativeName))
			hql = hql + " AND pat.name = 'Father/Husband Name' AND pa.value like '" + relativeName + "' ";
		if (age > 0)
			hql = hql + " AND EXTRACT(YEAR FROM (FROM_DAYS(DATEDIFF(NOW(),ps.birthdate)))) >=" + (age - rangeAge) + " AND EXTRACT(YEAR FROM (FROM_DAYS(DATEDIFF(NOW(),ps.birthdate)))) <= " + (age + rangeAge) + " ";
		if (StringUtils.isNotBlank(lastDayOfVisit))
			hql = hql + " AND (DATE_FORMAT(DATE(en.encounter_datetime),'%d/%m/%Y') = '" + lastDayOfVisit + "')";
		if (lastVisit > 0)
			hql = hql + " AND (DATEDIFF(NOW(), en.date_created) <= " + lastVisit + ")";
		if (StringUtils.isNotBlank(maritalStatus))
			hql = hql + "AND (pat.name LIKE '%Marital Status%' AND pa.value = '" + maritalStatus + "') ";
		if (StringUtils.isNotBlank(nationalId))
			hql = hql + "AND (pat.name LIKE '%National ID%' AND pa.value = '" + nationalId + "') ";
		if (StringUtils.isNotBlank(phoneNumber))
			hql = hql + "AND (pat.name LIKE '%Phone Number%' AND pa.value = '" + phoneNumber + "') ";
		if (StringUtils.isNotBlank(fileNumber))
			hql = hql + "AND (pat.name LIKE '%File Number%' AND pa.value = '" + fileNumber + "') ";
		hql = hql + " ORDER BY p.patient_id ASC LIMIT 0, 50";
		SQLQuery sQLQuery = this.sessionFactory.getCurrentSession().createSQLQuery(hql);
		List l = sQLQuery.list();
		if (CollectionUtils.isNotEmpty(l))
			for (Object obj : l) {
				Object[] obss = (Object[])obj;
				if (obss != null && obss.length > 0) {
					Person person = new Person((Integer)obss[0]);
					PersonName personName = new PersonName((Integer)obss[9]);
					personName.setGivenName((String)obss[2]);
					personName.setMiddleName((String)obss[3]);
					personName.setFamilyName((String)obss[4]);
					personName.setPerson(person);
					Set<PersonName> names = new HashSet<PersonName>();
					names.add(personName);
					person.setNames(names);
					Patient patient = new Patient(person);
					PatientIdentifier patientIdentifier = new PatientIdentifier();
					patientIdentifier.setPatient(patient);
					patientIdentifier.setIdentifier((String)obss[1]);
					Set<PatientIdentifier> identifier = new HashSet<PatientIdentifier>();
					identifier.add(patientIdentifier);
					patient.setIdentifiers(identifier);
					patient.setGender((String)obss[5]);
					patient.setBirthdate((Date)obss[6]);
					if (obss[7] != null)
						if (obss[7].toString().equals("1")) {
							patient.setDead(Boolean.valueOf(true));
						} else if (obss[7].toString().equals("0")) {
							patient.setDead(Boolean.valueOf(false));
						}
					if (obss[8] != null)
						if (obss[8].toString().equals("1")) {
							patient.setVoided(Boolean.valueOf(true));
						} else if (obss[8].toString().equals("0")) {
							patient.setVoided(Boolean.valueOf(false));
						}
					patients.add(patient);
				}
			}
		return patients;
	}

	public List<Patient> searchPatient(String hql) {
		List<Patient> patients = new Vector<Patient>();
		SQLQuery sQLQuery = this.sessionFactory.getCurrentSession().createSQLQuery(hql);
		List list = sQLQuery.list();
		if (CollectionUtils.isNotEmpty(list))
			for (Object obj : list) {
				Object[] obss = (Object[])obj;
				if (obss != null && obss.length > 0) {
					Person person = new Person((Integer)obss[0]);
					PersonName personName = new PersonName((Integer)obss[8]);
					personName.setGivenName((String)obss[2]);
					personName.setMiddleName((String)obss[3]);
					personName.setFamilyName((String)obss[4]);
					personName.setPerson(person);
					Set<PersonName> names = new HashSet<PersonName>();
					names.add(personName);
					person.setNames(names);
					Patient patient = new Patient(person);
					PatientIdentifier patientIdentifier = new PatientIdentifier();
					patientIdentifier.setPatient(patient);
					patientIdentifier.setIdentifier((String)obss[1]);
					Set<PatientIdentifier> identifier = new HashSet<PatientIdentifier>();
					identifier.add(patientIdentifier);
					patient.setIdentifiers(identifier);
					patient.setGender((String)obss[5]);
					patient.setBirthdate((Date)obss[6]);
					if (obss.length > 9 &&
							obss[9] != null)
						if (obss[9].toString().equals("1")) {
							patient.setDead(Boolean.valueOf(true));
						} else if (obss[9].toString().equals("0")) {
							patient.setDead(Boolean.valueOf(false));
						}
					if (obss.length > 10 &&
							obss[10] != null)
						if (obss[10].toString().equals("1")) {
							patient.setVoided(Boolean.valueOf(true));
						} else if (obss[10].toString().equals("0")) {
							patient.setVoided(Boolean.valueOf(false));
						}
					patients.add(patient);
				}
			}
		return patients;
	}

	public BigInteger getPatientSearchResultCount(String hql) {
		BigInteger count = new BigInteger("0");
		SQLQuery sQLQuery = this.sessionFactory.getCurrentSession().createSQLQuery(hql);
		List<BigInteger> list = sQLQuery.list();
		if (CollectionUtils.isNotEmpty(list))
			count = list.get(0);
		return count;
	}

	public List<PersonAttribute> getPersonAttributes(Integer patientId) {
		List<PersonAttribute> attributes = new ArrayList<PersonAttribute>();
		String hql = "SELECT pa.person_attribute_type_id, pa.`value` FROM person_attribute pa WHERE pa.person_id = " + patientId + " AND pa.voided = 0;";
		SQLQuery sQLQuery = this.sessionFactory.getCurrentSession().createSQLQuery(hql);
		List l = sQLQuery.list();
		if (CollectionUtils.isNotEmpty(l))
			for (Object obj : l) {
				Object[] obss = (Object[])obj;
				if (obss != null && obss.length > 0) {
					PersonAttribute attribute = new PersonAttribute();
					PersonAttributeType type = new PersonAttributeType((Integer)obss[0]);
					attribute.setAttributeType(type);
					attribute.setValue((String)obss[1]);
					attributes.add(attribute);
				}
			}
		return attributes;
	}

	public Encounter getLastVisitEncounter(Patient patient, List<EncounterType> types) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Encounter.class);
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		criteria.add(Restrictions.in("encounterType", types));
		criteria.addOrder(Order.desc("encounterDatetime"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);
		return (Encounter)criteria.uniqueResult();
	}

	public CoreForm saveCoreForm(CoreForm form) {
		return (CoreForm)this.sessionFactory.getCurrentSession().merge(form);
	}

	public CoreForm getCoreForm(Integer id) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CoreForm.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		return (CoreForm)criteria.uniqueResult();
	}

	public List<CoreForm> getCoreForms(String conceptName) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CoreForm.class);
		criteria.add((Criterion)Restrictions.eq("conceptName", conceptName));
		return criteria.list();
	}

	public List<CoreForm> getCoreForms() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CoreForm.class);
		return criteria.list();
	}

	public void deleteCoreForm(CoreForm form) {
		this.sessionFactory.getCurrentSession().delete(form);
	}

	public PatientSearch savePatientSearch(PatientSearch patientSearch) {
		return (PatientSearch)this.sessionFactory.getCurrentSession().merge(patientSearch);
	}

	public Date getLastVisitTime(Patient patientID) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Encounter.class);
		Encounter encounter = new Encounter();
		criteria.add((Criterion)Restrictions.eq("patient", patientID));
		criteria.addOrder(Order.desc("encounterId"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);
		encounter = (Encounter)criteria.uniqueResult();
		return (encounter == null) ? null : encounter.getEncounterDatetime();
	}

	public PatientSearch getPatientByPatientId(int patientId) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientSearch.class);
		criteria.add((Criterion)Restrictions.eq("patientId", Integer.valueOf(patientId)));
		return (PatientSearch)criteria.uniqueResult();
	}

	public PatientSearch getPatient(int patientID) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientSearch.class);
		criteria.add((Criterion)Restrictions.eq("patientId", Integer.valueOf(patientID)));
		return (PatientSearch)criteria.uniqueResult();
	}

	public List<Obs> getObsByEncounterAndConcept(Encounter encounter, Concept concept) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class);
		criteria.add((Criterion)Restrictions.eq("encounter", encounter));
		criteria.add((Criterion)Restrictions.eq("concept", concept));
		return criteria.list();
	}

	public PersonAddress getPersonAddress(Person person) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PersonAddress.class);
		criteria.add((Criterion)Restrictions.eq("person", person));
		return (PersonAddress)criteria.uniqueResult();
	}

	public OpdTestOrder getOpdTestOrder(Integer opdOrderId) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdTestOrder.class);
		criteria.add((Criterion)Restrictions.eq("opdOrderId", opdOrderId));
		return (OpdTestOrder)criteria.uniqueResult();
	}

	public PersonAttributeType getPersonAttributeTypeByName(String attributeName) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PersonAttributeType.class);
		criteria.add((Criterion)Restrictions.eq("name", attributeName));
		return (PersonAttributeType)criteria.uniqueResult();
	}

	public Obs getObs(Person person, Encounter encounter) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class);
		criteria.add((Criterion)Restrictions.eq("person", person));
		criteria.add((Criterion)Restrictions.eq("encounter", encounter));
		criteria.add((Criterion)Restrictions.eq("concept", Context.getConceptService().getConcept("REGISTRATION FEE")));
		return (Obs)criteria.uniqueResult();
	}

	public String getPatientType(Patient patientId) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IpdPatientAdmitted.class);
		criteria.add((Criterion)Restrictions.eq("patient", patientId));
		criteria.list();
		if (criteria.list().size() > 0)
			return "ipdPatient";
		return "opdPatient";
	}

	public List<Obs> getObsInstanceForDiagnosis(Encounter encounter, Concept concept) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Obs.class);
		criteria.add((Criterion)Restrictions.eq("encounter", encounter));
		criteria.add((Criterion)Restrictions.eq("concept", concept));
		return criteria.list();
	}
}
