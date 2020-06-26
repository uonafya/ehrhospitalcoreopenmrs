package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.hospitalcore.util.PatientUtils;

public class IpdPatientAdmission implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date admissionDate;

	private Patient patient;

	private String patientName;

	private String patientIdentifier;

	private Date birthDate;

	private String gender;

	private Concept admissionWard;

	private String status;

	private User opdAmittedUser;

	private OpdPatientQueueLog opdLog;

	private int acceptStatus;

	private int initialDepositStatus;

	private Encounter ipdEncounter;

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

	public String getAge() {
		return PatientUtils.estimateAge(this.patient);
	}

	public int getAcceptStatus() {
		return this.acceptStatus;
	}

	public void setAcceptStatus(int acceptStatus) {
		this.acceptStatus = acceptStatus;
	}

	public Encounter getIpdEncounter() {
		return this.ipdEncounter;
	}

	public void setIpdEncounter(Encounter ipdEncounter) {
		this.ipdEncounter = ipdEncounter;
	}

	public int getInitialDepositStatus() {
		return this.initialDepositStatus;
	}

	public void setInitialDepositStatus(int initialDepositStatus) {
		this.initialDepositStatus = initialDepositStatus;
	}

	public String toString() {
		return "IpdPatientAdmission [id=" + this.id + ", admissionDate=" + this.admissionDate + ", patient=" + this.patient + ", patientName=" + this.patientName + ", patientIdentifier=" + this.patientIdentifier + ", birthDate=" + this.birthDate + ", gender=" + this.gender + ", admissionWard=" + this.admissionWard + ", status=" + this.status + ", opdAmittedUser=" + this.opdAmittedUser + ", opdLog=" + this.opdLog + ", acceptStatus=" + this.acceptStatus + ", ipdEncounter=" + this.ipdEncounter + "]";
	}
}
