/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.hospitalcore.impl;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.InventoryCommonService;
import org.openmrs.module.hospitalcore.db.InventoryCommonDAO;
import org.openmrs.module.hospitalcore.model.InventoryDrug;
import org.openmrs.module.hospitalcore.model.InventoryDrugFormulation;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatient;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatientDetail;

/**
*
*/
public class InventoryCommonServiceImpl extends BaseOpenmrsService implements InventoryCommonService {

	public InventoryCommonServiceImpl() {
	}
	
	protected InventoryCommonDAO dao;
	
	public void setDao(InventoryCommonDAO dao) {
		this.dao = dao;
	}
	
	public List<InventoryStoreDrugPatient> getAllIssueDateByPatientId(Patient patient) throws APIException {
		return dao.getAllIssueDateByPatientId(patient);
	}
	
	public List<InventoryStoreDrugPatient> getDeatilOfInventoryStoreDrugPatient(Patient patient,String date) throws APIException {
		return dao.getDeatilOfInventoryStoreDrugPatient(patient,date);
	}
	
	public List<InventoryStoreDrugPatientDetail> getDrugDetailOfPatient(InventoryStoreDrugPatient isdpd) throws APIException {
		return dao.getDrugDetailOfPatient(isdpd);
	}
	
	//ghanshyam 12-june-2013 New Requirement #1635 User should be able to send pharmacy orders to issue drugs to a patient from dashboard
	public InventoryDrug getDrugByName(String name) throws APIException {
		return dao.getDrugByName(name);
	}
	
	public List<Concept> getDrugFrequency() throws APIException {
		return dao.getDrugFrequency();
	}
	
	public InventoryDrugFormulation getDrugFormulationById(Integer id) throws APIException {
		return dao.getDrugFormulationById(id);
	}

	@Override
	public List<InventoryStoreDrugPatient> getAllIssueByDateRange(String startDate, String endDate) throws APIException {
		return dao.getAllIssueByDateRange(startDate,endDate);
	}
}
