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
package org.openmrs.module.hospitalcore.db.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.hospitalcore.db.EhrAppointmentTypeDAO;
import org.openmrs.module.hospitalcore.model.EhrAppointmentType;

import java.util.Date;
import java.util.List;

public class HibernateEhrAppointmentTypeDAO extends HibernateEhrSingleClassDAO implements EhrAppointmentTypeDAO {

    public HibernateEhrAppointmentTypeDAO() {
        super(EhrAppointmentType.class);
    }

    public Integer getEhrAppointmentTypeCount(Date fromDate, Date endDate, EhrAppointmentType type) {
        String stringQuery = "SELECT count(*) As counter FROM EhrAppointment AS appointment"
                + " WHERE appointment.voided = false "
                + "AND appointment.timeSlot.startDate >= :fromDate " + "AND appointment.timeSlot.endDate <= :endDate "
                + "AND appointment.appointmentType=:appointmentType";

        Query query = super.sessionFactory.getCurrentSession().createQuery(stringQuery).setParameter("fromDate", fromDate)
                .setParameter("endDate", endDate).setParameter("appointmentType", type);

        return ((Long) query.uniqueResult()).intValue();
    }

    public List<EhrAppointmentType> getEhrAppointmentTypes(String fuzzySearchPhrase, boolean includeRetired) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mappedClass);
        criteria.add(Restrictions.ilike("name", fuzzySearchPhrase, MatchMode.ANYWHERE));
        if (!includeRetired)
            criteria.add(Restrictions.eq("retired", includeRetired));
        criteria.addOrder(Order.asc("name"));
        return criteria.list();
    }

    @Override
    public boolean verifyDuplicatedEhrAppointmentTypeName(EhrAppointmentType appointmentType) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mappedClass);
        criteria.add(Restrictions.eq("name", appointmentType.getName()).ignoreCase());
        criteria.add(Restrictions.eq("retired", false));
        criteria.add(Restrictions.not(Restrictions.eq("uuid", appointmentType.getUuid())));

        return !criteria.list().isEmpty();
    }
}