package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.openmrs.module.hospitalcore.util.ActionValue;

public class InventoryStoreDrugTransaction implements Serializable {
	public static final int STATUS_DONE = 1;

	public static final int STATUS_NOT_YET = 0;

	private static final long serialVersionUID = 1L;

	private Integer id;

	private InventoryStore store;

	private int status;

	private String description;

	private int typeTransaction;

	private Set<InventoryStoreDrugIndent> indents;

	private Date createdOn;

	private String createdBy;

	private String paymentMode;

	private String paymentCategory;

	public String getPaymentCategory() {
		return this.paymentCategory;
	}

	public void setPaymentCategory(String paymentCategory) {
		this.paymentCategory = paymentCategory;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return StringUtils.isBlank(this.description) ? "Unknown" : this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTypeTransaction() {
		return this.typeTransaction;
	}

	public String getTypeTransactionName() {
		return (this.typeTransaction == 1) ? ActionValue.TRANSACTION_NAMES.get(0) : ActionValue.TRANSACTION_NAMES.get(1);
	}

	public void setTypeTransaction(int typeTransaction) {
		this.typeTransaction = typeTransaction;
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

	public Set<InventoryStoreDrugIndent> getIndents() {
		return this.indents;
	}

	public void setIndents(Set<InventoryStoreDrugIndent> indents) {
		this.indents = indents;
	}
}
