package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

public class PatientPersonalHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private TriagePatientQueueLog triageLogId;

    private Integer patientId;

    private Date createdOn;

    private String smoke;

    private String smokeItem;

    private String smokeAverage;

    private String alcohol;

    private String alcoholItem;

    private String alcoholAverage;

    private String drug;

    private String drugItem;

    private String drugAverage;

    private String hivStatus;

    private String exposedHiv;

    private String exposedHivFactor;

    private String familyHelp;

    private String otherHelp;

    private String incomeSource;

    public String getSmoke() {
        return this.smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    public String getSmokeItem() {
        return this.smokeItem;
    }

    public void setSmokeItem(String smokeItem) {
        this.smokeItem = smokeItem;
    }

    public String getSmokeAverage() {
        return this.smokeAverage;
    }

    public void setSmokeAverage(String smokeAverage) {
        this.smokeAverage = smokeAverage;
    }

    public String getAlcohol() {
        return this.alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getAlcoholItem() {
        return this.alcoholItem;
    }

    public void setAlcoholItem(String alcoholItem) {
        this.alcoholItem = alcoholItem;
    }

    public String getAlcoholAverage() {
        return this.alcoholAverage;
    }

    public void setAlcoholAverage(String alcoholAverage) {
        this.alcoholAverage = alcoholAverage;
    }

    public String getDrug() {
        return this.drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getDrugItem() {
        return this.drugItem;
    }

    public void setDrugItem(String drugItem) {
        this.drugItem = drugItem;
    }

    public String getDrugAverage() {
        return this.drugAverage;
    }

    public void setDrugAverage(String drugAverage) {
        this.drugAverage = drugAverage;
    }

    public String getHivStatus() {
        return this.hivStatus;
    }

    public void setHivStatus(String hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getExposedHiv() {
        return this.exposedHiv;
    }

    public void setExposedHiv(String exposedHiv) {
        this.exposedHiv = exposedHiv;
    }

    public String getExposedHivFactor() {
        return this.exposedHivFactor;
    }

    public void setExposedHivFactor(String exposedHivFactor) {
        this.exposedHivFactor = exposedHivFactor;
    }

    public String getFamilyHelp() {
        return this.familyHelp;
    }

    public void setFamilyHelp(String familyHelp) {
        this.familyHelp = familyHelp;
    }

    public String getOtherHelp() {
        return this.otherHelp;
    }

    public void setOtherHelp(String otherHelp) {
        this.otherHelp = otherHelp;
    }

    public String getIncomeSource() {
        return this.incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
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
}
