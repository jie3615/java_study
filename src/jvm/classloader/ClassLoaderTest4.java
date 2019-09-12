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
        System.out.println(myParent5.getClass());
        /**
         * class [Ljvm.classloader.MyParent4;
           在运行期生成的的数组类型，并没有产生任何MyParent4的类对象
         */
    }

    @Test
    public void test003() {
        // 创建对象数组
        MyParent4 [][] myParent5 = new MyParent4[1][2];
        /**
         * 结果：没有任何输出结果
         *
         */
        System.out.println(myParent5.getClass());
        /**
         * class [[Ljvm.classloader.MyParent4;
         在运行期生成的的数组类型，并没有产生任何MyParent4的类对象
         */
        System.out.println(myParent5.getClass().getSuperclass());
    }

    @Test
    public void test004() {
        int[] j = new int[5];
        // 创建对象数组
        int [][] i = new int [1][2];

        System.out.println(i.getClass());
        /**
         对于数组类型来说，其类型是由虚拟机运行时期动态生成的
         * 动态生成的类型其父类型就是Object类，
         * 对于数组来说，javaDoc经常讲构成数组的元素为Component，实际上就是将数组降低一个维度后的类型
         */
        System.out.println(i.getClass().getSuperclass());
    }
}

class MyParent4{
    static {
        System.out.println("MyParent4 静态代码块执行");
    }

}