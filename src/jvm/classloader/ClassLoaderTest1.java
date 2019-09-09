package jvm.classloader;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/9/9
 * @description:
 */
public class ClassLoaderTest1 {
    public static void main(String[] args){

        /**
         * 子类主动get父类静态属性
         */
        System.out.println(MyChild1.str);
        /**
         * 结果
         * MyParent1 静态代码块执行
           hello world
         ############
         * 为什么子类的静态代码块没有执行，父类的执行了？
         * 对于静态字段来说，只有直接定义了这个字段的类才会初始化。这是对MyParent1的主动使用，并没有主动使用Mychild1。
         *
         * 那么Mychild 有没有被加载呢？
         * 可以通过XX:+TraceClassLoading 追踪类加载信息
         *  ....
         *  [Loaded jvm.classloader.ClassLoaderTest from file:/F:/gitPro/java_study/out/production/java_study/]
         *  .......
         *  [Loaded jvm.classloader.MyParent1 from file:/F:/gitPro/java_study/out/production/java_study/]
             [Loaded jvm.classloader.MyChild1 from file:/F:/gitPro/java_study/out/production/java_study/]
             MyParent1 静态代码块执行
             hello world
          .......
         *
         *  可以看出即使MyChild1没有初始化也是先加载了的
         */
    }

    @Test
    public void test001() {
        System.out.println(MyChild1.str2);
        /**
         * 结果
         * MyParent1 静态代码块执行
         MyChild1 静态代码块执行
         hello world child
         #############
         分析:子类定义的静态变量，子类的静态代码块肯定会执行。子类进行初始化，在子类初始化之前首先对所有父类进行初始化。

         */
    }

}

 class  MyParent1{
    //静态成员变量
     public static String str = "hello world";

     static {
         System.out.println("MyParent1 静态代码块执行");
     }
 }
 class MyChild1 extends  MyParent1{
     //静态成员变量
     public static String str2 = "hello world child";
     static {
         System.out.println("MyChild1 静态代码块执行");
     }
 }