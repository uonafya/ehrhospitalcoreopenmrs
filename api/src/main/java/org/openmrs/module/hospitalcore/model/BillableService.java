package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import org.openmrs.Concept;
import org.openmrs.module.hospitalcore.util.Money;

public class BillableService implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer serviceId;

    private String name;

    private String shortName;

    private BigDecimal price;

    private Integer conceptId;

    private Boolean disable = Boolean.valueOf(false);

    private Concept category;

    public Integer getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getTextPrice() {
        return (new Money(this.price)).toString();
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getConceptId() {
        return this.conceptId;
    }

    public void setConceptId(Integer conceptId) {
        this.conceptId = conceptId;
    }

    public Boolean getDisable() {
        return this.disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Concept getCategory() {
        return this.category;
    }

    public void setCategory(Concept category) {
        this.category = category;
    }
}
