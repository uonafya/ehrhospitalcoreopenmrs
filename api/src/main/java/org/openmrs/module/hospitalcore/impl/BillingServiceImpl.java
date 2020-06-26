package org.openmrs.module.hospitalcore.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptSet;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.LabService;
import org.openmrs.module.hospitalcore.RadiologyCoreService;
import org.openmrs.module.hospitalcore.concept.TestTree;
import org.openmrs.module.hospitalcore.db.BillingDAO;
import org.openmrs.module.hospitalcore.model.Ambulance;
import org.openmrs.module.hospitalcore.model.AmbulanceBill;
import org.openmrs.module.hospitalcore.model.BillableService;
import org.openmrs.module.hospitalcore.model.Company;
import org.openmrs.module.hospitalcore.model.Driver;
import org.openmrs.module.hospitalcore.model.IndoorPatientServiceBill;
import org.openmrs.module.hospitalcore.model.IndoorPatientServiceBillItem;
import org.openmrs.module.hospitalcore.model.Lab;
import org.openmrs.module.hospitalcore.model.MiscellaneousService;
import org.openmrs.module.hospitalcore.model.MiscellaneousServiceBill;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientSearch;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.hospitalcore.model.Receipt;
import org.openmrs.module.hospitalcore.model.Tender;
import org.openmrs.module.hospitalcore.model.TenderBill;
import org.openmrs.module.hospitalcore.util.ConceptAnswerComparator;
import org.openmrs.module.hospitalcore.util.ConceptSetComparator;
import org.openmrs.module.hospitalcore.util.GlobalPropertyUtil;

public class BillingServiceImpl extends BaseOpenmrsService implements BillingService {
	private Log log = LogFactory.getLog(getClass());

	protected BillingDAO dao;

	public void setDao(BillingDAO dao) {
		this.dao = dao;
	}

	public int countListTender() throws APIException {
		return this.dao.countListTender();
	}

	public List<Tender> listTender(int min, int max) throws APIException {
		return this.dao.listTender(min, max);
	}

	public Tender saveTender(Tender tender) throws APIException {
		return this.dao.saveTender(tender);
	}

	public void deleteTender(Tender tender) throws APIException {
		this.dao.deleteTender(tender);
	}

	public Tender getTenderById(Integer id) throws APIException {
		return this.dao.getTenderById(id);
	}

	public Tender getTenderByNameAndNumber(String name, int number) throws APIException {
		return this.dao.getTenderByNameAndNumber(name, number);
	}

	public int countListCompany() throws APIException {
		return this.dao.countListCompany();
	}

	public void deleteCompany(Company company) throws APIException {
		this.dao.deleteCompany(company);
	}

	public Company getCompanyById(Integer id) throws APIException {
		return this.dao.getCompanyById(id);
	}

	public List<Company> listCompany(int min, int max) throws APIException {
		return this.dao.listCompany(min, max);
	}

	public Company saveCompany(Company company) throws APIException {
		return this.dao.saveCompany(company);
	}

	public Company getCompanyByName(String name) throws APIException {
		return this.dao.getCompanyByName(name);
	}

	public int countListDriver() throws APIException {
		return this.dao.countListDriver();
	}

	public void deleteDriver(Driver driver) throws APIException {
		this.dao.deleteDriver(driver);
	}

	public Driver getDriverById(Integer id) throws APIException {
		return this.dao.getDriverById(id);
	}

	public Driver getDriverByName(String name) throws APIException {
		return this.dao.getDriveryByName(name);
	}

	public List<Driver> listDriver(int min, int max) throws APIException {
		return this.dao.listDriver(min, max);
	}

	public Driver saveDriver(Driver driver) throws APIException {
		return this.dao.saveDriver(driver);
	}

	public List<Company> searchCompany(String searchText) throws APIException {
		return this.dao.searchCompany(searchText);
	}

	public List<Driver> searchDriver(String searchText) throws APIException {
		return this.dao.searchDriver(searchText);
	}

	public List<Company> getAllCompany() throws APIException {
		return this.dao.getAllCompany();
	}

	public List<Driver> getAllDriver() throws APIException {
		return this.dao.getAllDriver();
	}

	public int countListTenderBillByCompany(Company company) throws APIException {
		return this.dao.countListTenderBillByCompany(company);
	}

	public List<TenderBill> getAllTenderBill() throws APIException {
		return this.dao.getAllTenderBill();
	}

	public TenderBill getTenderBillById(Integer tenderBillId) throws APIException {
		return this.dao.getTenderBillById(tenderBillId);
	}

	public List<TenderBill> listTenderBillByCompany(int min, int max, Company company) throws APIException {
		return this.dao.listTenderBillByCompany(min, max, company);
	}

	public TenderBill saveTenderBill(TenderBill tenderBill) throws APIException {
		return this.dao.saveTenderBill(tenderBill);
	}

	public List<Tender> getActiveTenders() throws APIException {
		return this.dao.getActiveTenders();
	}

	public int countListAmbulance() throws APIException {
		return this.dao.countListAmbulance();
	}

	public void deleteAmbulance(Ambulance ambulance) throws APIException {
		this.dao.deleteAmbulance(ambulance);
	}

	public List<Ambulance> getAllAmbulance() throws APIException {
		return this.dao.getAllAmbulance();
	}

	public Ambulance getAmbulanceById(Integer id) throws APIException {
		return this.dao.getAmbulanceById(id);
	}

	public Ambulance getAmbulanceByName(String name) throws APIException {
		return this.dao.getAmbulanceByName(name);
	}

	public List<Ambulance> listAmbulance(int min, int max) throws APIException {
		return this.dao.listAmbulance(min, max);
	}

	public Ambulance saveAmbulance(Ambulance ambulance) throws APIException {
		return this.dao.saveAmbulance(ambulance);
	}

	public List<AmbulanceBill> getAllAmbulanceBill() throws APIException {
		return this.dao.getAllAmbulanceBill();
	}

	public AmbulanceBill getAmbulanceBillById(Integer ambulanceBillId) throws APIException {
		return this.dao.getAmbulanceBillById(ambulanceBillId);
	}

	public AmbulanceBill saveAmbulanceBill(AmbulanceBill ambulanceBill) throws APIException {
		return this.dao.saveAmbulanceBill(ambulanceBill);
	}

	public int countListAmbulanceBillByDriver(Driver driver) throws APIException {
		return this.dao.countListAmbulanceBillByDriver(driver);
	}

	public List<AmbulanceBill> listAmbulanceBillByDriver(int min, int max, Driver driver) throws APIException {
		return this.dao.listAmbulanceBillByDriver(min, max, driver);
	}

	public List<Ambulance> getActiveAmbulances() throws APIException {
		return this.dao.getActiveAmbulances();
	}

	public String traversServices(Concept con, Map<Integer, BillableService> services) {
		if (con == null)
			return null;
		Collection<ConceptAnswer> answers = con.getAnswers();
		ConceptClass conceptClass = con.getConceptClass();
		if (answers != null && answers.size() > 0 && !conceptClass.getName().equalsIgnoreCase("Test") && !conceptClass.getName().equalsIgnoreCase("labset")) {
			String rs = "";
			rs = rs + "<ul>";
			String child = null;
			for (ConceptAnswer ca : answers) {
				if (ca.getConcept() == con) {
					Concept tmpAnswerConcept = ca.getAnswerConcept();
					ConceptClass tmpConceptClass = tmpAnswerConcept.getConceptClass();
					Integer id = tmpAnswerConcept.getConceptId();
					if (ca.getAnswerConcept().getAnswers() == null || ca.getAnswerConcept().getAnswers().size() == 0 || tmpConceptClass.getName().equalsIgnoreCase("Test") || tmpConceptClass.getName().equalsIgnoreCase("labset") || tmpConceptClass.getName().equalsIgnoreCase("Procedure")) {
						rs = rs + "<li>";
						rs = rs + "<input   name='cons' type='checkbox'  value='" + id + "' >";
						rs = rs + "<label>" + tmpAnswerConcept.getName() + "</label>";
						rs = rs + "<input id='" + id + "_concept'  name='" + id + "_concept'  type='hidden' value='" + id + "'>";
						rs = rs + "<input id='" + id + "_name'      name='" + id + "_name'      type='hidden' value='" + tmpAnswerConcept.getName() + "'>";
						rs = rs + "<input id='" + id + "_shortname' name='" + id + "_shortname' type='hidden' value='" + tmpAnswerConcept.getName().getShortName() + "'>";
						BillableService s = services.get(id);
						if (s != null) {
							rs = rs + "<span style='vertical-align:middle;'>";
							rs = rs + "&nbsp;&nbsp;<input onblur='updatePrice(this)'  type='text' class='priceField' id='" + id + "_price' name='" + id + "_price' value=" + s.getPrice() + ">";
							rs = rs + "</span>";
						} else {
							rs = rs + "<input onblur='updatePrice(this)' type='text' class='priceField' id='" + id + "_price' name='" + id + "_price' value=''>";
						}
					} else {
						rs = rs + "<li>";
						rs = rs + "<input   name='cons' type='checkbox'  value='" + id + "' >";
						rs = rs + "<label>" + tmpAnswerConcept.getName() + "</label>";
						rs = rs + "<input id='" + id + "_concept'  name='" + id + "_concept'  type='hidden' value='" + id + "'>";
						rs = rs + "<input id='" + id + "_name'      name='" + id + "_name'      type='hidden' value='" + tmpAnswerConcept.getName() + "'>";
						rs = rs + "<input id='" + id + "_shortname' name='" + id + "_shortname' type='hidden' value='" + tmpAnswerConcept.getName().getShortName() + "'>";
					}
				}
				child = traversServices(ca.getAnswerConcept(), services);
				if (child != null)
					rs = rs + child;
				rs = rs + "</li>";
			}
			rs = rs + "</ul>";
			return rs;
		}
		if (con.getConceptSets() != null && con.getConceptSets().size() > 0 && !conceptClass.getName().equalsIgnoreCase("Test") && !conceptClass.getName().equalsIgnoreCase("labset")) {
			String rs = "";
			rs = rs + "<ul>";
			String child = null;
			for (ConceptSet ca : con.getConceptSets()) {
				if (ca.getConceptSet() == con) {
					Integer id = ca.getConcept().getConceptId();
					ConceptClass tmpConceptClass = ca.getConcept().getConceptClass();
					if (ca.getConcept().getConceptSets() == null || ca.getConceptSet().getConceptSets().size() == 0 || tmpConceptClass.getName().equalsIgnoreCase("Test") || tmpConceptClass.getName().equalsIgnoreCase("labset") || tmpConceptClass.getName().equalsIgnoreCase("Procedure")) {
						rs = rs + "<li>";
						rs = rs + "<input name='cons' type='checkbox'  value='" + id + "' >";
						rs = rs + "<label>" + ca.getConcept().getName() + "</label>";
						rs = rs + "<input id='" + id + "_concept' name='" + id + "_concept' type='hidden' value='" + id + "'>";
						rs = rs + "<input id='" + id + "_name' name='" + id + "_name' type='hidden' value='" + ca.getConcept().getName() + "'>";
						rs = rs + "<input id='" + id + "_shortname'    name='" + id + "_shortname'    type='hidden' value='" + ca.getConcept().getName().getShortName() + "'>";
						BillableService s = services.get(id);
						if (s != null) {
							rs = rs + "<input onblure='updatePrice(this)'  type='text' class='priceField' id='" + id + "_price' name='" + id + "_price' value=" + s.getPrice() + ">";
						} else {
							rs = rs + "<input onblur='updatePrice(this)'  class='priceField' type='text' id='" + id + "_price' name='" + id + "_price' value=''>";
						}
					} else {
						rs = rs + "<li>";
						rs = rs + "<input name='cons' type='checkbox'  value='" + id + "' >";
						rs = rs + "<label>" + ca.getConcept().getName() + "</label>";
						rs = rs + "<input id='" + id + "_concept' name='" + id + "_concept' type='hidden' value='" + id + "'>";
						rs = rs + "<input id='" + id + "_name' name='" + id + "_name' type='hidden' value='" + ca.getConcept().getName() + "'>";
						rs = rs + "<input id='" + id + "_shortname'    name='" + id + "_shortname'    type='hidden' value='" + ca.getConcept().getName().getShortName() + "'>";
					}
				}
				child = traversServices(ca.getConcept(), services);
				if (child != null)
					rs = rs + child;
				rs = rs + "</li>";
			}
			rs = rs + "</ul>";
			return rs;
		}
		return null;
	}

	public List<BillableService> getAllServices() throws APIException {
		return this.dao.getAllServices();
	}

	public List<BillableService> searchService(String name) throws APIException {
		return this.dao.searchService(name);
	}

	public BillableService getServiceByConceptId(Integer conceptId) throws APIException {
		return this.dao.getServiceByConceptId(conceptId);
	}

	public BillableService getServiceById(Integer id) throws APIException {
		return this.dao.getServiceById(id);
	}

	public BillableService saveService(BillableService service) throws APIException {
		return this.dao.saveService(service);
	}

	public void saveServices(Collection<BillableService> services) throws APIException {
		for (BillableService service : services)
			this.dao.saveService(service);
	}

	public String traversTab(Concept concept, Map<Integer, BillableService> mapServices, int count) throws APIException {
		Integer rootConcept = Integer.valueOf(Context.getAdministrationService().getGlobalProperty("billing.rootServiceConceptId"));
		Collection<ConceptAnswer> answers = concept.getAnswers();
		Collection<ConceptSet> conceptSets = concept.getConceptSets();
		ConceptClass conceptClass = concept.getConceptClass();
		BillableService service = mapServices.get(concept.getConceptId());
		if ((service == null || service.getDisable().booleanValue()) && concept.getConceptId().intValue() != rootConcept.intValue())
			return null;
		String content = "";
		String tabsLi = "";
		String header = "";
		if (answers != null && answers.size() > 0 && !conceptClass.getName().equalsIgnoreCase("Test") && !conceptClass.getName().equalsIgnoreCase("labset")) {
			List<ConceptAnswer> children = new ArrayList<ConceptAnswer>(answers);
			Collections.sort(children, (Comparator<? super ConceptAnswer>)new ConceptAnswerComparator());
			List<ConceptAnswer> noChild = new ArrayList<ConceptAnswer>();
			BillableService s = null;
			for (ConceptAnswer ca : children) {
				if (ca.getConcept() == concept) {
					Concept tmpAnswerConcept = ca.getAnswerConcept();
					s = mapServices.get(tmpAnswerConcept.getId());
					if (s != null && !s.getDisable().booleanValue()) {
						ConceptClass tmpConceptClass = tmpAnswerConcept.getConceptClass();
						if (tmpAnswerConcept.getAnswers() == null || tmpAnswerConcept.getAnswers().size() == 0 || tmpConceptClass.getName().equalsIgnoreCase("Test") || tmpConceptClass.getName().equalsIgnoreCase("labset") || tmpConceptClass.getName().equalsIgnoreCase("Procedure")) {
							noChild.add(ca);
							continue;
						}
						String name = StringUtils.isBlank(tmpAnswerConcept.getName().getShortName()) ? tmpAnswerConcept.getName().getName() : tmpAnswerConcept.getName().getShortName();
						tabsLi = tabsLi + "<li><a title='" + tmpAnswerConcept.getName().getName() + "' href='#fragment-" + tmpAnswerConcept.getConceptId() + "'><span>" + name + "</span></a></li>";
					}
				}
			}
			ConceptAnswer tmpConcept = null;
			if (noChild.size() > 0)
				for (int i = 1; i < noChild.size() + 1; i++) {
					tmpConcept = noChild.get(i - 1);
					BillableService tmpService = mapServices.get(tmpConcept.getAnswerConcept().getConceptId());
					if (tmpService != null && !tmpService.getDisable().booleanValue()) {
						String tmp = "addToBill(\"" + tmpService.getConceptId() + "\",  \"" + tmpService.getName() + "\", " + tmpService.getPrice() + ", 1);";
						String name = StringUtils.isBlank(tmpService.getShortName()) ? tmpService.getName() : tmpService.getShortName();
						header = header + "<div title='" + tmpService.getName() + "' id='box_" + tmpService.getConceptId() + "' class='udiv boxNormal' onclick='" + tmp + "' >" + name + "</div>";
					}
				}
			if (!"".equals(tabsLi)) {
				header = header + "<div id='container-" + count + "' class='divContainer'>";
				count++;
				header = header + "<ul>";
				header = header + tabsLi;
				header = header + "</ul>";
				String tmp = null;
				for (ConceptAnswer c : children) {
					Concept tmpC = c.getAnswerConcept();
					if ((tmpC.getAnswers() != null && tmpC.getAnswers().size() > 0) || (tmpC.getConceptSets() != null && tmpC.getConceptSets().size() > 0)) {
						tmp = null;
						tmp = traversTab(tmpC, mapServices, count);
						if (tmp != null) {
							content = content + "<div id='fragment-" + tmpC.getConceptId() + "'>";
							content = content + tmp;
							content = content + "</div>";
						}
					}
					count++;
				}
				if (!"".equals(content))
					header = header + content;
				header = header + " </div>";
			}
		} else if (conceptSets != null && conceptSets.size() > 0 && !conceptClass.getName().equalsIgnoreCase("Test") && !conceptClass.getName().equalsIgnoreCase("labset")) {
			List<ConceptSet> children = new ArrayList<ConceptSet>(conceptSets);
			Collections.sort(children, (Comparator<? super ConceptSet>)new ConceptSetComparator());
			List<ConceptSet> noChild = new ArrayList<ConceptSet>();
			BillableService s = null;
			for (ConceptSet ca : children) {
				if (ca.getConceptSet() == concept) {
					Concept tmpConceptSet = ca.getConcept();
					s = mapServices.get(tmpConceptSet.getId());
					if (s != null && !s.getDisable().booleanValue()) {
						ConceptClass tmpConceptClass = ca.getConcept().getConceptClass();
						if (tmpConceptSet.getConceptSets() == null || tmpConceptSet.getConceptSets().size() == 0 || tmpConceptClass.getName().equalsIgnoreCase("Test") || tmpConceptClass.getName().equalsIgnoreCase("labset") || tmpConceptClass.getName().equalsIgnoreCase("Procedure")) {
							noChild.add(ca);
							continue;
						}
						String name = StringUtils.isBlank(tmpConceptSet.getName().getShortName()) ? tmpConceptSet.getName().getName() : tmpConceptSet.getName().getShortName();
						tabsLi = tabsLi + "<li><a title='" + tmpConceptSet.getName().getName() + "' href='#fragment-" + tmpConceptSet.getConceptId() + "'><span>" + name + "</span></a></li>";
					}
				}
			}
			ConceptSet tmpConcept = null;
			if (noChild.size() > 0)
				for (int i = 1; i < noChild.size() + 1; i++) {
					tmpConcept = noChild.get(i - 1);
					BillableService tmpService = mapServices.get(tmpConcept.getConcept().getConceptId());
					if (tmpService != null && !tmpService.getDisable().booleanValue()) {
						String tmp = "addToBill(\"" + tmpService.getConceptId() + "\",  \"" + tmpService.getName() + "\", " + tmpService.getPrice() + ", 1);";
						String name = StringUtils.isBlank(tmpService.getShortName()) ? tmpService.getName() : tmpService.getShortName();
						header = header + "<div title='" + tmpService.getName() + "' id='box_" + tmpService.getConceptId() + "' class='udiv boxNormal' onclick='" + tmp + "' >" + name + "</div>";
					}
				}
			if (!"".equals(tabsLi)) {
				header = header + "<div id='container-" + count + "' class='divContainer'>";
				count++;
				header = header + "<ul>";
				header = header + tabsLi;
				header = header + "</ul>";
				String tmp = null;
				for (ConceptSet c : conceptSets) {
					Concept tmpC = c.getConcept();
					if ((tmpC.getAnswers() != null && tmpC.getAnswers().size() > 0) || (tmpC.getConceptSets() != null && tmpC.getConceptSets().size() > 0)) {
						tmp = null;
						tmp = traversTab(tmpC, mapServices, count);
						if (tmp != null) {
							content = content + "<div id='fragment-" + tmpC.getConceptId() + "'>";
							content = content + tmp;
							content = content + "</div>";
						}
					}
					count++;
				}
				if (!"".equals(content))
					header = header + content;
				header = header + " </div>";
			}
		}
		return header;
	}

	public int countListPatientServiceBillByPatient(Patient patient) throws APIException {
		return this.dao.countListPatientServiceBillByPatient(patient);
	}

	public int countListIndoorPatientServiceBillByPatient(Patient patient) throws APIException {
		return this.dao.countListIndoorPatientServiceBillByPatient(patient);
	}

	public List<PatientServiceBill> getAllPatientServiceBill() throws APIException {
		return this.dao.getAllPatientServiceBill();
	}

	public PatientServiceBill getPatientServiceBillById(Integer patientServiceBillId) throws APIException {
		return this.dao.getPatientServiceBillById(patientServiceBillId);
	}

	public IndoorPatientServiceBill getIndoorPatientServiceBillById(Integer indoorPatientServiceBillId) throws APIException {
		return this.dao.getIndoorPatientServiceBillById(indoorPatientServiceBillId);
	}

	public PatientServiceBill getPatientServiceBillByEncounter(Encounter encounter) throws APIException {
		return this.dao.getPatientServiceBillByEncounter(encounter);
	}

	public List<IndoorPatientServiceBill> getIndoorPatientServiceBillByEncounter(Encounter encounter) throws APIException {
		return this.dao.getIndoorPatientServiceBillByEncounter(encounter);
	}

	public List<PatientServiceBill> listPatientServiceBillByPatient(int min, int max, Patient patient) throws APIException {
		return this.dao.listPatientServiceBillByPatient(min, max, patient);
	}

	public List<IndoorPatientServiceBill> listIndoorPatientServiceBillByPatient(int min, int max, Patient patient) throws APIException {
		return this.dao.listIndoorPatientServiceBillByPatient(min, max, patient);
	}

	public PatientServiceBill savePatientServiceBill(PatientServiceBill patientServiceBill) throws APIException {
		return this.dao.savePatientServiceBill(patientServiceBill);
	}

	public IndoorPatientServiceBill saveIndoorPatientServiceBill(IndoorPatientServiceBill indoorPatientServiceBill) throws APIException {
		return this.dao.saveIndoorPatientServiceBill(indoorPatientServiceBill);
	}

	public void deleteIndoorPatientServiceBill(IndoorPatientServiceBill indoorPatientServiceBill) throws APIException {
		this.dao.deleteIndoorPatientServiceBill(indoorPatientServiceBill);
	}

	public void saveBillEncounterAndOrder(PatientServiceBill bill) throws APIException {
		Set<Integer> labConceptIds = getLabConceptIds();
		Set<Integer> radiologyConceptIds = getRadiologyConceptIds();
		String labEncounterTypeText = GlobalPropertyUtil.getString("billing.labEncounterType", "LABENCOUNTER");
		EncounterType labEncounterType = Context.getEncounterService().getEncounterType(labEncounterTypeText);
		String radiologyEncounterTypeText = GlobalPropertyUtil.getString("billing.radiologyEncounterType", "RADIOLOGYENCOUNTER");
		EncounterType radiologyEncounterType = Context.getEncounterService().getEncounterType(radiologyEncounterTypeText);
		Integer labOrderTypeId = GlobalPropertyUtil.getInteger("billing.labOrderType", Integer.valueOf(2));
		OrderType labOrderType = Context.getOrderService().getOrderType(labOrderTypeId);
		Integer radiologyOrderTypeId = GlobalPropertyUtil.getInteger("billing.radiologyOrderType", Integer.valueOf(8));
		OrderType radiologyOrderType = Context.getOrderService().getOrderType(radiologyOrderTypeId);
		Encounter labEncounter = null;
		Encounter radiologyEncounter = null;
		Integer medicalExaminationClassId = GlobalPropertyUtil.getInteger("hospitalcore.medicalExamination", Integer.valueOf(9));
		ConceptClass medicalExaminationClass = Context.getConceptService().getConceptClass(medicalExaminationClassId);
		for (PatientServiceBillItem item : bill.getBillItems()) {
			Concept concept = Context.getConceptService().getConcept(item.getService().getConceptId());
			if (concept.getConceptClass().equals(medicalExaminationClass)) {
				Collection<ConceptSet> conceptSets = concept.getConceptSets();
				if (conceptSets != null && conceptSets.size() > 0)
					for (ConceptSet con : conceptSets) {
						if (labConceptIds.contains(con.getConcept().getConceptId())) {
							labEncounter = getEncounter(bill, labEncounter, labEncounterType);
							Order order = addOrder(labEncounter, con.getConcept(), bill, labOrderType);
							item.setOrder(order);
							continue;
						}
						if (radiologyConceptIds.contains(con.getConcept().getConceptId())) {
							radiologyEncounter = getEncounter(bill, radiologyEncounter, radiologyEncounterType);
							Order order = addOrder(radiologyEncounter, con.getConcept(), bill, radiologyOrderType);
							item.setOrder(order);
						}
					}
				continue;
			}
			if (labConceptIds.contains(concept.getConceptId())) {
				labEncounter = getEncounter(bill, labEncounter, labEncounterType);
				Order order = addOrder(labEncounter, concept, bill, labOrderType);
				item.setOrder(order);
				continue;
			}
			if (radiologyConceptIds.contains(concept.getConceptId())) {
				radiologyEncounter = getEncounter(bill, radiologyEncounter, radiologyEncounterType);
				Order order = addOrder(radiologyEncounter, concept, bill, radiologyOrderType);
				item.setOrder(order);
			}
		}
		savePatientServiceBill(bill);
	}

	public void saveBillEncounterAndOrderForIndoorPatient(IndoorPatientServiceBill bill) throws APIException {
		Set<Integer> labConceptIds = getLabConceptIds();
		Set<Integer> radiologyConceptIds = getRadiologyConceptIds();
		String labEncounterTypeText = GlobalPropertyUtil.getString("billing.labEncounterType", "LABENCOUNTER");
		EncounterType labEncounterType = Context.getEncounterService().getEncounterType(labEncounterTypeText);
		String radiologyEncounterTypeText = GlobalPropertyUtil.getString("billing.radiologyEncounterType", "RADIOLOGYENCOUNTER");
		EncounterType radiologyEncounterType = Context.getEncounterService().getEncounterType(radiologyEncounterTypeText);
		Integer labOrderTypeId = GlobalPropertyUtil.getInteger("billing.labOrderType", Integer.valueOf(2));
		OrderType labOrderType = Context.getOrderService().getOrderType(labOrderTypeId);
		Integer radiologyOrderTypeId = GlobalPropertyUtil.getInteger("billing.radiologyOrderType", Integer.valueOf(8));
		OrderType radiologyOrderType = Context.getOrderService().getOrderType(radiologyOrderTypeId);
		Encounter labEncounter = null;
		Encounter radiologyEncounter = null;
		Integer medicalExaminationClassId = GlobalPropertyUtil.getInteger("hospitalcore.medicalExamination", Integer.valueOf(9));
		ConceptClass medicalExaminationClass = Context.getConceptService().getConceptClass(medicalExaminationClassId);
		for (IndoorPatientServiceBillItem item : bill.getBillItems()) {
			Concept concept = Context.getConceptService().getConcept(item.getService().getConceptId());
			if (concept.getConceptClass().equals(medicalExaminationClass)) {
				Collection<ConceptSet> conceptSets = concept.getConceptSets();
				if (conceptSets != null && conceptSets.size() > 0)
					for (ConceptSet con : conceptSets) {
						if (labConceptIds.contains(con.getConcept().getConceptId())) {
							labEncounter = getEncounter(bill, labEncounter, labEncounterType);
							Order order = addOrder(labEncounter, con.getConcept(), bill, labOrderType);
							item.setOrder(order);
							continue;
						}
						if (radiologyConceptIds.contains(con.getConcept().getConceptId())) {
							radiologyEncounter = getEncounter(bill, radiologyEncounter, radiologyEncounterType);
							Order order = addOrder(radiologyEncounter, con.getConcept(), bill, radiologyOrderType);
							item.setOrder(order);
						}
					}
				continue;
			}
			if (labConceptIds.contains(concept.getConceptId())) {
				labEncounter = getEncounter(bill, labEncounter, labEncounterType);
				Order order = addOrder(labEncounter, concept, bill, labOrderType);
				item.setOrder(order);
				continue;
			}
			if (radiologyConceptIds.contains(concept.getConceptId())) {
				radiologyEncounter = getEncounter(bill, radiologyEncounter, radiologyEncounterType);
				Order order = addOrder(radiologyEncounter, concept, bill, radiologyOrderType);
				item.setOrder(order);
			}
		}
		saveIndoorPatientServiceBill(bill);
	}

	private Set<Integer> getLabConceptIds() {
		Set<Integer> conceptIdSet = new HashSet<Integer>();
		LabService ls = (LabService)Context.getService(LabService.class);
		List<Lab> labs = ls.getAllLab();
		for (Lab lab : labs)
			conceptIdSet.addAll(getConceptIdSet(lab.getInvestigationsToDisplay()));
		return conceptIdSet;
	}

	private Set<Integer> getRadiologyConceptIds() {
		Set<Integer> conceptIdSet = new HashSet<Integer>();
		RadiologyCoreService rcs = (RadiologyCoreService)Context.getService(RadiologyCoreService.class);
		List<RadiologyDepartment> departments = rcs.getAllRadiologyDepartments();
		for (RadiologyDepartment department : departments)
			conceptIdSet.addAll(getConceptIdSet(department.getInvestigations()));
		return conceptIdSet;
	}

	private Set<Integer> getConceptIdSet(Set<Concept> concepts) {
		Set<Integer> conceptIdSet = new HashSet<Integer>();
		for (Concept concept : concepts) {
			TestTree tree = new TestTree(concept);
			conceptIdSet.addAll(tree.getConceptIDSet());
		}
		return conceptIdSet;
	}

	private Encounter getEncounter(PatientServiceBill bill, Encounter encounter, EncounterType encounterType) {
		if (encounter == null) {
			Encounter enc = new Encounter();
			enc.setCreator(bill.getCreator());
			Location location = Context.getLocationService().getLocation(Integer.valueOf(1));
			enc.setLocation(location);
			enc.setDateCreated(new Date());
			enc.setEncounterDatetime(new Date());
			enc.setProvider(bill.getCreator());
			enc.setEncounterType(encounterType);
			enc.setPatient(bill.getPatient());
			Context.getEncounterService().saveEncounter(enc);
			return enc;
		}
		return encounter;
	}

	private Encounter getEncounter(IndoorPatientServiceBill bill, Encounter encounter, EncounterType encounterType) {
		if (encounter == null) {
			Encounter enc = new Encounter();
			enc.setCreator(bill.getCreator());
			Location location = Context.getLocationService().getLocation(Integer.valueOf(1));
			enc.setLocation(location);
			enc.setDateCreated(new Date());
			enc.setEncounterDatetime(new Date());
			enc.setProvider(bill.getCreator());
			enc.setEncounterType(encounterType);
			enc.setPatient(bill.getPatient());
			Context.getEncounterService().saveEncounter(enc);
			return enc;
		}
		return encounter;
	}

	private Order addOrder(Encounter encounter, Concept concept, PatientServiceBill bill, OrderType orderType) {
		Order order = new Order();
		order.setConcept(concept);
		order.setCreator(bill.getCreator());
		order.setDateCreated(new Date());
		order.setOrderer(Context.getAuthenticatedUser());
		order.setPatient(bill.getPatient());
		order.setStartDate(new Date());
		order.setAccessionNumber("0");
		order.setOrderType(orderType);
		order.setEncounter(encounter);
		encounter.addOrder(order);
		return order;
	}

	private Order addOrder(Encounter encounter, Concept concept, IndoorPatientServiceBill bill, OrderType orderType) {
		Order order = new Order();
		order.setConcept(concept);
		order.setCreator(bill.getCreator());
		order.setDateCreated(new Date());
		order.setOrderer(Context.getAuthenticatedUser());
		order.setPatient(bill.getPatient());
		order.setStartDate(new Date());
		order.setAccessionNumber("0");
		order.setOrderType(orderType);
		order.setEncounter(encounter);
		encounter.addOrder(order);
		return order;
	}

	public List<Company> getAllActiveCompany() throws APIException {
		return this.dao.getAllActiveCompany();
	}

	public List<Driver> getAllActiveDriver() throws APIException {
		return this.dao.getAllActiveDriver();
	}

	public void disableService(Integer conceptId) throws APIException {
		this.dao.disableService(conceptId);
	}

	public void voidBill(PatientServiceBill bill) throws APIException {
		bill.setVoided(Boolean.valueOf(true));
		savePatientServiceBill(bill);
		Patient pat = bill.getPatient();
	}

	public int countListMiscellaneousService() throws APIException {
		return this.dao.countListMiscellaneousService();
	}

	public int countListMiscellaneousServiceBill() throws APIException {
		return this.dao.countListMiscellaneousServiceBill();
	}

	public List<MiscellaneousService> getAllMiscellaneousService() throws APIException {
		return this.dao.getAllMiscellaneousService();
	}

	public List<MiscellaneousServiceBill> getAllMiscellaneousServiceBill() throws APIException {
		return this.dao.getAllMiscellaneousServiceBill();
	}

	public MiscellaneousServiceBill getMiscellaneousServiceBillById(Integer miscellaneousServiceBillId) throws APIException {
		return this.dao.getMiscellaneousServiceBillById(miscellaneousServiceBillId);
	}

	public MiscellaneousService getMiscellaneousServiceById(Integer miscellaneousServiceId) throws APIException {
		return this.dao.getMiscellaneousServiceById(miscellaneousServiceId);
	}

	public List<MiscellaneousService> listMiscellaneousService(int min, int max) throws APIException {
		return this.dao.listMiscellaneousService(min, max);
	}

	public List<MiscellaneousServiceBill> listMiscellaneousServiceBill(int min, int max) throws APIException {
		return this.dao.listMiscellaneousServiceBill(min, max);
	}

	public MiscellaneousService saveMiscellaneousService(MiscellaneousService miscellaneousService) throws APIException {
		return this.dao.saveMiscellaneousService(miscellaneousService);
	}

	public MiscellaneousServiceBill saveMiscellaneousServiceBill(MiscellaneousServiceBill miscellaneousServiceBill) throws APIException {
		return this.dao.saveMiscellaneousServiceBill(miscellaneousServiceBill);
	}

	public void deleteMiscellaneousService(MiscellaneousService miscellaneousService) throws APIException {
		this.dao.deleteMiscellaneousService(miscellaneousService);
	}

	public MiscellaneousService getMiscellaneousServiceByName(String name) throws APIException {
		return this.dao.getMiscellaneousServiceByName(name);
	}

	public int countListMiscellaneousServiceBill(MiscellaneousService service) throws APIException {
		return this.dao.countListMiscellaneousServiceBill(service);
	}

	public List<MiscellaneousServiceBill> listMiscellaneousServiceBill(int min, int max, MiscellaneousService service) throws APIException {
		return this.dao.listMiscellaneousServiceBill(min, max, service);
	}

	public Receipt createReceipt() throws APIException {
		Receipt receipt = new Receipt();
		receipt.setPaidDate(new Date());
		return this.dao.createReceipt(receipt);
	}

	public void updateReceipt() throws APIException {
		this.dao.updateReceipt();
	}

	public void updateOldBills() {
		this.dao.updateOldBills();
	}

	public PatientServiceBill getPatientServiceBillByReceiptId(Integer patientServiceBillReceiptId) throws APIException {
		return this.dao.getPatientServiceBillByReceiptId(patientServiceBillReceiptId);
	}

	public List<PatientSearch> searchListOfPatient(Date date, String searchKey, int page) throws APIException {
		return this.dao.searchListOfPatient(date, searchKey, page);
	}

	public List<PatientSearch> searchListOfPatient(Date date, String searchKey, int page, int pgSize) throws APIException {
		return this.dao.searchListOfPatient(date, searchKey, page, pgSize);
	}

	public int countSearchListOfPatient(Date date, String searchKey, int page) throws APIException {
		return this.dao.countSearchListOfPatient(date, searchKey, page);
	}

	public List<PatientSearch> listOfPatient() throws APIException {
		return this.dao.listOfPatient();
	}

	public List<BillableService> listOfServiceOrder(Integer patientId, Integer encounterId) throws APIException {
		return this.dao.listOfServiceOrder(patientId, encounterId);
	}

	public BillableService getServiceByConceptName(String conceptName) throws APIException {
		return this.dao.getServiceByConceptName(conceptName);
	}

	public List<OpdTestOrder> listOfOrder(Integer patientId, Date date) throws APIException {
		return this.dao.listOfOrder(patientId, date);
	}

	public OpdTestOrder getOpdTestOrder(Integer encounterId, Integer conceptId) throws APIException {
		return this.dao.getOpdTestOrder(encounterId, conceptId);
	}

	public PatientServiceBillItem getPatientServiceBillItem(Integer billId, String name) throws APIException {
		return this.dao.getPatientServiceBillItem(billId, name);
	}

	public IndoorPatientServiceBillItem getIndoorPatientServiceBillItem(String name, List<IndoorPatientServiceBill> indoorPatientServiceBillList) throws APIException {
		return this.dao.getIndoorPatientServiceBillItem(name, indoorPatientServiceBillList);
	}

	public void updateVoidBillItems(Boolean voided, String voidedBy, Date voidedDate, Integer itemID) {
		this.dao.updateVoidBillItems(voided, voidedBy, voidedDate, itemID);
	}

	public void updatePatientCategory(Integer selectedCategory, Encounter encounter, Patient patient) {
		this.dao.updatePatientCategory(selectedCategory, encounter, patient);
	}

	public List<IndoorPatientServiceBill> getSelectedCategory(Encounter encounter, Patient patient) throws APIException {
		return this.dao.getSelectedCategory(encounter, patient);
	}
}
