package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

public class PatientFamilyHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private TriagePatientQueueLog triageLogId;

    private Integer patientId;

    private String fatherStatus;

    private String fatherDeathCause;

    private String fatherDeathAge;

    private String motherStatus;

    private String motherDeathCause;

    private String motherDeathAge;

    private String siblingStatus;

    private String siblingDeathCause;

    private String siblingDeathAge;

    private String familyIllnessHistory;

    private Date createdOn;

    public String getSiblingStatus() {
        return this.siblingStatus;
    }

    public void setSiblingStatus(String siblingStatus) {
        this.siblingStatus = siblingStatus;
    }

    public String getSiblingDeathCause() {
        return this.siblingDeathCause;
    }

    public void setSiblingDeathCause(String siblingDeathCause) {
        this.siblingDeathCause = siblingDeathCause;
    }

    public String getSiblingDeathAge() {
        return this.siblingDeathAge;
    }

    public void setSiblingDeathAge(String siblingDeathAge) {
        this.siblingDeathAge = siblingDeathAge;
    }

    public String getFamilyIllnessHistory() {
        return this.familyIllnessHistory;
    }

    public void setFamilyIllnessHistory(String familyIllnessHistory) {
        this.familyIllnessHistory = familyIllnessHistory;
    }

    public Integer getPatientId() {
        return this.patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public static long getSerialversionuid() {
        return 1L;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TriagePatientQueueLog getTriageLogId() {
        return this.triageLogId;
    }

    public void setTriageLogId(TriagePatientQueueLog triageLogId) {
        this.triageLogId = triageLogId;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getFatherStatus() {
        return this.fatherStatus;
    }

    public void setFatherStatus(String fatherStatus) {
        this.fatherStatus = fatherStatus;
    }

    public String getFatherDeathCause() {
        return this.fatherDeathCause;
    }

    public void setFatherDeathCause(String fatherDeathCause) {
        this.fatherDeathCause = fatherDeathCause;
    }

    public String getFatherDeathAge() {
        return this.fatherDeathAge;
    }

    public void setFatherDeathAge(String fatherDeathAge) {
        this.fatherDeathAge = fatherDeathAge;
    }

    public String getMotherStatus() {
        return this.motherStatus;
    }

    public void setMotherStatus(String motherStatus) {
        this.motherStatus = motherStatus;
    }

    public String getMotherDeathCause() {
        return this.motherDeathCause;
    }

    public void setMotherDeathCause(String motherDeathCause) {
        this.motherDeathCause = motherDeathCause;
    }

    public String getMotherDeathAge() {
        return this.motherDeathAge;
    }

    public void setMotherDeathAge(String motherDeathAge) {
        this.motherDeathAge = motherDeathAge;
    }
}
