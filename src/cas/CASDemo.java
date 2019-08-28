package cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: wyj
 * @Date: 2019/8/23
 * @Description:
 */
public class CASDemo {
        public static void main(String[] args) {
            AtomicInteger atomicInteger = new AtomicInteger(1);

            //
//            System.out.println(atomicInteger.compareAndSet(1,2019) + "\t currentdata: "+ atomicInteger.get());
//
//            System.out.println(atomicInteger.compareAndSet(1,2019) + "\t currentdata: "+ atomicInteger.get());

            new Thread(()->{
                System.out.println(atomicInteger.compareAndSet(1,2019) + "\t currentdata: "+ atomicInteger.get());
                System.out.println(atomicInteger.compareAndSet(2019,1) + "\t currentdata: "+ atomicInteger.get());
            },"线程1").start();

            new Thread(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicInteger.compareAndSet(1,2) + "\t currentdata: "+ atomicInteger.get());
            },"线程2").start();

            /**
             * 执行结果
             * true	 currentdata: 2019
             true	 currentdata: 1
             true	 currentdata: 2
             出现了aba问题
             */
        }
}