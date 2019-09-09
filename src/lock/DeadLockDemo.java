package lock;

/**
 * @author: wyj
 * @date: 2019/8/30
 * @description:
 */
public class DeadLockDemo {

    public static void main(String[] args){
        ShareResource shareResource = new ShareResource();
         new Thread(()->{
             shareResource.m1();

         },"线程一").start();


        new Thread(()->{
            shareResource.m2();

        },"线程二").start();


    }


}

class ShareResource {

    private String resource1="lockA";
    private String resource2="lockB";


    void m1() {
         int  i=1;
        synchronized (resource1) {
            System.out.println(Thread.currentThread().getName()+"获取resource1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            m2();
        }


    }

    void m2() {
        synchronized (resource2) {
            System.out.println(Thread.currentThread().getName()+"获取resource2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            m1();

        }

    }
}