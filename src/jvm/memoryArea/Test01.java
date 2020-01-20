package jvm.memoryArea;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wyj
 * @date: 2019/11/19
 * @description:
 */
// JVM 参数-Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError
public class Test01 {
    public static void main(String[] args){

        List<Test01> list = new ArrayList<>();
        while (true) {
            list.add(new Test01());
            System.gc(); // 显式的调用gc，程序一直可以正常运行；堆空间维持在2m,小于2m程序会异常退出；
        }
    }
}