package jvm.classloaderProcessed;

/**
 * @author: wyj
 * @date: 2019/9/18
 * @description:
 */
public class FinalTest {
        public static  final int x= 3;
//    public static final int x = new Random().nextInt();
    static {
        // 类初始化会执行
        System.out.println("FinalTest 静态代码块执行");
    }
}
class MainTest{
    public static void main(String[] args){

        System.out.println(FinalTest.x);
        /**
         * 查看指令
         * 0 getstatic #2 <java/lang/System.out>
         3 iconst_3
         4 invokevirtual #4 <java/io/PrintStream.println>
         7 return

         可以看到main函数中在编译阶段就确定常量3了；
         */
    }
}
