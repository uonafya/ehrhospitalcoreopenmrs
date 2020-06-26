package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.openmrs.Concept;
import org.openmrs.Encounter;

public class InventoryStoreDrugTransactionDetail implements Serializable, Comparable<InventoryStoreDrugTransactionDetail> {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private InventoryStoreDrugTransaction transaction;

	private InventoryDrug drug;

	private InventoryDrugFormulation formulation;

	private Integer quantity;

	private Integer currentQuantity;

	private Integer issueQuantity;

	private BigDecimal unitPrice;

	private BigDecimal totalPrice;

	private BigDecimal VAT;

	private BigDecimal costToPatient;

	private String batchNo;

	private String companyName;

	private Date dateManufacture;

	private Date dateExpiry;

	private Date createdOn;

	private String receiptFrom;

	private long openingBalance;

	private long closingBalance;

	private String attribute;

	private Integer reorderPoint;

	private String patientType;

	private Encounter encounter;

	private Concept frequency;

	private Integer noOfDays;

	private String comments;

	private Integer flag;

	private InventoryStoreDrugTransactionDetail parent;

	private Set<InventoryStoreDrugTransactionDetail> subDetails;

	private Date receiptDate;

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Integer getReorderPoint() {
		return this.reorderPoint;
	}

	public void setReorderPoint(Integer reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InventoryStoreDrugTransaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(InventoryStoreDrugTransaction transaction) {
		this.transaction = transaction;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getCostToPatient() {
		return this.costToPatient;
	}

	public void setCostToPatient(BigDecimal costToPatient) {
		this.costToPatient = costToPatient;
	}

	public BigDecimal getVAT() {
		return this.VAT;
	}

	public void setVAT(BigDecimal vAT) {
		this.VAT = vAT;
	}

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public String getCompanyNameShort() {
		return (StringUtils.isNotBlank(this.companyName) && this.companyName.length() > 10) ? (this.companyName.substring(0, 7) + "...") : this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getReceiptFrom() {
		return this.receiptFrom;
	}

	public void setReceiptFrom(String receiptFrom) {
		this.receiptFrom = receiptFrom;
	}

	public Date getDateManufacture() {
		return this.dateManufacture;
	}

	public void setDateManufacture(Date dateManufacture) {
		this.dateManufacture = dateManufacture;
	}

	public Date getDateExpiry() {
		return this.dateExpiry;
	}

	public void setDateExpiry(Date dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	public Date getReceiptDate() {
		return this.receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCurrentQuantity() {
		return this.currentQuantity;
	}

	public void setCurrentQuantity(Integer currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public InventoryDrug getDrug() {
		return this.drug;
	}

	public void setDrug(InventoryDrug drug) {
		this.drug = drug;
	}

	public InventoryDrugFormulation getFormulation() {
		return this.formulation;
	}

	public void setFormulation(InventoryDrugFormulation formulation) {
		this.formulation = formulation;
	}

	public InventoryStoreDrugTransactionDetail getParent() {
		return this.parent;
	}

	public void setParent(InventoryStoreDrugTransactionDetail parent) {
		this.parent = parent;
	}

	public Set<InventoryStoreDrugTransactionDetail> getSubDetails() {
		return this.subDetails;
	}

	public void setSubDetails(Set<InventoryStoreDrugTransactionDetail> subDetails) {
		this.subDetails = subDetails;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public long getOpeningBalance() {
		return this.openingBalance;
	}

	public void setOpeningBalance(long openingBalance) {
		this.openingBalance = openingBalance;
	}

	public long getClosingBalance() {
		return this.closingBalance;
	}

	public void setClosingBalance(long closingBalance) {
		this.closingBalance = closingBalance;
	}

	public Integer getIssueQuantity() {
		return this.issueQuantity;
	}

	public void setIssueQuantity(Integer issueQuantity) {
		this.issueQuantity = issueQuantity;
	}

	public int getExpiryLessThan3Month() {
		Date currentDate = new Date();
		Date date3Month = DateUtils.addMonths(currentDate, 3);
		if (getTransaction().getTypeTransaction() == 1 && getCurrentQuantity().intValue() > 0 && this.dateExpiry.before(date3Month))
			return 1;
		return 0;
	}

	public int compareTo(InventoryStoreDrugTransactionDetail i) {
		return this.drug.compareTo(i.drug);
	}

	public String getPatientType() {
		return this.patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public Encounter getEncounter() {
		return this.encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Concept getFrequency() {
		return this.frequency;
	}

	public void setFrequency(Concept frequency) {
		this.frequency = frequency;
	}

	public Integer getNoOfDays() {
		return this.noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
