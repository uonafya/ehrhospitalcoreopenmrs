package org.openmrs.module.hospitalcore.db.hibernate;

//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Encounter;
import org.openmrs.GlobalProperty;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.BillingConstants;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.db.BillingDAO;
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
import org.openmrs.module.hospitalcore.util.PatientUtils;

public class HibernateBillingDAO implements BillingDAO {
	protected final Log log = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Tender> listTender(int min, int max) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Tender.class);
		criteria.setFirstResult(min).setMaxResults(max);
		return criteria.list();
	}

	public Tender saveTender(Tender tender) throws DAOException {
		return (Tender)this.sessionFactory.getCurrentSession().merge(tender);
	}

	public int countListTender() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Tender.class);
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return (rs != null) ? rs.intValue() : 0;
	}

	public void deleteTender(Tender tender) {
		this.sessionFactory.getCurrentSession().delete(tender);
	}

	public Tender getTenderById(Integer id) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Tender.class);
		criteria.add((Criterion)Restrictions.eq("tenderId", id));
		return (Tender)criteria.uniqueResult();
	}

	public Tender getTenderByNameAndNumber(String name, int number) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Tender.class);
		crit.add((Criterion)Restrictions.eq("name", name));
		crit.add((Criterion)Restrictions.eq("number", Integer.valueOf(number)));
		return (Tender)crit.uniqueResult();
	}

	public int countListCompany() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Company.class);
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return (rs != null) ? rs.intValue() : 0;
	}

	public void deleteCompany(Company company) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(company);
	}

	public Company getCompanyById(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Company.class);
		criteria.add((Criterion)Restrictions.eq("companyId", id));
		return (Company)criteria.uniqueResult();
	}

	public List<Company> listCompany(int min, int max) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Company.class);
		criteria.setFirstResult(min).setMaxResults(max);
		return criteria.list();
	}

	public Company saveCompany(Company company) throws DAOException {
		return (Company)this.sessionFactory.getCurrentSession().merge(company);
	}

	public Company getCompanyByName(String name) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Company.class);
		crit.add((Criterion)Restrictions.eq("name", name));
		return (Company)crit.uniqueResult();
	}

	public int countListDriver() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Driver.class);
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return (rs != null) ? rs.intValue() : 0;
	}

	public void deleteDriver(Driver driver) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(driver);
	}

	public Driver getDriverById(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Driver.class);
		criteria.add((Criterion)Restrictions.eq("driverId", id));
		return (Driver)criteria.uniqueResult();
	}

	public Driver getDriveryByName(String name) throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Driver.class);
		crit.add((Criterion)Restrictions.eq("name", name));
		return (Driver)crit.uniqueResult();
	}

	public List<Driver> listDriver(int min, int max) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Driver.class);
		criteria.setFirstResult(min).setMaxResults(max);
		return criteria.list();
	}

	public Driver saveDriver(Driver driver) throws DAOException {
		return (Driver)this.sessionFactory.getCurrentSession().merge(driver);
	}

	public List<Company> searchCompany(String searchText) throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Company.class);
		crit.add((Criterion)Restrictions.like("name", searchText + "%")).add((Criterion)Restrictions.eq("retired", Boolean.valueOf(false)));
		return crit.list();
	}

	public List<Driver> searchDriver(String searchText) throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Driver.class);
		crit.add((Criterion)Restrictions.like("name", searchText + "%")).add((Criterion)Restrictions.eq("retired", Boolean.valueOf(false)));
		return crit.list();
	}

	public List<Company> getAllCompany() throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Company.class);
		crit.addOrder(Order.asc("name"));
		return crit.list();
	}

	public List<Driver> getAllDriver() throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Driver.class);
		crit.addOrder(Order.asc("name"));
		return crit.list();
	}

	public List<Tender> getActiveTenders() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Tender.class);
		try {
			Date today = Context.getDateFormat().parse(Context.getDateFormat().format(new Date()));
			criteria.add((Criterion)Restrictions.ge("closingDate", new Date(today.getTime())));
			criteria.add((Criterion)Restrictions.eq("retired", Boolean.valueOf(false)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return criteria.list();
	}

	public int countListTenderBillByCompany(Company company) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(TenderBill.class);
		criteria.add((Criterion)Restrictions.eq("company", company));
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return (rs != null) ? rs.intValue() : 0;
	}

	public List<TenderBill> getAllTenderBill() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(TenderBill.class);
		return criteria.list();
	}

	public TenderBill getTenderBillById(Integer tenderBillId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(TenderBill.class);
		criteria.add((Criterion)Restrictions.eq("tenderBillId", tenderBillId));
		return (TenderBill)criteria.uniqueResult();
	}

	public List<TenderBill> listTenderBillByCompany(int min, int max, Company company) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(TenderBill.class);
		criteria.add((Criterion)Restrictions.eq("company", company)).addOrder(Order.desc("createdDate")).setFirstResult(min).setMaxResults(max);
		return criteria.list();
	}

	public TenderBill saveTenderBill(TenderBill tenderBill) throws DAOException {
		return (TenderBill)this.sessionFactory.getCurrentSession().merge(tenderBill);
	}

	public int countListAmbulance() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Ambulance.class);
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return (rs != null) ? rs.intValue() : 0;
	}

	public void deleteAmbulance(Ambulance ambulance) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(ambulance);
	}

	public List<Ambulance> getAllAmbulance() throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Company.class);
		crit.addOrder(Order.asc("name"));
		return crit.list();
	}

	public Ambulance getAmbulanceById(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Ambulance.class);
		criteria.add((Criterion)Restrictions.eq("ambulanceId", id));
		return (Ambulance)criteria.uniqueResult();
	}

	public Ambulance getAmbulanceByName(String name) throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Ambulance.class);
		crit.add((Criterion)Restrictions.eq("name", name));
		return (Ambulance)crit.uniqueResult();
	}

	public List<Ambulance> listAmbulance(int min, int max) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Ambulance.class);
		criteria.setFirstResult(min).setMaxResults(max);
		return criteria.list();
	}

	public Ambulance saveAmbulance(Ambulance ambulance) throws DAOException {
		return (Ambulance)this.sessionFactory.getCurrentSession().merge(ambulance);
	}

	public int countListAmbulanceBillByDriver(Driver driver) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(AmbulanceBill.class);
		criteria.add((Criterion)Restrictions.eq("driver", driver));
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return (rs != null) ? rs.intValue() : 0;
	}

	public List<AmbulanceBill> getAllAmbulanceBill() throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(AmbulanceBill.class);
		crit.addOrder(Order.desc("createdDate"));
		return crit.list();
	}

	public AmbulanceBill getAmbulanceBillById(Integer ambulanceBillId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(AmbulanceBill.class);
		criteria.add((Criterion)Restrictions.eq("ambulanceBillId", ambulanceBillId));
		return (AmbulanceBill)criteria.uniqueResult();
	}

	public List<AmbulanceBill> listAmbulanceBillByDriver(int min, int max, Driver driver) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(AmbulanceBill.class);
		criteria.add((Criterion)Restrictions.eq("driver", driver)).addOrder(Order.desc("createdDate")).setFirstResult(min).setMaxResults(max);
		return criteria.list();
	}

	public AmbulanceBill saveAmbulanceBill(AmbulanceBill ambulanceBill) throws DAOException {
		return (AmbulanceBill)this.sessionFactory.getCurrentSession().merge(ambulanceBill);
	}

	public List<Ambulance> getActiveAmbulances() throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Ambulance.class);
		crit.addOrder(Order.asc("name"));
		crit.add((Criterion)Restrictions.eq("retired", Boolean.valueOf(false)));
		return crit.list();
	}

	public List<BillableService> getAllServices() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(BillableService.class);
		return criteria.list();
	}

	public List<BillableService> searchService(String name) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(BillableService.class);
		criteria.add((Criterion)Restrictions.eq("disable", Boolean.valueOf(false)));
		if (StringUtils.isNotBlank(name))
			criteria.add((Criterion)Restrictions.like("name", name, MatchMode.ANYWHERE));
		return criteria.list();
	}

	public BillableService getServiceByConceptId(Integer conceptId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(BillableService.class);
		criteria.add((Criterion)Restrictions.eq("conceptId", conceptId));
		return (BillableService)criteria.uniqueResult();
	}

	public BillableService getServiceById(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(BillableService.class);
		criteria.add((Criterion)Restrictions.eq("serviceId", id));
		return (BillableService)criteria.uniqueResult();
	}

	public BillableService saveService(BillableService service) throws DAOException {
		return (BillableService)this.sessionFactory.getCurrentSession().merge(service);
	}

	public int countListPatientServiceBillByPatient(Patient patient) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientServiceBill.class);
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return (rs != null) ? rs.intValue() : 0;
	}

	public int countListIndoorPatientServiceBillByPatient(Patient patient) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IndoorPatientServiceBill.class);
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return (rs != null) ? rs.intValue() : 0;
	}

	public List<PatientServiceBill> getAllPatientServiceBill() throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(PatientServiceBill.class);
		crit.addOrder(Order.asc("createdDate"));
		return crit.list();
	}

	public PatientServiceBill getPatientServiceBillById(Integer patientServiceBillId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientServiceBill.class);
		criteria.add((Criterion)Restrictions.eq("patientServiceBillId", patientServiceBillId));
		return (PatientServiceBill)criteria.uniqueResult();
	}

	public IndoorPatientServiceBill getIndoorPatientServiceBillById(Integer indoorPatientServiceBillId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IndoorPatientServiceBill.class);
		criteria.add((Criterion)Restrictions.eq("indoorPatientServiceBillId", indoorPatientServiceBillId));
		return (IndoorPatientServiceBill)criteria.uniqueResult();
	}

	public PatientServiceBill getPatientServiceBillByEncounter(Encounter encounter) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientServiceBill.class);
		criteria.add((Criterion)Restrictions.eq("encounter", encounter));
		return (PatientServiceBill)criteria.uniqueResult();
	}

	public List<IndoorPatientServiceBill> getIndoorPatientServiceBillByEncounter(Encounter encounter) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IndoorPatientServiceBill.class);
		criteria.add((Criterion)Restrictions.eq("encounter", encounter));
		return criteria.list();
	}

	public List<PatientServiceBill> listPatientServiceBillByPatient(int min, int max, Patient patient) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientServiceBill.class);
		criteria.add((Criterion)Restrictions.eq("patient", patient)).addOrder(Order.desc("createdDate")).setFirstResult(min).setMaxResults(max);
		return criteria.list();
	}

	public List<IndoorPatientServiceBill> listIndoorPatientServiceBillByPatient(int min, int max, Patient patient) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientServiceBill.class);
		criteria.add((Criterion)Restrictions.eq("patient", patient)).addOrder(Order.desc("createdDate")).setFirstResult(min).setMaxResults(max);
		return criteria.list();
	}

	public PatientServiceBill savePatientServiceBill(PatientServiceBill patientServiceBill) throws DAOException {
		return (PatientServiceBill)this.sessionFactory.getCurrentSession().merge(patientServiceBill);
	}

	public IndoorPatientServiceBill saveIndoorPatientServiceBill(IndoorPatientServiceBill indoorPatientServiceBill) throws DAOException {
		return (IndoorPatientServiceBill)this.sessionFactory.getCurrentSession().merge(indoorPatientServiceBill);
	}

	public void deleteIndoorPatientServiceBill(IndoorPatientServiceBill indoorPatientServiceBill) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(indoorPatientServiceBill);
	}

	public List<Company> getAllActiveCompany() throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Company.class);
		crit.addOrder(Order.asc("name")).add((Criterion)Restrictions.eq("retired", Boolean.valueOf(false)));
		return crit.list();
	}

	public List<Driver> getAllActiveDriver() throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(Driver.class);
		crit.addOrder(Order.asc("name")).add((Criterion)Restrictions.eq("retired", Boolean.valueOf(false)));
		return crit.list();
	}

	public void disableService(Integer conceptId) throws DAOException {
		BillableService service = getServiceByConceptId(conceptId);
		if (service != null) {
			service.setDisable(Boolean.valueOf(true));
			saveService(service);
		}
	}

	public int countListMiscellaneousService() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MiscellaneousService.class);
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return (rs != null) ? rs.intValue() : 0;
	}

	public int countListMiscellaneousServiceBill() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MiscellaneousServiceBill.class);
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return (rs != null) ? rs.intValue() : 0;
	}

	public List<MiscellaneousService> getAllMiscellaneousService() throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MiscellaneousService.class);
		crit.addOrder(Order.asc("name"));
		crit.add((Criterion)Restrictions.eq("retired", Boolean.valueOf(false)));
		return crit.list();
	}

	public List<MiscellaneousServiceBill> getAllMiscellaneousServiceBill() throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MiscellaneousServiceBill.class);
		crit.addOrder(Order.asc("liableName"));
		crit.add((Criterion)Restrictions.eq("voided", Boolean.valueOf(false)));
		return crit.list();
	}

	public MiscellaneousServiceBill getMiscellaneousServiceBillById(Integer id) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MiscellaneousServiceBill.class);
		criteria.add((Criterion)Restrictions.eq("id", id));
		return (MiscellaneousServiceBill)criteria.uniqueResult();
	}

	public MiscellaneousService getMiscellaneousServiceById(Integer serviceId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MiscellaneousService.class);
		criteria.add((Criterion)Restrictions.eq("id", serviceId));
		return (MiscellaneousService)criteria.uniqueResult();
	}

	public List<MiscellaneousService> listMiscellaneousService(int min, int max) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MiscellaneousService.class);
		criteria.setFirstResult(min).setMaxResults(max);
		return criteria.list();
	}

	public List<MiscellaneousServiceBill> listMiscellaneousServiceBill(int min, int max) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MiscellaneousServiceBill.class);
		criteria.setFirstResult(min).setMaxResults(max);
		return criteria.list();
	}

	public MiscellaneousService saveMiscellaneousService(MiscellaneousService service) throws DAOException {
		return (MiscellaneousService)this.sessionFactory.getCurrentSession().merge(service);
	}

	public MiscellaneousServiceBill saveMiscellaneousServiceBill(MiscellaneousServiceBill bill) throws DAOException {
		return (MiscellaneousServiceBill)this.sessionFactory.getCurrentSession().merge(bill);
	}

	public void deleteMiscellaneousService(MiscellaneousService miscellaneousService) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(miscellaneousService);
	}

	public MiscellaneousService getMiscellaneousServiceByName(String name) throws DAOException {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(MiscellaneousService.class);
		crit.add((Criterion)Restrictions.eq("name", name));
		return (MiscellaneousService)crit.uniqueResult();
	}

	public int countListMiscellaneousServiceBill(MiscellaneousService service) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MiscellaneousServiceBill.class);
		if (service != null)
			criteria.add((Criterion)Restrictions.eq("service", service));
		Number rs = (Number)criteria.setProjection(Projections.rowCount()).uniqueResult();
		return (rs != null) ? rs.intValue() : 0;
	}

	public List<MiscellaneousServiceBill> listMiscellaneousServiceBill(int min, int max, MiscellaneousService service) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MiscellaneousServiceBill.class);
		if (service != null)
			criteria.add((Criterion)Restrictions.eq("service", service));
		criteria.setFirstResult(min).setMaxResults(max);
		criteria.addOrder(Order.desc("createdDate"));
		return criteria.list();
	}

	public Receipt createReceipt(Receipt receipt) throws DAOException {
		return (Receipt)this.sessionFactory.getCurrentSession().merge(receipt);
	}

	public void updateReceipt() {
		GlobalProperty isUpdated = Context.getAdministrationService().getGlobalPropertyObject("billing.updatedReceiptIds");
		if ("false".equalsIgnoreCase(isUpdated.getPropertyValue())) {
			try {
				GlobalProperty encounterTypeId = Context.getAdministrationService().getGlobalPropertyObject("billing.encounterTypeId");
			} catch (Exception e) {
				e.printStackTrace();
			}
			isUpdated.setPropertyValue("updating");
			this.log.info("Start updating receipt ID for old BillingService ");
			BillingService billingService = (BillingService)Context.getService(BillingService.class);
			List<AmbulanceBill> ambulanceBills = billingService.getAllAmbulanceBill();
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			tx.begin();
			int i = 0;
			for (AmbulanceBill bill : ambulanceBills) {
				if (bill.getReceipt() == null) {
					i++;
					bill.setReceipt(billingService.createReceipt());
					billingService.saveAmbulanceBill(bill);
					if (i % 20 == 0) {
						session.flush();
						session.clear();
					}
				}
			}
			List<TenderBill> tenderBills = billingService.getAllTenderBill();
			this.log.info("Start updating receipt ID for old TenderBill ");
			i = 0;
			for (TenderBill bill : tenderBills) {
				if (bill.getReceipt() == null) {
					i++;
					bill.setReceipt(billingService.createReceipt());
					billingService.saveTenderBill(bill);
					if (i % 20 == 0) {
						session.flush();
						session.clear();
					}
				}
			}
			List<PatientServiceBill> patientServiceBills = billingService.getAllPatientServiceBill();
			this.log.info("Start updating receipt ID for old PatientServiceBill ");
			i = 0;
			for (PatientServiceBill bill : patientServiceBills) {
				if (bill.getReceipt() == null) {
					i++;
					bill.setReceipt(billingService.createReceipt());
					billingService.savePatientServiceBill(bill);
					if (i % 20 == 0) {
						session.flush();
						session.clear();
					}
				}
			}
			List<MiscellaneousServiceBill> miscellaneousBills = billingService.getAllMiscellaneousServiceBill();
			this.log.info("Start updating receipt ID for old MiscellaneousServiceBill ");
			i = 0;
			for (MiscellaneousServiceBill bill : miscellaneousBills) {
				if (bill.getReceipt() == null) {
					i++;
					bill.setReceipt(billingService.createReceipt());
					billingService.saveMiscellaneousServiceBill(bill);
					if (i % 20 == 0) {
						session.flush();
						session.clear();
					}
				}
			}
			isUpdated.setPropertyValue("true");
			Context.getAdministrationService().saveGlobalProperty(isUpdated);
			tx.commit();
			this.log.info("End updating receipt ID for old bills ");
		}
	}

	public void updateOldBills() {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientServiceBillItem.class);
		List<PatientServiceBillItem> items = criteria.list();
		int i = 0;
		for (PatientServiceBillItem item : items) {
			i++;
			String category = PatientUtils.getPatientAttribute(item.getPatientServiceBill().getPatient(), "Payment Category");
			if (i % 50 == 0) {
				session.flush();
				session.clear();
			}
		}
		tx.commit();
	}

	public PatientServiceBill getPatientServiceBillByReceiptId(Integer patientServiceBillReceiptId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientServiceBill.class);
		Receipt receipt = new Receipt();
		receipt.setId(patientServiceBillReceiptId);
		criteria.add((Criterion)Restrictions.eq("receipt", receipt));
		return (PatientServiceBill)criteria.uniqueResult();
	}

	public List<PatientSearch> searchListOfPatient(Date date, String searchKey, int page) throws DAOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";
		String hql = "SELECT DISTINCT ps from PatientSearch ps,OpdTestOrder o INNER JOIN o.patient p where p.patientId=ps.patientId AND o.scheduleDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND o.billingStatus=0 AND o.cancelStatus=0 AND o.billableService is NOT NULL AND o.valueCoded NOT IN (SELECT c.answerConcept FROM ConceptAnswer c,ConceptName cn WHERE cn.name='MAJOR OPERATION' AND c.concept=cn.concept) AND (ps.identifier LIKE '%" + searchKey + "%' OR ps.fullname LIKE '" + searchKey + "%')";
		int firstResult = (page - 1) * BillingConstants.PAGESIZE.intValue();
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);
		List<PatientSearch> list = q.list();
		return list;
	}

	public List<PatientSearch> searchListOfPatient(Date date, String searchKey, int page, int pgSize) throws DAOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";
		String hql = "SELECT DISTINCT ps from PatientSearch ps,OpdTestOrder o INNER JOIN o.patient p where p.patientId=ps.patientId AND o.scheduleDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND o.billingStatus=0 AND o.cancelStatus=0 AND o.billableService is NOT NULL AND o.valueCoded NOT IN (SELECT c.answerConcept FROM ConceptAnswer c,ConceptName cn WHERE cn.name='MAJOR OPERATION' AND c.concept=cn.concept) AND (ps.identifier LIKE '%" + searchKey + "%' OR ps.fullname LIKE '%" + searchKey + "%')";
		int firstResult = (page - 1) * pgSize;
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql).setFirstResult(firstResult).setMaxResults(pgSize);
		List<PatientSearch> list = q.list();
		return list;
	}

	public int countSearchListOfPatient(Date date, String searchKey, int page) throws DAOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";
		String hql = "SELECT DISTINCT ps from PatientSearch ps,OpdTestOrder o INNER JOIN o.patient p where p.patientId=ps.patientId AND o.scheduleDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND o.billingStatus=0 AND o.cancelStatus=0 AND o.billableService is NOT NULL AND o.valueCoded NOT IN (SELECT c.answerConcept FROM ConceptAnswer c,ConceptName cn WHERE cn.name='MAJOR OPERATION' AND c.concept=cn.concept) AND (ps.identifier LIKE '%" + searchKey + "%' OR ps.fullname LIKE '" + searchKey + "%')";
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);
		List<PatientSearch> list = q.list();
		return list.size();
	}

	public List<PatientSearch> listOfPatient() throws DAOException {
		String hql = "from PatientSearch ps where ps.patientId IN (SELECT o.patient FROM OpdTestOrder o where o.billingStatus=0 AND o.cancelStatus=0 AND o.billableService is NOT NULL AND o.valueCoded NOT IN (SELECT c.answerConcept FROM ConceptAnswer c,ConceptName cn WHERE cn.name='MAJOR OPERATION' AND c.concept=cn.concept) GROUP BY o.patient)";
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);
		List<PatientSearch> list = q.list();
		return list;
	}

	public List<BillableService> listOfServiceOrder(Integer patientId, Integer encounterId) throws DAOException {
		String hql = "from BillableService b where b.conceptId IN (SELECT o.valueCoded FROM OpdTestOrder o where o.patient='" + patientId + "' AND o.encounter='" + encounterId + "' AND o.billingStatus=0 AND o.cancelStatus=0 AND o.billableService is NOT NULL AND o.valueCoded NOT IN (SELECT c.answerConcept FROM ConceptAnswer c,ConceptName cn WHERE cn.name='MAJOR OPERATION' AND c.concept=cn.concept))";
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);
		List<BillableService> list = q.list();
		return list;
	}

	public BillableService getServiceByConceptName(String conceptName) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(BillableService.class);
		criteria.add((Criterion)Restrictions.eq("conceptId", Context.getConceptService().getConcept(conceptName).getConceptId()));
		return (BillableService)criteria.uniqueResult();
	}

	public List<OpdTestOrder> listOfOrder(Integer patientId, Date date) throws DAOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";
		String hql = "from OpdTestOrder o where o.patient='" + patientId + "' AND o.scheduleDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND o.billingStatus=0 AND o.cancelStatus=0 AND o.billableService is NOT NULL AND o.valueCoded NOT IN (SELECT c.answerConcept FROM ConceptAnswer c,ConceptName cn WHERE cn.name='MAJOR OPERATION' AND c.concept=cn.concept) GROUP BY encounter";
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);
		List<OpdTestOrder> list = q.list();
		return list;
	}

	public OpdTestOrder getOpdTestOrder(Integer encounterId, Integer conceptId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(OpdTestOrder.class);
		criteria.add((Criterion)Restrictions.eq("encounter.encounterId", encounterId));
		criteria.add((Criterion)Restrictions.eq("valueCoded.conceptId", conceptId));
		return (OpdTestOrder)criteria.uniqueResult();
	}

	public PatientServiceBillItem getPatientServiceBillItem(Integer billId, String name) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PatientServiceBillItem.class);
		criteria.add((Criterion)Restrictions.eq("patientServiceBill.patientServiceBillId", billId));
		criteria.add((Criterion)Restrictions.eq("name", name));
		return (PatientServiceBillItem)criteria.uniqueResult();
	}

	public IndoorPatientServiceBillItem getIndoorPatientServiceBillItem(String name, List<IndoorPatientServiceBill> indoorPatientServiceBillList) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IndoorPatientServiceBillItem.class);
		criteria.add((Criterion)Restrictions.eq("name", name));
		criteria.add(Restrictions.in("indoorPatientServiceBill", indoorPatientServiceBillList));
		return (IndoorPatientServiceBillItem)criteria.uniqueResult();
	}

	public void updateVoidBillItems(Boolean voided, String voidedBy, Date voidedDate, Integer itemID) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "UPDATE IndoorPatientServiceBillItem set voided = :voided, voidedby = :voidedby, voidedDate = :voidedDate WHERE indoorPatientServiceBillItemId = :itemID";
		Query query = session.createQuery(hql);
		query.setParameter("voided", voided);
		query.setParameter("voidedby", voidedBy);
		query.setParameter("voidedDate", voidedDate);
		query.setParameter("itemID", itemID);
		query.executeUpdate();
	}

	public void updatePatientCategory(Integer selectedCategory, Encounter encounter, Patient patient) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "UPDATE IndoorPatientServiceBill set selectedCategory = :selectedCategory WHERE encounter = :encounter AND patient= :patient";
		Query query = session.createQuery(hql);
		query.setParameter("selectedCategory", selectedCategory);
		query.setParameter("encounter", encounter);
		query.setParameter("patient", patient);
		query.executeUpdate();
	}

	public List<IndoorPatientServiceBill> getSelectedCategory(Encounter encounter, Patient patient) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(IndoorPatientServiceBill.class);
		criteria.add((Criterion)Restrictions.eq("encounter", encounter));
		criteria.add((Criterion)Restrictions.eq("patient", patient));
		criteria.add(Restrictions.isNotNull("selectedCategory"));
		return criteria.list();
	}
}
