package org.openmrs.module.hospitalcore.model;

import java.util.Date;
import org.openmrs.User;

public class CoreForm {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String content;

	private String description;

	private String afterSubmit;

	private Date createdOn;

	private User createdBy;

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

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getAfterSubmit() {
		return this.afterSubmit;
	}

	public void setAfterSubmit(String afterSubmit) {
		this.afterSubmit = afterSubmit;
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
		CoreForm other = (CoreForm)obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "Form [id=" + this.id + ", name=" + this.name + "]";
	}
}
