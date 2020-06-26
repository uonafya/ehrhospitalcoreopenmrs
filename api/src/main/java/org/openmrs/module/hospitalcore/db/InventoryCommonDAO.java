package org.openmrs.module.hospitalcore.db;

import java.util.List;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.InventoryDrug;
import org.openmrs.module.hospitalcore.model.InventoryDrugFormulation;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatient;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatientDetail;

public interface InventoryCommonDAO {
	List<InventoryStoreDrugPatient> getAllIssueDateByPatientId(Patient paramPatient) throws DAOException;

	List<InventoryStoreDrugPatient> getDeatilOfInventoryStoreDrugPatient(Patient paramPatient, String paramString) throws DAOException;

	List<InventoryStoreDrugPatientDetail> getDrugDetailOfPatient(InventoryStoreDrugPatient paramInventoryStoreDrugPatient) throws DAOException;

	InventoryDrug getDrugByName(String paramString) throws DAOException;

	List<Concept> getDrugFrequency() throws DAOException;

	InventoryDrugFormulation getDrugFormulationById(Integer paramInteger) throws DAOException;
}
