package org.openmrs.module.hospitalcore.model;

import java.util.Set;

import org.openmrs.Role;

public class InventoryStoreRoleRelation {
	private Integer id;
	
	private Integer storeid;
	private String roleName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}
