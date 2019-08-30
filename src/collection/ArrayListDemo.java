package collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @Auther: wyj
 * @Date: 2019/8/28
 * @Description:线程不安全的类
 */
public class ArrayListDemo {

    // 模拟不安全性

    public static void main(String[] args){
//        ArrayList<String> list = new ArrayList();

//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        List<String> list = new Vector<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        /**
         * ArrayList
         * 元素个数不稳定，有时候会报如下异常
         * Exception in thread "1" Exception in thread "3" java.util.ConcurrentModificationException
         *
         */
    }

}