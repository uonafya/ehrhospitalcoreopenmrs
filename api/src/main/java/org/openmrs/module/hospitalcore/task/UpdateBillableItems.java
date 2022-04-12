package org.openmrs.module.hospitalcore.task;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.Department;
import org.openmrs.module.hospitalcore.model.EhrDepartment;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.scheduler.tasks.AbstractTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UpdateBillableItems extends AbstractTask {
  private static final Logger log = LoggerFactory.getLogger(UpdateBillableItems.class);
  @Override
  public void execute() {

    if (!isExecuting) {
      if (log.isDebugEnabled()) {
        log.debug("Updating the billable service items with the associated department");
      }

      startExecuting();
      try {
        //do all the work here
        performBillableItemsUpdate();

      }
      catch (Exception e) {
        log.error("Error while updating the billable items ", e);
      }
      finally {
        stopExecuting();
      }
    }

  }

  private void performBillableItemsUpdate() {
    BillingService billingService = Context.getService(BillingService.class);
    HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
    List<PatientServiceBillItem> emptyDepartmentItems = billingService.getPatientBillableServicesItemsWithNoDepartment();
    for(PatientServiceBillItem item :emptyDepartmentItems ) {

    }
  }

  private EhrDepartment getDepartment(String department) {
    return Context.getService(HospitalCoreService.class).getDepartmentByName(department);
  }
}
