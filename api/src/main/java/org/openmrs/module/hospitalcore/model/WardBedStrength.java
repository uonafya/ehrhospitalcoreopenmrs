package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.User;

public class WardBedStrength implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer wardBedStrengthId;

    private Concept ward;

    private Integer bedStrength;

    private Date createdOn;

    private User createdBy;

    public Integer getWardBedStrengthId() {
        return this.wardBedStrengthId;
    }

    public void setWardBedStrengthId(Integer wardBedStrengthId) {
        this.wardBedStrengthId = wardBedStrengthId;
    }

    public Concept getWard() {
        return this.ward;
    }

    public void setWard(Concept ward) {
        this.ward = ward;
    }

    public Integer getBedStrength() {
        return this.bedStrength;
    }

    public void setBedStrength(Integer bedStrength) {
        this.bedStrength = bedStrength;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
