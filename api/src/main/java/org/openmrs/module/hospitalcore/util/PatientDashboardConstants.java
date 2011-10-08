/**
 *  Copyright 2011 Health Information Systems Project of India
 *
 *  This file is part of Hospital-core module.
 *
 *  Hospital-core module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Hospital-core module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Hospital-core module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.hospitalcore.util;

public class PatientDashboardConstants {
	public static String MODULE_ID = "patientdashboard";
	public static String CONCEPT_CLASS_NAME_DIAGNOSIS = "Diagnosis";
	public static String CONCEPT_CLASS_NAME_PROCEDURE = "Procedure";
	public static String PROPERTY_OPDWARD = MODULE_ID + ".OPDRootConcept";
	
	public static String PROPERTY_IPDWARD = MODULE_ID + ".IPDRootConcept";
	public static String PROPERTY_HOSPITAL = MODULE_ID + ".externalHospitalConcept";
	public static String PROPERTY_PROVISIONAL_DIAGNOSIS = MODULE_ID + ".provisionalDiagnosisConcept";
	public static String PROPERTY_POST_FOR_PROCEDURE = MODULE_ID + ".postForProcedureConcept";
	public static String PROPERTY_INTERNAL_REFERRAL = MODULE_ID + ".internalReferralConcept";
	public static String PROPERTY_EXTERNAL_REFERRAL = MODULE_ID +  ".externalReferralConcept";
	public static String PROPERTY_VISIT_OUTCOME = MODULE_ID + ".visitOutcomeConcept";
	public static String PROPERTY_OPD_ENCOUTNER_TYPE = MODULE_ID + ".opdEncounterType";
	public static String PROPERTY_LAB_ENCOUTNER_TYPE = MODULE_ID + ".labEncounterType";
	public static String PROPERTY_INIT_CONCEPT = MODULE_ID + ".initNeededConcept";
	
}
