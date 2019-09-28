package jvm.classloaderProcessed;

/**
 * @author: wyj
 * @date: 2019/9/27
 * @description:
 */
public class LoaderTest11 {
    public static void main(String[] args){

        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.class.getClassLoader());
        /**
         * 执行结果：sun.misc.Launcher$AppClassLoader@18b4aac2
                      null
           线程类线程上下文类加载器；
           当前类加载器就是加载当前类的加载器；
           每个类都会使用自己的类加载器加载这个类所依赖的其他的类
           如果classX引用了classY 那么classX的加载器会尝试加载classY（前提是classY尚未被加载）

          contextClassLoader 从jdk1.2开始引入；
         */

    }

}