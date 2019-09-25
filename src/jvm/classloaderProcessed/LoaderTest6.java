package jvm.classloaderProcessed;

/**
 * @author: wyj
 * @date: 2019/9/25
 * @description:
 */
public class LoaderTest6 {

    // 演示类的卸载 自定义的类加载器加载的类可以被卸载；
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {

        LoaderTest5 myClassLoader = new LoaderTest5("myClassLoader");
        myClassLoader.setPath("E:\\");


        Class<?> clazz = myClassLoader.loadClass("beans.User");

        System.out.println("class :"+clazz.hashCode());
        // 需要有无参构造器
        Object object = clazz.newInstance();
        System.out.println(object.getClass().getClassLoader());
        System.out.println(object);
        System.out.println(object.hashCode());

        System.out.println();
        myClassLoader = null;
        clazz = null;
        object  = null;
        System.gc();// 加上-XX:+TraceClassUnloading 会打印出类的卸载信息，[Unloading class beans.User 0x00000007c0061028]

        Thread.sleep(100000);

        myClassLoader = new LoaderTest5("myClassLoader");
        myClassLoader.setPath("E:\\");


        clazz = myClassLoader.loadClass("beans.User");

        System.out.println("class :"+clazz.hashCode());
        // 需要有无参构造器
         object = clazz.newInstance();
        System.out.println(object.getClass().getClassLoader());
        System.out.println(object);
        System.out.println(object.hashCode());


    }
}