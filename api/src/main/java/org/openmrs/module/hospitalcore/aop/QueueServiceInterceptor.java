package org.openmrs.module.hospitalcore.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.openmrs.module.queue.model.Queue;

public class QueueServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        /* whenever a client is sent to another service point, replicate the data to the custom EHR-queue tables associating them to
         * corresponding payment categories
         *  */
        Object object = methodInvocation.proceed();
        Queue queue = (Queue) object;
        System.out.println("Service requested on 3.x is:" + queue.getService()+ " : " + queue.getName());
        return object;
    }
}
