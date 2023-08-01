/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.hospitalcore.model;

import org.openmrs.Concept;

import java.util.Date;

public class EhrMorgueQueue {
    private Integer ehrMorgueQueueId;

    private Integer patientId;

    private Date dateAndTimeOfDeath;

    public Integer getEhrMorgueQueueId() {
        return ehrMorgueQueueId;
    }

    public void setEhrMorgueQueueId(Integer ehrMorgueQueueId) {
        this.ehrMorgueQueueId = ehrMorgueQueueId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Date getDateAndTimeOfDeath() {
        return dateAndTimeOfDeath;
    }

    public void setDateAndTimeOfDeath(Date dateAndTimeOfDeath) {
        this.dateAndTimeOfDeath = dateAndTimeOfDeath;
    }

    public Concept getReasonOfDeath() {
        return reasonOfDeath;
    }

    public void setReasonOfDeath(Concept reasonOfDeath) {
        this.reasonOfDeath = reasonOfDeath;
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

    private Concept reasonOfDeath;

    private Date createdOn;

    private Integer createdBy;
}
