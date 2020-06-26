package org.openmrs.module.hospitalcore.db;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.Patient;
import org.openmrs.Role;
import org.openmrs.module.hospitalcore.form.RadiologyForm;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.hospitalcore.model.RadiologyTest;
import org.openmrs.module.hospitalcore.template.RadiologyTemplate;

public interface RadiologyDAO {
	RadiologyForm saveRadiologyForm(RadiologyForm paramRadiologyForm);

	RadiologyForm getRadiologyFormById(Integer paramInteger);

	List<RadiologyForm> getRadiologyForms(String paramString);

	List<RadiologyForm> getAllRadiologyForms();

	void deleteRadiologyForm(RadiologyForm paramRadiologyForm);

	RadiologyDepartment saveRadiologyDepartment(RadiologyDepartment paramRadiologyDepartment);

	RadiologyDepartment getRadiologyDepartmentById(Integer paramInteger);

	void deleteRadiologyDepartment(RadiologyDepartment paramRadiologyDepartment);

	RadiologyDepartment getRadiologyDepartmentByRole(Role paramRole);

	List<Order> getOrders(Date paramDate, OrderType paramOrderType, Set<Concept> paramSet, List<Patient> paramList, int paramInt1, int paramInt2) throws ParseException;

	Integer countOrders(Date paramDate, OrderType paramOrderType, Set<Concept> paramSet, List<Patient> paramList) throws ParseException;

	RadiologyTest saveRadiologyTest(RadiologyTest paramRadiologyTest);

	RadiologyTest getRadiologyTestById(Integer paramInteger);

	void deleteRadiologyTest(RadiologyTest paramRadiologyTest);

	List<RadiologyTest> getAllRadiologyTests();

	RadiologyTest getRadiologyTestByOrder(Order paramOrder);

	List<RadiologyTest> getRadiologyTestsByDateAndStatus(Date paramDate, String paramString) throws ParseException;

	List<RadiologyTest> getRadiologyTests(Date paramDate, String paramString, Set<Concept> paramSet, List<Patient> paramList, int paramInt1, int paramInt2) throws ParseException;

	List<RadiologyTest> getRadiologyTestsByDate(Date paramDate) throws ParseException;

	List<RadiologyTest> getRadiologyTestsByDiscontinuedDate(Date paramDate, Set<Concept> paramSet, List<Patient> paramList, int paramInt1, int paramInt2) throws ParseException;

	Integer countRadiologyTestsByDiscontinuedDate(Date paramDate, Set<Concept> paramSet, List<Patient> paramList) throws ParseException;

	List<RadiologyTest> getRadiologyTestsByDateAndPatient(Date paramDate, Patient paramPatient) throws ParseException;

	RadiologyTest getRadiologyTest(Encounter paramEncounter);

	void createConceptsForXrayDefaultForm();

	Integer countRadiologyTests(Date paramDate, String paramString, Set<Concept> paramSet, List<Patient> paramList) throws ParseException;

	RadiologyTemplate getRadiologyTemplate(Integer paramInteger);

	RadiologyTemplate saveRadiologyTemplate(RadiologyTemplate paramRadiologyTemplate);

	List<RadiologyTemplate> getAllRadiologyTemplates();

	void deleteRadiologyTemplate(RadiologyTemplate paramRadiologyTemplate);

	List<RadiologyTemplate> getRadiologyTemplates(Concept paramConcept);

	List<RadiologyTest> getCompletedRadiologyTestsByPatient(Patient paramPatient);
}
