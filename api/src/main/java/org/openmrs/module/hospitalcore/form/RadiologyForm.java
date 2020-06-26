package org.openmrs.module.hospitalcore.form;

import org.openmrs.Concept;

public class RadiologyForm {
	private static final long serialVersionUID = 1L;

	public static final String GIVEN = "Given";

	public static final String NOT_GIVEN = "Not given";

	private Integer id;

	private String name;

	private String content;

	private String description;

	private Concept concept;

	private String conceptName;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Concept getConcept() {
		return this.concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public String getConceptName() {
		return this.conceptName;
	}

	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RadiologyForm other = (RadiologyForm)obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "RadiologyForm [id=" + this.id + ", name=" + this.name + "]";
	}
}
