package org.smart4j.framework.proxy.testspringtest;

import org.springframework.stereotype.Component;

/**
 * Created by chengwenjie on 2017/2/9.
 */
@Component
public class GreetingImpl implements Greeting {
    @Override
    public void greet(String name) {
        System.out.println("Greeting from GreetingImpl");
    }
}
