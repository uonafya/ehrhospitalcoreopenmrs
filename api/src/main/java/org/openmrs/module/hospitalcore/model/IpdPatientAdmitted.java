package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.hospitalcore.util.PatientUtils;

public class IpdPatientAdmitted implements Serializable {
	public static final String[] STATUS = new String[] { "transfer", "discharge from", "discharge to", "call" };

	public static final String STATUS_TRANSFER = "transfer";

	public static final String STATUS_DISCHARGE = "discharge";

	public static final String STATUS_CALL = "call";

	public static final String STATUS_ADMITTED = "admitted";

	public static final String OUTCOME_IMPROVED = "improved";

	public static final String OUTCOME_CURED = "cured";

	public static final String OUTCOME_ON_REQUEST = "discharge on request";

	public static final String OUTCOME_LAMA = "lama";

	public static final String OUTCOME_ABSCONDING = "absconding";

	public static final String OUTCOME_DEATH = "death";

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date admissionDate;

	private Patient patient;

	private String patientIdentifier;

	private String patientName;

	private Date birthDate;

	private String patientAddress;

	private String bed;

	private String comments;

	private String fatherName;

	private String relationshipType;

	private String gender;

	private Concept admittedWard;

	private User user;

	private String status;

	private String caste;

	private BigDecimal monthlyIncome;

	private String basicPay;

	private User ipdAdmittedUser;

	private IpdPatientAdmissionLog patientAdmissionLog;

	private IpdPatientAdmittedLog patientAdmittedLogTransferFrom;

	private String admissionOutCome;

	private int requestForDischargeStatus;

	private int billingStatus;

	private String chief;

	private String subChief;

	private String religion;

	private Integer absconded;

	private Date abscondedDate;

	public Date getAbscondedDate() {
		return this.abscondedDate;
	}

	public void setAbscondedDate(Date abscondedDate) {
		this.abscondedDate = abscondedDate;
	}

	public Integer getAbsconded() {
		return this.absconded;
	}

	public void setAbsconded(Integer absconded) {
		this.absconded = absconded;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getAge() {
		return PatientUtils.estimateAge(this.patient);
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCaste() {
		return this.caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public BigDecimal getMonthlyIncome() {
		return this.monthlyIncome;
	}

	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getBasicPay() {
		return this.basicPay;
	}

	public void setBasicPay(String basicPay) {
		this.basicPay = basicPay;
	}

	public IpdPatientAdmissionLog getPatientAdmissionLog() {
		return this.patientAdmissionLog;
	}

	public void setPatientAdmissionLog(IpdPatientAdmissionLog patientAdmissionLog) {
		this.patientAdmissionLog = patientAdmissionLog;
	}

	public String getPatientIdentifier() {
		return this.patientIdentifier;
	}

	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}

	public User getIpdAdmittedUser() {
		return this.ipdAdmittedUser;
	}

	public void setIpdAdmittedUser(User ipdAdmittedUser) {
		this.ipdAdmittedUser = ipdAdmittedUser;
	}

	public Concept getAdmittedWard() {
		return this.admittedWard;
	}

	public void setAdmittedWard(Concept admittedWard) {
		this.admittedWard = admittedWard;
	}

	public String getPatientAddress() {
		return this.patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getBed() {
		return this.bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getRelationshipType() {
		return this.relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public IpdPatientAdmittedLog getPatientAdmittedLogTransferFrom() {
		return this.patientAdmittedLogTransferFrom;
	}

	public void setPatientAdmittedLogTransferFrom(IpdPatientAdmittedLog patientAdmittedLogTransferFrom) {
		this.patientAdmittedLogTransferFrom = patientAdmittedLogTransferFrom;
	}

	public String getAdmissionOutCome() {
		return this.admissionOutCome;
	}

	public void setAdmissionOutCome(String admissionOutCome) {
		this.admissionOutCome = admissionOutCome;
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

	public String getChief() {
		return this.chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	public String getSubChief() {
		return this.subChief;
	}

	public void setSubChief(String subChief) {
		this.subChief = subChief;
	}

	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}
}
