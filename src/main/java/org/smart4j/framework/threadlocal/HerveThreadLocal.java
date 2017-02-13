package org.smart4j.framework.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengwenjie on 2017/2/13.
 *
 * 其实可以想想 ThreadLocal 里不就是封装了一个 Map 嘛, 自己都可以写一个 ThreadLocal 了
 */
public class HerveThreadLocal<T> {

    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T value) {
        container.put(Thread.currentThread(), value);
    }

    public T get() {
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if (value==null && !container.containsKey(thread)) {
            value = initialValue();
            container.put(thread, value);
        }
        return value;
    }

    public void remove() {
        container.remove(Thread.currentThread());
    }

    public T initialValue() {
        return null;
    }
}
