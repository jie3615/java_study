package jvm.classloaderProcessed;

/**
 * @author: wyj
 * @date: 2019/9/18
 * @description:
 */
public class LoaderTest2 {

    public static void main(String[] args){

        System.out.println("测试开始*************************");
        Parent2 parent2;
        System.out.println("************************");
        Parent2 parent21 = new Parent2();
        System.out.println("************************");
        System.out.println(Parent2.a);
        System.out.println("************************");
        System.out.println(Child2.b);
        System.out.println("测试结束**********************");

    }

}

class  Parent2{
    public static int a = 2;
    static {
        System.out.println("Parent2 静态代码块执行");
    }
}

class Child2 extends Parent2{
    public static int b = 3;
    static {
        System.out.println("Child2 静态代码块执行");
    }
}