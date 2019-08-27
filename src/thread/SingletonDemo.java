package thread;

/**
 * @Auther: wyj
 * @Date: 2019/8/27
 * @Description:单例模式在多线程环境下的问题
 */
public class SingletonDemo {

    private volatile   static SingletonDemo singletonDemo;
    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName()+"###执行单例构造方法");

    }

    public  static SingletonDemo getInstance() {
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo == null) {

                    singletonDemo = new SingletonDemo();
                }
            }
        }
            return singletonDemo;
    }

}


class SingletonTest{
    /**
     * 单线程环境下没问题
     * @param args
     */
    /*public static void main(String[] args){
        // 构造方法私有，无法手动创建实例
        // SingletonDemo singletonDemo = new SingletonDemo();
        SingletonDemo instance = SingletonDemo.getInstance();
        System.out.println(instance);
        System.out.println("########");
        SingletonDemo instance1 = SingletonDemo.getInstance();
        System.out.println(instance1==instance);
    }*/

    public static void main(String[] args){

        for (int i = 0; i < 10; i++) {
            new Thread(()->{

                    SingletonDemo instance = SingletonDemo.getInstance();

            }).start();
        }
        /**
         * Thread-1###执行单例构造方法
         Thread-0###执行单例构造方法
         Thread-2###执行单例构造方法
         Thread-6###执行单例构造方法
         Thread-5###执行单例构造方法
         Thread-4###执行单例构造方法
         Thread-3###执行单例构造方法
         Thread-8###执行单例构造方法
         Thread-7###执行单例构造方法
           原因：多线程下无可见性，出现变量副本无法同步的情况
           解决：加锁
         加锁之后仍然存在私有构造方法执行多次的情况（多线程情况下），此时是由于锁的粒度比较小，在判断instance == null 的时候多个线程满足条件，其中某些没获取到锁，blocked后重新执行的情况
         此时，应该通过双端检查来规避

         仍然存在问题，返回的instace 可能==null ，因为对象创建的过程可能发生指令重排序
         创建一个对象的主要过程有 1、分配内存空间 2、初始化对象数据 3、将刚分配的地址赋给对象的instance;
         在重排序之后可能会一个线程先把空的对象赋值，然后另一个线程返回空对象(地址不为空，但是内容为空)
         解决：加volatile
         */
    }
}