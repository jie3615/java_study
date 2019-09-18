package jvm.classloaderProcessed;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/9/18
 * @description:
 */
public class LoaderTest3 {
    public static void main(String[] args){

//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        System.out.println(classLoader);
//        System.out.println(classLoader.getParent());
//        System.out.println(classLoader.getParent().getParent());
//        System.out.println(classLoader.getParent().getParent().getParent());
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        while (classLoader != null) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }

    @Test
    public void test001() {
        // 上下文类加载器给i他ada
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}