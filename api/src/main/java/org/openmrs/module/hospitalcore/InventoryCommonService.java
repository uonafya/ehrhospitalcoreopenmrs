package org.openmrs.module.hospitalcore;

import java.util.List;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.InventoryDrug;
import org.openmrs.module.hospitalcore.model.InventoryDrugFormulation;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatient;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatientDetail;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface InventoryCommonService extends OpenmrsService {
	List<InventoryStoreDrugPatient> getAllIssueDateByPatientId(Patient paramPatient) throws APIException;

	List<InventoryStoreDrugPatient> getDeatilOfInventoryStoreDrugPatient(Patient paramPatient, String paramString) throws APIException;

	List<InventoryStoreDrugPatientDetail> getDrugDetailOfPatient(InventoryStoreDrugPatient paramInventoryStoreDrugPatient) throws APIException;

	InventoryDrug getDrugByName(String paramString) throws APIException;

	List<Concept> getDrugFrequency() throws APIException;

	InventoryDrugFormulation getDrugFormulationById(Integer paramInteger) throws APIException;
}
