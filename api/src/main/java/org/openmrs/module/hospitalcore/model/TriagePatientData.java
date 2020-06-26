package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.openmrs.Patient;

public class TriagePatientData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Patient patient;

    private Integer id;

    private TriagePatientQueueLog triageLogId;

    private BigDecimal weight;

    private BigDecimal height;

    private BigDecimal BMI;

    private BigDecimal mua;

    private BigDecimal chest;

    private BigDecimal abdominal;

    private BigDecimal temperature;

    private Integer systolic;

    private Integer daistolic;

    private Integer respiratoryRate;

    private Integer pulsRate;

    private String bloodGroup;

    private Date lastMenstrualDate;

    private String rhesusFactor;

    private String pitct;

    private Date createdOn;

    private Integer encounterOpd;

    private Double oxygenSaturation;

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public BigDecimal getMua() {
        return this.mua;
    }

    public void setMua(BigDecimal mua) {
        this.mua = mua;
    }

    public BigDecimal getChest() {
        return this.chest;
    }

    public void setChest(BigDecimal chest) {
        this.chest = chest;
    }

    public BigDecimal getAbdominal() {
        return this.abdominal;
    }

    public void setAbdominal(BigDecimal abdominal) {
        this.abdominal = abdominal;
    }

    public Integer getEncounterOpd() {
        return this.encounterOpd;
    }

    public void setEncounterOpd(Integer encounterOpd) {
        this.encounterOpd = encounterOpd;
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

    public BigDecimal getWeight() {
        return this.weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return this.height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getTemperature() {
        return this.temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public Integer getSystolic() {
        return this.systolic;
    }

    public void setSystolic(Integer systolic) {
        this.systolic = systolic;
    }

    public Integer getDaistolic() {
        return this.daistolic;
    }

    public void setDaistolic(Integer daistolic) {
        this.daistolic = daistolic;
    }

    public Integer getRespiratoryRate() {
        return this.respiratoryRate;
    }

    public void setRespiratoryRate(Integer respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public Integer getPulsRate() {
        return this.pulsRate;
    }

    public void setPulsRate(Integer pulsRate) {
        this.pulsRate = pulsRate;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Date getLastMenstrualDate() {
        return this.lastMenstrualDate;
    }

    public void setLastMenstrualDate(Date lastMenstrualDate) {
        this.lastMenstrualDate = lastMenstrualDate;
    }

    public String getRhesusFactor() {
        return this.rhesusFactor;
    }

    public void setRhesusFactor(String rhesusFactor) {
        this.rhesusFactor = rhesusFactor;
    }

    public String getPitct() {
        return this.pitct;
    }

    public void setPitct(String pitct) {
        this.pitct = pitct;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public BigDecimal getBMI() {
        return this.BMI;
    }

    public void setBMI(BigDecimal BMI) {
        this.BMI = BMI;
    }

    public Double getOxygenSaturation() {
        return this.oxygenSaturation;
    }

    public void setOxygenSaturation(Double oxygenSaturation) {
        this.oxygenSaturation = oxygenSaturation;
    }
}
