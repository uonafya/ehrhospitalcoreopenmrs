package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;

public class ImmunizationStoreTransactionType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String transactionType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}

