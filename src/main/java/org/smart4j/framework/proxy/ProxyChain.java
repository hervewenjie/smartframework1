package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengwenjie on 2017/2/8.
 */
public class ProxyChain {

    private final Class<?> targetClass;
    private final Object targetObject;
    private final Method targetMethod;
    private final MethodProxy methodProxy;
    private final Object[] methodParams;

    private List<Proxy> proxyList = new ArrayList<Proxy>();
    private int proxyIndex = 0;

    /**
     *
     * @param targetClass
     * @param targetObject
     * @param targetMethod
     * @param methodProxy
     * @param methodParams
     * 需要注意的是 MethodProxy 这个类, 它是CGLib开源项目为我们提供的一个方法代理对象
     * 在 doProxyChain 方法中被使用
     */
    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy,
                      Object[] methodParams, List<Proxy> proxyList){
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Object[] getMethodParams(){
        return methodParams;
    }

    public Class<?> getTargetClass(){
        return targetClass;
    }

    public Method getTargetMethod(){
        return targetMethod;
    }

    /**
     * 需要解释的是 doProxyChain 方法, 在该方法中, 我们通过 proxyIndex 来充当代理对象的计数器, 若尚未达到 proxyList 的上限
     * 则从 proxyList 中取出相应的 Proxy 对象, 并调用其 doProxy 方法
     * 在 Proxy 的接口中会提供相应的横切逻辑, 并调用 doProxyChain 方法
     * 随后再次调用当前 ProxyChain 对象的 doProxyChain 方法, 直到 proxyIndex 达到 proxyList 的上限为止
     * 最后调用 methodProxy 的 invokeSuper 方法, 执行目标对象的业务逻辑
     * @return
     * @throws Throwable
     */
    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if (proxyIndex < proxyList.size()){
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        } else {
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }

}
