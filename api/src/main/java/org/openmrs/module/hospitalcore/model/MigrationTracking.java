package org.openmrs.module.hospitalcore.model;

import java.util.Date;

public class MigrationTracking {

    private Integer migrationId;

    public Integer getMigrationId() {
        return migrationId;
    }

    public void setMigrationId(Integer migrationId) {
        this.migrationId = migrationId;
    }

    public Integer getOldPatientId() {
        return oldPatientId;
    }

    public void setOldPatientId(Integer oldPatientId) {
        this.oldPatientId = oldPatientId;
    }

    public Integer getNewPatientId() {
        return newPatientId;
    }

    public void setNewPatientId(Integer newPatientId) {
        this.newPatientId = newPatientId;
    }

    public String getOpenmrsId() {
        return openmrsId;
    }

    public void setOpenmrsId(String openmrsId) {
        this.openmrsId = openmrsId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private Integer oldPatientId;

    private Integer newPatientId;

    private String openmrsId;

    private Date createdOn;

    private String createdBy;
}
