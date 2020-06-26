package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.openmrs.Drug;
import org.openmrs.module.hospitalcore.util.ActionValue;

public class InventoryDrug implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private InventoryDrugUnit unit;

	private InventoryDrugCategory category;

	private Set<InventoryDrugFormulation> formulations;

	private Date createdOn;

	private String createdBy;

	private Drug drugCore;

	private int attribute;

	private Integer reorderQty;

	private Integer consumption;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public String getNameShort() {
		return (StringUtils.isNotBlank(this.name) && this.name.length() > 30) ? (this.name.substring(0, 30) + "...") : this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InventoryDrugUnit getUnit() {
		return this.unit;
	}

	public void setUnit(InventoryDrugUnit unit) {
		this.unit = unit;
	}

	public InventoryDrugCategory getCategory() {
		return this.category;
	}

	public void setCategory(InventoryDrugCategory category) {
		this.category = category;
	}

	public Set<InventoryDrugFormulation> getFormulations() {
		return this.formulations;
	}

	public void setFormulations(Set<InventoryDrugFormulation> formulations) {
		this.formulations = formulations;
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

	public int getAttribute() {
		return this.attribute;
	}

	public String getAttributeName() {
		return ActionValue.getDrugAttribute(this.attribute);
	}

	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}

	public Integer getConsumption() {
		return this.consumption;
	}

	public void setConsumption(Integer consumption) {
		this.consumption = consumption;
	}

	public Drug getDrugCore() {
		return this.drugCore;
	}

	public void setDrugCore(Drug drugCore) {
		this.drugCore = drugCore;
	}

	public Integer getReorderQty() {
		return this.reorderQty;
	}

	public void setReorderQty(Integer reorderQty) {
		this.reorderQty = reorderQty;
	}

	public int compareTo(InventoryDrug drug) {
		return this.name.compareTo(drug.name);
	}
}
