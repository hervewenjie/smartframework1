package org.smart4j.framework.proxy;

/**
 * Created by chengwenjie on 2017/2/8.
 */
public interface Proxy {

    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
