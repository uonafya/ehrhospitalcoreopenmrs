package org.openmrs.module.hospitalcore.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

public class EhrQuePatientAdviser extends StaticMethodMatcherPointcutAdvisor implements Advisor {


    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return method.getName().equals("createQueueEntry");
    }

    @Override
    public Advice getAdvice() {
        return new QueueServiceInterceptor();
    }
}
