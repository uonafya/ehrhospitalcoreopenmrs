package org.openmrs.module.hospitalcore.db.hibernate;

import org.hibernate.Query;
import org.openmrs.Location;
import org.openmrs.Provider;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.EhrProviderScheduleDAO;
import org.openmrs.module.hospitalcore.model.EhrProviderSchedule;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HibernateEhrProviderScheduleDAO extends HibernateEhrSingleClassDAO implements EhrProviderScheduleDAO {

        /**
         * You must call this before using any of the data access methods, since it's not actually
         * possible to write them all with compile-time class information.
         *
         * @param
         */
    public HibernateEhrProviderScheduleDAO() {
            super(EhrProviderSchedule.class);
        }

        @Override
        @Transactional(readOnly = true)
        public List<EhrProviderSchedule> getEhrProviderScheduleByConstraints(Location location, Provider provider, Date appointmentDate) throws DAOException {

            if (location != null) {
                String stringQuery = "SELECT providerSchedule FROM EhrProviderSchedule AS providerSchedule WHERE providerSchedule.voided = false";

                if (location != null)
                    stringQuery += " AND providerSchedule.location=:location";
                if (provider != null)
                    stringQuery += " AND providerSchedule.provider=:provider";
                if (appointmentDate != null) {
                    if (!new SimpleDateFormat("HH:mm:ss").format(appointmentDate).equals("00:00:00")) {
                        stringQuery += " AND :appointmentTime >= providerSchedule.startTime AND :appointmentTime <= providerSchedule.endTime";
                    }
                }
                Query query = super.sessionFactory.getCurrentSession().createQuery(
                        stringQuery);

                if (location != null)
                    query.setParameter("location", location);
                if (provider != null)
                    query.setParameter("provider", provider);
                if (appointmentDate != null) {
                    if (!new SimpleDateFormat("HH:mm:ss").format(appointmentDate).equals("00:00:00")) {
                        query.setParameter("appointmentTime", getTimeFromDate(appointmentDate));
                    }
                }

                return (List<EhrProviderSchedule>) query.list();

            } else {
                throw new DAOException("location must have a value");
            }
        }

        private Time getTimeFromDate(Date date) {
            try {
                return new Time(new SimpleDateFormat("HH:mm:ss")
                        .parse(new SimpleDateFormat("HH:mm:ss").format(date)).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
