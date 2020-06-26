package org.openmrs.module.hospitalcore.model;

import java.util.HashSet;
import java.util.Set;
import org.openmrs.Concept;
import org.openmrs.Role;

public class RadiologyDepartment {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String description;

	private Set<Concept> investigations = new HashSet<Concept>();

	private Role role;

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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Concept> getInvestigations() {
		return this.investigations;
	}

	public void setInvestigations(Set<Concept> investigations) {
		this.investigations = investigations;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
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
		RadiologyDepartment other = (RadiologyDepartment)obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "RadiologyDepartment [id=" + this.id + ", name=" + this.name + "]";
	}
}
