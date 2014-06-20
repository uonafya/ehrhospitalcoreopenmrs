/**
 *  Copyright 2010 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of Hospital-core module.
 *
 *  Hospital-core module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Hospital-core module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Hospital-core module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PatientFamilyHistory implements Serializable {

	public String getParentStatus() {
		return parentStatus;
	}

	public void setParentStatus(String parentStatus) {
		this.parentStatus = parentStatus;
	}

	public String getParentDeathCause() {
		return parentDeathCause;
	}

	public void setParentDeathCause(String parentDeathCause) {
		this.parentDeathCause = parentDeathCause;
	}

	public String getParentDeathAge() {
		return parentDeathAge;
	}

	public void setParentDeathAge(String parentDeathAge) {
		this.parentDeathAge = parentDeathAge;
	}

	public String getSiblingStatus() {
		return siblingStatus;
	}

	public void setSiblingStatus(String siblingStatus) {
		this.siblingStatus = siblingStatus;
	}

	public String getSiblingDeathCause() {
		return siblingDeathCause;
	}

	public void setSiblingDeathCause(String siblingDeathCause) {
		this.siblingDeathCause = siblingDeathCause;
	}

	public String getSiblingDeathAge() {
		return siblingDeathAge;
	}

	public void setSiblingDeathAge(String siblingDeathAge) {
		this.siblingDeathAge = siblingDeathAge;
	}

	public String getFamilyIllnessHistory() {
		return familyIllnessHistory;
	}

	public void setFamilyIllnessHistory(String familyIllnessHistory) {
		this.familyIllnessHistory = familyIllnessHistory;
	}

	private static final long serialVersionUID = 1L;
	private Integer id;
	private TriagePatientQueueLog triageLogId;
	private Integer patientId;
	private String parentStatus;
	private String parentDeathCause;
	private String parentDeathAge;
	private String siblingStatus;
	private String siblingDeathCause;
	private String siblingDeathAge;
	private String familyIllnessHistory;

	
	private Date createdOn;
	
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TriagePatientQueueLog getTriageLogId() {
		return triageLogId;
	}

	public void setTriageLogId(TriagePatientQueueLog triageLogId) {
		this.triageLogId = triageLogId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
