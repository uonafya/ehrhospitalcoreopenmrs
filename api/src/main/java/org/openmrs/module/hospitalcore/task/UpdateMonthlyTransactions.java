package org.openmrs.module.hospitalcore.task;

import org.openmrs.scheduler.tasks.AbstractTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateMonthlyTransactions extends AbstractTask {

    private static final Logger log = LoggerFactory.getLogger(UpdateMonthlyTransactions.class);

    @Override
    public void execute() {

        if (!isExecuting) {
            if (log.isDebugEnabled()) {
                log.debug("Updating the Monthly summary  service table");
            }

            startExecuting();
            try {
                //do all the work here
                performMonthlySummary();

            }
            catch (Exception e) {
                log.error("Error while updating Monthly summary  service table ", e);
            }
            finally {
                stopExecuting();
            }
        }

    }
    private void performMonthlySummary() {
        log.info("The system logic for updating the table will be put here");
    }
}
