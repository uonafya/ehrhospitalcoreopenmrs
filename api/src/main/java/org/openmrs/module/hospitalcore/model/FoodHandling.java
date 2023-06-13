package org.openmrs.module.hospitalcore.model;

import org.openmrs.Concept;
import org.openmrs.User;
import org.openmrs.api.context.Context;

import java.io.Serializable;
import java.util.Date;

public class FoodHandling implements Serializable {

    private Integer foodHandlingId;

    public Integer getFoodHandlingId() {
        return foodHandlingId;
    }

    public void setFoodHandlingId(Integer foodHandlingId) {
        this.foodHandlingId = foodHandlingId;
    }

    public Integer getConceptId() {
        return conceptId;
    }

    public void setConceptId(Integer conceptId) {
        this.conceptId = conceptId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Integer conceptId;

    private String  description;

    public FoodHandling(Integer conceptId, String description) {
        this.conceptId = conceptId;
        this.description = description;
    }

    public FoodHandling() {
    }

    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    private User creator;

    private Boolean retired =false;

    public Boolean getRetired() {
        return retired;
    }

    public void setRetired(Boolean retired) {
        this.retired = retired;
    }

    public Date getRetiredDate() {
        return retiredDate;
    }

    public void setRetiredDate(Date retiredDate) {
        this.retiredDate = retiredDate;
    }

    private Date retiredDate;

    public Concept getFoodHandlerConcept() {
        return Context.getConceptService().getConcept(getConceptId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
