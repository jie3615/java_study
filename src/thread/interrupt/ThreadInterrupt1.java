package thread.interrupt;

/**
 * @author: wyj
 * @date: 2019/9/20
 * @description:
 */
public class ThreadInterrupt1 {

   static Thread thread = new Thread(){
        @Override
        public void run() {

            while (true) {
                try {
                    Thread.sleep(3_1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
//                    break;
                }
                System.out.println("23");
            }
        }
    };
    public static void main(String[] args){

        thread.start();
      /*  try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        thread.interrupt();


    }

}