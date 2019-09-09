package jvm.classloader;

import org.junit.Test;

import java.util.UUID;

/**
 * @author: wyj
 * @date: 2019/9/9
 * @description:
 */
public class ClassLoaderTest3 {

    @Test
    public void test001() {
        System.out.println(MyParent3.str);
    }
}

/**
 * 跟上个例子的区别在于str的值是编译器是无法确定下来的。
 * 这个值只有在运行期间才能确定，那么这个类就要进行初始化。。。
 * 如果像上面把MyParent3编译后的类字节码删除会抛出classNotFound 异常
 * 在一个常量在编译期间不能确定的话就不会放在调用类的常量池中,从class文件的对比情况也可以发现
 */
class MyParent3{
    //静态成员变量,编译阶段是无法知道str的值的，怎么办？
    public final static String str = UUID.randomUUID().toString();
    static {
        System.out.println("MyParent3 静态代码块执行");
    }

}