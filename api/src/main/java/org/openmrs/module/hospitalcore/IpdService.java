package org.openmrs.module.hospitalcore;

import java.util.ArrayList;
import java.util.List;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmission;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmittedLog;
import org.openmrs.module.hospitalcore.model.IpdPatientVitalStatistics;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.WardBedStrength;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public interface IpdService extends OpenmrsService {
	IpdPatientAdmission saveIpdPatientAdmission(IpdPatientAdmission paramIpdPatientAdmission) throws APIException;

	void removeIpdPatientAdmission(IpdPatientAdmission paramIpdPatientAdmission) throws APIException;

	IpdPatientAdmissionLog saveIpdPatientAdmissionLog(IpdPatientAdmissionLog paramIpdPatientAdmissionLog) throws APIException;

	IpdPatientAdmitted saveIpdPatientAdmitted(IpdPatientAdmitted paramIpdPatientAdmitted) throws APIException;

	void removeIpdPatientAdmitted(IpdPatientAdmitted paramIpdPatientAdmitted) throws APIException;

	IpdPatientAdmittedLog saveIpdPatientAdmittedLog(IpdPatientAdmittedLog paramIpdPatientAdmittedLog) throws APIException;

	@Transactional(readOnly = true)
	IpdPatientAdmittedLog getIpdPatientAdmittedLog(Integer paramInteger) throws APIException;

	@Transactional(readOnly = true)
	IpdPatientAdmitted getIpdPatientAdmitted(Integer paramInteger) throws APIException;

	@Transactional(readOnly = true)
	IpdPatientAdmissionLog getIpdPatientAdmissionLog(Integer paramInteger) throws APIException;

	@Transactional(readOnly = true)
	IpdPatientAdmissionLog getIpdPatientAdmissionLog(OpdPatientQueueLog paramOpdPatientQueueLog) throws APIException;

	@Transactional(readOnly = true)
	IpdPatientAdmissionLog getIpdPatientAdmissionLog(Encounter paramEncounter) throws APIException;

	@Transactional(readOnly = true)
	IpdPatientAdmission getIpdPatientAdmission(Integer paramInteger) throws APIException;

	@Transactional(readOnly = true)
	List<IpdPatientAdmittedLog> getAllIpdPatientAdmittedLog() throws APIException;

	@Transactional(readOnly = true)
	List<IpdPatientAdmitted> getAllIpdPatientAdmitted() throws APIException;

	@Transactional(readOnly = true)
	List<IpdPatientAdmissionLog> listIpdPatientAdmissionLog(Integer paramInteger1, Integer paramInteger2, String paramString, Integer paramInteger3, Integer paramInteger4) throws APIException;

	@Transactional(readOnly = true)
	List<IpdPatientAdmission> getAllIpdPatientAdmission() throws APIException;

	@Transactional(readOnly = true)
	List<IpdPatientAdmission> getAllIndoorPatient() throws APIException;

	@Transactional(readOnly = true)
	List<IpdPatientAdmissionLog> getAllIndoorPatientFromAdmissionLog(String paramString, int paramInt1, int paramInt2) throws APIException;

	@Transactional(readOnly = true)
	int countGetAllIndoorPatientFromAdmissionLog(String paramString, int paramInt) throws APIException;

	IpdPatientAdmitted transfer(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString) throws APIException;

	IpdPatientAdmitted transfer(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString1, String paramString2) throws APIException;

	IpdPatientAdmittedLog discharge(Integer paramInteger1, Integer paramInteger2) throws APIException;

	IpdPatientAdmittedLog discharge(Integer paramInteger1, Integer paramInteger2, String paramString) throws APIException;

	@Transactional(readOnly = true)
	List<IpdPatientAdmittedLog> listAdmittedLogByPatientId(Integer paramInteger) throws APIException;

	@Transactional(readOnly = true)
	IpdPatientAdmitted getAdmittedByPatientId(Integer paramInteger) throws APIException;

	@Transactional(readOnly = true)
	IpdPatientAdmitted getAdmittedByAdmissionLogId(IpdPatientAdmissionLog paramIpdPatientAdmissionLog) throws APIException;

	void saveWardBedStrength(WardBedStrength paramWardBedStrength) throws APIException;

	WardBedStrength getWardBedStrengthByWardId(Integer paramInteger) throws APIException;

	IpdPatientVitalStatistics saveIpdPatientVitalStatistics(IpdPatientVitalStatistics paramIpdPatientVitalStatistics) throws APIException;

	List<Concept> getDiet() throws APIException;

	List<IpdPatientVitalStatistics> getIpdPatientVitalStatistics(Integer paramInteger1, Integer paramInteger2) throws APIException;

	IpdPatientAdmission getIpdPatientAdmissionByEncounter(Encounter paramEncounter) throws APIException;

	@Transactional(readOnly = true)
	List<IpdPatientAdmission> searchIpdPatientAdmission(String paramString1, ArrayList<Integer> paramArrayList, String paramString2, String paramString3, String paramString4, String paramString5) throws APIException;

	@Transactional(readOnly = true)
	List<IpdPatientAdmitted> searchIpdPatientAdmitted(String paramString1, ArrayList<Integer> paramArrayList, String paramString2, String paramString3, String paramString4, String paramString5) throws APIException;

	@Transactional(readOnly = true)
	IpdPatientAdmission getIpdPatientAdmissionByPatientId(Patient paramPatient) throws APIException;
}
