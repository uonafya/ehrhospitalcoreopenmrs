package org.openmrs.module.hospitalcore.model;

public class MigrationObsTracking {

    private Integer migrationObsId;

    public Integer getMigrationObsId() {
        return migrationObsId;
    }

    public void setMigrationObsId(Integer migrationObsId) {
        this.migrationObsId = migrationObsId;
    }

    public Integer getOldObsId() {
        return oldObsId;
    }

    public void setOldObsId(Integer oldObsId) {
        this.oldObsId = oldObsId;
    }

    public Integer getNewObsId() {
        return newObsId;
    }

    public void setNewObsId(Integer newObsId) {
        this.newObsId = newObsId;
    }

    private Integer oldObsId;
    private Integer newObsId;
}
