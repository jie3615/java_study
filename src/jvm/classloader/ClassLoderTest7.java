package jvm.classloader;

import org.junit.Test;

import java.util.Random;

/**
 * @author: wyj
 * @date: 2019/9/11
 * @description:
 */
public class ClassLoderTest7 {

    public static void main(String[] args){

        System.out.println(MyChild7.b);
    }

    @Test
    public void test001() {
        System.out.println(MyChild8.bb);
        MyChild8 myChild8 = new MyChild8();
//        System.out.println(m);
    }

}


interface MyParent7{
    // 接口中的变量默认是public static final 的；会放在调用类的常量池中.
    int a = 5;
    int aa = 0;
}

interface MyChild7 extends MyParent7{
    int b = new Random(1).nextInt();
}
class MyParent8{
   static  {
        System.out.println("MyParent8 init");
    }
}
class MyChild8 extends MyParent8{
    public MyChild8() {
    }

    {
        System.out.println("hello child8");

    }
    static {
        System.out.println("Mychild8 init");
    }
    static int bb;
}