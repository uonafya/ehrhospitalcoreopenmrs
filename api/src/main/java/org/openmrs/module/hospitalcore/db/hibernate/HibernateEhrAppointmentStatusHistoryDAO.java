package org.openmrs.module.hospitalcore.db.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.hospitalcore.db.EhrAppointmentStatusHistoryDAO;
import org.openmrs.module.hospitalcore.model.EhrAppointment;
import org.openmrs.module.hospitalcore.model.EhrAppointmentStatusHistory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class HibernateEhrAppointmentStatusHistoryDAO extends HibernateEhrSingleClassDAO implements EhrAppointmentStatusHistoryDAO {


    public HibernateEhrAppointmentStatusHistoryDAO() {
        super(EhrAppointmentStatusHistory.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentStatusHistory> getAll(EhrAppointment.EhrAppointmentStatus status) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mappedClass);
        criteria.add(Restrictions.eq("status", status));
        criteria.addOrder(Order.asc("status"));
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Date getStartDateOfCurrentStatus(EhrAppointment appointment) {
        String query = "Select Max(endDate) from EhrAppointmentStatusHistory where appointment=:appointment";
        Date endDate = (Date) super.sessionFactory.getCurrentSession().createQuery(query)
                .setParameter("appointment", appointment).uniqueResult();
        endDate = (endDate == null && appointment != null) ? appointment.getDateCreated() : endDate;

        return endDate;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EhrAppointmentStatusHistory> getHistoriesByInterval(Date fromDate, Date endDate, EhrAppointment.EhrAppointmentStatus status) {
        List<EhrAppointmentStatusHistory> histories = null;
        if (fromDate == null || endDate == null || status == null)
            return histories;

        String stringQuery = "Select history from EhrAppointmentStatusHistory AS history WHERE history.startDate >= :fromDate AND history.endDate <= :endDate AND history.status = :status";
        Query query = super.sessionFactory.getCurrentSession().createQuery(stringQuery);

        query.setParameter("fromDate", fromDate).setParameter("endDate", endDate).setParameter("status", status);

        histories = query.list();

        return histories;

    }

    @Override
    public void purgeHistoryBy(EhrAppointment appointment) {
        String hql = "delete from EhrAppointmentStatusHistory where appointment= :appointment";
        Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("appointment", appointment).executeUpdate();
    }

    @Override
    public List<EhrAppointmentStatusHistory> getEhrAppointmentStatusHistories(EhrAppointment appointment) {
        String query = "Select appointmentHistory from EhrAppointmentStatusHistory AS appointmentHistory where appointmentHistory.appointment=:appointment";
        return  (List<EhrAppointmentStatusHistory>) super.sessionFactory.getCurrentSession().createQuery(query)
                .setParameter("appointment", appointment).list();

    }

    @Override
    public EhrAppointmentStatusHistory getMostRecentEhrAppointmentStatusHistory(EhrAppointment appointment) {

        String stringQuery = "Select history from EhrAppointmentStatusHistory AS history  " +
                "WHERE history.startDate = (select max(statusHistory.startDate) from EhrAppointmentStatusHistory AS statusHistory " +
                "WHERE statusHistory.appointment = :appointment)";
        Query query = super.sessionFactory.getCurrentSession().createQuery(stringQuery).setParameter("appointment", appointment);
        return (EhrAppointmentStatusHistory) query.uniqueResult();
    }
}
