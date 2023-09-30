package org.openmrs.module.hospitalcore.model;

public class MorgueCompatimentAllocation {

    private Integer compartimentId;
    private EhrMorgueStrength morgueStrength;

    public Integer getCompartimentId() {
        return compartimentId;
    }

    public void setCompartimentId(Integer compartimentId) {
        this.compartimentId = compartimentId;
    }

    public EhrMorgueStrength getMorgueStrength() {
        return morgueStrength;
    }

    public void setMorgueStrength(EhrMorgueStrength morgueStrength) {
        this.morgueStrength = morgueStrength;
    }

    public String getCompartimentNumber() {
        return compartimentNumber;
    }

    public void setCompartimentNumber(String compartimentNumber) {
        this.compartimentNumber = compartimentNumber;
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

    private String compartimentNumber;

    private Integer allocated;

    private Integer voided;
}
