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

import org.openmrs.module.hospitalcore.model.EhrAppointmentType;

import java.util.Date;
import java.util.List;

public interface EhrAppointmentTypeDAO extends EhrSingleClassDAO {
    /**
     * Retrieves the amount of appointments in the given interval with the given type.
     *
     * @param fromDate The lower bound of the interval.
     * @param toDate The upper bound of the interval.
     * @param type The Appointment Type
     * @return Integer count of appointments in the given interval with the given type.
     */
    public Integer getEhrAppointmentTypeCount(Date fromDate, Date toDate, EhrAppointmentType type);

    public List<EhrAppointmentType> getEhrAppointmentTypes(String fuzzySearchPhrase, boolean includeRetired);

    boolean verifyDuplicatedEhrAppointmentTypeName(EhrAppointmentType appointmentType);
}
