package org.openmrs.module.hospitalcore.util;

import java.util.Comparator;

public class TestModel implements Comparator<TestModel>, Comparable<TestModel> {
	private String startDate;

	private String patientIdentifier;

	private String patientName;

	private String gender;

	private String testName;

	private Integer orderId;

	private String status;

	private Integer testId;

	private Integer givenFormId;

	private Integer notGivenFormId;

	private String acceptedDate;

	private String investigation;

	private Integer givenEncounterId;

	private Integer notGivenEncounterId;

	private Boolean xray;

	private String age;

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPatientIdentifier() {
		return this.patientIdentifier;
	}

	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTestName() {
		return this.testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTestId() {
		return this.testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getGivenFormId() {
		return this.givenFormId;
	}

	public void setGivenFormId(Integer formId) {
		this.givenFormId = formId;
	}

	public String getAcceptedDate() {
		return this.acceptedDate;
	}

	public void setAcceptedDate(String acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public String getInvestigation() {
		return this.investigation;
	}

	public void setInvestigation(String investigation) {
		this.investigation = investigation;
	}

	public Integer getGivenEncounterId() {
		return this.givenEncounterId;
	}

	public void setGivenEncounterId(Integer encounterId) {
		this.givenEncounterId = encounterId;
	}

	public Integer getNotGivenFormId() {
		return this.notGivenFormId;
	}

	public void setNotGivenFormId(Integer notGivenFormId) {
		this.notGivenFormId = notGivenFormId;
	}

	public Integer getNotGivenEncounterId() {
		return this.notGivenEncounterId;
	}

	public void setNotGivenEncounterId(Integer notGivenEncounterId) {
		this.notGivenEncounterId = notGivenEncounterId;
	}

	public Boolean getXray() {
		return this.xray;
	}

	public void setXray(Boolean xray) {
		this.xray = xray;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int compareTo(TestModel t) {
		return this.patientName.compareTo(t.patientName);
	}

	public int compare(TestModel t, TestModel t1) {
		return 0;
	}
}
