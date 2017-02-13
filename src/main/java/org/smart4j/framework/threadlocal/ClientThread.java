package org.smart4j.framework.threadlocal;

/**
 * Created by chengwenjie on 2017/2/13.
 */
public class ClientThread extends Thread {

    private Sequence sequence;

    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }

    /**
     * 在线程中连续三次输出线程名字与其对应的序列号
     */
    @Override
    public void run() {
        for (int i=0;i<3;i++) {
            System.out.println(Thread.currentThread().getName() + "=>" + sequence.getNumber());
        }
    }
}
