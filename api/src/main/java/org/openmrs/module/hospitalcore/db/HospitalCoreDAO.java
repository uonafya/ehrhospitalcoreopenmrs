/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */


package org.openmrs.module.hospitalcore.db;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.LocationAttribute;
import org.openmrs.Obs;
import org.openmrs.Patient;

import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.Provider;
import org.openmrs.api.APIException;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.appointments.model.Appointment;
import org.openmrs.module.appointments.model.AppointmentServiceType;
import org.openmrs.module.hospitalcore.concept.ConceptModel;
import org.openmrs.module.hospitalcore.model.*;

public interface HospitalCoreDAO {

	public List<Obs> listObsGroup(Integer personId, Integer conceptId, Integer min, Integer max) throws DAOException;
	public Obs getObsGroupCurrentDate(Integer personId, Integer conceptId) throws DAOException;	
	public Integer buildConcepts(List<ConceptModel> conceptModels);
	public List<Patient> searchPatient(String nameOrIdentifier,String gender, int age , int rangeAge, String date, int rangeDay,String relativeName) throws DAOException;
	public List<Patient> searchPatient(String nameOrIdentifier,String gender, int age , int rangeAge, String lastDayOfVisit,
                                       int lastVisit,String relativeName,String maritalStatus,String phoneNumber,
                                       String nationalId,String fileNumber) throws DAOException;
	
	/**
	 * Search patients
	 * @param hql
	 * @return
	 */
	public List<Patient> searchPatient(String hql);
	
	/**
	 * Get patient search result count
	 * @param hql
	 * @return
	 */
	public BigInteger getPatientSearchResultCount(String hql);
	
	/** 
	 * Get all attributes of an patient
	 * @param patientId
	 * @return
	 */
	public List<PersonAttribute> getPersonAttributes(Integer patientId);
	
	/**
	 * Get last visit encounter
	 * @param patient
	 * @param types
	 * @return
	 */
	public Encounter getLastVisitEncounter(Patient patient, List<EncounterType> types);
	
	/**
	 * Save core form
	 * @param form
	 * @return
	 */
	public CoreForm saveCoreForm(CoreForm form);

	/**
	 * Get core form by id
	 * @param id
	 * @return
	 */
	public CoreForm getCoreForm(Integer id);

	/**
	 * Get core forms by name
	 * @param conceptName
	 * @return
	 */
	public List<CoreForm> getCoreForms(String conceptName);

	/**
	 * Get all core forms
	 * @return
	 */
	public List<CoreForm> getCoreForms();

	/**
	 * Delete core form
	 * @param form
	 */
	public void deleteCoreForm(CoreForm form);
	
	/**
	 * Save patientSearch
	 * @param patientSearch
	 * @return
	 */
	public PatientSearch savePatientSearch(PatientSearch patientSearch);
	
	/**
	 * 
	 * Auto generated method comment
	 * 
	 * @param patientID
	 * @return
	 */
	public java.util.Date getLastVisitTime (Patient patientID);
	
	//ghanshyam 3-june-2013 New Requirement #1632 Orders from dashboard must be appear in billing queue.User must be able to generate bills from this queue
	public PatientSearch getPatientByPatientId(int patientId);
	public PatientSearch getPatient(int patientID);
	public List<Obs> getObsByEncounterAndConcept(Encounter encounter,Concept concept);
	public PersonAddress getPersonAddress(Person person);
	public OpdTestOrder getOpdTestOrder(Integer opdOrderId);
	public PersonAttributeType getPersonAttributeTypeByName(String attributeName) throws DAOException;
	public Obs getObs(Person person,Encounter encounter) throws DAOException;
	public String getPatientType(Patient patientId) throws DAOException;
	public List<Obs> getObsInstanceForDiagnosis(Encounter encounter,Concept concept) throws DAOException;
	public List<OpdTestOrder> getAllOpdOrdersByDateRange(boolean today,String fromDate,String toDate);
	public List<PatientServiceBillItem> getAllPatientServiceBillItemsByDate(boolean today,String fromDate,String toDate);

	public PatientCategoryDetails savePatientCategoryDetails(PatientCategoryDetails patientCategoryDetails) throws DAOException;
	public PatientCategoryDetails getPatientCategoryDetailsById(Integer patientDetailsId) throws DAOException;
	public PatientCategoryDetails getPatientCategoryDetailsByPatient(Patient patient) throws DAOException;
	public List<PatientCategoryDetails> getAllPatientCategoryDetails(String property, String value, String startDate, String endDate) throws DAOException;

	//provide several interfaces to handle departmental services
	public EhrDepartment saveDepartment(EhrDepartment ehrDepartment) throws DAOException;
	public EhrDepartment getDepartmentById(Integer departmentId) throws DAOException;
	public List<EhrDepartment> getAllDepartment() throws DAOException;
	public EhrDepartment getDepartmentByName(String departmentName) throws DAOException;

	//provide mechanisms to filter payments
	public List<PatientServiceBillItem> getPatientServiceBillByDepartment(EhrDepartment ehrDepartment, Date startDate, Date endDate) throws DAOException;
	public EhrHospitalWaiver saveEhrHospitalWaiver(EhrHospitalWaiver ehrHospitalWaiver) throws DAOException;
	public EhrHospitalWaiver getEhrHospitalWaiverById(Integer waiverId) throws DAOException;
	public List<EhrHospitalWaiver> getAllEhrHospitalWaiver(Date startDate, Date endDate) throws DAOException;

	//Provide patient sickOff utility methods
	public SickOff getPatientSickOffById(Integer sickOffId) throws DAOException;
	public List<SickOff> getPatientSickOffs(Patient patient, Date startDate, Date endDate) throws DAOException;
	public SickOff savePatientSickOff(SickOff sickOff) throws DAOException;
	public List<SickOff> getPatientSickOffsCreated(Date startDate, Date endDate) throws DAOException;

	public List<Encounter> getProviderEncounters(Date startDate, Date endDate, Provider provider, Collection<EncounterType> encounterTypes) throws DAOException;
	public List<Encounter> getDeadPatientsForEhr(EncounterType encounterType, Date startDate, Date endDate) throws DAOException;
//
	//add the Opd generator functionalities
	public IdentifierNumbersGenerator saveOpdNumbersGenerator(IdentifierNumbersGenerator opdNumbersGenerator) throws DAOException;
	public List<IdentifierNumbersGenerator> getOpdNumbers(Integer type) throws DAOException;

	public IdentifierNumbersGenerator getLastSavedOpdNumber(Integer type) throws DAOException;

	public EhrMorgueQueue saveEhrMorgueQueue(EhrMorgueQueue ehrMorgueQueue) throws DAOException;
	public List<EhrMorgueQueue> getEhrMorgueQueue() throws DAOException;

	public List<Obs> getObsBasedOnClassAndDateRange(Date startDate, Date endDate, Concept concept, EncounterType types, Provider provider) throws DAOException;

	public List<Obs> getObsBasedOnClassAndDateRangeForTestsAndRadiology(Date startDate, Date endDate, ConceptClass conceptClass, EncounterType types, Provider provider) throws DAOException;

	public EhrMorgueStrength saveEhrMorgueStrength(EhrMorgueStrength ehrMorgueStrength) throws DAOException;
	public List<EhrMorgueStrength> getEhrMorgueStrength(Integer retired) throws DAOException;

	public AppointmentServiceType saveAppointmentServiceType(AppointmentServiceType appointmentServiceType) throws DAOException;
	public List<AppointmentServiceType> getAppointmentServiceType() throws DAOException;

	public List<Appointment> getPatientAppointments(Patient patient) throws DAOException;

	public MorgueAdmission saveMorgueAdmission(MorgueAdmission morgueAdmission) throws DAOException;
	public List<MorgueAdmission> getMorgueAdmissionList(Date startDate, Date endDate, Integer status) throws DAOException;

	public MorgueAdmission getMorgueAdmissionById(Integer morgueAdmissionId) throws DAOException;

	public EhrMorgueStrength getEhrMorgueStrengthById(Integer strengthId) throws DAOException;

	public MorgueCompartmentAllocation saveMorgueCompartmentAllocation(MorgueCompartmentAllocation morgueCompatimentAllocation) throws DAOException;
	public List<MorgueCompartmentAllocation> getMorgueCompartmentAllocationList(Integer ehrMorgueStrengtId, Integer allocated) throws DAOException;

	public MorgueCompartmentAllocation getMorgueCompartmentAllocationById(Integer morgueCompartmentAllocationId) throws DAOException;

	public MorgueCompartmentAllocation getMorgueCompartmentAllocationUsed(Integer morgueStrength, String compartmentId) throws DAOException;

	public CertifiedDeceasedList getCertifiedDeceasedListById(Integer certifiedDeceasedListId) throws DAOException;
	public CertifiedDeceasedList saveCertifiedDeceasedList(CertifiedDeceasedList certifiedDeceasedList) throws DAOException;
	public List<CertifiedDeceasedList> getAllCertifiedDeceasedList() throws DAOException;

	public DrugAdministration createDrugAdministration(DrugAdministration drugAdministration) throws DAOException;

	public DrugAdministration updateDrugAdministration(DrugAdministration drugAdministration) throws DAOException;

	public void deleteDrugAdministration(DrugAdministration drugAdministration) throws DAOException;

	List<DrugAdministration> retrieveDrugAdministrations(int patientId) throws DAOException;

	List<Location> getLocationsBasedOnNameOrMflCode(String identifier) throws DAOException;

	public EhrReferralComponent createEhrReferralComponent(EhrReferralComponent ehrReferralComponent) throws DAOException;
	public EhrReferralComponent getEhrReferralComponentById(Integer ehrReferralComponentId) throws DAOException;
	public List<EhrReferralComponent> getEhrReferralComponentList(String identifier) throws DAOException;

	public List<LocationAttribute> getMflCodeFromLocationAttribute(Location location) throws DAOException;

	public MorgueAdmission getMorgueAdmissionByPatient(Patient patient) throws DAOException;

	public MigrationTracking createMigrationPatientTrackingDetails(MigrationTracking migrationTracking) throws DAOException;
	public List<MigrationTracking> getMigrationPatientTrackingDetails() throws DAOException;

	public MigrationVisitsTracking createMigrationVisitsTrackingDetails(MigrationVisitsTracking migrationVisitsTracking) throws DAOException;
	public List<MigrationVisitsTracking> getMigrationVisitsTrackingDetails() throws DAOException;

	public MigrationEncounterTracking createMigrationEncounterTrackingDetails(MigrationEncounterTracking migrationEncounterTracking) throws DAOException;
	public List<MigrationEncounterTracking> getMigrationEncounterTrackingDetails() throws DAOException;

	public MigrationTracking getMigrationPatientTrackingDetailsByOldPatientId(Integer oldPatientId) throws DAOException;

	public MigrationVisitsTracking getMigrationVisitsTrackingDetailsByOldVisitId(Integer oldVisitId) throws DAOException;
	public MigrationEncounterTracking getMigrationEncounterTrackingDetailsByOldEncounterID(Integer id) throws DAOException;

	public MigrationOrders createMigrationOrdersTrackingDetails(MigrationOrders migrationOrders) throws DAOException;
	public MigrationOrders getMigrationOrdersDetailsByOldOrderID(Integer id) throws DAOException;
	public List<MigrationOrders> getMigrationOrdersDetails() throws DAOException;


	public MigrationObsTracking createMigrationObsTrackingDetails(MigrationObsTracking migrationObsTracking) throws DAOException;
	public MigrationObsTracking getMigrationObsTrackingDetailsByOldOrderID(Integer id) throws DAOException;
	public List<MigrationObsTracking> getMigrationObsTrackingDetails() throws DAOException;

	public MigrationObsTracking getLastMigrationObsTracking() throws DAOException;
}
