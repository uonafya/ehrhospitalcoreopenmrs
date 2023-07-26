package org.openmrs.module.hospitalcore.model;

import org.openmrs.User;

import java.util.Date;

public class OpdNumbersGenerator {
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

    public String getOpdNumber() {
        return opdNumber;
    }

    public void setOpdNumber(String opdNumber) {
        this.opdNumber = opdNumber;
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
    private String opdNumber;

    private Date dateCreated;

    private Integer createdBy;
}
