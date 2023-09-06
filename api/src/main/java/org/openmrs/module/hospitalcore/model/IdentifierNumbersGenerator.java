package org.openmrs.module.hospitalcore.model;

import java.util.Date;

public class IdentifierNumbersGenerator {
    private Integer opdNumberGeneratorId;

    public Integer getOpdNumberGeneratorId() {
        return opdNumberGeneratorId;
    }

    public void setOpdNumberGeneratorId(Integer opdNumberGeneratorId) {
        this.opdNumberGeneratorId = opdNumberGeneratorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    private Integer patientId;
    private String identifier;

    private Date dateCreated;

    private Integer createdBy;

    public Integer getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(Integer identifierType) {
        this.identifierType = identifierType;
    }

    private Integer identifierType;
}
