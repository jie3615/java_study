package jvm.bytecode;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/10/14
 * @description:动态分派
 */
public class ByteCodeTest6 {


    public static void main(String[] args){
        Fruit apple = new Apple();
        Fruit orange = new Orange();
        apple.test();
        orange.test();
        apple = new Orange();
        apple.test();

        /**
         * 结果分析：
          Apple
         Orange
         Orange

         字节码分析
         0 new #2 <jvm/bytecode/Apple> 开辟内存空间 分配实例变量空间
         3 dup 复制操作数栈顶层的值压入栈
         4 invokespecial #3 <jvm/bytecode/Apple.<init>> 调用Apple的构造方法
         7 astore_1 将引用存储到局部变量中，也就是apple中
         以上是Fruit apple = new Apple();对应的指令
         8 new #4 <jvm/bytecode/Orange>
         11 dup
         12 invokespecial #5 <jvm/bytecode/Orange.<init>>
         15 astore_2
         16 aload_1 局部变量中加载索引为1的变量；
         17 invokevirtual #6 <jvm/bytecode/Fruit.test> //test()方法的调用，到底是apple 还是fruit ?
//         *  invokevertual :动态分派，字节码指令，多态
//         *  执行流程：
//         *  找到操作数栈顶的第一个元素所指向的哪个对象的实际的类型：apple
//         *  在apple中找到与test方法一样的方法，那么就执行（确定一个方法的方式:方法名+方法的描述符（方法的参数和返回值））
//         *  找不到就去父类查找；
         20 aload_2
         21 invokevirtual #6 <jvm/bytecode/Fruit.test>
         24 new #4 <jvm/bytecode/Orange>
         27 dup
         28 invokespecial #5 <jvm/bytecode/Orange.<init>>
         31 astore_1
         32 aload_1
         33 invokevirtual #6 <jvm/bytecode/Fruit.test>
         36 return

         */

    }


    @Test
    public void test001() {
        Animal a1= new Animal();
        Animal dog =  new Dog();
        a1.test("haha");
        dog.test(1);
    }
    /**
     * 字节码分析：
     *  0 new #7 <jvm/bytecode/Animal>
     3 dup
     4 invokespecial #8 <jvm/bytecode/Animal.<init>>
     7 astore_1
     8 new #9 <jvm/bytecode/Dog>
     11 dup
     12 invokespecial #10 <jvm/bytecode/Dog.<init>>
     15 astore_2
     16 aload_1
     17 ldc #11 <haha>
     19 invokevirtual #12 <jvm/bytecode/Animal.test> //
     22 aload_2
     23 iconst_1
     24 invokestatic #13 <java/lang/Integer.valueOf>
     27 invokevirtual #14 <jvm/bytecode/Animal.test> // 会找到具体的子类#14
     30 return

     /**
     */
}
class Fruit{
    public void test() {
        System.out.println("Fruit");
    }
}
class Apple extends Fruit {
    @Override
    public void test() {
        System.out.println("Apple");
    }
}

class Orange extends Fruit{
    @Override
    public void test() {
        System.out.println("Orange");
    }
}

class Animal{

    public void test(String string) {
        System.out.println("animal string");
    }

    public void test(Integer integer) {
        System.out.println("animal integer");
    }
}

class Dog extends Animal{
    @Override
    public void test(String string) {
        System.out.println("dog string");
    }

    @Override
    public void test(Integer integer) {
        System.out.println("dog integer");
    }
}


