package org.openmrs.module.hospitalcore.model;

import java.util.Date;

public class EhrMorgueStrength {
    private Integer ehrMorgueStrengthId;
    private String morgueName;

    private String description;

    public Integer getEhrMorgueStrengthId() {
        return ehrMorgueStrengthId;
    }

    public void setEhrMorgueStrengthId(Integer ehrMorgueStrengthId) {
        this.ehrMorgueStrengthId = ehrMorgueStrengthId;
    }

    public String getMorgueName() {
        return morgueName;
    }

    public void setMorgueName(String morgueName) {
        this.morgueName = morgueName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getRetired() {
        return retired;
    }

    public void setRetired(Integer retired) {
        this.retired = retired;
    }

    private Integer strength;

    private Integer retired;

    private String createdBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    private Date createdOn;
}
