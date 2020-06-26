package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import org.openmrs.Concept;

public class Answer implements Serializable {
    private Integer answerId;

    private Question question;

    private Question quest;

    private Concept answerConcept;

    private String freeText;

    public Question getQuest() {
        return this.quest;
    }

    public void setQuest(Question quest) {
        this.quest = quest;
    }

    public Integer getAnswerId() {
        return this.answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Concept getAnswerConcept() {
        return this.answerConcept;
    }

    public void setAnswerConcept(Concept answerConcept) {
        this.answerConcept = answerConcept;
    }

    public String getFreeText() {
        return this.freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }
}
