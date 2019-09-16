package jvm.classloader;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/9/16
 * @description:
 */
public class ClassloaderTest8 {

    @Test
    public void test001() {
        System.out.println(Child8.a);
        /**
         * j结果：8
         */
    }

    @Test
    public void test002() {
        System.out.println(CChild8.b);
        /**
         * 结果：
         * Parent8 内部类
            222
            8
         */
    }

}

interface IParent8 {
public static Thread t = new Thread(){
    {
        System.out.println("IParent8 内部类");
    }
};
}

class Parent8 {
    public static Thread t = new Thread(){
        {
            System.out.println("Parent8 内部类");
        }
    };

    static Child8 s = new Child8(){
        //括号里面重写类方法的或者实现继承的抽象类/接口
        {
            System.out.println(222);
        }
    };
}

class CChild8 extends  Parent8{
    public  static int b =8;
}

class Child8 implements IParent8{
    public  static int a =8;
}
