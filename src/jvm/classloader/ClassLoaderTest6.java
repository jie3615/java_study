package jvm.classloader;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/9/10
 * @description:
 */
public class ClassLoaderTest6 {

    @Test
    public void test001() {
        Singleton singleton = Singleton.getSingleton();
        System.out.println("counter1:"+Singleton.counter1);
        System.out.println("counter2:"+Singleton.counter2);
        /**
         * 结果：counter1:1
                counter2:1
          没有赋初值的counter1在初始化的时候默认设置为0；
         */
    }

    // 把counter2放在构造函数的下面，会有什么变化？
    @Test
    public void test002() {
        Singleton2 singleton = Singleton2.getSingleton();
        System.out.println("counter1:" + Singleton2.counter1);
        System.out.println("counter2:" + Singleton2.counter2);
        /**
         * 执行结果
         * counter1:1
           counter2:0
          分析：counter2变成了0
                调用类的静态方法，主动使用，，要进行初始化；
                初始化之前：准备阶段（赋初始值）>初始化，按照代码顺序（）,再进行复制为0
         */
    }
}

class Singleton {
    public static int counter1;

    public static Singleton singleton = new Singleton();
    public static int counter2 = 0;
    private Singleton() {
        counter1++;
        counter2++;
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}

class Singleton2 {
    public static int counter1=1;

    public static Singleton2 singleton = new Singleton2();
    private Singleton2() {
        counter1++;
        counter2=2;
        System.out.println(counter1);// 进到这个地方为1
        System.out.println(counter2);// 进到这个地方为1
    }
    public static int counter2 = 0;

    public static Singleton2 getSingleton() {
        return singleton;
    }
}