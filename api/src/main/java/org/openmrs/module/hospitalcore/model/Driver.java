package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

public class Driver implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer driverId;

	private String name;

	private String address;

	private String description;

	private String phone;

	private Date createdDate;

	private Boolean retired = Boolean.valueOf(false);

	private Date retiredDate;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getRetired() {
		return this.retired;
	}

	public void setRetired(Boolean retired) {
		this.retired = retired;
	}

	public Date getRetiredDate() {
		return this.retiredDate;
	}

	public void setRetiredDate(Date retiredDate) {
		this.retiredDate = retiredDate;
	}

	public Integer getDriverId() {
		return this.driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
}
