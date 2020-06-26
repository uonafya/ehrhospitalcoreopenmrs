package org.openmrs.module.hospitalcore.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.DmsCommonService;
import org.openmrs.module.hospitalcore.db.DmsCommonDAO;
import org.openmrs.module.hospitalcore.model.DmsOpdUnit;

public class DmsCommonServiceImpl extends BaseOpenmrsService implements DmsCommonService {
    private Log log = LogFactory.getLog(getClass());

    protected DmsCommonDAO dao;

    public DmsCommonDAO getDao() {
        return this.dao;
    }

    public void setDao(DmsCommonDAO dao) {
        this.dao = dao;
    }

    public ConceptName getOpdWardNameByConceptId(Concept con) throws APIException {
        return this.dao.getOpdWardNameByConceptId(con);
    }

    public List<DmsOpdUnit> getOpdActivatedIdList() {
        return this.dao.getOpdActivatedIdList();
    }
}
