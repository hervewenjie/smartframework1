package org.smart4j.framework.proxy.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by chengwenjie on 2017/2/9.
 * 需要实现 CGLib 给我们提供的 MethodInterceptor 类, 并填充 intercept 方法
 * 方法中最后一个 MethodProxy 类型的参数值得注意, CGLib 给我们提供的是方法级别的代理, 也可以理解为对方法的拦截
 * 这不是传说中的方法拦截器么? 我们直接调用 proxy 的 invokeSuper 方法, 将被代理对象的 obj 及方法参数 args 传入即可
 */
public class CGLibProxy implements MethodInterceptor {

    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    private void before(){
        System.out.println("CGLib Before...");
    }

    private void after(){
        System.out.println("CGLib After...");
    }

    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy();
        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        helloProxy.say("Herve");
    }
}
