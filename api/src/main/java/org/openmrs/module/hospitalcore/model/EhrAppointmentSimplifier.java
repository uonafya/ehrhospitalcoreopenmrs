package org.openmrs.module.hospitalcore.model;

import org.openmrs.Patient;

public class EhrAppointmentSimplifier {

    private String appointmentType;
    private String provider;
    public EhrAppointmentSimplifier(){

    }
    public EhrAppointmentSimplifier(String appointmentType, String provider, String status, String appointmentReason, String startTime, String endTime) {
        this.appointmentType = appointmentType;
        this.provider = provider;
        Status = status;
        this.appointmentReason = appointmentReason;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private String Status;

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAppointmentReason() {
        return appointmentReason;
    }

    public void setAppointmentReason(String appointmentReason) {
        this.appointmentReason = appointmentReason;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private String appointmentReason;
    private String startTime;
    private String endTime;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    private Integer patientId;

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    private Integer appointmentId;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private Patient patient;
}