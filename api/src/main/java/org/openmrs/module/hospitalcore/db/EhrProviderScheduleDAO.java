package org.openmrs.module.hospitalcore.db;

import org.openmrs.Location;
import org.openmrs.Provider;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.EhrProviderSchedule;

import java.util.Date;
import java.util.List;

public interface EhrProviderScheduleDAO extends EhrSingleClassDAO{
    List<EhrProviderSchedule> getEhrProviderScheduleByConstraints(Location location, Provider provider, Date appointmentDate) throws DAOException;
}
