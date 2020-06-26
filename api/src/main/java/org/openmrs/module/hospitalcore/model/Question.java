package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import org.openmrs.Concept;

public class Question implements Serializable {
    private Integer questionId;

    private Symptom symptom;

    private Examination examination;

    private Concept questionConcept;

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Symptom getSymptom() {
        return this.symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public Concept getQuestionConcept() {
        return this.questionConcept;
    }

    public void setQuestionConcept(Concept questionConcept) {
        this.questionConcept = questionConcept;
    }

    public Examination getExamination() {
        return this.examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
    }
}
