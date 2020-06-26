package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

public class PatientMedicalHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private TriagePatientQueueLog triageLogId;

    private Integer patientId;

    private String illnessExisting;

    private String illnessProblem;

    private String illnessLong;

    private String illnessProgress;

    private String illnessRecord;

    private Date createdOn;

    private String chronicIllness;

    private String chronicIllnessProblem;

    private String chronicIllnessOccure;

    private String chronicIllnessOutcome;

    private String chronicIllnessRecord;

    private String previousAdmission;

    private String previousAdmissionWhen;

    private String previousAdmissionProblem;

    private String previousAdmissionOutcome;

    private String previousAdmissionRecord;

    private String previousInvestigation;

    private String previousInvestigationWhen;

    private String previousInvestigationProblem;

    private String previousInvestigationOutcome;

    private String previousInvestigationRecord;

    private String bcg;

    private String polio;

    private String dpt;

    private String measles;

    private String pneumococcal;

    private String yellowFever;

    private String tetanusMale;

    private String tetanusFemale;

    private String otherVaccinations;

    public String getChronicIllness() {
        return this.chronicIllness;
    }

    public void setChronicIllness(String chronicIllness) {
        this.chronicIllness = chronicIllness;
    }

    public String getChronicIllnessProblem() {
        return this.chronicIllnessProblem;
    }

    public void setChronicIllnessProblem(String chronicIllnessProblem) {
        this.chronicIllnessProblem = chronicIllnessProblem;
    }

    public String getChronicIllnessOccure() {
        return this.chronicIllnessOccure;
    }

    public void setChronicIllnessOccure(String chronicIllnessOccure) {
        this.chronicIllnessOccure = chronicIllnessOccure;
    }

    public String getChronicIllnessOutcome() {
        return this.chronicIllnessOutcome;
    }

    public void setChronicIllnessOutcome(String chronicIllnessOutcome) {
        this.chronicIllnessOutcome = chronicIllnessOutcome;
    }

    public String getChronicIllnessRecord() {
        return this.chronicIllnessRecord;
    }

    public void setChronicIllnessRecord(String chronicIllnessRecord) {
        this.chronicIllnessRecord = chronicIllnessRecord;
    }

    public String getPreviousAdmission() {
        return this.previousAdmission;
    }

    public void setPreviousAdmission(String previousAdmission) {
        this.previousAdmission = previousAdmission;
    }

    public String getPreviousAdmissionWhen() {
        return this.previousAdmissionWhen;
    }

    public void setPreviousAdmissionWhen(String previousAdmissionWhen) {
        this.previousAdmissionWhen = previousAdmissionWhen;
    }

    public String getPreviousAdmissionProblem() {
        return this.previousAdmissionProblem;
    }

    public void setPreviousAdmissionProblem(String previousAdmissionProblem) {
        this.previousAdmissionProblem = previousAdmissionProblem;
    }

    public String getPreviousAdmissionOutcome() {
        return this.previousAdmissionOutcome;
    }

    public void setPreviousAdmissionOutcome(String previousAdmissionOutcome) {
        this.previousAdmissionOutcome = previousAdmissionOutcome;
    }

    public String getPreviousAdmissionRecord() {
        return this.previousAdmissionRecord;
    }

    public void setPreviousAdmissionRecord(String previousAdmissionRecord) {
        this.previousAdmissionRecord = previousAdmissionRecord;
    }

    public String getPreviousInvestigation() {
        return this.previousInvestigation;
    }

    public void setPreviousInvestigation(String previousInvestigation) {
        this.previousInvestigation = previousInvestigation;
    }

    public String getPreviousInvestigationWhen() {
        return this.previousInvestigationWhen;
    }

    public void setPreviousInvestigationWhen(String previousInvestigationWhen) {
        this.previousInvestigationWhen = previousInvestigationWhen;
    }

    public String getPreviousInvestigationProblem() {
        return this.previousInvestigationProblem;
    }

    public void setPreviousInvestigationProblem(String previousInvestigationProblem) {
        this.previousInvestigationProblem = previousInvestigationProblem;
    }

    public String getPreviousInvestigationOutcome() {
        return this.previousInvestigationOutcome;
    }

    public void setPreviousInvestigationOutcome(String previousInvestigationOutcome) {
        this.previousInvestigationOutcome = previousInvestigationOutcome;
    }

    public String getPreviousInvestigationRecord() {
        return this.previousInvestigationRecord;
    }

    public void setPreviousInvestigationRecord(String previousInvestigationRecord) {
        this.previousInvestigationRecord = previousInvestigationRecord;
    }

    public String getBcg() {
        return this.bcg;
    }

    public void setBcg(String bcg) {
        this.bcg = bcg;
    }

    public String getPolio() {
        return this.polio;
    }

    public void setPolio(String polio) {
        this.polio = polio;
    }

    public String getDpt() {
        return this.dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public String getMeasles() {
        return this.measles;
    }

    public void setMeasles(String measles) {
        this.measles = measles;
    }

    public String getPneumococcal() {
        return this.pneumococcal;
    }

    public void setPneumococcal(String pneumococcal) {
        this.pneumococcal = pneumococcal;
    }

    public String getYellowFever() {
        return this.yellowFever;
    }

    public void setYellowFever(String yellowFever) {
        this.yellowFever = yellowFever;
    }

    public String getTetanusMale() {
        return this.tetanusMale;
    }

    public void setTetanusMale(String tetanusMale) {
        this.tetanusMale = tetanusMale;
    }

    public String getTetanusFemale() {
        return this.tetanusFemale;
    }

    public void setTetanusFemale(String tetanusFemale) {
        this.tetanusFemale = tetanusFemale;
    }

    public String getOtherVaccinations() {
        return this.otherVaccinations;
    }

    public void setOtherVaccinations(String otherVaccinations) {
        this.otherVaccinations = otherVaccinations;
    }

    public Integer getPatientId() {
        return this.patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getIllnessExisting() {
        return this.illnessExisting;
    }

    public void setIllnessExisting(String illnessExisting) {
        this.illnessExisting = illnessExisting;
    }

    public String getIllnessProblem() {
        return this.illnessProblem;
    }

    public void setIllnessProblem(String illnessProblem) {
        this.illnessProblem = illnessProblem;
    }

    public String getIllnessLong() {
        return this.illnessLong;
    }

    public void setIllnessLong(String illnessLong) {
        this.illnessLong = illnessLong;
    }

    public String getIllnessProgress() {
        return this.illnessProgress;
    }

    public void setIllnessProgress(String illnessProgress) {
        this.illnessProgress = illnessProgress;
    }

    public String getIllnessRecord() {
        return this.illnessRecord;
    }

    public void setIllnessRecord(String illnessRecord) {
        this.illnessRecord = illnessRecord;
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
}
