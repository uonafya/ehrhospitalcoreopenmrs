package org.openmrs.module.hospitalcore.concept;

public class Mapping implements Comparable<Mapping> {
	private String name;

	private String source;

	private String sourceCode;

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceCode() {
		return this.sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = 31 * result + ((this.source == null) ? 0 : this.source.hashCode());
		result = 31 * result + ((this.sourceCode == null) ? 0 : this.sourceCode.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mapping other = (Mapping)obj;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.source == null) {
			if (other.source != null)
				return false;
		} else if (!this.source.equals(other.source)) {
			return false;
		}
		if (this.sourceCode == null) {
			if (other.sourceCode != null)
				return false;
		} else if (!this.sourceCode.equals(other.sourceCode)) {
			return false;
		}
		return true;
	}

	public int compareTo(Mapping o) {
		String mt = getName() + getSource() + getSourceCode();
		String ot = o.getName() + o.getSource() + o.getSourceCode();
		return mt.compareTo(ot);
	}
}
