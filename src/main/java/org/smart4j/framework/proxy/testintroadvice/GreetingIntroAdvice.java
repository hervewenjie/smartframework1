package org.smart4j.framework.proxy.testintroadvice;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by chengwenjie on 2017/2/9.
 * 我们想用这个增强类去丰富 GreetingImpl 的类的功能
 * 那么这个 GreetingImpl 类无需实现 Apology 接口, 就可以在程序运行的时候调用 Apology接口的方法
 * 神奇?
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        return super.invoke(mi);
    }

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! " + name);
    }
}
