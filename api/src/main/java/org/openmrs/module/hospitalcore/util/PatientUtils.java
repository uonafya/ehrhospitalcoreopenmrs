package org.openmrs.module.hospitalcore.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;

public class PatientUtils {
	public static final String MODULE_ID = "hospitalcore.";

	public static final String PATIENT_ATTRIBUTE_CATEGORY = "Payment Category";

	public static final String PATIENT_ATTRIBUTE_EXEMPTION_CATEGORY = "Exemption Number";

	public static final String PATIENT_ATTRIBUTE_WAIVER_CATEGORY = "Waiver Number";

	public static final String PATIENT_AGE_CATEGORY = "hospitalcore.ageCategory";

	public static String getPatientCategory(Patient patient) {
		String patientCategory = getPatientAttribute(patient, "Payment Category");
		return patientCategory;
	}

	public static String getFullName(Patient patient) {
		String fullName = "";
		if (!StringUtils.isBlank(patient.getGivenName()))
			fullName = fullName + patient.getGivenName() + " ";
		if (!StringUtils.isBlank(patient.getFamilyName()))
			fullName = fullName + patient.getFamilyName() + " ";
		if (!StringUtils.isBlank(patient.getMiddleName()))
			fullName = fullName + patient.getMiddleName();
		return fullName;
	}

	public static String getAgeCategory(Patient patient) {
		String ageCategories = GlobalPropertyUtil.getString("hospitalcore.ageCategory", "null");
		try {
			String[] categories = ageCategories.split(";");
			for (String category : categories) {
				String[] parts = category.split(":");
				String categoryName = parts[1];
				String categoryCondition = parts[0];
				String[] conditions = categoryCondition.split("-");
				Integer lower = Integer.valueOf(Integer.parseInt(conditions[0]));
				Integer upper = Integer.valueOf(Integer.parseInt(conditions[1]));
				if (lower.intValue() <= patient.getAge().intValue() && patient.getAge().intValue() <= upper.intValue())
					return categoryName;
			}
		} catch (Exception e) {
			System.out.println("Error while generating age category!");
			e.printStackTrace();
		}
		return null;
	}

	public static String getPatientAttribute(Patient patient, String attributeNameType) {
		String value = null;
		PersonAttributeType pat = Context.getPersonService().getPersonAttributeTypeByName(attributeNameType);
		PersonAttribute pa = patient.getAttribute(pat);
		if (pa != null)
			value = pa.getValue();
		return value;
	}

	public static void removePatientAttribute(Patient patient, String attributeNameType) {
		PersonAttributeType pat = Context.getPersonService().getPersonAttributeTypeByName(attributeNameType);
		PersonAttribute pa = patient.getAttribute(pat);
		patient.removeAttribute(pa);
		Context.getPatientService().savePatient(patient);
	}

	public static Map<String, String> getAttributes(Patient patient) {
		Map<String, String> attributes = new HashMap<String, String>();
		for (String key : patient.getAttributeMap().keySet())
			attributes.put(((PersonAttribute)patient.getAttributeMap().get(key)).getAttributeType().getName(), ((PersonAttribute)patient.getAttributeMap().get(key)).getValue());
		List<EncounterType> types = new ArrayList<EncounterType>();
		EncounterType reginit = Context.getEncounterService().getEncounterType(Integer.valueOf(1));
		types.add(reginit);
		EncounterType revisit = Context.getEncounterService().getEncounterType(Integer.valueOf(2));
		types.add(revisit);
		Encounter lastVisit = ((HospitalCoreService)Context.getService(HospitalCoreService.class)).getLastVisitEncounter(patient, types);
		if (lastVisit != null)
			for (Obs obs : lastVisit.getAllObs()) {
				if (!obs.isVoided().booleanValue()) {
					if (obs.getConcept().getDatatype().getName().equalsIgnoreCase("Coded")) {
						String str = " ";
						if (obs.getValueCoded() != null)
							str = obs.getValueCoded().getName().getName();
						attributes.put(obs.getConcept().getName().getName(), str);
						continue;
					}
					if (obs.getConcept().getDatatype().getName().equalsIgnoreCase("Text")) {
						String str = " ";
						if (obs.getValueText() != null)
							str = obs.getValueText();
						attributes.put(obs.getConcept().getName().getName(), str);
					}
				}
			}
		return attributes;
	}

	public static String estimateAge(Patient patient) {
		return estimateAge(patient.getBirthdate());
	}

	public static String estimateAge(Date date) {
		String age = "~";
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date);
		Date date2 = cal.getTime();
		int yearNew = cal.get(1);
		int yearOld = cal2.get(1);
		int monthNew = cal.get(2);
		int monthOld = cal2.get(2);
		int dayNew = cal.get(5);
		int dayOld = cal2.get(5);
		int maxDayInOldMonth = cal2.getActualMaximum(5);
		int yearDiff = yearNew - yearOld;
		int monthDiff = monthNew - monthOld;
		int dayDiff = dayNew - dayOld;
		int ageYear = yearDiff, ageMonth = monthDiff, ageDay = dayDiff;
		if (monthDiff < 0) {
			ageYear--;
			ageMonth = 12 - Math.abs(monthDiff);
		}
		if (dayDiff < 0) {
			ageMonth--;
			if (ageMonth < 0) {
				ageYear--;
				ageMonth = 12 - Math.abs(ageMonth);
			}
			ageDay = maxDayInOldMonth - dayOld + dayNew;
		}
		if (ageYear >= 1) {
			age = age + ageYear;
			if (ageMonth >= 6)
				age = age + ".5";
			if (ageYear == 1) {
				age = age + " year";
			} else {
				age = age + " years";
			}
		} else if (ageYear <= 0) {
			if (ageMonth >= 1)
				if (ageMonth == 1) {
					age = age + ageMonth + " month ";
				} else {
					age = age + ageMonth + " months ";
				}
			if (ageMonth <= 0)
				if (ageDay == 1 || ageDay == 0) {
					age = age + ageDay + " day ";
				} else {
					age = age + ageDay + " days ";
				}
		}
		return age;
	}

	public static String estimateAgeInYear(Date date) {
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date);
		int yearNew = cal.get(1);
		int yearOld = cal2.get(1);
		int yearDiff = yearNew - yearOld;
		String ageYear = String.valueOf(yearDiff);
		return ageYear;
	}
}
