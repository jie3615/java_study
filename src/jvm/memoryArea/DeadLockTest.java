package jvm.memoryArea;

/**
 * @author: wyj
 * @date: 2019/11/19
 * @description:
 */
public class DeadLockTest {

   static String lockA = "lockA";
    static String lockB = "lockB";

    public static void m1() {
        synchronized (lockA) {
            System.out.println("进入m1(),持有lockA");
            m2();
        }
    }

    public static void m2() {
        synchronized (lockB) {
            System.out.println("进入m2(),持有lockB");
            m1();
        }
    }



    public static void main(String[] args){
        Thread thread1 = new Thread(()->{
            m1();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"线程1");

        Thread thread2 = new Thread(() -> {
            m2();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程2");
        thread1.start();
        thread2.start();
    }


}