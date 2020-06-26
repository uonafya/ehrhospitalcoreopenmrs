package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;

public class IndoorPatientServiceBill implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer indoorPatientServiceBillId;

	private Patient patient;

	private User creator;

	private BigDecimal amount;

	private BigDecimal actualAmount;

	private Date createdDate;

	private Set<IndoorPatientServiceBillItem> billItems;

	private Encounter encounter;

	private Integer selectedCategory;

	public Integer getIndoorPatientServiceBillId() {
		return this.indoorPatientServiceBillId;
	}

	public void setIndoorPatientServiceBillId(Integer indoorPatientServiceBillId) {
		this.indoorPatientServiceBillId = indoorPatientServiceBillId;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void addBillItem(IndoorPatientServiceBillItem item) {
		if (this.billItems == null)
			this.billItems = new HashSet<IndoorPatientServiceBillItem>();
		this.billItems.add(item);
	}

	public Set<IndoorPatientServiceBillItem> getBillItems() {
		return this.billItems;
	}

	public void setBillItems(Set<IndoorPatientServiceBillItem> billItems) {
		this.billItems = billItems;
	}

	public User getCreator() {
		return this.creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public BigDecimal getActualAmount() {
		return this.actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Encounter getEncounter() {
		return this.encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Integer getSelectedCategory() {
		return this.selectedCategory;
	}

	public void setSelectedCategory(Integer selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
}
