package thread;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.*;

/**
 * @Auther: wyj
 * @Date: 2019/8/27
 * @Description:
 */
public class VolatileDemo {
    // Demo1
  /*  public static void main(String[] args){
        MyData myData = new MyData();
        new Thread(()->{
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addData();

        },"线程一").start();

        while (myData.i == 0) {
        }
    }
*/
  // Demo2

    /**
     *使用十个线程，每个线程加1000次，看结果:均小于10000，eg:十个线程分别加1000次结果9989
     * 解决方案：1.加锁，同步关键字，lock
     *           2.使用cas,atomic原子类
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        MyData myData = new MyData();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{

                for (int j = 0; j < 1000; j++) {
                    myData.addOne();
                }

            },"线程一").start();
        }

//        Thread.sleep(3000);

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println("十个线程分别加1000次结果（非原子）"+myData.i);
        System.out.println("#############");
        System.out.println("十个线程分别加1000次结果(原子类)"+myData.atomicInteger.get());
        // 结果
        /**
         * 十个线程分别加1000次结果（非原子）9904
         #############
         十个线程分别加1000次结果(原子类)10000
         */
    }

}

/**
 * 在没有加volatile之前子线程修改了资源中的值，主线程没有拿到，会一直死循环
 * 加了volatile之后，在子线程修改完之后会立马通知主线程，强制刷新，循环结束
 */
class MyData{
    volatile int i =0;
    //    int i= 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    void addData() {
        this.i=i+19;
    }

    // 验证不保证原子性
    void addOne() {
        i++;
        atomicInteger.getAndIncrement();
    }
}