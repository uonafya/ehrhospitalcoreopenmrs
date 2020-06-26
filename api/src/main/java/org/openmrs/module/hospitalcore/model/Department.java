package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.openmrs.Concept;

public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Date createdOn;

	private String createdBy;

	private Set<Concept> wards;

	private Boolean retired = Boolean.valueOf(false);

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

	public Set<Concept> getWards() {
		return this.wards;
	}

	public void setWards(Set<Concept> wards) {
		this.wards = wards;
	}

	public Boolean getRetired() {
		return this.retired;
	}

	public void setRetired(Boolean retired) {
		this.retired = retired;
	}
}
