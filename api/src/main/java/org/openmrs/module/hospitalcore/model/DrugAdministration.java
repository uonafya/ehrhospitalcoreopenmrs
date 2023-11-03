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
  private Date drugAdministrationDate;
  private Date createdOn;
  private User createdBy;
  private Date dateModified;
  private User changedBy;
}
