package org.smart4j.framework.proxy.testspringtest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyChain;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by chengwenjie on 2017/2/9.
 * 我们需要写一个类, 让它提供一个创建代理对象的方法
 * 输入一个目标类和一组 Proxy 接口实现, 输出一个代理对象
 */
public class ProxyManager {

    /**
     * 泛型的声明, 必须在方法修饰符 (public/static/final/abstract等) 之后, 返回值声明之前
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){

        // 需要实现 CGLib 给我们提供的 MethodInterceptor 类, 并填充 intercept 方法
        // 套路是, 一个 proxy 需要继承 MethodInterceptor, 填充 intercept
        //    before();
        //    Object result = methodProxy.invokeSuper(o, objects);
        //    after();
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
            }
        });
    }
}
