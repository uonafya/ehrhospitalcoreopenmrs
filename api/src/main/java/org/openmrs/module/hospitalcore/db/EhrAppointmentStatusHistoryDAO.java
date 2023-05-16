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

import org.openmrs.module.hospitalcore.EhrAppointmentService;
import org.openmrs.module.hospitalcore.model.EhrAppointment;
import org.openmrs.module.hospitalcore.model.EhrAppointmentStatusHistory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Database methods for {@link EhrAppointmentService}.
 */
public interface EhrAppointmentStatusHistoryDAO extends EhrSingleClassDAO {
    /**
     * Retrives the start date of the current status of a given appointment.
     *
     * @param appointment - The appointment.
     * @return the start date of the current status of a given appointment.
     */
    @Transactional(readOnly = true)
    public Date getStartDateOfCurrentStatus(EhrAppointment appointment);

    @Transactional(readOnly = true)
    public List<EhrAppointmentStatusHistory> getAll(EhrAppointment.EhrAppointmentStatus status);

    /**
     * Retrieves the histories in the given interval that describes the given status Does not takes
     * into consideration current appointments with the given status.
     *
     * @param fromDate The lower bound of the date interval.
     * @param endDate The upper bound of the date interval.
     * @param status The AppointmentStatus status to filter histories by.
     * @return A list of AppointmentStatusHistory histories that fits the given criteria.
     */
    @Transactional(readOnly = true)
    public List<EhrAppointmentStatusHistory> getHistoriesByInterval(Date fromDate, Date endDate, EhrAppointment.EhrAppointmentStatus status);

    /**
     * Purge all history from a specific appointment
     *
     * @param appointment The appointment which the story should be deleted.
     * @return
     */
    public void purgeHistoryBy(EhrAppointment appointment);

    /**
     * retrieves all the statuses of an appointment
     * @param appointment
     * @return
     */
    @Transactional(readOnly = true)
    List<EhrAppointmentStatusHistory> getEhrAppointmentStatusHistories(EhrAppointment appointment);

    /**
     * retrieves the most recent status of an appointment
     * @param appointment
     * @return
     */
    @Transactional(readOnly = true)
    EhrAppointmentStatusHistory getMostRecentEhrAppointmentStatusHistory(EhrAppointment appointment);
}
