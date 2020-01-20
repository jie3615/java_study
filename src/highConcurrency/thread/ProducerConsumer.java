package highConcurrency.thread;

/**
 * @author: wyj
 * @date: 2020/1/20
 * @description:
 */

// 两个线程交替打印0和1；
public class ProducerConsumer {

    static class MyData{
        Object object  = new Object();
        int count = 0;

        void addOne() throws InterruptedException {
            synchronized (object) {
                while (true) {
                    if (count != 0) {
                        object.wait();
                    }
                    System.out.println(count);
                    count++;
                    object.notify();
                }

            }
        }
        void reduceOne() throws InterruptedException {
            synchronized (object) {
                while (true) {
                    if (count != 1) {
                        object.wait();
                    }
                    System.out.println(count);
                    count--;
                    object.notify();
                }
            }
        }
    }

    public static void main(String[] args){

        MyData data = new MyData();
        Thread thread1 = new Thread(()->{
            try {
                data.addOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        thread1.start();

        Thread thread2 = new Thread(()->{
            try {
                data.reduceOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        thread2.start();

    }

}