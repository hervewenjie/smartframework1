package org.smart4j.framework.threadlocal;

/**
 * Created by chengwenjie on 2017/2/13.
 */
public class Sequence2 implements Sequence {

    /**
     * 把 ThreadLocal 看作是一个容器, 这样理解起来就简单了
     * 同样是 static 变量, 对应不同的线程而言, 它没有被共享, 而是每个线程各一份, 这样就保证了线程安全
     * 也就是说, ThreadLocal 为每一个线程提供了一个独立的副本
     *
     * ThreadLocal 的应用还有在数据库的连接上, 每个线程要拥有自己的连接, 而不是共享一个连接
     * 否则 线程1 有可能关闭 线程2 的连接, 线程2写入的时候就会报错
     */
    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence2();

        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
