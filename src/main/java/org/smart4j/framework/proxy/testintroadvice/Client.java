package org.smart4j.framework.proxy.testintroadvice;

import org.smart4j.framework.proxy.testspringtest.GreetingImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by chengwenjie on 2017/2/9.
 */
public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 注意 转型为目标类 而不是它实现的接口 Greeting
        GreetingImpl greetingImpl = (GreetingImpl) context.getBean("greetingProxy");
        greetingImpl.greet("Intro Advice Herve");

        // 强制向上转型为 Apology
        Apology apology = (Apology) greetingImpl;
        apology.saySorry("Herve");
    }
}
