package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

public class InventoryDrugFormulation implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String dozage;

	private String description;

	private Date createdOn;

	private String createdBy;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDozage() {
		return this.dozage;
	}

	public void setDozage(String dozage) {
		this.dozage = dozage;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}
