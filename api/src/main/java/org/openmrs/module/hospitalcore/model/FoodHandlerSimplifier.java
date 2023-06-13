package org.openmrs.module.hospitalcore.model;

public class FoodHandlerSimplifier {

    private String testName;
    private String conceptReference;

    private String description;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getConceptReference() {
        return conceptReference;
    }

    public void setConceptReference(String conceptReference) {
        this.conceptReference = conceptReference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    private String creator;

    private String dateCreated;

}
