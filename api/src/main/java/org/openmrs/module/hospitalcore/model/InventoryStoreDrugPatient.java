package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.openmrs.Patient;
import org.openmrs.User;

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

	private BigDecimal waiverAmount;

	private String comment;

	public User getPrescriber() {
		return this.prescriber;
	}

	public void setPrescriber(User prescriber) {
		this.prescriber = prescriber;
	}

	public Integer getValues() {
		return this.values;
	}

	public void setValues(Integer values) {
		this.values = values;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InventoryStore getStore() {
		return this.store;
	}

	public void setStore(InventoryStore store) {
		this.store = store;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrescription() {
		return this.prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getStatuss() {
		return this.statuss;
	}

	public void setStatuss(Integer statuss) {
		this.statuss = statuss;
	}

	public BigDecimal getWaiverAmount() {
		return this.waiverAmount;
	}

	public void setWaiverAmount(BigDecimal waiverAmount) {
		this.waiverAmount = waiverAmount;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
