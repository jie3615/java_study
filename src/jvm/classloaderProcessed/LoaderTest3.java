package jvm.classloaderProcessed;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

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
        // 上下文类加载器
        System.out.println(Thread.currentThread().getContextClassLoader());
    }

    // 资源读取
    @Test
    public void test002() throws IOException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        String resource = "jvm/classloaderProcessed/Child2.class";
        Enumeration<URL> resources = contextClassLoader.getResources(resource);
        while (resources.hasMoreElements()) {
            System.out.println(resources.nextElement());
        }
        /**
         * 执行结果
         * file:/F:/gitPro/java_study/out/production/java_study/jvm/classloaderProcessed/Child2.class
         */

    }

    @Test
   public void  test003(){
        // 应用类/系统类加载器加载
        System.out.println(LoaderTest3.class.getClassLoader());
        // 由启动类加载器加载的
        System.out.println(String.class.getClassLoader());
    }
}