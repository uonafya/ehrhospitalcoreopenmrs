package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.hospitalcore.util.PatientUtils;

public class OpdPatientQueue implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Patient patient;

	private String patientName;

	private String patientIdentifier;

	private Date birthDate;

	private String sex;

	private String referralConceptName;

	private Concept referralConcept;

	private Concept opdConcept;

	private String opdConceptName;

	private String status;

	private User user;

	private Date createdOn;

	private TriagePatientData triageDataId;

	private String category;

	private String visitStatus;

	public String toString() {
		return "OpdPatientQueue [id=" + this.id + ", patient=" + this.patient + ", patientName=" + this.patientName + ", birthDate=" + this.birthDate + ", sex=" + this.sex + ", referralConceptName=" + this.referralConceptName + ", referralConcept=" + this.referralConcept + ", opdConcept=" + this.opdConcept + ", opdConceptName=" + this.opdConceptName + ", status=" + this.status + ", user=" + this.user + ", createdOn=" + this.createdOn + ",triageDataId=" + this.triageDataId + " ,category=" + this.category + ",visitStatus=" + this.visitStatus + "]";
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public String getAge() {
		return PatientUtils.estimateAge(this.patient);
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getReferralConceptName() {
		return this.referralConceptName;
	}

	public void setReferralConceptName(String referralConceptName) {
		this.referralConceptName = referralConceptName;
	}

	public Concept getReferralConcept() {
		return this.referralConcept;
	}

	public void setReferralConcept(Concept referralConcept) {
		this.referralConcept = referralConcept;
	}

	public Concept getOpdConcept() {
		return this.opdConcept;
	}

	public void setOpdConcept(Concept opdConcept) {
		this.opdConcept = opdConcept;
	}

	public String getOpdConceptName() {
		return this.opdConceptName;
	}

	public void setOpdConceptName(String opdConceptName) {
		this.opdConceptName = opdConceptName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getPatientIdentifier() {
		return this.patientIdentifier;
	}

	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public TriagePatientData getTriageDataId() {
		return this.triageDataId;
	}

	public void setTriageDataId(TriagePatientData triageDataId) {
		this.triageDataId = triageDataId;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getVisitStatus() {
		return this.visitStatus;
	}

	public void setVisitStatus(String visitStatus) {
		this.visitStatus = visitStatus;
	}
}
