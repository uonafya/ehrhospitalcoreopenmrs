package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.User;

public class LabTest implements Serializable {
	private static final long serialVersionUID = 552808832594082704L;

	private Integer labTestId;

	private Lab lab;

	private Date acceptDate;

	private String sampleNumber;

	private Order order;

	private int labTestStatus;

	private Patient patient;

	private Concept concept;

	private User creator;

	private String status;

	private Encounter encounter;

	public Date getAcceptDate() {
		return this.acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getSampleNumber() {
		return this.sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getLabTestId() {
		return this.labTestId;
	}

	public void setLabTestId(Integer labTestId) {
		this.labTestId = labTestId;
	}

	public Lab getLab() {
		return this.lab;
	}

	public void setLab(Lab lab) {
		this.lab = lab;
	}

	public int getLabTestStatus() {
		return this.labTestStatus;
	}

	public void setLabTestStatus(int labTestStatus) {
		this.labTestStatus = labTestStatus;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Concept getConcept() {
		return this.concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public User getCreator() {
		return this.creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Encounter getEncounter() {
		return this.encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
}
