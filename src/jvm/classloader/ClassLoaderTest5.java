package jvm.classloader;

import org.junit.Test;

import java.util.UUID;

/**
 * @author: wyj
 * @date: 2019/9/9
 * @description:
 */
public class ClassLoaderTest5{

    @Test
    public void test001() {
        System.out.println(Mychild5.b);
        /**
         * 当一个接口在初始化的时候并不要求其父类也完成初始化
         * 验证：可以删除MyParent5对应的class文件
         *
         *   把MyChild5的class 文件删除，也能运行？？为什么？
         *   也就是说在编译后MyChild5 的变量已经放在了test001对应的类的常量池中，也就是说属性默认也是final的；
         *
         *   换成动态获取的呢，编译期间无法确定值》
         */
    }
    @Test
    public void test002() {
        System.out.println(Mychild5.s);
        /**
         *  在删除了子类对应的class 文件之后会抛出异常
           java.lang.NoClassDefFoundError: jvm/classloader/Mychild5
           说明在运行期才可以确定；
         */


    }

 }
 interface  MyParent5{
    // 接口中的字段都是public static 的
     int a = 5;
 }

 interface   Mychild5 extends  MyParent5{
    int b = 3;
    String s  = UUID.randomUUID().toString();
 }