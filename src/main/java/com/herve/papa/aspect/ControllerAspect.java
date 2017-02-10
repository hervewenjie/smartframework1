package com.herve.papa.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * Created by chengwenjie on 2017/2/10.
 *
 * 拦截 Controller 所有方法
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOGGER.debug("-------- begin --------");
        LOGGER.debug(String.format("Class: %s", cls.getName()));
        LOGGER.debug(String.format("Method: %s", method.getName()));
        begin = System.currentTimeMillis();

        System.out.println("-------- begin --------");
        System.out.println(String.format("Class: %s", cls.getName()));
        System.out.println(String.format("Method: %s", method.getName()));
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOGGER.debug(String.format("Time: %dms", System.currentTimeMillis() - begin));
        LOGGER.debug("-------- end --------");

        System.out.println(String.format("Time: %dms", System.currentTimeMillis() - begin));
        System.out.println("-------- end --------");
    }
}
