package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by chengwenjie on 2017/2/9.
 * 需要注意的是 doProxy 方法, 我们从 proxyChain 参数中获取了目标类, 目标方法和方法参数
 * 最后通过一个 try/catch/finally 代码块来实现调用框架, 从框架中抽象出一系列的 "钩子方法"
 * 这些抽象方法可在 AspectProxy 的子类中有选择性地进行实现
 */
public abstract class AspectProxy implements Proxy {

    private static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        Class<?> cls    = proxyChain.getTargetClass();
        Method method   = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if (intercept(cls, method, params)){
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {

        } finally {
            end();
        }

        return result;
    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
        return true;
    }

    public void begin() {

    }

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {

    }
    public void after(Class<?> cls, Method method, Object[] params) throws Throwable {

    }

    public void error(Class<?> cls, Method method, Object[] params) throws Throwable {

    }

    public void end() {

    }

}
