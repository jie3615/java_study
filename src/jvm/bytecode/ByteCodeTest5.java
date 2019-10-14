package jvm.bytecode;

/**
 * @author: wyj
 * @date: 2019/10/14
 * @description: 静态分派
 */
public class ByteCodeTest5 {

    // 方法重载，根据参数类型的不同
    public void test(Grandpa grandpa) {
        System.out.println("grandpa");
    }
    public void test(Father father) {
        System.out.println("father");
    }
    public void test(Son son) {
        System.out.println("son");
    }

    public static void main(String[] args){

        Grandpa g1 = new Father();
        Grandpa g2 = new Son();
        ByteCodeTest5 byteCodeTest5 = new ByteCodeTest5();
        byteCodeTest5.test(g1);
        byteCodeTest5.test(g2);

        /**
         * 结果分析
             grandpa
             grandpa

         方法的静态分派：g1的类型(静态类型是Grandpa,实际指向的类型是Father)
         变量的静态类型是无法发生改变的，实际指向的类型会发生改变；（多态）
         实际类型是在运行期才能确定的；

         注意：
         方法的重载是一种的静态的行为，是根据方法的声明来确定的；重写是动态性的；
         重载是在编译期间就可以完全确定的；


         以上三种方式是通过方法的重载的实现的，也就是会通过静态类型来匹配（静态分派）

         */
    }


}

    class Grandpa{

    }
    class Father extends Grandpa{

    }
    class Son extends Father{

    }