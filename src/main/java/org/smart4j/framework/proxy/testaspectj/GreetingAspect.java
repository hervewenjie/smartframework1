package org.smart4j.framework.proxy.testaspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by chengwenjie on 2017/2/10.
 * 最简单的例子来实现环绕增强, 先定义一个Aspect切面类
 *
 * Aspect 注解表明这个类是一个 Aspect(其实就是 Advisor)
 * 该类无须实现任何接口, 只需定义一个方法(方法叫什么无所谓)
 * 在方法上标注 Around 注解, 在注解中使用 AspectJ 表达式
 * 方法参数中包括一个 ProceedingJoinPoint 对象, 它在 AOP 中称为 Joinpoint(连接点)
 * 通过该对象获取该方法的任何信息, 例如方法名, 参数等
 */
@Aspect
@Component
public class GreetingAspect {

    /**
     * Execution 表示拦截方法, 括号中可以定义需要匹配的规则
     * 第一个 * 表示方法的返回值是任意的
     * 第二个 * 表示匹配该类中所有方法
     * (..) 表示方法参数是任意的
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.smart4j.framework.proxy.testspringtest.GreetingImpl.* (..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        before();
        Object result = pjp.proceed();
        after();
        return result;
    }

    private void before() {
        System.out.println("Before Aspect...");
    }

    private void after() {
        System.out.println("After Aspect..");
    }
}
