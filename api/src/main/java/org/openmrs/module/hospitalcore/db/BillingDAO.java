package org.openmrs.module.hospitalcore.db;

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
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

public interface BillingDAO {
	void setSessionFactory(SessionFactory paramSessionFactory) throws DAOException;

	Tender saveTender(Tender paramTender) throws DAOException;

	List<Tender> listTender(int paramInt1, int paramInt2) throws DAOException;

	int countListTender() throws DAOException;

	Tender getTenderById(Integer paramInteger) throws DAOException;

	void deleteTender(Tender paramTender) throws DAOException;

	Tender getTenderByNameAndNumber(String paramString, int paramInt) throws DAOException;

	List<Tender> getActiveTenders() throws DAOException;

	Company saveCompany(Company paramCompany) throws DAOException;

	void deleteCompany(Company paramCompany) throws DAOException;

	List<Company> listCompany(int paramInt1, int paramInt2) throws DAOException;

	int countListCompany() throws DAOException;

	Company getCompanyById(Integer paramInteger) throws DAOException;

	Company getCompanyByName(String paramString) throws DAOException;

	List<Company> searchCompany(String paramString) throws DAOException;

	List<Company> getAllCompany() throws DAOException;

	List<Company> getAllActiveCompany() throws DAOException;

	Driver saveDriver(Driver paramDriver) throws DAOException;

	void deleteDriver(Driver paramDriver) throws DAOException;

	List<Driver> listDriver(int paramInt1, int paramInt2) throws DAOException;

	int countListDriver() throws DAOException;

	Driver getDriverById(Integer paramInteger) throws DAOException;

	Driver getDriveryByName(String paramString) throws DAOException;

	List<Driver> searchDriver(String paramString) throws DAOException;

	List<Driver> getAllDriver() throws DAOException;

	List<Driver> getAllActiveDriver() throws DAOException;

	TenderBill getTenderBillById(Integer paramInteger) throws DAOException;

	TenderBill saveTenderBill(TenderBill paramTenderBill) throws DAOException;

	List<TenderBill> listTenderBillByCompany(int paramInt1, int paramInt2, Company paramCompany) throws DAOException;

	int countListTenderBillByCompany(Company paramCompany) throws DAOException;

	List<TenderBill> getAllTenderBill() throws DAOException;

	Ambulance saveAmbulance(Ambulance paramAmbulance) throws DAOException;

	void deleteAmbulance(Ambulance paramAmbulance) throws DAOException;

	List<Ambulance> listAmbulance(int paramInt1, int paramInt2) throws DAOException;

	int countListAmbulance() throws DAOException;

	Ambulance getAmbulanceById(Integer paramInteger) throws DAOException;

	Ambulance getAmbulanceByName(String paramString) throws DAOException;

	List<Ambulance> getAllAmbulance() throws DAOException;

	List<Ambulance> getActiveAmbulances() throws DAOException;

	AmbulanceBill getAmbulanceBillById(Integer paramInteger) throws DAOException;

	AmbulanceBill saveAmbulanceBill(AmbulanceBill paramAmbulanceBill) throws DAOException;

	List<AmbulanceBill> listAmbulanceBillByDriver(int paramInt1, int paramInt2, Driver paramDriver) throws DAOException;

	int countListAmbulanceBillByDriver(Driver paramDriver) throws DAOException;

	List<AmbulanceBill> getAllAmbulanceBill() throws DAOException;

	BillableService getServiceById(Integer paramInteger) throws DAOException;

	List<BillableService> getAllServices() throws DAOException;

	List<BillableService> searchService(String paramString) throws DAOException;

	BillableService saveService(BillableService paramBillableService) throws DAOException;

	BillableService getServiceByConceptId(Integer paramInteger) throws DAOException;

	void disableService(Integer paramInteger) throws DAOException;

	PatientServiceBill getPatientServiceBillById(Integer paramInteger) throws DAOException;

	IndoorPatientServiceBill getIndoorPatientServiceBillById(Integer paramInteger) throws DAOException;

	PatientServiceBill getPatientServiceBillByEncounter(Encounter paramEncounter) throws DAOException;

	List<IndoorPatientServiceBill> getIndoorPatientServiceBillByEncounter(Encounter paramEncounter) throws DAOException;

	PatientServiceBill savePatientServiceBill(PatientServiceBill paramPatientServiceBill) throws DAOException;

	IndoorPatientServiceBill saveIndoorPatientServiceBill(IndoorPatientServiceBill paramIndoorPatientServiceBill) throws DAOException;

	void deleteIndoorPatientServiceBill(IndoorPatientServiceBill paramIndoorPatientServiceBill) throws DAOException;

	List<PatientServiceBill> listPatientServiceBillByPatient(int paramInt1, int paramInt2, Patient paramPatient) throws DAOException;

	List<IndoorPatientServiceBill> listIndoorPatientServiceBillByPatient(int paramInt1, int paramInt2, Patient paramPatient) throws DAOException;

	int countListPatientServiceBillByPatient(Patient paramPatient) throws DAOException;

	int countListIndoorPatientServiceBillByPatient(Patient paramPatient) throws DAOException;

	List<PatientServiceBill> getAllPatientServiceBill() throws DAOException;

	PatientServiceBill getPatientServiceBillByReceiptId(Integer paramInteger) throws DAOException;

	MiscellaneousServiceBill getMiscellaneousServiceBillById(Integer paramInteger) throws DAOException;

	MiscellaneousServiceBill saveMiscellaneousServiceBill(MiscellaneousServiceBill paramMiscellaneousServiceBill) throws DAOException;

	List<MiscellaneousServiceBill> listMiscellaneousServiceBill(int paramInt1, int paramInt2) throws DAOException;

	int countListMiscellaneousServiceBill() throws DAOException;

	List<MiscellaneousServiceBill> listMiscellaneousServiceBill(int paramInt1, int paramInt2, MiscellaneousService paramMiscellaneousService) throws DAOException;

	int countListMiscellaneousServiceBill(MiscellaneousService paramMiscellaneousService) throws DAOException;

	List<MiscellaneousServiceBill> getAllMiscellaneousServiceBill() throws DAOException;

	MiscellaneousService getMiscellaneousServiceById(Integer paramInteger) throws DAOException;

	MiscellaneousService saveMiscellaneousService(MiscellaneousService paramMiscellaneousService) throws DAOException;

	List<MiscellaneousService> listMiscellaneousService(int paramInt1, int paramInt2) throws DAOException;

	int countListMiscellaneousService() throws DAOException;

	List<MiscellaneousService> getAllMiscellaneousService() throws DAOException;

	void deleteMiscellaneousService(MiscellaneousService paramMiscellaneousService) throws DAOException;

	MiscellaneousService getMiscellaneousServiceByName(String paramString) throws DAOException;

	Receipt createReceipt(Receipt paramReceipt) throws DAOException;

	void updateReceipt() throws DAOException;

	void updateOldBills();

	List<PatientSearch> searchListOfPatient(Date paramDate, String paramString, int paramInt) throws DAOException;

	List<PatientSearch> searchListOfPatient(Date paramDate, String paramString, int paramInt1, int paramInt2) throws DAOException;

	int countSearchListOfPatient(Date paramDate, String paramString, int paramInt) throws DAOException;

	List<PatientSearch> listOfPatient() throws DAOException;

	List<BillableService> listOfServiceOrder(Integer paramInteger1, Integer paramInteger2) throws DAOException;

	BillableService getServiceByConceptName(String paramString) throws DAOException;

	List<OpdTestOrder> listOfOrder(Integer paramInteger, Date paramDate) throws DAOException;

	OpdTestOrder getOpdTestOrder(Integer paramInteger1, Integer paramInteger2) throws DAOException;

	PatientServiceBillItem getPatientServiceBillItem(Integer paramInteger, String paramString) throws DAOException;

	IndoorPatientServiceBillItem getIndoorPatientServiceBillItem(String paramString, List<IndoorPatientServiceBill> paramList) throws DAOException;

	void updateVoidBillItems(Boolean paramBoolean, String paramString, Date paramDate, Integer paramInteger);

	void updatePatientCategory(Integer paramInteger, Encounter paramEncounter, Patient paramPatient);

	List<IndoorPatientServiceBill> getSelectedCategory(Encounter paramEncounter, Patient paramPatient) throws DAOException;
}
