package org.smart4j.framework.proxy.testadvisor;

import org.springframework.stereotype.Component;

/**
 * Created by chengwenjie on 2017/2/9.
 * AOP中 Pointcut(切点) 说白了就是基于表达式的拦截条件
 * Advisor(切面)封装了 Advice(增强)与 Pointcut(切点)
 *
 * 在GreetingImpl中故意增加两个方法, 都以 good 开头
 * 下面要做的就是拦截这两个新增的方法, 而对 sayHello 方法不做拦截
 *
 * 不管怎样简化, 始终解决不了切面的配置这件繁重的手工劳动, 在 Spring 配置文件中, 仍然会存在大量的切面配置
 */
@Component
public class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello ! " + name);
    }

    public void goodMorning(String name){
        System.out.println("Good Morning! " + name);
    }

    public void goodNight(String name){
        System.out.println("Good Night! " + name);
    }


}
