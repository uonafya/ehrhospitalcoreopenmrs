package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.Concept;

public class DepartmentConcept implements Serializable {
	public static final int[] TYPES = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer typeConcept;

	private Department department;

	private Concept concept;

	private Date createdOn;

	private String createdBy;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeConcept() {
		return this.typeConcept;
	}

	public void setTypeConcept(Integer typeConcept) {
		this.typeConcept = typeConcept;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Concept getConcept() {
		return this.concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
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
}
