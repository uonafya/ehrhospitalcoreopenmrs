package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;

public class InventoryStoreDrugPatientDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private InventoryStoreDrugPatient storeDrugPatient;

	private Integer quantity;

	private Integer issueCount;

	private InventoryStoreDrugTransactionDetail transactionDetail;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public InventoryStoreDrugPatient getStoreDrugPatient() {
		return this.storeDrugPatient;
	}

	public void setStoreDrugPatient(InventoryStoreDrugPatient storeDrugPatient) {
		this.storeDrugPatient = storeDrugPatient;
	}

	public Integer getIssueCount() {
		return this.issueCount;
	}

	public void setIssueCount(Integer issueCount) {
		this.issueCount = issueCount;
	}

	public InventoryStoreDrugTransactionDetail getTransactionDetail() {
		return this.transactionDetail;
	}

	public void setTransactionDetail(InventoryStoreDrugTransactionDetail transactionDetail) {
		this.transactionDetail = transactionDetail;
	}
}
