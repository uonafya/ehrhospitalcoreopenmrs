package org.openmrs.module.hospitalcore.concept;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptSet;
import org.openmrs.api.context.Context;

public class TestTree {
	private ConceptNode root;

	private boolean buildTreeSuccessful = false;

	private Set<Integer> conceptIDSet = new TreeSet<Integer>();

	private List<ConceptNode> flatList = new ArrayList<ConceptNode>();

	public TestTree(Concept labConcept) {
		this.root = new ConceptNode(labConcept);
		buildTestTree(this.root);
		this.buildTreeSuccessful = true;
		this.flatList = asList();
	}

	public TestTree(String labName) {
		Concept labConcept = Context.getConceptService().getConcept(labName);
		if (labConcept != null &&
				labConcept.getConceptClass().getName().equals("Question") && labConcept.getDatatype().getName().equals("Coded")) {
			this.root = new ConceptNode(labConcept);
			buildTestTree(this.root);
			this.buildTreeSuccessful = true;
			this.flatList = asList();
		}
	}

	public boolean buildTreeSuccessful() {
		return this.buildTreeSuccessful;
	}

	public boolean contains(Concept concept) {
		return this.conceptIDSet.contains(concept.getId());
	}

	public ConceptNode getRootLab() {
		return this.root;
	}

	public ConceptNode getRootNode() {
		return this.root;
	}

	public void printTestTree() {
		if (this.buildTreeSuccessful)
			printNode(this.root, 1);
	}

	private void printNode(ConceptNode node, int level) {
		for (int i = 0; i < level; i++)
			System.out.print("   ");
		System.out.println(node.getConcept().getName().getName());
		for (ConceptNode child : node.getChildNodes())
			printNode(child, level + 1);
	}

	private void buildTestTree(ConceptNode node) {
		if (!node.getConcept().getConceptClass().getName().equals("Test") || node.getConcept().getConceptClass().getName().equals("LabSet")) {
			for (ConceptAnswer ca : node.getConcept().getAnswers()) {
				Concept c = ca.getAnswerConcept();
				ConceptNode child = new ConceptNode(c, node);
				node.getChildNodes().add(child);
				this.conceptIDSet.add(c.getConceptId());
				buildTestTree(child);
			}
			for (ConceptSet cs : node.getConcept().getConceptSets()) {
				Concept c = cs.getConcept();
				ConceptNode child = new ConceptNode(c, node);
				node.getChildNodes().add(child);
				this.conceptIDSet.add(c.getConceptId());
				buildTestTree(child);
			}
		}
	}

	public Set<Integer> getConceptIDSet() {
		return this.conceptIDSet;
	}

	public List<ConceptNode> getTreeAsList() {
		return this.flatList;
	}

	private List<ConceptNode> asList() {
		List<ConceptNode> list = new ArrayList<ConceptNode>();
		Set<ConceptNode> set = new HashSet<ConceptNode>();
		convertToList(set, this.root);
		list.addAll(set);
		Collections.sort(list, new Comparator<ConceptNode>() {
			public int compare(ConceptNode o1, ConceptNode o2) {
				return o1.getConcept().getConceptId().intValue() - o2.getConcept().getConceptId().intValue();
			}
		});
		return list;
	}

	private void convertToList(Set<ConceptNode> set, ConceptNode node) {
		set.add(node);
		for (ConceptNode child : node.getChildNodes())
			convertToList(set, child);
	}

	public ConceptNode findNode(Concept concept) {
		for (int i = 0; i < this.flatList.size(); i++) {
			ConceptNode node = this.flatList.get(i);
			if (node.getConcept().getConceptId().equals(concept.getConceptId()))
				return node;
		}
		return null;
	}

	public Set<Concept> getConceptSet() {
		Set<Concept> set = new HashSet<Concept>();
		for (ConceptNode node : this.flatList)
			set.add(node.getConcept());
		return set;
	}
}
