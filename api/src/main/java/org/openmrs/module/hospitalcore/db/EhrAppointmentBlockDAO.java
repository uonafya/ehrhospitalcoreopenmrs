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

import org.openmrs.Provider;
import org.openmrs.module.hospitalcore.model.EhrAppointmentBlock;
import org.openmrs.module.hospitalcore.model.EhrAppointmentType;

import java.util.Date;
import java.util.List;

public interface EhrAppointmentBlockDAO extends EhrSingleClassDAO {

    List<EhrAppointmentBlock> getEhrAppointmentBlocks(Date fromDate, Date toDate, String locations, Provider provider,
                                                   List<EhrAppointmentType> appointmentType);

    List<EhrAppointmentBlock> getOverlappingEhrAppointmentBlocks(EhrAppointmentBlock appointmentBlock);
}
