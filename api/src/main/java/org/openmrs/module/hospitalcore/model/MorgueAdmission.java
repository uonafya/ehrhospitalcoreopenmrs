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

import org.openmrs.Patient;
import org.openmrs.User;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <p> Class: MorguePatientAdmission </p>
 * <p> Package: org.openmrs.module.hospitalcore.model </p>

 *
 */
public class MorgueAdmission implements Serializable{
	private static final long serialVersionUID = 1L;

	public Integer getMorgueAdmissionId() {
		return morgueAdmissionId;
	}

	public void setMorgueAdmissionId(Integer morgueAdmissionId) {
		this.morgueAdmissionId = morgueAdmissionId;
	}

	private Integer morgueAdmissionId;
	private Patient patient;
	private Date dateOfDeath;
	private Date dateOfAdmission;
	private String receivedBy;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public String getPropertyWithBody() {
		return propertyWithBody;
	}

	public void setPropertyWithBody(String propertyWithBody) {
		this.propertyWithBody = propertyWithBody;
	}

	public String getIdentificationTagNo() {
		return identificationTagNo;
	}

	public void setIdentificationTagNo(String identificationTagNo) {
		this.identificationTagNo = identificationTagNo;
	}

	public String getBroughtBy() {
		return broughtBy;
	}

	public void setBroughtBy(String broughtBy) {
		this.broughtBy = broughtBy;
	}

	public String getCompartmentNo() {
		return compartmentNo;
	}

	public void setCompartmentNo(String compartmentNo) {
		this.compartmentNo = compartmentNo;
	}

	public String getConsent() {
		return consent;
	}

	public void setConsent(String consent) {
		this.consent = consent;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	private String propertyWithBody;
	private String identificationTagNo;
	private String broughtBy;
	private String compartmentNo;
	private String consent;
	private String bodyType;

	private Date createdOn;

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	private User createdBy;



}
