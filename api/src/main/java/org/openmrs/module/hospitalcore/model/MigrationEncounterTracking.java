package org.openmrs.module.hospitalcore.model;

import java.util.Date;

public class MigrationEncounterTracking {

    private Integer MigrationEncounterTrackingId;
    private Integer oldEncounterId;

    public Integer getMigrationEncounterTrackingId() {
        return MigrationEncounterTrackingId;
    }

    public void setMigrationEncounterTrackingId(Integer migrationEncounterTrackingId) {
        MigrationEncounterTrackingId = migrationEncounterTrackingId;
    }

    public Integer getOldEncounterId() {
        return oldEncounterId;
    }

    public void setOldEncounterId(Integer oldEncounterId) {
        this.oldEncounterId = oldEncounterId;
    }

    public Integer getNewEncounterId() {
        return newEncounterId;
    }

    public void setNewEncounterId(Integer newEncounterId) {
        this.newEncounterId = newEncounterId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    private Integer newEncounterId;

    private Date createdOn;
}
