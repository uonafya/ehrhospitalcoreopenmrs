package org.openmrs.module.hospitalcore.db.hibernate;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.api.ConceptNameType;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.DmsCommonDAO;
import org.openmrs.module.hospitalcore.model.DmsOpdUnit;

public class HibernateDmsCommonDAO implements DmsCommonDAO {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ConceptName getOpdWardNameByConceptId(Concept con) throws DAOException {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ConceptName.class);
        criteria.add((Criterion)Restrictions.eq("concept", con));
        criteria.add((Criterion)Restrictions.like("conceptNameType", ConceptNameType.FULLY_SPECIFIED));
        return (ConceptName)criteria.uniqueResult();
    }

    public List<DmsOpdUnit> getOpdActivatedIdList() {
        Date date = new Date();
        int hour = date.getHours();
        int minute = date.getMinutes();
        int second = date.getSeconds();
        String curtime = "";
        if (hour < 10) {
            curtime = "0" + hour + ":" + minute + ":" + second;
        } else {
            curtime = hour + ":" + minute + ":" + second;
        }
        String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        GregorianCalendar gcalendar = new GregorianCalendar();
        String dayName = days[gcalendar.get(7) - 1];
        String hql = "from DmsOpdUnit d where d.opdWorkingDay='" + dayName + "' AND '" + curtime + "' BETWEEN d.startTime AND d.endTime AND d.unitActiveDate is not null";
        Session session = this.sessionFactory.getCurrentSession();
        Query q = session.createQuery(hql);
        List<DmsOpdUnit> list = q.list();
        return list;
    }
}
