package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;

public class PatientServiceBill implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer patientServiceBillId;

	private Patient patient;

	private User creator;

	private BigDecimal amount;

	private BigDecimal actualAmount;

	private Boolean printed = Boolean.valueOf(false);

	private Boolean voided = Boolean.valueOf(false);

	private BigDecimal rebateAmount;

	private String categoryNumber;

	private String patientCategory;

	private Integer admittedDays;

	private String patientSubCategory;

	private Date voidedDate;

	private Date createdDate;

	private String description;

	private Receipt receipt;

	private BigDecimal waiverAmount;

	private Integer freeBill;

	private String comment;

	private String paymentMode;

	private Set<PatientServiceBillItem> billItems;

	private Encounter encounter;

	private int dischargeStatus;

	public String getPatientSubCategory() {
		return this.patientSubCategory;
	}

	public void setPatientSubCategory(String patientSubCategory) {
		this.patientSubCategory = patientSubCategory;
	}

	public BigDecimal getRebateAmount() {
		return this.rebateAmount;
	}

	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	public String getCategoryNumber() {
		return this.categoryNumber;
	}

	public void setCategoryNumber(String categoryNumber) {
		this.categoryNumber = categoryNumber;
	}

	public String getPatientCategory() {
		return this.patientCategory;
	}

	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}

	public Integer getAdmittedDays() {
		return this.admittedDays;
	}

	public void setAdmittedDays(Integer admittedDays) {
		this.admittedDays = admittedDays;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Integer getPatientServiceBillId() {
		return this.patientServiceBillId;
	}

	public void setPatientServiceBillId(Integer patientServiceBillId) {
		this.patientServiceBillId = patientServiceBillId;
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

	public Boolean getPrinted() {
		return this.printed;
	}

	public void setPrinted(Boolean printed) {
		this.printed = printed;
	}

	public Boolean getVoided() {
		return this.voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

	public Date getVoidedDate() {
		return this.voidedDate;
	}

	public void setVoidedDate(Date voidedDate) {
		this.voidedDate = voidedDate;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addBillItem(PatientServiceBillItem item) {
		if (this.billItems == null)
			this.billItems = new HashSet<PatientServiceBillItem>();
		this.billItems.add(item);
	}

	public Set<PatientServiceBillItem> getBillItems() {
		return this.billItems;
	}

	public void setBillItems(Set<PatientServiceBillItem> billItems) {
		this.billItems = billItems;
	}

	public User getCreator() {
		return this.creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Receipt getReceipt() {
		return this.receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Integer getFreeBill() {
		return this.freeBill;
	}

	public void setFreeBill(Integer freeBill) {
		this.freeBill = freeBill;
	}

	public BigDecimal getActualAmount() {
		return this.actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getWaiverAmount() {
		return this.waiverAmount;
	}

	public void setWaiverAmount(BigDecimal waiverAmount) {
		this.waiverAmount = waiverAmount;
	}

	public Encounter getEncounter() {
		return this.encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public int getDischargeStatus() {
		return this.dischargeStatus;
	}

	public void setDischargeStatus(int dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}
}
