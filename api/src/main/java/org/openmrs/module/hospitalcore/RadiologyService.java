package org.openmrs.module.hospitalcore;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.form.RadiologyForm;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.hospitalcore.model.RadiologyTest;
import org.openmrs.module.hospitalcore.template.RadiologyTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RadiologyService extends OpenmrsService {
	RadiologyForm saveRadiologyForm(RadiologyForm paramRadiologyForm);

	RadiologyForm getRadiologyFormById(Integer paramInteger);

	List<RadiologyForm> getAllRadiologyForms();

	void deleteRadiologyForm(RadiologyForm paramRadiologyForm);

	List<RadiologyForm> getRadiologyForms(String paramString);

	RadiologyDepartment saveRadiologyDepartment(RadiologyDepartment paramRadiologyDepartment);

	RadiologyDepartment getRadiologyDepartmentById(Integer paramInteger);

	void deleteRadiologyDepartment(RadiologyDepartment paramRadiologyDepartment);

	RadiologyDepartment getCurrentRadiologyDepartment();

	List<Order> getOrders(Date paramDate, String paramString, Set<Concept> paramSet, int paramInt) throws ParseException;

	Integer countOrders(Date paramDate, String paramString, Set<Concept> paramSet) throws ParseException;

	Integer acceptTest(Order paramOrder);

	RadiologyTest saveRadiologyTest(RadiologyTest paramRadiologyTest);

	RadiologyTest getRadiologyTestById(Integer paramInteger);

	List<RadiologyTest> getAllRadiologyTests();

	void deleteRadiologyTest(RadiologyTest paramRadiologyTest);

	String unacceptTest(RadiologyTest paramRadiologyTest);

	RadiologyTest getRadiologyTestByOrder(Order paramOrder);

	String rescheduleTest(Order paramOrder, Date paramDate);

	List<RadiologyTest> getAcceptedRadiologyTests(Date paramDate, String paramString, Set<Concept> paramSet, int paramInt) throws ParseException;

	Integer countAcceptedRadiologyTests(Date paramDate, String paramString, Set<Concept> paramSet) throws ParseException;

	String completeTest(RadiologyTest paramRadiologyTest);

	List<RadiologyTest> getAllRadiologyTestsByDate(Date paramDate, String paramString, Concept paramConcept) throws ParseException;

	List<RadiologyTest> getCompletedRadiologyTests(Date paramDate, String paramString, Set<Concept> paramSet, int paramInt) throws ParseException;

	Integer countCompletedRadiologyTests(Date paramDate, String paramString, Set<Concept> paramSet) throws ParseException;

	List<RadiologyTest> getRadiologyTestsByDateAndPatient(Date paramDate, Patient paramPatient) throws ParseException;

	RadiologyTest getRadiologyTest(Encounter paramEncounter);

	void createConceptsForXrayDefaultForm();

	RadiologyForm getDefaultForm();

	RadiologyTemplate getRadiologyTemplate(Integer paramInteger);

	RadiologyTemplate saveRadiologyTemplate(RadiologyTemplate paramRadiologyTemplate);

	List<RadiologyTemplate> getAllRadiologyTemplates();

	void deleteRadiologyTemplate(RadiologyTemplate paramRadiologyTemplate);

	List<RadiologyTemplate> getRadiologyTemplates(Concept paramConcept);

	List<RadiologyTest> getCompletedRadiologyTestsByPatient(Patient paramPatient);
}
