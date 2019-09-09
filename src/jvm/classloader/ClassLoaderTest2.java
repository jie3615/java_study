package jvm.classloader;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/9/9
 * @description:
 */
public class ClassLoaderTest2 {

    @Test
    public void test001(){
//        System.out.println(MyParent2.str);
        /**
         * 在静态变量上面加上final 修饰符
         * 执行结果：hello world
         *  并没有对Myparent2进行初始化，为什么？
         *  final :本身表示常量，编译器也知道，所以在编译阶段这个常量就会被存入到调用这个方法所在类的常量池当中；
         *   也就是 "hello world" 在编译阶段就在ClassLoaderTest2中的类的常量池中，本质上并没有引用到定义常量的这个类，因此也不会触发这个类的初始化；那么静态代码块也就不会执行；
         *注意： 在这个字符串在放入到ClassLoaderTest2的常量池中之后就跟MyParent2没有任何关系了，甚至可以删除编译后的MyParent2的字节码文件（在out路径下删除字节码文件依然可以运行）
         *
         *
         */
//        System.out.println(MyParent2.aa);
        /**
         * z字节码 bipush 6
         *  表示将单字节常量推送到栈顶；
         *
         *   bb
         *  超过127
         *  字节码：sipush 1116
         *  表示将短整型常量值推送到栈顶；
         *
         *......
         *
         */
        System.out.println(MyParent2.str);
    }


}

class MyParent2{
    //静态成员变量
    public final static String str = "hello world";
    public final static short aa = 5;
    public final static short bb = 1116;

    public final static int cc = 5;

    static {
        System.out.println("MyParent2 静态代码块执行");
    }


}