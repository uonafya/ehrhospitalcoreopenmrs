package org.openmrs.module.hospitalcore.model;

import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;

public class OpdTestOrder {
	private static final long serialVersionUID = 1L;

	private Integer opdOrderId;

	private Patient patient;

	private Encounter encounter;

	private Concept concept;

	private Integer typeConcept;

	private Concept valueCoded;

	private User creator;

	private Date createdOn;

	private int billingStatus;

	private int cancelStatus;

	private BillableService billableService;

	private Date scheduleDate;

	private int indoorStatus;

	private String fromDept;

	public String getFromDept() {
		return this.fromDept;
	}

	public void setFromDept(String fromDept) {
		this.fromDept = fromDept;
	}

	public Integer getOpdOrderId() {
		return this.opdOrderId;
	}

	public void setOpdOrderId(Integer opdOrderId) {
		this.opdOrderId = opdOrderId;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Encounter getEncounter() {
		return this.encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Concept getConcept() {
		return this.concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public Integer getTypeConcept() {
		return this.typeConcept;
	}

	public void setTypeConcept(Integer typeConcept) {
		this.typeConcept = typeConcept;
	}

	public Concept getValueCoded() {
		return this.valueCoded;
	}

	public void setValueCoded(Concept valueCoded) {
		this.valueCoded = valueCoded;
	}

	public User getCreator() {
		return this.creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getBillingStatus() {
		return this.billingStatus;
	}

	public void setBillingStatus(int billingStatus) {
		this.billingStatus = billingStatus;
	}

	public int getCancelStatus() {
		return this.cancelStatus;
	}

	public void setCancelStatus(int cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	public BillableService getBillableService() {
		return this.billableService;
	}

	public void setBillableService(BillableService billableService) {
		this.billableService = billableService;
	}

	public Date getScheduleDate() {
		return this.scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public int getIndoorStatus() {
		return this.indoorStatus;
	}

	public void setIndoorStatus(int indoorStatus) {
		this.indoorStatus = indoorStatus;
	}
}
