package org.openmrs.module.hospitalcore.model;

public class MigrationOrders {
    private Integer migrationOrderId;
    private Integer oldOrderId;

    public Integer getMigrationOrderId() {
        return migrationOrderId;
    }

    public void setMigrationOrderId(Integer migrationOrderId) {
        this.migrationOrderId = migrationOrderId;
    }

    public Integer getOldOrderId() {
        return oldOrderId;
    }

    public void setOldOrderId(Integer oldOrderId) {
        this.oldOrderId = oldOrderId;
    }

    public Integer getNewOrderId() {
        return newOrderId;
    }

    public void setNewOrderId(Integer newOrderId) {
        this.newOrderId = newOrderId;
    }

    private Integer newOrderId;
}
