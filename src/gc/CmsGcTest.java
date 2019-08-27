package gc;

/**
 * @Auther: wyj
 * @Date: 2019/8/26
 * @Description:
 */

/**
 * 程序配套的JVM参数：
 -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx20m -Xms20m -Xmn10m -XX:PretenureSizeThreshold=2M -XX:+UseConcMarkSweepGC -XX:+UseParNewGC  -XX:CMSInitiatingOccupancyFraction=60 -XX:+UseCMSInitiatingOccupancyOnly
 说明：这样配置后，Eden区8M，S0/S1区各1M，old区10M，且当Old区占用60%就达到触发CMS GC的条件；

 */
public class CmsGcTest {
    private static final int _1M = 1*1024*1024;
    private static final int _2M = 2*1024*1024;

    public static void main(String[] args) {
        ygc(3);
        cmsGc(1);
        // 在这里想怎么触发GC就怎么调用ygc()和cmsGc()两个方法
    }

    /**
     * @param n 预期发生n次young gc
     */
    private static void ygc(int n){
        for (int i=0; i<n; i++){
            // 由于Eden区设置为8M, 所以分配8个1M就会导致一次YoungGC
            for(int j=0; j<8; j++){
                byte[] tmp = new byte[_1M];
            }
        }
    }

    /**
     * @param n 预期发生n次CMS gc
     */
    private static void cmsGc(int n){
        for (int i=0; i<n; i++){
            for(int j=0; j<3; j++) {
                // 由于设置了-XX:PretenureSizeThreshold=2M, 所以分配的2M对象不会在Eden区分配而是直接在Old区分配
                byte[] tmp = new byte[_2M];
            }
            try {
                // sleep10秒是为了让CMS GC线程能够有足够的时间检测到Old区达到了触发CMS GC的条件并完成CMS GC, CMS GC线程默认2s扫描一次，可以通过参数CMSWaitDuration配置，例如-XX:CMSWaitDuration=3000
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}