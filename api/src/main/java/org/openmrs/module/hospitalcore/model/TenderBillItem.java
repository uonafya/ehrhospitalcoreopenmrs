package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TenderBillItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer tenderBillItemId;

    private String name;

    private BigDecimal amount;

    private BigDecimal unitPrice;

    private int quantity;

    private Date createdDate;

    private Boolean voided = Boolean.valueOf(false);

    private Date voidedDate;

    private Tender tender;

    private TenderBill tenderBill;

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Tender getTender() {
        return this.tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }

    public TenderBill getTenderBill() {
        return this.tenderBill;
    }

    public void setTenderBill(TenderBill tenderBill) {
        this.tenderBill = tenderBill;
    }

    public Integer getTenderBillItemId() {
        return this.tenderBillItemId;
    }

    public void setTenderBillItemId(Integer tenderBillItemId) {
        this.tenderBillItemId = tenderBillItemId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
