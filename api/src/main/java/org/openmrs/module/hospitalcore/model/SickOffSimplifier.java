package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;

public class SickOffSimplifier implements Serializable {

    private static final long serialVersionUID = 1L;

    private String patientIdentifier;
    private Integer sickOffId;
    private String provider;

    public String getPatientIdentifier() {
        return patientIdentifier;
    }

    public void setPatientIdentifier(String patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    public Integer getSickOffId() {
        return sickOffId;
    }

    public void setSickOffId(Integer sickOffId) {
        this.sickOffId = sickOffId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public SickOffSimplifier(){}
    public SickOffSimplifier(String patientIdentifier, Integer sickOffId, String provider, String notes, String patientName, String user) {
        this.patientIdentifier = patientIdentifier;
        this.sickOffId = sickOffId;
        this.provider = provider;
        this.notes = notes;
        this.patientName = patientName;
        this.user = user;
    }

    private String notes;

    private String patientName;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;
}
