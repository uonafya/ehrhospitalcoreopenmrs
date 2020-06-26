package org.openmrs.module.hospitalcore.db.hibernate;

//import java.sql.Date;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Order;
import org.openmrs.Role;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.LabDAO;
import org.openmrs.module.hospitalcore.model.Lab;
import org.openmrs.module.hospitalcore.model.LabTest;

public class HibernateLabDAO implements LabDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Lab> getAllActivelab() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Lab.class);
		criteria.add((Criterion)Restrictions.eq("retired", Boolean.valueOf(false)));
		return criteria.list();
	}

	public List<Lab> getAllLab() throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Lab.class);
		return criteria.list();
	}

	public Lab getLabById(Integer labId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Lab.class);
		criteria.add((Criterion)Restrictions.eq("labId", labId));
		return (Lab)criteria.uniqueResult();
	}

	public Lab getLabByName(String name) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Lab.class);
		criteria.add((Criterion)Restrictions.eq("name", name));
		return (Lab)criteria.uniqueResult();
	}

	public LabTest getLabTestById(Integer labTestId) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(LabTest.class);
		criteria.add((Criterion)Restrictions.eq("labTestId", labTestId));
		return (LabTest)criteria.uniqueResult();
	}

	public LabTest getLabTestByOrder(Order order) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(LabTest.class);
		criteria.add((Criterion)Restrictions.eq("order", order));
		return (LabTest)criteria.uniqueResult();
	}

	public LabTest getLabTestBySampleNumber(String sampleNumber) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(LabTest.class);
		criteria.add((Criterion)Restrictions.eq("sampleNumber", sampleNumber));
		return (LabTest)criteria.uniqueResult();
	}

	public Lab saveLab(Lab lab) throws DAOException {
		return (Lab)this.sessionFactory.getCurrentSession().merge(lab);
	}

	public LabTest saveLabTest(LabTest labTest) throws DAOException {
		return (LabTest)this.sessionFactory.getCurrentSession().merge(labTest);
	}

	public void deleteLab(Lab lab) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(lab);
	}

	public Lab getLabByRole(Role role) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Lab.class);
		criteria.add((Criterion)Restrictions.eq("role", role));
		return (Lab)criteria.uniqueResult();
	}

	public List<Lab> getLabByRoles(List<Role> roles) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Lab.class);
		criteria.add(Restrictions.in("role", roles));
		return criteria.list();
	}

	public List<LabTest> getLatestLabTestByDate(Date today, Date nextDay, Lab lab) throws DAOException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(LabTest.class);
		criteria.add((Criterion)Restrictions.eq("lab", lab));
		criteria.add((Criterion)Restrictions.ge("acceptDate", new Date(today.getTime())));
		criteria.add((Criterion)Restrictions.lt("acceptDate", new Date(nextDay.getTime())));
		return criteria.list();
	}

	public void deleteLabTest(LabTest labtest) throws DAOException {
		this.sessionFactory.getCurrentSession().delete(labtest);
	}
}
