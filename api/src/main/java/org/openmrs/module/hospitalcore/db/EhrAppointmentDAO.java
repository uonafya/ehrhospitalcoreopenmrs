/**
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
package org.openmrs.module.hospitalcore.db;

import org.openmrs.*;
import org.openmrs.api.APIException;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.EhrAppointmentService;
import org.openmrs.module.hospitalcore.model.*;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

/**
 * Database methods for {@link EhrAppointmentService}.
 */
public interface EhrAppointmentDAO extends EhrSingleClassDAO {

    List<EhrAppointment> getEhrAppointmentsByPatient(Patient patient);

    EhrAppointment getEhrAppointmentByVisit(Visit visit);

    EhrAppointment getLastEhrAppointment(Patient patient);

    @Transactional(readOnly = true)
    List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate, Date toDate, Provider provider,
                                                      EhrAppointmentType appointmentType, List<EhrAppointment.EhrAppointmentStatus> statuses, Patient patient,
                                                      VisitType visitType, Visit visit) throws APIException;

    List<EhrAppointment> getEhrAppointmentsByConstraints(Date fromDate, Date toDate, Provider provider, EhrAppointmentType type,
                                                      EhrAppointment.EhrAppointmentStatus status, Patient patient) throws APIException;

    List<EhrAppointment> getEhrAppointmentsByStates(List<EhrAppointment.EhrAppointmentStatus> states);

    List<EhrAppointment> getPastEhrAppointmentsByStates(List<EhrAppointment.EhrAppointmentStatus> states);

    List<EhrAppointment> getScheduledEhrAppointmentsForPatient(Patient patient);

    List<EhrAppointment> getEhrAppointmentsByAppointmentBlockAndAppointmentTypes(EhrAppointmentBlock appointmentBlock,
                                                                              List<EhrAppointmentType> appointmentTypes);

    /**
     * Retrieve all appointments in a given time slot.
     *
     * @param timeSlot - The time slot to look into.
     * @return a list of the appointments in the given time slot.
     * <strong>Should</strong> not return voided time slots
     */
    List<EhrAppointment> getEhrAppointmentsInTimeSlot(EhrTimeSlot timeSlot);

    /**
     * Retrieve a count of appointments in a given time slot.
     *
     * @param timeSlot - The time slot to look into.
     * @return a count of the appointments in the given time slot.
     * <strong>Should</strong> not return voided time slots
     */
    Integer getCountOfEhrAppointmentsInTimeSlot(EhrTimeSlot timeSlot);

    /**
     * Retrieve all appointments in a given time slot, filtered by status
     *
     * @param timeSlot - The time slot to look into.
     * @param statuses - statues to match against
     * @return a list of the appointments in the given time slot.
     * <strong>Should</strong> not return voided time slots
     */
    List<EhrAppointment> getEhrAppointmentsInTimeSlotByStatus(EhrTimeSlot timeSlot, List<EhrAppointment.EhrAppointmentStatus> statuses);

    /**
     * Retrieve a count of all appointments in a given time slot, filtered by status
     *
     * @param timeSlot - The time slot to look into.
     * @param statuses - statues to match against
     * @return a cont of the appointments in the given time slot.
     * <strong>Should</strong> not return voided time slots
     */
    Integer getCountOfEhrAppointmentsInTimeSlotByStatus(EhrTimeSlot timeSlot, List<EhrAppointment.EhrAppointmentStatus> statuses);

    /** returns list of appointments aggregated by date
     * @param fromDate
     * @param toDate
     * @param location
     * @param provider
     * @param status
     * @return
     */
    List<EhrAppointmentDailyCount> getEhrAppointmentDailyCount(String fromDate, String toDate, Location location,
                                                            Provider provider, EhrAppointment.EhrAppointmentStatus status) throws DAOException;


    List<EhrAppointment> getScheduledEhrAppointmentsForPatients() throws DAOException;
}
