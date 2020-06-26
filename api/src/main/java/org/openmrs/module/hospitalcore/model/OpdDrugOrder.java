package org.openmrs.module.hospitalcore.model;

import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;

public class OpdDrugOrder {
	private static final long serialVersionUID = 1L;

	private Integer opdDrugOrderId;

	private Patient patient;

	private Encounter encounter;

	private InventoryDrug inventoryDrug;

	private InventoryDrugFormulation inventoryDrugFormulation;

	private Concept frequency;

	private Integer noOfDays;

	private String comments;

	private String dosage;

	private Concept dosageUnit;

	private User creator;

	private Date createdOn;

	private int orderStatus;

	private int cancelStatus;

	private String referralWardName;

	public String getDosage() {
		return this.dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Concept getDosageUnit() {
		return this.dosageUnit;
	}

	public void setDosageUnit(Concept dosageUnit) {
		this.dosageUnit = dosageUnit;
	}

	public Integer getOpdDrugOrderId() {
		return this.opdDrugOrderId;
	}

	public void setOpdDrugOrderId(Integer opdDrugOrderId) {
		this.opdDrugOrderId = opdDrugOrderId;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Encounter getEncounter() {
		return this.encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public InventoryDrug getInventoryDrug() {
		return this.inventoryDrug;
	}

	public void setInventoryDrug(InventoryDrug inventoryDrug) {
		this.inventoryDrug = inventoryDrug;
	}

	public InventoryDrugFormulation getInventoryDrugFormulation() {
		return this.inventoryDrugFormulation;
	}

	public void setInventoryDrugFormulation(InventoryDrugFormulation inventoryDrugFormulation) {
		this.inventoryDrugFormulation = inventoryDrugFormulation;
	}

	public Concept getFrequency() {
		return this.frequency;
	}

	public void setFrequency(Concept frequency) {
		this.frequency = frequency;
	}

	public Integer getNoOfDays() {
		return this.noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public User getCreator() {
		return this.creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getCancelStatus() {
		return this.cancelStatus;
	}

	public void setCancelStatus(int cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	public String getReferralWardName() {
		return this.referralWardName;
	}

	public void setReferralWardName(String referralWardName) {
		this.referralWardName = referralWardName;
	}
}
