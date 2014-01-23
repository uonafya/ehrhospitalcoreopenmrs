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

import org.openmrs.Concept;
import org.openmrs.Patient;

import org.openmrs.module.hospitalcore.util.PatientUtils;


public class TriagePatientData implements  Serializable {


	 private static final long serialVersionUID = 1L;
	 private Integer id;
	 private TriagePatientQueueLog triageId;
	 private BigDecimal weight;
	 private Patient patient;
	 private BigDecimal height;
	 private BigDecimal temperature;
	 private BigDecimal	systolic;
	 private BigDecimal daistolic;
	 private BigDecimal respiratoryRate;
	 private BigDecimal pulsRate;
	 private String bloodGroup;
	 private Date lastMenstrualDate;
	 private String rhesusFactor;
	 private String pitch;
	 private Integer opdConceptId;
	 private Date createdOn;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public TriagePatientQueueLog getTriageId(){
		return triageId;
	}
	
	public void setTriageId(){
		this.triageId = triageId;
	}

	public BigDecimal getHeight(){
		return height;
	}
	
	public void setHeight(){
		this.height = height;
	}
	
	public BigDecimal getTemperature(){
		return temperature;
	}
	
	public void setTemperature(){
		this.temperature = temperature;
	}
	public BigDecimal getSystolic(){
		return systolic;
	}
	
	public void setSystolic(){
		this.systolic = systolic;
	}
	public BigDecimal getDaistolic(){
		return daistolic;
	}
	
	public void setDaistolic(){
		this.daistolic = daistolic;
	}
	public BigDecimal getRespiratoryRate(){
		return respiratoryRate;
	}
	
	public void setRespiratoryRate(){
		this.respiratoryRate = respiratoryRate;
	}
	public BigDecimal getPulsRate(){
		return pulsRate;
	}
	
	public void setPulsRate(){
		this.pulsRate = pulsRate;
	}
	
	public String getBloodGroup(){
		return bloodGroup;
	}
	
	public void setBloodGroup(){
		this.bloodGroup = bloodGroup;
	}
	public Date getLastMenstrualDate(){
		return lastMenstrualDate;
	}
	
	public void setLastMenstrualDate(){
		this.lastMenstrualDate = lastMenstrualDate;
	}

	public String getRhesusFactor(){
		return rhesusFactor;
	}
	
	public void setRhesusFactor(){
		this.rhesusFactor = rhesusFactor;
	}
	public String getPicth(){
		return pitch;
	}
	
	public void setPicth(){
		this.pitch = pitch;
	}

	public Integer getOpdConceptId(){
		return opdConceptId;
	}
	
	public void setOpdConceptId(){
		this.opdConceptId = opdConceptId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	  
}
