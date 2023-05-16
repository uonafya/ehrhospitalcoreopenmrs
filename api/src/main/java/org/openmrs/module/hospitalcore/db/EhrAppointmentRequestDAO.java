package org.openmrs.module.hospitalcore.db;

import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.api.APIException;
import org.openmrs.module.hospitalcore.model.EhrAppointmentRequest;
import org.openmrs.module.hospitalcore.model.EhrAppointmentType;

import java.util.List;

public interface EhrAppointmentRequestDAO extends EhrSingleClassDAO<EhrAppointmentRequest> {


    /**
     * Retrieves Appointments Requests that satisfy the given constraints
     *
     * @param patient  - The patient
     * @param type     - The appointment type
     * @param provider - The requested provider
     * @param status   - The appointment request status
     * @return a list of appointment requests that satisfy the given constraints
     */
    List<EhrAppointmentRequest> getEhrAppointmentRequestsByConstraints(Patient patient, EhrAppointmentType type, Provider provider,
                                                                    EhrAppointmentRequest.EhrAppointmentRequestStatus status) throws APIException;

}
