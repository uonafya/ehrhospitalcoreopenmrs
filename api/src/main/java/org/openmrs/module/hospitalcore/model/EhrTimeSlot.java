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
package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseOpenmrsData;

import java.util.Date;

public class EhrTimeSlot extends BaseOpenmrsData {



    private static final long serialVersionUID = 1L;

    private Integer timeSlotId;

    private EhrAppointmentBlock appointmentBlock;

    private Date startDate;

    private Date endDate;

    public EhrTimeSlot() {

    }

    public EhrTimeSlot(Integer timeSlotId) {
        setId(timeSlotId);
    }

    public EhrTimeSlot(EhrAppointmentBlock appointmentBlock, Date startDate, Date endDate) {
        setEhrAppointmentBlock(appointmentBlock);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public Integer getEhrTimeSlotId() {
        return timeSlotId;
    }

    public void setEhrTimeSlotId(Integer timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    /**
     * @see org.openmrs.OpenmrsObject#getId()
     */
    @Override
    public Integer getId() {
        return getEhrTimeSlotId();
    }

    /**
     * @see org.openmrs.OpenmrsObject#setId(java.lang.Integer)
     */
    @Override
    public void setId(Integer id) {
        setEhrTimeSlotId(id);
    }

    public EhrAppointmentBlock getEhrAppointmentBlock() {
        return appointmentBlock;
    }

    public void setEhrAppointmentBlock(EhrAppointmentBlock appointmentBlock) {
        this.appointmentBlock = appointmentBlock;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
