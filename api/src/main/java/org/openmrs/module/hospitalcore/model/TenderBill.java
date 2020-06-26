package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.openmrs.User;

public class TenderBill implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer tenderBillId;

	private BigDecimal amount;

	private String description;

	private Boolean printed = Boolean.valueOf(false);

	private Date createdDate;

	private Boolean voided = Boolean.valueOf(false);

	private Date voidedDate;

	private Company company;

	private User creator;

	private Receipt receipt;

	private Set<TenderBillItem> billItems;

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

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTenderBillId() {
		return this.tenderBillId;
	}

	public void setTenderBillId(Integer tenderBillId) {
		this.tenderBillId = tenderBillId;
	}

	public Set<TenderBillItem> getBillItems() {
		return this.billItems;
	}

	public void setBillItems(Set<TenderBillItem> billItems) {
		this.billItems = billItems;
	}

	public void addBillItem(TenderBillItem billItem) {
		if (this.billItems == null)
			this.billItems = new HashSet<TenderBillItem>();
		this.billItems.add(billItem);
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
