package org.openmrs.module.hospitalcore.model;

import org.openmrs.User;

import java.io.Serializable;
import java.util.Date;

public class DrugAdministration implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer drugAdministrationId;
  private Integer drugOrderId;
  private Double quantity;
  private Double amount;
  private String status;

  public Integer getDrugAdministrationId() {
    return drugAdministrationId;
  }

  public void setDrugAdministrationId(Integer drugAdministrationId) {
    this.drugAdministrationId = drugAdministrationId;
  }

  public Integer getDrugOrderId() {
    return drugOrderId;
  }

  public void setDrugOrderId(Integer drugOrderId) {
    this.drugOrderId = drugOrderId;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getDrugAdministrationDate() {
    return drugAdministrationDate;
  }

  public void setDrugAdministrationDate(Date drugAdministrationDate) {
    this.drugAdministrationDate = drugAdministrationDate;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public Date getDateModified() {
    return dateModified;
  }

  public void setDateModified(Date dateModified) {
    this.dateModified = dateModified;
  }

  public User getChangedBy() {
    return changedBy;
  }

  public void setChangedBy(User changedBy) {
    this.changedBy = changedBy;
  }

  private Date drugAdministrationDate;
  private Date createdOn;
  private User createdBy;
  private Date dateModified;
  private User changedBy;
}
