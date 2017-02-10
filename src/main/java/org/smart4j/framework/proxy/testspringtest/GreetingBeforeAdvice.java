package org.smart4j.framework.proxy.testspringtest;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by chengwenjie on 2017/2/9.
 */
@Component
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Greeting Before Advice...");
    }
}
