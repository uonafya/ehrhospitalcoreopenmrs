package org.openmrs.module.hospitalcore.model;

import java.util.Date;

public class MigrationVisitsTracking {

    private Integer MigrationVisitsTrackingId;
    private Integer oldVisitId;

    private Integer newVisitId;

    private Date createdOn;

    public Integer getMigrationVisitsTrackingId() {
        return MigrationVisitsTrackingId;
    }

    public void setMigrationVisitsTrackingId(Integer migrationVisitsTrackingId) {
        MigrationVisitsTrackingId = migrationVisitsTrackingId;
    }

    public Integer getOldVisitId() {
        return oldVisitId;
    }

    public void setOldVisitId(Integer oldVisitId) {
        this.oldVisitId = oldVisitId;
    }

    public Integer getNewVisitId() {
        return newVisitId;
    }

    public void setNewVisitId(Integer newVisitId) {
        this.newVisitId = newVisitId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    private Integer createdBy;
}
