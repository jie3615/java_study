package jvm.bytecode;
/**
 * @author: wyj
 * @date: 2019/10/11
 * @description:
 */
public class ByteCodeTest2 {
    String str = "welcome";
    private int x = 5;
    public static Integer in = 10;
    public static void main(String[] args){
        ByteCodeTest2 byteCodeTest2 = new ByteCodeTest2();
        byteCodeTest2.setX(8);
        in=20;

    }

    public ByteCodeTest2() {
    }

    public ByteCodeTest2(String str) {
        this.str = str;
    }

    private synchronized void setX(int x){
        this.x=x;
    }

    private void test(String str){
        synchronized (this.str){
            System.out.println("hello world");
        }
    }

    private synchronized static void test2() {

    }


}