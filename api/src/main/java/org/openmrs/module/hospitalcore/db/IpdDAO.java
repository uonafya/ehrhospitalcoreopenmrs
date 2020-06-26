package org.openmrs.module.hospitalcore.db;

import java.util.ArrayList;
import java.util.List;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmission;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmittedLog;
import org.openmrs.module.hospitalcore.model.IpdPatientVitalStatistics;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.WardBedStrength;

public interface IpdDAO {
	IpdPatientAdmission saveIpdPatientAdmission(IpdPatientAdmission paramIpdPatientAdmission) throws DAOException;

	IpdPatientAdmissionLog saveIpdPatientAdmissionLog(IpdPatientAdmissionLog paramIpdPatientAdmissionLog) throws DAOException;

	IpdPatientAdmitted saveIpdPatientAdmitted(IpdPatientAdmitted paramIpdPatientAdmitted) throws DAOException;

	IpdPatientAdmittedLog saveIpdPatientAdmittedLog(IpdPatientAdmittedLog paramIpdPatientAdmittedLog) throws DAOException;

	IpdPatientAdmittedLog getIpdPatientAdmittedLog(Integer paramInteger) throws DAOException;

	IpdPatientAdmitted getIpdPatientAdmitted(Integer paramInteger) throws DAOException;

	IpdPatientAdmissionLog getIpdPatientAdmissionLog(Integer paramInteger) throws DAOException;

	IpdPatientAdmissionLog getIpdPatientAdmissionLog(OpdPatientQueueLog paramOpdPatientQueueLog) throws DAOException;

	IpdPatientAdmissionLog getIpdPatientAdmissionLog(Encounter paramEncounter) throws DAOException;

	IpdPatientAdmission getIpdPatientAdmission(Integer paramInteger) throws DAOException;

	List<IpdPatientAdmittedLog> getAllIpdPatientAdmittedLog() throws DAOException;

	List<IpdPatientAdmitted> getAllIpdPatientAdmitted() throws DAOException;

	List<IpdPatientAdmissionLog> listIpdPatientAdmissionLog(Integer paramInteger1, Integer paramInteger2, String paramString, Integer paramInteger3, Integer paramInteger4) throws DAOException;

	List<IpdPatientAdmission> getAllIpdPatientAdmission() throws DAOException;

	List<IpdPatientAdmission> getAllIndoorPatient() throws DAOException;

	List<IpdPatientAdmissionLog> getAllIndoorPatientFromAdmissionLog(String paramString, int paramInt1, int paramInt2) throws DAOException;

	int countGetAllIndoorPatientFromAdmissionLog(String paramString, int paramInt) throws DAOException;

	void removeIpdPatientAdmission(IpdPatientAdmission paramIpdPatientAdmission) throws DAOException;

	void removeIpdPatientAdmitted(IpdPatientAdmitted paramIpdPatientAdmitted) throws DAOException;

	List<IpdPatientAdmittedLog> listAdmittedLogByPatientId(Integer paramInteger) throws DAOException;

	IpdPatientAdmitted getAdmittedByPatientId(Integer paramInteger) throws DAOException;

	IpdPatientAdmitted getAdmittedByAdmissionLogId(IpdPatientAdmissionLog paramIpdPatientAdmissionLog) throws DAOException;

	List<IpdPatientAdmitted> getAllIpdAdmittedPatientByWardId(Integer paramInteger) throws DAOException;

	WardBedStrength getWardBedStrengthByWardId(Integer paramInteger) throws DAOException;

	void saveWardBedStrength(WardBedStrength paramWardBedStrength) throws DAOException;

	IpdPatientVitalStatistics saveIpdPatientVitalStatistics(IpdPatientVitalStatistics paramIpdPatientVitalStatistics) throws DAOException;

	List<IpdPatientVitalStatistics> getIpdPatientVitalStatistics(Integer paramInteger1, Integer paramInteger2) throws DAOException;

	List<Concept> getDiet() throws DAOException;

	IpdPatientAdmission getIpdPatientAdmissionByEncounter(Encounter paramEncounter) throws DAOException;

	List<IpdPatientAdmission> searchIpdPatientAdmission(String paramString1, ArrayList<Integer> paramArrayList, String paramString2, String paramString3, String paramString4, String paramString5) throws DAOException;

	List<IpdPatientAdmitted> searchIpdPatientAdmitted(String paramString1, ArrayList<Integer> paramArrayList, String paramString2, String paramString3, String paramString4, String paramString5) throws DAOException;

	IpdPatientAdmission getIpdPatientAdmissionByPatientId(Patient paramPatient) throws DAOException;
}
