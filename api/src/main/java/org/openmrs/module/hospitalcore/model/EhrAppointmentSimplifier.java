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
        this.status = status;
        this.appointmentReason = appointmentReason;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private String status;

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
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    private String appointmentNumber;

    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public String getAppointmentService() {
        return appointmentService;
    }

    public void setAppointmentService(String appointmentService) {
        this.appointmentService = appointmentService;
    }

    public String getAppointmentServiceType() {
        return appointmentServiceType;
    }

    public void setAppointmentServiceType(String appointmentServiceType) {
        this.appointmentServiceType = appointmentServiceType;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private String appointmentService;
    private String appointmentServiceType;
    private String	response;

    public String getPatientNames() {
        return patientNames;
    }

    public void setPatientNames(String patientNames) {
        this.patientNames = patientNames;
    }

    public String getPatientIdentifier() {
        return patientIdentifier;
    }

    public void setPatientIdentifier(String patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    private String patientNames;
    private String patientIdentifier;

}
