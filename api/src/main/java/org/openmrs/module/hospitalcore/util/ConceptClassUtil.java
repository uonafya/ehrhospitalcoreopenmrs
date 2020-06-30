package org.openmrs.module.hospitalcore.util;

import org.openmrs.ConceptClass;
import org.openmrs.api.context.Context;

public class ConceptClassUtil {
	private static ConceptClass medicalExaminationClass = null;

	public static ConceptClass getMedicalExamminationClass() {
		return getConceptClass(medicalExaminationClass, "hospitalcore.medicalExamination");
	}

	private static ConceptClass getConceptClass(ConceptClass conceptClass, String keyname) {
		if (conceptClass == null) {
			String idStr = Context.getAdministrationService().getGlobalProperty(keyname);
			try {
				Integer id = Integer.valueOf(Integer.parseInt(idStr));
				conceptClass = Context.getConceptService().getConceptClass(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conceptClass;
	}
}
