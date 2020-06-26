package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.openmrs.User;

public class AmbulanceBill implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer ambulanceBillId;

	private Ambulance ambulance;

	private String name;

	private Driver driver;

	private String description;

	private BigDecimal amount;

	private Date createdDate;

	private Boolean voided = Boolean.valueOf(false);

	private Boolean printed = Boolean.valueOf(false);

	private Date voidedDate;

	private User creator;

	private Integer numberOfTrip;

	private Set<AmbulanceBillItem> billItems;

	private Receipt receipt;

	public Ambulance getAmbulance() {
		return this.ambulance;
	}

	public void setAmbulance(Ambulance ambulance) {
		this.ambulance = ambulance;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public Integer getNumberOfTrip() {
		return this.numberOfTrip;
	}

	public void setNumberOfTrip(Integer numberOfTrip) {
		this.numberOfTrip = numberOfTrip;
	}

	public Integer getAmbulanceBillId() {
		return this.ambulanceBillId;
	}

	public void setAmbulanceBillId(Integer ambulanceBillId) {
		this.ambulanceBillId = ambulanceBillId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addBillItem(AmbulanceBillItem billItem) {
		if (this.billItems == null)
			this.billItems = new HashSet<AmbulanceBillItem>();
		this.billItems.add(billItem);
	}

	public Set<AmbulanceBillItem> getBillItems() {
		return this.billItems;
	}

	public void setBillItems(Set<AmbulanceBillItem> billItems) {
		this.billItems = billItems;
	}

	public Boolean getPrinted() {
		return this.printed;
	}

	public void setPrinted(Boolean printed) {
		this.printed = printed;
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
}
