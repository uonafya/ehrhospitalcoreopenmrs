package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AmbulanceBillItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer ambulanceBillItemId;

	private String name;

	private Ambulance ambulance;

	private AmbulanceBill ambulanceBill;

	private Integer numberOfTrip;

	private BigDecimal amount;

	private Date createdDate;

	private Boolean voided = Boolean.valueOf(false);

	private Date voidedDate;

	private String patientName;

	private String receiptNumber;

	private String origin;

	private String destination;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ambulance getAmbulance() {
		return this.ambulance;
	}

	public void setAmbulance(Ambulance ambulance) {
		this.ambulance = ambulance;
	}

	public AmbulanceBill getAmbulanceBill() {
		return this.ambulanceBill;
	}

	public void setAmbulanceBill(AmbulanceBill ambulanceBill) {
		this.ambulanceBill = ambulanceBill;
	}

	public Integer getNumberOfTrip() {
		return this.numberOfTrip;
	}

	public void setNumberOfTrip(Integer numberOfTrip) {
		this.numberOfTrip = numberOfTrip;
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

	public Integer getAmbulanceBillItemId() {
		return this.ambulanceBillItemId;
	}

	public void setAmbulanceBillItemId(Integer ambulanceBillItemId) {
		this.ambulanceBillItemId = ambulanceBillItemId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getReceiptNumber() {
		return this.receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
