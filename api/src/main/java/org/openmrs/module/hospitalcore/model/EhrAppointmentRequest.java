package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.web.WebUtil;

import java.util.Date;

public class EhrAppointmentRequest extends BaseOpenmrsData {

    public enum EhrAppointmentRequestStatus { PENDING, FULFILLED, CANCELLED };

    private Integer appointmentRequestId;

    private Patient patient;

    private EhrAppointmentType appointmentType;

    private Provider provider;

    private EhrAppointmentRequestStatus status;

    private String notes;

    private Provider requestedBy;

    private Date requestedOn;

    private Integer minTimeFrameValue;

    private EhrTimeFrameUnits minTimeFrameUnits;

    private Integer maxTimeFrameValue;

    private EhrTimeFrameUnits maxTimeFrameUnits;

    // TODO Do we want tio link to the created appointment somehow?

    @Override
    public Integer getId() {
        return appointmentRequestId;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setId(Integer id) {
        this.appointmentRequestId = id;
    }

    public Integer getAppointmentRequestId() {
        return appointmentRequestId;
    }

    public void setAppointmentRequestId(Integer appointmentRequestId) {
        this.appointmentRequestId = appointmentRequestId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public EhrAppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(EhrAppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public EhrAppointmentRequestStatus getStatus() {
        return status;
    }

    public void setStatus(EhrAppointmentRequestStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = WebUtil.escapeHTML(notes);
    }

    public Provider getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Provider requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Date getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(Date requestedOn) {
        this.requestedOn = requestedOn;
    }

    public Integer getMinTimeFrameValue() {
        return minTimeFrameValue;
    }

    public void setMinTimeFrameValue(Integer minTimeFrameValue) {
        this.minTimeFrameValue = minTimeFrameValue;
    }

    public EhrTimeFrameUnits getMinTimeFrameUnits() {
        return minTimeFrameUnits;
    }

    public void setMinTimeFrameUnits(EhrTimeFrameUnits minTimeFrameUnits) {
        this.minTimeFrameUnits = minTimeFrameUnits;
    }

    public Integer getMaxTimeFrameValue() {
        return maxTimeFrameValue;
    }

    public void setMaxTimeFrameValue(Integer maxTimeFrameValue) {
        this.maxTimeFrameValue = maxTimeFrameValue;
    }

    public EhrTimeFrameUnits getMaxTimeFrameUnits() {
        return maxTimeFrameUnits;
    }

    public void setMaxTimeFrameUnits(EhrTimeFrameUnits maxTimeFrameUnits) {
        this.maxTimeFrameUnits = maxTimeFrameUnits;
    }
}
