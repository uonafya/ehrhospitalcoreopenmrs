package org.openmrs.module.hospitalcore.db.hibernate;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.openmrs.module.hospitalcore.db.LaboratoryCoreDAO;
import org.openmrs.module.hospitalcore.model.Lab;

public class HibernateLaboratoryCoreDAO implements LaboratoryCoreDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Lab> getAllDepartments() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Lab.class);
		return criteria.list();
	}
}
