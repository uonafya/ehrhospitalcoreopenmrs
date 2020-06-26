package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

public class PatientDrugHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private TriagePatientQueueLog triageLogId;

    private Integer patientId;

    private String currentMedication;

    private String medicationName;

    private String medicationPeriod;

    private String medicationReason;

    private String medicationRecord;

    private String sensitiveMedication;

    private String sensitiveMedicationName;

    private String sensitiveMedicationSymptom;

    private String invasiveContraception;

    private String invasiveContraceptionName;

    private Date createdOn;

    public String getCurrentMedication() {
        return this.currentMedication;
    }

    public void setCurrentMedication(String currentMedication) {
        this.currentMedication = currentMedication;
    }

    public String getMedicationName() {
        return this.medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getMedicationPeriod() {
        return this.medicationPeriod;
    }

    public void setMedicationPeriod(String medicationPeriod) {
        this.medicationPeriod = medicationPeriod;
    }

    public String getMedicationReason() {
        return this.medicationReason;
    }

    public void setMedicationReason(String medicationReason) {
        this.medicationReason = medicationReason;
    }

    public String getMedicationRecord() {
        return this.medicationRecord;
    }

    public void setMedicationRecord(String medicationRecord) {
        this.medicationRecord = medicationRecord;
    }

    public String getSensitiveMedication() {
        return this.sensitiveMedication;
    }

    public void setSensitiveMedication(String sensitiveMedication) {
        this.sensitiveMedication = sensitiveMedication;
    }

    public String getSensitiveMedicationName() {
        return this.sensitiveMedicationName;
    }

    public void setSensitiveMedicationName(String sensitiveMedicationName) {
        this.sensitiveMedicationName = sensitiveMedicationName;
    }

    public String getSensitiveMedicationSymptom() {
        return this.sensitiveMedicationSymptom;
    }

    public void setSensitiveMedicationSymptom(String sensitiveMedicationSymptom) {
        this.sensitiveMedicationSymptom = sensitiveMedicationSymptom;
    }

    public String getInvasiveContraception() {
        return this.invasiveContraception;
    }

    public void setInvasiveContraception(String invasiveContraception) {
        this.invasiveContraception = invasiveContraception;
    }

    public String getInvasiveContraceptionName() {
        return this.invasiveContraceptionName;
    }

    public void setInvasiveContraceptionName(String invasiveContraceptionName) {
        this.invasiveContraceptionName = invasiveContraceptionName;
    }

    public Integer getPatientId() {
        return this.patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public static long getSerialversionuid() {
        return 1L;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TriagePatientQueueLog getTriageLogId() {
        return this.triageLogId;
    }

    public void setTriageLogId(TriagePatientQueueLog triageLogId) {
        this.triageLogId = triageLogId;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
