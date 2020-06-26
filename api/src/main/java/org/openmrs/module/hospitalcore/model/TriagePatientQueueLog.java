package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;

public class TriagePatientQueueLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Patient patient;

    private String patientName;

    private String patientIdentifier;

    private Date birthDate;

    private String sex;

    private String referralConceptName;

    private Concept referralConcept;

    private Concept triageConcept;

    private String triageConceptName;

    private String status;

    private User user;

    private Date createdOn;

    private Encounter encounter;

    private String category;

    private String visitStatus;

    public String toString() {
        return "TriagePatientQueueLog [id=" + this.id + ", patient=" + this.patient + ", patientName=" + this.patientName + ", birthDate=" + this.birthDate + ", sex=" + this.sex + ", referralConceptName=" + this.referralConceptName + ", referralConcept=" + this.referralConcept + ", opdConcept=" + this.triageConcept + ", triageConceptName=" + this.triageConceptName + ", status=" + this.status + ", user=" + this.user + ", createdOn=" + this.createdOn + " ,category=" + ",visitStatus=" + this.visitStatus + "]";
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

    public Concept getTriageConcept() {
        return this.triageConcept;
    }

    public void setTriageConcept(Concept triageConcept) {
        this.triageConcept = triageConcept;
    }

    public String getTriageConceptName() {
        return this.triageConceptName;
    }

    public void setTriageConceptName(String triageConceptName) {
        this.triageConceptName = triageConceptName;
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

    public Encounter getEncounter() {
        return this.encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
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
