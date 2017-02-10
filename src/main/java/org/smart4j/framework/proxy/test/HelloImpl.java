package org.smart4j.framework.proxy.test;

/**
 * Created by chengwenjie on 2017/2/9.
 */
public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("Hello ! " + name);
    }
}
