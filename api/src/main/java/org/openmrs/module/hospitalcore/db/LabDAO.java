package org.openmrs.module.hospitalcore.db;

import java.util.Date;
import java.util.List;
import org.openmrs.Order;
import org.openmrs.Role;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.Lab;
import org.openmrs.module.hospitalcore.model.LabTest;

public interface LabDAO {
	Lab saveLab(Lab paramLab) throws DAOException;

	Lab getLabByName(String paramString) throws DAOException;

	List<Lab> getAllLab() throws DAOException;

	List<Lab> getAllActivelab() throws DAOException;

	Lab getLabByRole(Role paramRole) throws DAOException;

	List<Lab> getLabByRoles(List<Role> paramList) throws DAOException;

	Lab getLabById(Integer paramInteger) throws DAOException;

	void deleteLab(Lab paramLab) throws DAOException;

	LabTest saveLabTest(LabTest paramLabTest) throws DAOException;

	LabTest getLabTestById(Integer paramInteger) throws DAOException;

	LabTest getLabTestByOrder(Order paramOrder) throws DAOException;

	LabTest getLabTestBySampleNumber(String paramString) throws DAOException;

	List<LabTest> getLatestLabTestByDate(Date paramDate1, Date paramDate2, Lab paramLab) throws DAOException;

	void deleteLabTest(LabTest paramLabTest) throws DAOException;
}
