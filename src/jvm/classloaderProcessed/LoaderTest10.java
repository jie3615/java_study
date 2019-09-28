package jvm.classloaderProcessed;


import beans.MyPerson;
import org.junit.Test;
import sun.misc.Launcher;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author: wyj
 * @date: 2019/9/26
 * @description:
 */
public class LoaderTest10 {



    static {
        System.out.println("LoaderTest10 static block...");
    }

    public static void main(String[] args){
        System.setProperty("java.ext.dirs","jvm.classloaderProcessed");

        System.out.println("Bootstrap 加载路径：");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }

        System.out.println("ExtClassLoader 加载路径：");
        URLClassLoader urlClassLoader = (URLClassLoader)ClassLoader.getSystemClassLoader().getParent();
        System.out.println(urlClassLoader);
        for (URL url : urlClassLoader.getURLs()) {
            System.out.println(url);
        }

        System.out.println("SystemClassLoader 加载路径：");
        URLClassLoader systemClassLoader =(URLClassLoader) ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        for (URL url : systemClassLoader.getURLs()) {
            System.out.println(url);
        }

        System.out.println(LoaderTest10.class.getClassLoader());

        System.out.println(MyPerson.class.getClassLoader().hashCode());
    }


     @Test
    public void test001() {
        System.out.println(Launcher.class.getClassLoader());
    }
}