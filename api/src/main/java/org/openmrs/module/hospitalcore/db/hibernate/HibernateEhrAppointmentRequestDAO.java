package org.openmrs.module.hospitalcore.db.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.api.APIException;
import org.openmrs.module.hospitalcore.db.EhrAppointmentRequestDAO;
import org.openmrs.module.hospitalcore.model.EhrAppointmentRequest;
import org.openmrs.module.hospitalcore.model.EhrAppointmentType;

import java.util.List;

public class HibernateEhrAppointmentRequestDAO extends HibernateEhrSingleClassDAO<EhrAppointmentRequest> implements EhrAppointmentRequestDAO {

    public HibernateEhrAppointmentRequestDAO() {
        super(EhrAppointmentRequest.class);
    }

    public List<EhrAppointmentRequest> getEhrAppointmentRequestsByConstraints(Patient patient, EhrAppointmentType type, Provider provider,
                                                                           EhrAppointmentRequest.EhrAppointmentRequestStatus status) throws APIException {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EhrAppointmentRequest.class);

        if (patient != null) {
            criteria.add(Restrictions.eq("patient", patient));
        }

        if (type != null) {
            criteria.add(Restrictions.eq("appointmentType", type));
        }

        if (provider != null) {
            criteria.add(Restrictions.eq("provider", provider));
        }

        if (status != null) {
            criteria.add(Restrictions.eq("status", status));
        }

        // always exclude voided
        criteria.add(Restrictions.eq("voided", Boolean.FALSE));

        return criteria.list();
    }

}