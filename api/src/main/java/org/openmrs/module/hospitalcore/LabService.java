package org.openmrs.module.hospitalcore;

import java.util.Date;
import java.util.List;
import org.openmrs.Concept;
import org.openmrs.Order;
import org.openmrs.Role;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.Lab;
import org.openmrs.module.hospitalcore.model.LabTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LabService extends OpenmrsService {
	Lab saveLab(Lab paramLab) throws APIException;

	Lab getLabByName(String paramString) throws APIException;

	List<Lab> getAllLab() throws APIException;

	List<Lab> getAllActivelab() throws APIException;

	Lab getLabByRole(Role paramRole) throws APIException;

	List<Lab> getLabByRoles(List<Role> paramList) throws APIException;

	Lab getLabById(Integer paramInteger) throws APIException;

	void deleteLab(Lab paramLab) throws APIException;

	Lab getLabByConcept(Concept paramConcept, List<Lab> paramList) throws APIException;

	LabTest saveLabTest(LabTest paramLabTest) throws APIException;

	LabTest getLabTestById(Integer paramInteger) throws APIException;

	LabTest getLabTestByOrder(Order paramOrder) throws APIException;

	LabTest getLabTestBySampleNumber(String paramString) throws APIException;

	List<LabTest> getLatestLabTestByDate(Lab paramLab, Date paramDate) throws APIException;

	String getNextSampleNumber(Lab paramLab, Date paramDate) throws APIException;

	void deleteLabTest(LabTest paramLabTest) throws APIException;

	void deleteLabTestByOrder(Order paramOrder) throws APIException;
}
