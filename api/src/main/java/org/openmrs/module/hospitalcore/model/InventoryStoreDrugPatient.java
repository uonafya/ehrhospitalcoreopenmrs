/**
 * Copyright 2011 Society for Health Information Systems Programmes, India (HISP India)
 * <p/>
 * This file is part of Hospital-core module.
 * <p/>
 * Hospital-core module is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Hospital-core module is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Hospital-core module.  If not, see <http://www.gnu.org/licenses/>.
 **/


package org.openmrs.module.hospitalcore.model;

import org.openmrs.Patient;
import org.openmrs.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InventoryStoreDrugPatient implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private InventoryStore store;
    private String name;

    private String prescription;
    private Date createdOn;
    private String createdBy;
    private Patient patient;
    private String identifier;
    private Integer values;
    private Integer statuss;
    private User prescriber;

    public User getPrescriber() {
        return prescriber;
    }

    public void setPrescriber(User prescriber) {
        this.prescriber = prescriber;
    }

    //Add waiver and comment processing - needs review
    private BigDecimal waiverAmount;
    private String comment;

    public Integer getValues() {
        return values;
    }

    public void setValues(Integer values) {
        this.values = values;
    }

    //private String patientCategory;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InventoryStore getStore() {
        return store;
    }

    public void setStore(InventoryStore store) {
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getStatuss() {
        return statuss;
    }

    public void setStatuss(Integer statuss) {
        this.statuss = statuss;
    }

    public BigDecimal getWaiverAmount() {
        return waiverAmount;
    }

    public void setWaiverAmount(BigDecimal waiverAmount) {
        this.waiverAmount = waiverAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
