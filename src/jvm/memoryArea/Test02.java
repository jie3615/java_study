package jvm.memoryArea;

/**
 * @author: wyj
 * @date: 2019/11/19
 * @description:
 */
// JVM 参数： -Xss108k -XX:+HeapDumpOnOutOfMemoryError
public class Test02 {


   static int len = 0;

    public void test1() {
        len++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test1();
    }
    public static void main(String[] args){

        try {
            new Test02().test1();
        } catch (Throwable e) {
            System.out.println(len);
            e.printStackTrace();
        }

    }
}