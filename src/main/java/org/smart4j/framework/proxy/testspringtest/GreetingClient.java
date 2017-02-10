package org.smart4j.framework.proxy.testspringtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by chengwenjie on 2017/2/9.
 */
public class GreetingClient {

    public static void main(String[] args) {
        // 读取 src 目录下的配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Greeting greeting = (Greeting) context.getBean("greetingProxy");
        greeting.greet("Config Herve");
    }
}
