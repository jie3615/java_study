package highConcurrency.thread;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2020/1/19
 * @description:
 */
public class WaitNotifyTest01 {

    public static void main(String[] args) throws InterruptedException {

        Object object  = new Object();
        object.wait();
    }

    @Test
    public void test01() throws InterruptedException {
        Object object  = new Object();
        object.wait();
    }
    /**
     * java.lang.IllegalMonitorStateException
     at java.lang.Object.wait(Native Method)
     at java.lang.Object.wait(Object.java:502)
     at highConcurrency.thread.WaitNotifyTest01.test01(WaitNotifyTest01.java:21)

    分析：未获取锁的情况下执行wait,抛出监视器状态异常
     */

    @Test
    public void test02() throws InterruptedException {
        Object object  = new Object();
        synchronized (object) {
            object.wait();
        }
        /**
         * 当前线程在object获取锁，并处于等待状态，
         * 一旦调用了wait方法就会释放当前锁，在调用wait前一定要获取锁
         * 需要通过另外一个线程进行唤醒操作
         *
         * 注：sleep方法线程是不会释放当前锁
         *   这个锁就是monitor
         */
    }
}