package org.openmrs.module.hospitalcore.web.controller.department;

public class DepartmentConceptCommand {
	private Integer[] selectedDiagnosisList;

	private Integer[] selectedProcedureList;

	private Integer departmentId;

	public Integer[] getSelectedDiagnosisList() {
		return this.selectedDiagnosisList;
	}

	public void setSelectedDiagnosisList(Integer[] selectedDiagnosisList) {
		this.selectedDiagnosisList = selectedDiagnosisList;
	}

	public Integer[] getSelectedProcedureList() {
		return this.selectedProcedureList;
	}

	public void setSelectedProcedureList(Integer[] selectedProcedureList) {
		this.selectedProcedureList = selectedProcedureList;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
}
