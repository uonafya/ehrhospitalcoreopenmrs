package org.openmrs.module.hospitalcore.db.hibernate;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Provider;
import org.openmrs.api.APIException;
import org.openmrs.module.hospitalcore.db.EhrTimeSlotDAO;
import org.openmrs.module.hospitalcore.model.EhrAppointmentBlock;
import org.openmrs.module.hospitalcore.model.EhrAppointmentType;
import org.openmrs.module.hospitalcore.model.EhrTimeSlot;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Vector;

public class HibernateEhrTimeSlotDAO extends HibernateEhrSingleClassDAO implements EhrTimeSlotDAO {

    public HibernateEhrTimeSlotDAO() {
        super(EhrTimeSlot.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrTimeSlot> getEhrTimeSlotsByConstraints(EhrAppointmentType appointmentType, Date fromDate, Date toDate,
                                                       Provider provider) throws APIException {

        if (appointmentType == null) {
            throw new APIException("EhrAppointment Type can not be null.");
        }
        else if (fromDate != null && toDate != null && !fromDate.before(toDate)) {
            throw new APIException("fromDate can not be later than toDate");
        }
        else {
            Date startDate = (fromDate == null) ? new Date() : fromDate;

            String stringQuery = "SELECT ehrTimeSlot FROM EhrTimeSlot AS timeSlot WHERE ehrTimeSlot.ehrAppointmentBlock IN("
                    + " FROM EhrAppointmentBlock WHERE :appointmentType IN elements(types)) AND voided = false AND endDate > :startDate";

            if (toDate != null) {
                stringQuery += " AND endDate <= :endDate";
            }

            if (provider != null) {
                stringQuery += " AND ehrTimeSlot.ehrAppointmentBlock.provider = :provider";
            }

            stringQuery += " ORDER BY startDate";

            Query query = super.sessionFactory.getCurrentSession().createQuery(stringQuery)
                    .setParameter("appointmentType", appointmentType).setParameter("startDate", startDate);

            if (toDate != null) {
                query.setParameter("endDate", toDate);
            }

            if (provider != null) {
                query.setParameter("provider", provider);
            }

            return query.list();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrTimeSlot> getEhrTimeSlotsByAppointmentBlock(EhrAppointmentBlock appointmentBlock) {
        if (appointmentBlock == null)
            return new Vector<EhrTimeSlot>();
        return super.sessionFactory.getCurrentSession().createCriteria(EhrTimeSlot.class)
                .add(Restrictions.eq("appointmentBlock", appointmentBlock)).add(Restrictions.eq("voided", false)).list();
    }
}
