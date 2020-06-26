package org.openmrs.module.hospitalcore;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.Ambulance;
import org.openmrs.module.hospitalcore.model.AmbulanceBill;
import org.openmrs.module.hospitalcore.model.BillableService;
import org.openmrs.module.hospitalcore.model.Company;
import org.openmrs.module.hospitalcore.model.Driver;
import org.openmrs.module.hospitalcore.model.IndoorPatientServiceBill;
import org.openmrs.module.hospitalcore.model.IndoorPatientServiceBillItem;
import org.openmrs.module.hospitalcore.model.MiscellaneousService;
import org.openmrs.module.hospitalcore.model.MiscellaneousServiceBill;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientSearch;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.module.hospitalcore.model.Receipt;
import org.openmrs.module.hospitalcore.model.Tender;
import org.openmrs.module.hospitalcore.model.TenderBill;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BillingService extends OpenmrsService {
	@Transactional(readOnly = true)
	@Authorized({"View Tenders"})
	List<Tender> listTender(int paramInt1, int paramInt2) throws APIException;

	@Authorized({"Add/Edit Tender"})
	Tender saveTender(Tender paramTender) throws APIException;

	@Authorized({"View Tenders"})
	int countListTender() throws APIException;

	@Authorized({"View Tenders"})
	Tender getTenderById(Integer paramInteger) throws APIException;

	@Authorized({"Delete Tender"})
	void deleteTender(Tender paramTender) throws APIException;

	@Authorized({"View Tenders"})
	Tender getTenderByNameAndNumber(String paramString, int paramInt) throws APIException;

	@Authorized({"View Tenders"})
	List<Tender> getActiveTenders() throws APIException;

	@Authorized({"Add/Edit Company"})
	Company saveCompany(Company paramCompany) throws APIException;

	@Authorized({"Delete Company"})
	void deleteCompany(Company paramCompany) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Companies"})
	List<Company> listCompany(int paramInt1, int paramInt2) throws APIException;

	@Authorized({"View Companies"})
	int countListCompany() throws APIException;

	@Authorized({"View Companies"})
	Company getCompanyById(Integer paramInteger) throws APIException;

	@Authorized({"View Companies"})
	Company getCompanyByName(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Companies"})
	List<Company> searchCompany(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Companies"})
	List<Company> getAllCompany() throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Companies"})
	List<Company> getAllActiveCompany() throws APIException;

	@Authorized({"Add/Edit Driver"})
	Driver saveDriver(Driver paramDriver) throws APIException;

	@Authorized({"Delete Driver"})
	void deleteDriver(Driver paramDriver) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Drivers"})
	List<Driver> listDriver(int paramInt1, int paramInt2) throws APIException;

	@Authorized({"View Drivers"})
	int countListDriver() throws APIException;

	@Authorized({"View Drivers"})
	Driver getDriverById(Integer paramInteger) throws APIException;

	@Authorized({"View Drivers"})
	Driver getDriverByName(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Drivers"})
	List<Driver> searchDriver(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Drivers"})
	List<Driver> getAllDriver() throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Drivers"})
	List<Driver> getAllActiveDriver() throws APIException;

	@Authorized({"View Bills"})
	TenderBill getTenderBillById(Integer paramInteger) throws APIException;

	@Authorized({"Add Bill"})
	TenderBill saveTenderBill(TenderBill paramTenderBill) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Bills"})
	List<TenderBill> listTenderBillByCompany(int paramInt1, int paramInt2, Company paramCompany) throws APIException;

	@Authorized({"View Bills"})
	int countListTenderBillByCompany(Company paramCompany) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Bills"})
	List<TenderBill> getAllTenderBill() throws APIException;

	@Authorized({"Add/Edit Ambulance"})
	Ambulance saveAmbulance(Ambulance paramAmbulance) throws APIException;

	@Authorized({"Delete Ambulance"})
	void deleteAmbulance(Ambulance paramAmbulance) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Ambulances"})
	List<Ambulance> listAmbulance(int paramInt1, int paramInt2) throws APIException;

	@Authorized({"View Ambulances"})
	int countListAmbulance() throws APIException;

	@Authorized({"View Ambulances"})
	Ambulance getAmbulanceById(Integer paramInteger) throws APIException;

	@Authorized({"View Ambulances"})
	Ambulance getAmbulanceByName(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Ambulances"})
	List<Ambulance> getAllAmbulance() throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Ambulances"})
	List<Ambulance> getActiveAmbulances() throws APIException;

	@Authorized({"View Bills"})
	AmbulanceBill getAmbulanceBillById(Integer paramInteger) throws APIException;

	@Authorized({"Add Bill"})
	AmbulanceBill saveAmbulanceBill(AmbulanceBill paramAmbulanceBill) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Bills"})
	List<AmbulanceBill> listAmbulanceBillByDriver(int paramInt1, int paramInt2, Driver paramDriver) throws APIException;

	@Authorized({"View Bills"})
	int countListAmbulanceBillByDriver(Driver paramDriver) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Bills"})
	List<AmbulanceBill> getAllAmbulanceBill() throws APIException;

	@Transactional(readOnly = true)
	BillableService getServiceById(Integer paramInteger) throws APIException;

	@Transactional(readOnly = true)
	List<BillableService> getAllServices() throws APIException;

	@Transactional(readOnly = true)
	List<BillableService> searchService(String paramString) throws APIException;

	BillableService saveService(BillableService paramBillableService) throws APIException;

	@Transactional(readOnly = true)
	BillableService getServiceByConceptId(Integer paramInteger) throws APIException;

	String traversServices(Concept paramConcept, Map<Integer, BillableService> paramMap) throws APIException;

	void saveServices(Collection<BillableService> paramCollection) throws APIException;

	String traversTab(Concept paramConcept, Map<Integer, BillableService> paramMap, int paramInt) throws APIException;

	void disableService(Integer paramInteger) throws APIException;

	@Authorized({"View Bills"})
	PatientServiceBill getPatientServiceBillById(Integer paramInteger) throws APIException;

	IndoorPatientServiceBill getIndoorPatientServiceBillById(Integer paramInteger) throws APIException;

	@Authorized({"View Bills"})
	PatientServiceBill getPatientServiceBillByEncounter(Encounter paramEncounter) throws APIException;

	@Authorized({"View Bills"})
	List<IndoorPatientServiceBill> getIndoorPatientServiceBillByEncounter(Encounter paramEncounter) throws APIException;

	@Authorized({"Add Bill"})
	PatientServiceBill savePatientServiceBill(PatientServiceBill paramPatientServiceBill) throws APIException;

	IndoorPatientServiceBill saveIndoorPatientServiceBill(IndoorPatientServiceBill paramIndoorPatientServiceBill) throws APIException;

	void deleteIndoorPatientServiceBill(IndoorPatientServiceBill paramIndoorPatientServiceBill) throws APIException;

	@Authorized({"Add Bill"})
	void saveBillEncounterAndOrder(PatientServiceBill paramPatientServiceBill) throws APIException;

	void saveBillEncounterAndOrderForIndoorPatient(IndoorPatientServiceBill paramIndoorPatientServiceBill) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Bills"})
	List<PatientServiceBill> listPatientServiceBillByPatient(int paramInt1, int paramInt2, Patient paramPatient) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Bills"})
	List<IndoorPatientServiceBill> listIndoorPatientServiceBillByPatient(int paramInt1, int paramInt2, Patient paramPatient) throws APIException;

	@Authorized({"View Bills"})
	int countListPatientServiceBillByPatient(Patient paramPatient) throws APIException;

	@Authorized({"View Bills"})
	int countListIndoorPatientServiceBillByPatient(Patient paramPatient) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Bills"})
	List<PatientServiceBill> getAllPatientServiceBill() throws APIException;

	@Authorized({"Edit Bill"})
	void voidBill(PatientServiceBill paramPatientServiceBill) throws APIException;

	@Authorized({"View Bills"})
	PatientServiceBill getPatientServiceBillByReceiptId(Integer paramInteger) throws APIException;

	@Authorized({"View Bills"})
	MiscellaneousServiceBill getMiscellaneousServiceBillById(Integer paramInteger) throws APIException;

	@Authorized({"Add Bill"})
	MiscellaneousServiceBill saveMiscellaneousServiceBill(MiscellaneousServiceBill paramMiscellaneousServiceBill) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Bills"})
	List<MiscellaneousServiceBill> listMiscellaneousServiceBill(int paramInt1, int paramInt2) throws APIException;

	@Authorized({"View Bills"})
	int countListMiscellaneousServiceBill() throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Bills"})
	List<MiscellaneousServiceBill> listMiscellaneousServiceBill(int paramInt1, int paramInt2, MiscellaneousService paramMiscellaneousService) throws APIException;

	@Authorized({"View Bills"})
	int countListMiscellaneousServiceBill(MiscellaneousService paramMiscellaneousService) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Bills"})
	List<MiscellaneousServiceBill> getAllMiscellaneousServiceBill() throws APIException;

	@Authorized({"View Miscellaneous Service"})
	MiscellaneousService getMiscellaneousServiceById(Integer paramInteger) throws APIException;

	@Authorized({"Add/Edit Miscellaneous Service"})
	MiscellaneousService saveMiscellaneousService(MiscellaneousService paramMiscellaneousService) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Miscellaneous Service"})
	List<MiscellaneousService> listMiscellaneousService(int paramInt1, int paramInt2) throws APIException;

	@Authorized({"View Miscellaneous Service"})
	int countListMiscellaneousService() throws APIException;

	@Transactional(readOnly = true)
	@Authorized({"View Miscellaneous Service"})
	List<MiscellaneousService> getAllMiscellaneousService() throws APIException;

	@Authorized({"Delete Miscellaneous Service"})
	void deleteMiscellaneousService(MiscellaneousService paramMiscellaneousService) throws APIException;

	@Authorized({"View Miscellaneous Service"})
	MiscellaneousService getMiscellaneousServiceByName(String paramString) throws APIException;

	@Authorized({"Add Bill"})
	Receipt createReceipt() throws APIException;

	@Authorized({"Add Bill"})
	void updateReceipt() throws APIException;

	void updateOldBills();

	List<PatientSearch> searchListOfPatient(Date paramDate, String paramString, int paramInt) throws APIException;

	List<PatientSearch> searchListOfPatient(Date paramDate, String paramString, int paramInt1, int paramInt2) throws APIException;

	int countSearchListOfPatient(Date paramDate, String paramString, int paramInt) throws APIException;

	List<PatientSearch> listOfPatient() throws APIException;

	List<BillableService> listOfServiceOrder(Integer paramInteger1, Integer paramInteger2) throws APIException;

	BillableService getServiceByConceptName(String paramString) throws APIException;

	List<OpdTestOrder> listOfOrder(Integer paramInteger, Date paramDate) throws APIException;

	OpdTestOrder getOpdTestOrder(Integer paramInteger1, Integer paramInteger2) throws APIException;

	PatientServiceBillItem getPatientServiceBillItem(Integer paramInteger, String paramString) throws APIException;

	IndoorPatientServiceBillItem getIndoorPatientServiceBillItem(String paramString, List<IndoorPatientServiceBill> paramList) throws APIException;

	void updateVoidBillItems(Boolean paramBoolean, String paramString, Date paramDate, Integer paramInteger);

	void updatePatientCategory(Integer paramInteger, Encounter paramEncounter, Patient paramPatient);

	List<IndoorPatientServiceBill> getSelectedCategory(Encounter paramEncounter, Patient paramPatient);
}
