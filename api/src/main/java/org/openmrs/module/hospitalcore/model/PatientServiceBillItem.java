package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.openmrs.Order;

public class PatientServiceBillItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer patientServiceBillItemId;

	private BillableService service;

	private PatientServiceBill patientServiceBill;

	private BigDecimal unitPrice;

	private BigDecimal amount;

	private BigDecimal actualAmount;

	private Integer quantity;

	private String name;

	private Date createdDate;

	private Boolean voided = Boolean.valueOf(false);

	private Order order;

	private Date voidedDate;

	private String voidedby;

	public BillableService getService() {
		return this.service;
	}

	public void setService(BillableService service) {
		this.service = service;
	}

	public PatientServiceBill getPatientServiceBill() {
		return this.patientServiceBill;
	}

	public void setPatientServiceBill(PatientServiceBill patientServiceBill) {
		this.patientServiceBill = patientServiceBill;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getPatientServiceBillItemId() {
		return this.patientServiceBillItemId;
	}

	public void setPatientServiceBillItemId(Integer patientServiceBillItemId) {
		this.patientServiceBillItemId = patientServiceBillItemId;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public BigDecimal getActualAmount() {
		return this.actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getVoidedby() {
		return this.voidedby;
	}

	public void setVoidedby(String voidedby) {
		this.voidedby = voidedby;
	}
}
