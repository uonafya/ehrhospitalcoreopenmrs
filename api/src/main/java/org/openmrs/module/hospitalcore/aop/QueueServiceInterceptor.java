package org.openmrs.module.hospitalcore.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.openmrs.Concept;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.PatientQueueService;
import org.openmrs.module.hospitalcore.model.OpdPatientQueue;
import org.openmrs.module.queue.model.Queue;
import org.openmrs.module.queue.model.QueueEntry;
import org.openmrs.util.OpenmrsConstants;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

public class QueueServiceInterceptor implements MethodInterceptor {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(OpenmrsConstants.LOG_CLASS_DEFAULT);
    private static final String[] QUE_METHOD_PREFIXES = { "createQueueEntry" };

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        /* whenever a client is sent to another service point, replicate the data to the custom EHR-queue tables associating them to
         * corresponding payment categories
         *  */
        Object object = methodInvocation.proceed();

        try {
            QueueEntry queue = (QueueEntry) object;
            System.out.println("Service requested on 3.x is:" + queue.getQueue().getName()+ " : " + queue.getUuid());
            final Concept service =queue.getQueue().getService();
            int [] trackedQueues = {5, 2, 7, 1, 3};
//            if(Arrays.binarySearch(trackedQueues,service.getConceptId())<0) {
                PatientQueueService patientQueueService = Context.getService(PatientQueueService.class);
                OpdPatientQueue opdPatientQueue = new OpdPatientQueue();
                opdPatientQueue.setPatient(queue.getPatient());
                opdPatientQueue.setPatientName(queue.getPatient().getNames().toString());
                opdPatientQueue.setSex(queue.getPatient().getGender());
                opdPatientQueue.setStatus(queue.getStatus().getName().getName());
                opdPatientQueue.setOpdConcept(service);
                opdPatientQueue.setUser(Context.getAuthenticatedUser());
                opdPatientQueue.setCreatedOn(new Date());
                opdPatientQueue.setOpdConceptName(service.getName().getName());
                opdPatientQueue.setVisitStatus("Revisit Patient");
                opdPatientQueue.setPatientIdentifier(queue.getPatient().getPatientIdentifier().getIdentifier());
                opdPatientQueue.setCategory("Paying");//visitAttribute
                opdPatientQueue.setReferralConcept((queue.getQueueComingFrom()!=null)?queue.getQueueComingFrom().getService():null);
                //if paying send registration fee to cashier, check if bill had been settled over today
                //sent to these service queue
                patientQueueService.saveOpdPatientQueue(opdPatientQueue);


//            }

            return methodInvocation.proceed();
        }
        catch (Exception e) {
                if (methodInvocation.getMethod().getName().equals("createQueueEntry")) {
                    String username;
                    User user = Context.getAuthenticatedUser();
                    if (user == null) {
                        username = "Guest (Not logged in)";
                    } else {
                        username = user.getUsername();
                        if (username == null || username.length() == 0) {
                            username = user.getSystemId();
                        }
                    }
                    log.debug(String.format(
                            "An error occurred while executing this method.%nCurrent user: %s%nError message: %s", username, e
                                    .getMessage()), e);
                }
                throw e;
            }

    }
}
