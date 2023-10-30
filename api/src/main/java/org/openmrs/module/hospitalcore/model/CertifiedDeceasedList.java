package org.openmrs.module.hospitalcore.model;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.User;

import java.util.Date;

public class CertifiedDeceasedList {

    private Integer certifiedDeceasedListId;
    private Patient patient;

    public Integer getCertifiedDeceasedListId() {
        return certifiedDeceasedListId;
    }

    public void setCertifiedDeceasedListId(Integer certifiedDeceasedListId) {
        this.certifiedDeceasedListId = certifiedDeceasedListId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Concept getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(Concept causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public Date getEntryDateAndTime() {
        return entryDateAndTime;
    }

    public void setEntryDateAndTime(Date entryDateAndTime) {
        this.entryDateAndTime = entryDateAndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private Date dateOfDeath;

    private Concept causeOfDeath;

    private Date entryDateAndTime;

    private String status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

}
