package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.User;

public class Examination implements Serializable {
    private Integer examinationId;

    private Encounter encounters;

    private Concept examinationConcept;

    private Date createdDate;

    private User creator;

    public Integer getExaminationId() {
        return this.examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Encounter getEncounters() {
        return this.encounters;
    }

    public void setEncounters(Encounter encounters) {
        this.encounters = encounters;
    }

    public Concept getExaminationConcept() {
        return this.examinationConcept;
    }

    public void setExaminationConcept(Concept examinationConcept) {
        this.examinationConcept = examinationConcept;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreator() {
        return this.creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
