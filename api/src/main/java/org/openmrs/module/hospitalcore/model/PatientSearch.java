package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

public class PatientSearch implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer patientId;

	private String identifier;

	private String fullname;

	private String givenName;

	private String middleName;

	private String familyName;

	private String gender;

	private Date birthdate;

	private Integer age;

	private Integer personNameId;

	private Boolean dead = Boolean.valueOf(false);

	private Boolean admitted = Boolean.valueOf(false);

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGivenName() {
		return this.givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getPersonNameId() {
		return this.personNameId;
	}

	public void setPersonNameId(Integer personNameId) {
		this.personNameId = personNameId;
	}

	public Boolean getDead() {
		return this.dead;
	}

	public void setDead(Boolean dead) {
		this.dead = dead;
	}

	public Boolean getAdmitted() {
		return this.admitted;
	}

	public void setAdmitted(Boolean admitted) {
		this.admitted = admitted;
	}
}
