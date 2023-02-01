package org.openmrs.module.hospitalcore.model;

import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.User;

import java.io.Serializable;
import java.util.Date;

public class SickOff implements Serializable {

    private Integer sickOffId;

    private Patient patient;

    private User creator;

    private String clinicianNotes;

    public Integer getSickOffId() {
        return sickOffId;
    }

    public void setSickOffId(Integer sickOffId) {
        this.sickOffId = sickOffId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getClinicianNotes() {
        return clinicianNotes;
    }

    public void setClinicianNotes(String clinicianNotes) {
        this.clinicianNotes = clinicianNotes;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getSickOffStartDate() {
        return sickOffStartDate;
    }

    public void setSickOffStartDate(Date sickOffStartDate) {
        this.sickOffStartDate = sickOffStartDate;
    }

    public Date getSickOffEndDate() {
        return sickOffEndDate;
    }

    public void setSickOffEndDate(Date sickOffEndDate) {
        this.sickOffEndDate = sickOffEndDate;
    }

    private Provider provider;

    private Date createdOn;

    private Date sickOffStartDate;

    private Date sickOffEndDate;
}
