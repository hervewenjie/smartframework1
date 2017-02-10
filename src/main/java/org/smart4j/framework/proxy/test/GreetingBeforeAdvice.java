package org.smart4j.framework.proxy.test;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by chengwenjie on 2017/2/9.
 * 实现增强类, 把他们横切到代码中, 而不是将这些写死到代码中
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Spring Before...");
    }
}
