package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.hospitalcore.util.PatientUtils;

public class IpdPatientAdmissionLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date admissionDate;

	private Patient patient;

	private String patientIdentifier;

	private String patientName;

	private Date birthDate;

	private String gender;

	private Concept admissionWard;

	private String status;

	private OpdPatientQueueLog opdLog;

	private User opdAmittedUser;

	private Obs opdObsGroup;

	private int indoorStatus;

	private int requestForDischargeStatus;

	private int billingStatus;

	private Encounter ipdEncounter;

	private Integer absconded;

	public Integer getAbsconded() {
		return this.absconded;
	}

	public void setAbsconded(Integer absconded) {
		this.absconded = absconded;
	}

	public Date getAdmissionDate() {
		return this.admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getPatientCategory() {
		return PatientUtils.getPatientCategory(this.patient);
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Concept getAdmissionWard() {
		return this.admissionWard;
	}

	public void setAdmissionWard(Concept admissionWard) {
		this.admissionWard = admissionWard;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OpdPatientQueueLog getOpdLog() {
		return this.opdLog;
	}

	public void setOpdLog(OpdPatientQueueLog opdLog) {
		this.opdLog = opdLog;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPatientIdentifier() {
		return this.patientIdentifier;
	}

	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}

	public User getOpdAmittedUser() {
		return this.opdAmittedUser;
	}

	public void setOpdAmittedUser(User opdAmittedUser) {
		this.opdAmittedUser = opdAmittedUser;
	}

	public Obs getOpdObsGroup() {
		return this.opdObsGroup;
	}

	public void setOpdObsGroup(Obs opdObsGroup) {
		this.opdObsGroup = opdObsGroup;
	}

	public Encounter getIpdEncounter() {
		return this.ipdEncounter;
	}

	public void setIpdEncounter(Encounter ipdEncounter) {
		this.ipdEncounter = ipdEncounter;
	}

	public String getAge() {
		return PatientUtils.estimateAge(this.patient);
	}

	public int getIndoorStatus() {
		return this.indoorStatus;
	}

	public void setIndoorStatus(int indoorStatus) {
		this.indoorStatus = indoorStatus;
	}

	public int getRequestForDischargeStatus() {
		return this.requestForDischargeStatus;
	}

	public void setRequestForDischargeStatus(int requestForDischargeStatus) {
		this.requestForDischargeStatus = requestForDischargeStatus;
	}

	public int getBillingStatus() {
		return this.billingStatus;
	}

	public void setBillingStatus(int billingStatus) {
		this.billingStatus = billingStatus;
	}
}
