package org.smart4j.framework.threadlocal;

/**
 * Created by chengwenjie on 2017/2/13.
 */
public class Sequence1 implements Sequence {

    // 这里的 static 当然是线程之间共享的啊...
    private static int number = 0;

    @Override
    public int getNumber() {
        number = number + 1;
        return number;
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence1();

        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
