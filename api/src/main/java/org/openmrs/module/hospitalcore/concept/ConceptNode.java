package org.openmrs.module.hospitalcore.concept;

import java.util.Set;
import java.util.TreeSet;
import org.openmrs.Concept;

public class ConceptNode implements Comparable<ConceptNode> {
	private Concept concept;

	private Set<ConceptNode> childNodes = new TreeSet<ConceptNode>();

	private ConceptNode parent;

	public ConceptNode(Concept concept) {
		this.concept = concept;
	}

	public ConceptNode(Concept concept, ConceptNode parent) {
		this.concept = concept;
		this.parent = parent;
	}

	public int compareTo(ConceptNode o) {
		String mName = this.concept.getName().getName();
		String oName = o.getConcept().getName().getName();
		return mName.compareToIgnoreCase(oName);
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + ((this.concept == null) ? 0 : this.concept.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConceptNode other = (ConceptNode)obj;
		if (this.concept == null) {
			if (other.concept != null)
				return false;
		} else if (!this.concept.equals(other.concept)) {
			return false;
		}
		return true;
	}

	public Concept getConcept() {
		return this.concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public Set<ConceptNode> getChildNodes() {
		return this.childNodes;
	}

	public void setChildNodes(Set<ConceptNode> childNodes) {
		this.childNodes = childNodes;
	}

	public ConceptNode getParent() {
		return this.parent;
	}

	public void setParent(ConceptNode parent) {
		this.parent = parent;
	}

	public String toString() {
		return "ConceptNode [conceptId=" + this.concept.getConceptId() + "]";
	}

	public ConceptNode() {}
}
