package thread;

import sun.instrument.InstrumentationImpl;

/**
 * @Auther: wyj
 * @Date: 2019/8/27
 * @Description:指令重排序
 */
public class InstructionReSortDemo {
    int a = 0;
    boolean flag = false;

    public void m1() {
        a = 1;
        flag = true;
    }

    public void m2() {
        if (flag) {
            a = a + 5;
            System.out.println("a = " + a);
        }
    }

    public static void main(String[] args) {

        InstructionReSortDemo instructionReSortDemo = new InstructionReSortDemo();


        new Thread(() -> {
            instructionReSortDemo.m1();
        }).start();
        new Thread(() -> {
            instructionReSortDemo.m2();
        }).start();

    }
}

/**
 * @description:另一个指令重排序案例 循环次数足够多，可能会发生重排序现象，如果未发生重排序的情况下，肯定先给a,b赋值，随后x,y 不为0
 **/
class ResortSeqDemo {
    static int x, y, a, b;

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            ResortSeqDemo resortSeqDemo = new ResortSeqDemo();
            Thread thread1 = new Thread(() -> {
                a = 1;
                x = b;
            }, String.valueOf(i));
            Thread thread2 = new Thread(() -> {
                b = 2;
                y = a;
            }, String.valueOf(i));
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            thread1.start();
            thread2.start();
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (x == 0 && y == 0) {
                System.out.println("指令重排");
            }
        }
    }
}