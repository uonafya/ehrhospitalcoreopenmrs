package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.openmrs.Role;

public class InventoryStore implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Date createdOn;

	private String createdBy;

	private Role role;

	private Boolean retired = Boolean.valueOf(false);

	private String code;

	private int isDrug;

	private Set<InventoryStore> parentStores;

	private Set<InventoryStore> subStores;

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

	public Boolean getRetired() {
		return this.retired;
	}

	public void setRetired(Boolean retired) {
		this.retired = retired;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<InventoryStore> getParentStores() {
		return this.parentStores;
	}

	public void setParentStores(Set<InventoryStore> parentStores) {
		this.parentStores = parentStores;
	}

	public Set<InventoryStore> getSubStores() {
		return this.subStores;
	}

	public void setSubStores(Set<InventoryStore> subStores) {
		this.subStores = subStores;
	}

	public int getIsDrug() {
		return this.isDrug;
	}

	public String getIsDrugName() {
		return (this.isDrug == 1) ? "Yes" : "No";
	}

	public void setIsDrug(int isDrug) {
		this.isDrug = isDrug;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
