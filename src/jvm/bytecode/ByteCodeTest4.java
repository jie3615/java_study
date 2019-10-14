package jvm.bytecode;

/**
 * @author: wyj
 * @date: 2019/10/13
 * @description:
 */

import org.junit.Test;

/**
 * 栈帧：stack Frame
 * 帮助虚拟机来进行方法调用和执行的一种数据结构；
 * 归属于某一个线程
 * 本身是一个数据结构；封装了方法的局部变量表，动态连接，返回地址，操作数栈；
 *
 *  动态连接：A B两个类的调用关系，
 *
 *  两种引用：
 *  符号引用：在常量池中A调用了B类的方法，那么A的常量池中保存了B的类的全限定名；存在常量池中；
 *  直接引用：在具体的方法调用的时候，会将符号引用转话为直接引用，内存的地址，可以直接调用到具体的方法；
 *
 * 有些符号引用在类加载的时候或者第一次使用的时候就转化成直接引用了（静态解析），有些在运行期间才转化成直接引用-动态链接（多态的体现）
 *
 *
 * Animal a= new Cat();
 * a.sleep();
 * a = new Dog();
 * a.sleep();
 * a = new Tigger();
 * a.sleep();
 *
 * 字节码指令都是调用的父类的invokeVirtual
 * 每一次调用都是会找到真正使用的实现类的方法
 *
 * 五种指令调用：
 * invokeinterface:调用接口中的方法，在运行期决定的，决定到底调用实现该接口的哪个对象的该方法；
 * invokestatic :调用静态方法；
 * invokespecial:调用自己的私有方法，构造方法<init>以及父类方法；
 * invokevirtual:调用虚方法，运行期间动态查找。
 * invokedynamic:动态调用方法；
 *
 */
public class ByteCodeTest4 {

    public static void test() {
        System.out.println("test invokestatic");
    }

    @Test
    public void test001() {
        test();
    }
    /**
     *
     0 invokestatic #5 <jvm/bytecode/ByteCodeTest4.test>
     3 return
     分析：静态解析，非虚方法，在字节码还没运行就可以确定到底调用哪个对象的方法；
     四种情况
     1、静态方法
     2、父类方法
     3、构造方法
     4、私有方法：私有方法被重写，公有方法可能被覆盖，存在多态性
     */

}