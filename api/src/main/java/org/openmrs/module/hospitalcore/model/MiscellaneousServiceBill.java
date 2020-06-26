package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MiscellaneousServiceBill implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String liableName;

	private String description;

	private BigDecimal amount;

	private Date createdDate;

	private Integer creator;

	private Boolean voided = Boolean.valueOf(false);

	private Date voidedDate;

	private Boolean printed = Boolean.valueOf(false);

	private MiscellaneousService service;

	private Integer quantity;

	private Receipt receipt;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLiableName() {
		return this.liableName;
	}

	public void setLiableName(String liableName) {
		this.liableName = liableName;
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

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreator() {
		return this.creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
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

	public MiscellaneousService getService() {
		return this.service;
	}

	public void setService(MiscellaneousService service) {
		this.service = service;
	}

	public Boolean getPrinted() {
		return this.printed;
	}

	public void setPrinted(Boolean printed) {
		this.printed = printed;
	}

	public Receipt getReceipt() {
		return this.receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
