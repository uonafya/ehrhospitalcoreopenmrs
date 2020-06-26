package org.openmrs.module.hospitalcore.db;

import java.util.List;
import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.DmsOpdUnit;

public interface DmsCommonDAO {
    ConceptName getOpdWardNameByConceptId(Concept paramConcept) throws DAOException;

    List<DmsOpdUnit> getOpdActivatedIdList();
}
