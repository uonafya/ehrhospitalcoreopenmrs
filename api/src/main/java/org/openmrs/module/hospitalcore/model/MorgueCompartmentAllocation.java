package org.openmrs.module.hospitalcore.model;

public class MorgueCompartmentAllocation {

    private Integer compartmentId;
    private Integer morgueStrength;

    public Integer getCompartmentId() {
        return compartmentId;
    }

    public void setCompartmentId(Integer compartmentId) {
        this.compartmentId = compartmentId;
    }

    public Integer getMorgueStrength() {
        return morgueStrength;
    }

    public void setMorgueStrength(Integer morgueStrength) {
        this.morgueStrength = morgueStrength;
    }

    public String getCompartmentNumber() {
        return compartmentNumber;
    }

    public void setCompartmentNumber(String compartmentNumber) {
        this.compartmentNumber = compartmentNumber;
    }

    public Integer getAllocated() {
        return allocated;
    }

    public void setAllocated(Integer allocated) {
        this.allocated = allocated;
    }

    public Integer getVoided() {
        return voided;
    }

    public void setVoided(Integer voided) {
        this.voided = voided;
    }

    private String compartmentNumber;

    private Integer allocated;

    private Integer voided;
}
