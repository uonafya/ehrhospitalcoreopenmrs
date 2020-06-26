package org.openmrs.module.hospitalcore;

import java.util.List;
import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.DmsOpdUnit;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DmsCommonService extends OpenmrsService {
    ConceptName getOpdWardNameByConceptId(Concept paramConcept) throws APIException;

    List<DmsOpdUnit> getOpdActivatedIdList();
}
