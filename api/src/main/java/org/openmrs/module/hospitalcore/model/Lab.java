package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.OrderType;
import org.openmrs.PatientIdentifierType;
import org.openmrs.Role;

public class Lab implements Serializable {
	private static final long serialVersionUID = 716116064672914345L;

	private Integer labId;

	private String name;

	private String description;

	private OrderType labOrderType;

	private EncounterType labTestEncounterType;

	private PatientIdentifierType patientIdentifierType;

	private Set<Concept> investigationsToDisplay;

	private Set<Concept> confidentialTestsToDisplay;

	private Integer rescheduleperiod = Integer.valueOf(7);

	private Boolean retired = Boolean.valueOf(false);

	private Role role;

	private Date retiredDate;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OrderType getLabOrderType() {
		return this.labOrderType;
	}

	public void setLabOrderType(OrderType labOrderType) {
		this.labOrderType = labOrderType;
	}

	public EncounterType getLabTestEncounterType() {
		return this.labTestEncounterType;
	}

	public void setLabTestEncounterType(EncounterType labTestEncounterType) {
		this.labTestEncounterType = labTestEncounterType;
	}

	public PatientIdentifierType getPatientIdentifierType() {
		return this.patientIdentifierType;
	}

	public void setPatientIdentifierType(PatientIdentifierType patientIdentifierType) {
		this.patientIdentifierType = patientIdentifierType;
	}

	public Set<Concept> getInvestigationsToDisplay() {
		return this.investigationsToDisplay;
	}

	public void setInvestigationsToDisplay(Set<Concept> investigationsToDisplay) {
		this.investigationsToDisplay = investigationsToDisplay;
	}

	public Set<Concept> getConfidentialTestsToDisplay() {
		return this.confidentialTestsToDisplay;
	}

	public void setConfidentialTestsToDisplay(Set<Concept> confidentialTestsToDisplay) {
		this.confidentialTestsToDisplay = confidentialTestsToDisplay;
	}

	public Integer getRescheduleperiod() {
		return this.rescheduleperiod;
	}

	public void setRescheduleperiod(Integer rescheduleperiod) {
		this.rescheduleperiod = rescheduleperiod;
	}

	public Integer getLabId() {
		return this.labId;
	}

	public void setLabId(Integer labId) {
		this.labId = labId;
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

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
