package org.openmrs.module.hospitalcore.concept;

public class Synonym implements Comparable<Synonym> {
	private String name;

	private String synonym;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSynonym() {
		return this.synonym;
	}

	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = 31 * result + ((this.synonym == null) ? 0 : this.synonym.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Synonym other = (Synonym)obj;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.synonym == null) {
			if (other.synonym != null)
				return false;
		} else if (!this.synonym.equals(other.synonym)) {
			return false;
		}
		return true;
	}

	public int compareTo(Synonym o) {
		String mt = getName() + getSynonym();
		String ot = getName() + getSynonym();
		return mt.compareTo(ot);
	}
}
