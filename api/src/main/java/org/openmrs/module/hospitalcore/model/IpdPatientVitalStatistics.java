package org.openmrs.module.hospitalcore.model;

import java.util.Date;
import org.openmrs.Patient;

public class IpdPatientVitalStatistics {
    private Integer id;

    private Patient patient;

    private IpdPatientAdmissionLog ipdPatientAdmissionLog;

    private String bloodPressure;

    private String pulseRate;

    private String temperature;

    private String dietAdvised;

    private String note;

    private Integer creator;

    private Date createdOn;

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

    public IpdPatientAdmissionLog getIpdPatientAdmissionLog() {
        return this.ipdPatientAdmissionLog;
    }

    public void setIpdPatientAdmissionLog(IpdPatientAdmissionLog ipdPatientAdmissionLog) {
        this.ipdPatientAdmissionLog = ipdPatientAdmissionLog;
    }

    public String getBloodPressure() {
        return this.bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getPulseRate() {
        return this.pulseRate;
    }

    public void setPulseRate(String pulseRate) {
        this.pulseRate = pulseRate;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDietAdvised() {
        return this.dietAdvised;
    }

    public void setDietAdvised(String dietAdvised) {
        this.dietAdvised = dietAdvised;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getCreator() {
        return this.creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
