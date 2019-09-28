package jvm.classloaderProcessed;

/**
 * @author: wyj
 * @date: 2019/9/28
 * @description:
 */

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 线程上下文类加载器一般使用方式，（获取-使用-还原）
 */
public class LoaderTest13 {
    public static void main(String[] args){

        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("当前上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的加载器："+ServiceLoader.class.getClassLoader());

    }

}