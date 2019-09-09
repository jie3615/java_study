package jvm.classloader;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/9/9
 * @description:
 */
public class ClassLoaderTest4 {

    @Test
    public void test001() {
        MyParent4 myParent4 = new MyParent4();
        MyParent4 myParent2 = new MyParent4();
        /**
         * 结果：MyParent4 静态代码块执行
         * 这是对类的主动使用，静态代码块会执行，并且只会初始化一次
         */
    }
    @Test
    public void test002() {
        // 创建对象数组
        MyParent4 [] myParent5 = new MyParent4[1];
        /**
         * 结果：没有任何输出结果
         *
         */
    }
}

class MyParent4{
    static {
        System.out.println("MyParent4 静态代码块执行");
    }

}