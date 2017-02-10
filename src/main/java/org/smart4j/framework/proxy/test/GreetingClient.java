package org.smart4j.framework.proxy.test;

import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by chengwenjie on 2017/2/9.
 * 代理是方法级别的, 被射入的目标对象也可以不实现任何接口
 */
public class GreetingClient {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new NoInterface()); // 射入目标类对象
        proxyFactory.addAdvice(new GreetingBeforeAdvice());
        proxyFactory.addAdvice(new GreetingAfterAdvice());

        NoInterface noInterface = (NoInterface) proxyFactory.getProxy(); // 从工厂中获取代理
        noInterface.say();
    }
}
