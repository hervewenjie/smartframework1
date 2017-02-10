package org.smart4j.framework.proxy.testspringtest;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by chengwenjie on 2017/2/9.
 */
@Component
public class GreetingAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("Greeting After Advice...///");
    }
}
