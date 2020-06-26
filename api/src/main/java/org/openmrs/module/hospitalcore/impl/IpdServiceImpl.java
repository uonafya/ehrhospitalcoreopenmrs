package org.openmrs.module.hospitalcore.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.IpdService;
import org.openmrs.module.hospitalcore.db.IpdDAO;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmission;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmittedLog;
import org.openmrs.module.hospitalcore.model.IpdPatientVitalStatistics;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.WardBedStrength;

public class IpdServiceImpl extends BaseOpenmrsService implements IpdService {
	protected IpdDAO dao;

	public void setDao(IpdDAO dao) {
		this.dao = dao;
	}

	public List<IpdPatientAdmission> getAllIpdPatientAdmission() throws APIException {
		return this.dao.getAllIpdPatientAdmission();
	}

	public List<IpdPatientAdmission> getAllIndoorPatient() throws APIException {
		return this.dao.getAllIndoorPatient();
	}

	public List<IpdPatientAdmissionLog> getAllIndoorPatientFromAdmissionLog(String searchKey, int page, int pgSize) throws APIException {
		return this.dao.getAllIndoorPatientFromAdmissionLog(searchKey, page, pgSize);
	}

	public int countGetAllIndoorPatientFromAdmissionLog(String searchKey, int page) throws APIException {
		return this.dao.countGetAllIndoorPatientFromAdmissionLog(searchKey, page);
	}

	public List<IpdPatientAdmissionLog> listIpdPatientAdmissionLog(Integer patientId, Integer admissionWardId, String status, Integer min, Integer max) throws APIException {
		return this.dao.listIpdPatientAdmissionLog(patientId, admissionWardId, status, min, max);
	}

	public List<IpdPatientAdmitted> getAllIpdPatientAdmitted() throws APIException {
		return this.dao.getAllIpdPatientAdmitted();
	}

	public List<IpdPatientAdmittedLog> getAllIpdPatientAdmittedLog() throws APIException {
		return this.dao.getAllIpdPatientAdmittedLog();
	}

	public IpdPatientAdmission getIpdPatientAdmission(Integer id) throws APIException {
		return this.dao.getIpdPatientAdmission(id);
	}

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Integer id) throws APIException {
		return this.dao.getIpdPatientAdmissionLog(id);
	}

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(OpdPatientQueueLog opdLog) throws APIException {
		return this.dao.getIpdPatientAdmissionLog(opdLog);
	}

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Encounter encounter) throws APIException {
		return this.dao.getIpdPatientAdmissionLog(encounter);
	}

	public IpdPatientAdmitted getIpdPatientAdmitted(Integer id) throws APIException {
		return this.dao.getIpdPatientAdmitted(id);
	}

	public IpdPatientAdmittedLog getIpdPatientAdmittedLog(Integer id) throws APIException {
		return this.dao.getIpdPatientAdmittedLog(id);
	}

	public IpdPatientAdmission saveIpdPatientAdmission(IpdPatientAdmission admission) throws APIException {
		return this.dao.saveIpdPatientAdmission(admission);
	}

	public IpdPatientAdmissionLog saveIpdPatientAdmissionLog(IpdPatientAdmissionLog admissionLog) throws APIException {
		return this.dao.saveIpdPatientAdmissionLog(admissionLog);
	}

	public IpdPatientAdmitted saveIpdPatientAdmitted(IpdPatientAdmitted admitted) throws APIException {
		return this.dao.saveIpdPatientAdmitted(admitted);
	}

	public IpdPatientAdmittedLog saveIpdPatientAdmittedLog(IpdPatientAdmittedLog admitted) throws APIException {
		return this.dao.saveIpdPatientAdmittedLog(admitted);
	}

	public void removeIpdPatientAdmission(IpdPatientAdmission admission) throws APIException {
		this.dao.removeIpdPatientAdmission(admission);
	}

	public void removeIpdPatientAdmitted(IpdPatientAdmitted admitted) throws APIException {
		this.dao.removeIpdPatientAdmitted(admitted);
	}

	public IpdPatientAdmitted transfer(Integer id, Integer wardId, Integer doctorId, String bed) throws APIException {
		IpdPatientAdmitted from = getIpdPatientAdmitted(id);
		if (from == null)
			throw new APIException("Can not found IpdPatientAdmitted with id :" + id);
		Concept ward = Context.getConceptService().getConcept(wardId);
		if (ward == null)
			throw new APIException("Can not find IPD Ward with id : " + wardId);
		User user = Context.getUserService().getUser(doctorId);
		if (user == null)
			throw new APIException("Can not find Doctor with user id :" + doctorId);
		IpdPatientAdmittedLog log = new IpdPatientAdmittedLog();
		log.setAdmissionDate(new Date());
		log.setAdmittedWard(from.getAdmittedWard());
		log.setBasicPay(from.getBasicPay());
		log.setBed(from.getBed());
		log.setBirthDate(from.getBirthDate());
		log.setCaste(from.getCaste());
		log.setFatherName(from.getFatherName());
		log.setGender(from.getGender());
		log.setIpdAdmittedUser(from.getIpdAdmittedUser());
		log.setMonthlyIncome(from.getMonthlyIncome());
		log.setPatient(from.getPatient());
		log.setPatientAdmittedLogTransferFrom(from.getPatientAdmittedLogTransferFrom());
		log.setPatientAddress(from.getPatientAddress());
		log.setPatientIdentifier(from.getPatientIdentifier());
		log.setPatientAdmissionLog(from.getPatientAdmissionLog());
		log.setPatientName(from.getPatientName());
		log.setUser(Context.getAuthenticatedUser());
		log.setStatus("transfer");
		log = saveIpdPatientAdmittedLog(log);
		if (log.getId() != null)
			removeIpdPatientAdmitted(from);
		IpdPatientAdmitted to = new IpdPatientAdmitted();
		to.setAdmissionDate(new Date());
		to.setAdmittedWard(ward);
		to.setBasicPay(from.getBasicPay());
		to.setBed(bed);
		to.setBirthDate(from.getBirthDate());
		to.setCaste(from.getCaste());
		to.setFatherName(from.getFatherName());
		to.setGender(from.getGender());
		to.setUser(Context.getAuthenticatedUser());
		to.setIpdAdmittedUser(user);
		to.setMonthlyIncome(from.getMonthlyIncome());
		to.setPatient(from.getPatient());
		to.setPatientAddress(from.getPatientAddress());
		to.setPatientIdentifier(from.getPatientIdentifier());
		to.setPatientAdmissionLog(from.getPatientAdmissionLog());
		to.setPatientName(from.getPatientName());
		to.setStatus("admitted");
		to.setPatientAdmissionLog(log.getPatientAdmissionLog());
		to.setPatientAdmittedLogTransferFrom(log);
		to = saveIpdPatientAdmitted(to);
		return to;
	}

	public IpdPatientAdmitted transfer(Integer id, Integer wardId, Integer doctorId, String bed, String comments) throws APIException {
		IpdPatientAdmitted from = getIpdPatientAdmitted(id);
		if (from == null)
			throw new APIException("Can not found IpdPatientAdmitted with id :" + id);
		Concept ward = Context.getConceptService().getConcept(wardId);
		if (ward == null)
			throw new APIException("Can not find IPD Ward with id : " + wardId);
		User user = Context.getUserService().getUser(doctorId);
		if (user == null)
			throw new APIException("Can not find Doctor with user id :" + doctorId);
		IpdPatientAdmittedLog log = new IpdPatientAdmittedLog();
		log.setAdmissionDate(new Date());
		log.setAdmittedWard(from.getAdmittedWard());
		log.setBasicPay(from.getBasicPay());
		log.setBed(from.getBed());
		log.setComments(comments);
		log.setBirthDate(from.getBirthDate());
		log.setCaste(from.getCaste());
		log.setFatherName(from.getFatherName());
		log.setGender(from.getGender());
		log.setIpdAdmittedUser(from.getIpdAdmittedUser());
		log.setMonthlyIncome(from.getMonthlyIncome());
		log.setPatient(from.getPatient());
		log.setPatientAdmittedLogTransferFrom(from.getPatientAdmittedLogTransferFrom());
		log.setPatientAddress(from.getPatientAddress());
		log.setPatientIdentifier(from.getPatientIdentifier());
		log.setPatientAdmissionLog(from.getPatientAdmissionLog());
		log.setPatientName(from.getPatientName());
		log.setUser(Context.getAuthenticatedUser());
		log.setStatus("transfer");
		log = saveIpdPatientAdmittedLog(log);
		if (log.getId() != null)
			removeIpdPatientAdmitted(from);
		IpdPatientAdmitted to = new IpdPatientAdmitted();
		to.setAdmissionDate(new Date());
		to.setAdmittedWard(ward);
		to.setBasicPay(from.getBasicPay());
		to.setBed(bed);
		to.setComments(comments);
		to.setBirthDate(from.getBirthDate());
		to.setCaste(from.getCaste());
		to.setFatherName(from.getFatherName());
		to.setGender(from.getGender());
		to.setUser(Context.getAuthenticatedUser());
		to.setIpdAdmittedUser(user);
		to.setMonthlyIncome(from.getMonthlyIncome());
		to.setPatient(from.getPatient());
		to.setPatientAddress(from.getPatientAddress());
		to.setPatientIdentifier(from.getPatientIdentifier());
		to.setPatientAdmissionLog(from.getPatientAdmissionLog());
		to.setPatientName(from.getPatientName());
		to.setStatus("admitted");
		to.setPatientAdmissionLog(log.getPatientAdmissionLog());
		to.setPatientAdmittedLogTransferFrom(log);
		to = saveIpdPatientAdmitted(to);
		return to;
	}

	public IpdPatientAdmittedLog discharge(Integer id, Integer outComeConceptId) throws APIException {
		Concept outComeConcept = Context.getConceptService().getConcept(outComeConceptId);
		IpdPatientAdmitted admitted = getIpdPatientAdmitted(id);
		IpdPatientAdmittedLog log = new IpdPatientAdmittedLog();
		log.setAdmissionDate(new Date());
		log.setAdmittedWard(admitted.getAdmittedWard());
		log.setBasicPay(admitted.getBasicPay());
		log.setBed(admitted.getBed());
		log.setBirthDate(admitted.getBirthDate());
		log.setCaste(admitted.getCaste());
		log.setFatherName(admitted.getFatherName());
		log.setUser(Context.getAuthenticatedUser());
		log.setGender(admitted.getGender());
		log.setIpdAdmittedUser(admitted.getIpdAdmittedUser());
		log.setMonthlyIncome(admitted.getMonthlyIncome());
		log.setPatient(admitted.getPatient());
		log.setPatientAddress(admitted.getPatientAddress());
		log.setPatientIdentifier(admitted.getPatientIdentifier());
		log.setPatientAdmissionLog(admitted.getPatientAdmissionLog());
		log.setPatientName(admitted.getPatientName());
		log.setPatientAdmittedLogTransferFrom(admitted.getPatientAdmittedLogTransferFrom());
		log.setStatus("discharge");
		log.setAdmissionOutCome(outComeConcept.getName().getName());
		log = saveIpdPatientAdmittedLog(log);
		if (log.getId() != null) {
			IpdPatientAdmissionLog admissionLog = admitted.getPatientAdmissionLog();
			admissionLog.setStatus("discharge");
			saveIpdPatientAdmissionLog(admissionLog);
			removeIpdPatientAdmitted(admitted);
			Concept conVisitOutCome = Context.getConceptService().getConcept("Admission outcome");
			Location location = new Location(Integer.valueOf(1));
			Encounter ipdEncounter = admissionLog.getIpdEncounter();
			Obs dischargeObs = new Obs();
			dischargeObs.setConcept(conVisitOutCome);
			dischargeObs.setValueCoded(outComeConcept);
			dischargeObs.setCreator(Context.getAuthenticatedUser());
			dischargeObs.setObsDatetime(new Date());
			dischargeObs.setLocation(location);
			dischargeObs.setDateCreated(new Date());
			dischargeObs.setPatient(ipdEncounter.getPatient());
			dischargeObs.setEncounter(ipdEncounter);
			dischargeObs = Context.getObsService().saveObs(dischargeObs, "update obs dischargeObs if need");
			ipdEncounter.addObs(dischargeObs);
			Context.getEncounterService().saveEncounter(ipdEncounter);
		}
		return log;
	}

	public IpdPatientAdmittedLog discharge(Integer id, Integer outComeConceptId, String otherInstructions) throws APIException {
		Concept outComeConcept = Context.getConceptService().getConcept(outComeConceptId);
		IpdPatientAdmitted admitted = getIpdPatientAdmitted(id);
		IpdPatientAdmittedLog log = new IpdPatientAdmittedLog();
		log.setAdmissionDate(new Date());
		log.setAdmittedWard(admitted.getAdmittedWard());
		log.setBasicPay(admitted.getBasicPay());
		log.setBed(admitted.getBed());
		log.setComments(admitted.getComments());
		log.setBirthDate(admitted.getBirthDate());
		log.setCaste(admitted.getCaste());
		log.setFatherName(admitted.getFatherName());
		log.setUser(Context.getAuthenticatedUser());
		log.setGender(admitted.getGender());
		log.setIpdAdmittedUser(admitted.getIpdAdmittedUser());
		log.setMonthlyIncome(admitted.getMonthlyIncome());
		log.setPatient(admitted.getPatient());
		log.setPatientAddress(admitted.getPatientAddress());
		log.setPatientIdentifier(admitted.getPatientIdentifier());
		log.setPatientAdmissionLog(admitted.getPatientAdmissionLog());
		log.setPatientName(admitted.getPatientName());
		log.setPatientAdmittedLogTransferFrom(admitted.getPatientAdmittedLogTransferFrom());
		log.setStatus("discharge");
		log.setAdmissionOutCome(outComeConcept.getName().getName());
		log.setOtherInstructions(otherInstructions);
		log.setChief(admitted.getChief());
		log.setSubChief(admitted.getSubChief());
		log.setReligion(admitted.getReligion());
		log.setAbscondedDate(admitted.getAbscondedDate());
		log = saveIpdPatientAdmittedLog(log);
		if (log.getId() != null) {
			IpdPatientAdmissionLog admissionLog = admitted.getPatientAdmissionLog();
			admissionLog.setStatus("discharge");
			saveIpdPatientAdmissionLog(admissionLog);
			removeIpdPatientAdmitted(admitted);
			Concept conVisitOutCome = Context.getConceptService().getConcept("Admission outcome");
			Location location = new Location(Integer.valueOf(1));
			Encounter ipdEncounter = admissionLog.getIpdEncounter();
			Obs dischargeObs = new Obs();
			dischargeObs.setConcept(conVisitOutCome);
			dischargeObs.setValueCoded(outComeConcept);
			dischargeObs.setCreator(Context.getAuthenticatedUser());
			dischargeObs.setObsDatetime(new Date());
			dischargeObs.setLocation(location);
			dischargeObs.setDateCreated(new Date());
			dischargeObs.setPatient(ipdEncounter.getPatient());
			dischargeObs.setEncounter(ipdEncounter);
			dischargeObs = Context.getObsService().saveObs(dischargeObs, "update obs dischargeObs if need");
			ipdEncounter.addObs(dischargeObs);
			Context.getEncounterService().saveEncounter(ipdEncounter);
		}
		return log;
	}

	public List<IpdPatientAdmittedLog> listAdmittedLogByPatientId(Integer patientId) throws APIException {
		return this.dao.listAdmittedLogByPatientId(patientId);
	}

	public IpdPatientAdmitted getAdmittedByPatientId(Integer patientId) throws APIException {
		return this.dao.getAdmittedByPatientId(patientId);
	}

	public IpdPatientAdmitted getAdmittedByAdmissionLogId(IpdPatientAdmissionLog ipdPatientAdmissionLog) throws APIException {
		return this.dao.getAdmittedByAdmissionLogId(ipdPatientAdmissionLog);
	}

	public IpdPatientVitalStatistics saveIpdPatientVitalStatistics(IpdPatientVitalStatistics vitalStatistics) throws APIException {
		return this.dao.saveIpdPatientVitalStatistics(vitalStatistics);
	}

	public List<IpdPatientVitalStatistics> getIpdPatientVitalStatistics(Integer patientId, Integer patientAdmissionLogId) throws APIException {
		return this.dao.getIpdPatientVitalStatistics(patientId, patientAdmissionLogId);
	}

	public List<Concept> getDiet() throws APIException {
		return this.dao.getDiet();
	}

	public void saveWardBedStrength(WardBedStrength wardBedStrength) throws APIException {
		this.dao.saveWardBedStrength(wardBedStrength);
	}

	public WardBedStrength getWardBedStrengthByWardId(Integer wardId) throws APIException {
		return this.dao.getWardBedStrengthByWardId(wardId);
	}

	public IpdPatientAdmission getIpdPatientAdmissionByEncounter(Encounter encounter) throws APIException {
		return this.dao.getIpdPatientAdmissionByEncounter(encounter);
	}

	public List<IpdPatientAdmission> searchIpdPatientAdmission(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate, String wardId, String status) throws APIException {
		return this.dao.searchIpdPatientAdmission(patientSearch, userIds, fromDate, toDate, wardId, status);
	}

	public List<IpdPatientAdmitted> searchIpdPatientAdmitted(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate, String wardId, String status) throws APIException {
		return this.dao.searchIpdPatientAdmitted(patientSearch, userIds, fromDate, toDate, wardId, status);
	}

	public IpdPatientAdmission getIpdPatientAdmissionByPatientId(Patient patientId) throws APIException {
		return this.dao.getIpdPatientAdmissionByPatientId(patientId);
	}
}
