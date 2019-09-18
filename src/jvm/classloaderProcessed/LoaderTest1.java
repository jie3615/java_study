package jvm.classloaderProcessed;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/9/17
 * @description:
 */
public class LoaderTest1 {
    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> aClass = Class.forName("java.lang.String");
        ClassLoader classLoader = aClass.getClassLoader();
        System.out.println(classLoader);
    }

    @Test
    public void test001() {
        try {
            Class<?> aClass = Class.forName("jvm.classloaderProcessed.Wyj");
            ClassLoader classLoader = aClass.getClassLoader();
            System.out.println(classLoader);
            /**
             * 结果：sun.misc.Launcher$AppClassLoader@18b4aac2
             *  内部类AppClassLoader
             */
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test002() throws ClassNotFoundException {

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> aClass1 = systemClassLoader.loadClass("jvm.classloaderProcessed.Wyj");
        System.out.println(aClass1);

        // 以上不会导致类的初始化，并不是对类的主动使用，下面则是

        System.out.println("*****************");
        try {
            Class<?> aClass = Class.forName("jvm.classloaderProcessed.Wyj");
            ClassLoader classLoader = aClass.getClassLoader();
            System.out.println(classLoader);
            /**
             * 结果：sun.misc.Launcher$AppClassLoader@18b4aac2
             *  内部类AppClassLoader
             */
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}


class  Wyj{

    static {
        System.out.println("Wyj 初始化");

    }
}