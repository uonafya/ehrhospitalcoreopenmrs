package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

public class Ambulance implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer ambulanceId;

    private String name;

    private String description;

    private Date createdDate;

    private Boolean retired = Boolean.valueOf(false);

    private Date retiredDate;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getRetired() {
        return this.retired;
    }

    public void setRetired(Boolean retired) {
        this.retired = retired;
    }

    public Date getRetiredDate() {
        return this.retiredDate;
    }

    public void setRetiredDate(Date retiredDate) {
        this.retiredDate = retiredDate;
    }

    public Integer getAmbulanceId() {
        return this.ambulanceId;
    }

    public void setAmbulanceId(Integer ambulanceId) {
        this.ambulanceId = ambulanceId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
