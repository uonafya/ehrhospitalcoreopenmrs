package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.module.hospitalcore.util.ActionValue;

public class InventoryStoreDrugIndent implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private InventoryStore store;

	private String name;

	private Date createdOn;

	private Integer subStoreStatus;

	private Integer mainStoreStatus;

	private InventoryStore mainStore;

	private InventoryStoreDrugTransaction transaction;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InventoryStore getStore() {
		return this.store;
	}

	public void setStore(InventoryStore store) {
		this.store = store;
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

	public Integer getSubStoreStatus() {
		return this.subStoreStatus;
	}

	public void setSubStoreStatus(Integer subStoreStatus) {
		this.subStoreStatus = subStoreStatus;
	}

	public Integer getMainStoreStatus() {
		return this.mainStoreStatus;
	}

	public String getMainStoreStatusName() {
		return ActionValue.getIndentMainbStoreName(this.mainStoreStatus.intValue());
	}

	public String getSubStoreStatusName() {
		return ActionValue.getIndentSubStoreName(this.subStoreStatus.intValue());
	}

	public void setMainStoreStatus(Integer mainStoreStatus) {
		this.mainStoreStatus = mainStoreStatus;
	}

	public InventoryStore getMainStore() {
		return this.mainStore;
	}

	public void setMainStore(InventoryStore mainStore) {
		this.mainStore = mainStore;
	}

	public InventoryStoreDrugTransaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(InventoryStoreDrugTransaction transaction) {
		this.transaction = transaction;
	}
}
