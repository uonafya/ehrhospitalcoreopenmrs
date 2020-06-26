package org.openmrs.module.hospitalcore.concept;

import java.util.HashSet;
import java.util.Set;

public class ConceptModel implements Comparable<ConceptModel> {
	private static final String CONCEPT_CLASS = "Diagnosis";

	private static final String DATA_TYPE = "N/A";

	private String name;

	private String conceptClass = "Diagnosis";

	private String conceptDatatype = "N/A";

	private String description;

	private String shortname;

	private Set<String> synonyms = new HashSet<String>();

	private Set<Mapping> mappings = new HashSet<Mapping>();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConceptClass() {
		return this.conceptClass;
	}

	public void setConceptClass(String conceptClass) {
		this.conceptClass = conceptClass;
	}

	public String getConceptDatatype() {
		return this.conceptDatatype;
	}

	public void setConceptDatatype(String conceptDatatype) {
		this.conceptDatatype = conceptDatatype;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public Set<String> getSynonyms() {
		return this.synonyms;
	}

	public void setSynonyms(Set<String> synonyms) {
		this.synonyms = synonyms;
	}

	public Set<Mapping> getMappings() {
		return this.mappings;
	}

	public void setMappings(Set<Mapping> mappings) {
		this.mappings = mappings;
	}

	public int compareTo(ConceptModel o) {
		try {
			return getName().compareToIgnoreCase(o.getName());
		} catch (NullPointerException e) {
			System.out.println("NULL CONCEPTMODEL");
			System.out.println(getName());
			System.out.println(o.getName());
			return -1;
		}
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConceptModel other = (ConceptModel)obj;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
