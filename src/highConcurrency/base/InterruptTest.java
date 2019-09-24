package highConcurrency.base;

/**
 * @author: wyj
 * @date: 2019/9/24
 * @description:
 */
public class InterruptTest {
    private  static  Thread thread = new Thread(){
        @Override
        public void run() {
            int i =0;
            while (i < 10) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("interrupted");
                    break;
                }
                System.out.println("hello world");
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt(); // 异常信息会清除中断标志，程序会继续运行，想要在异常时候终止运行线程，则需要在异常捕获处理地方再次设置中断标志；
                }
            }


        }
    };
    public static void main(String[] args) throws InterruptedException {
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}