package cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: wyj
 * @Date: 2019/8/23
 * @Description:
 */
public class CASDemo {
        public static void main(String[] args) {
            AtomicInteger atomicInteger = new AtomicInteger(1);

            //
            System.out.println(atomicInteger.compareAndSet(1,2019) + "\t currentdata: "+ atomicInteger.get());

            System.out.println(atomicInteger.compareAndSet(1,2019) + "\t currentdata: "+ atomicInteger.get());
    }
}