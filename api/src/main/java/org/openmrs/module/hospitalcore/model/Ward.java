package org.openmrs.module.hospitalcore.model;

import org.openmrs.User;

import java.io.Serializable;
import java.util.Date;

public class Ward implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer wardId;

    private String wardName;

    private String wardCode;

    private String wardUuid;

    private Date createdOn;

    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getWardUuid() {
        return wardUuid;
    }

    public void setWardUuid(String wardUuid) {
        this.wardUuid = wardUuid;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public User getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(User changedBy) {
        this.changedBy = changedBy;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    private Date dateChanged;

    private User changedBy;

    private User createdBy;
}
