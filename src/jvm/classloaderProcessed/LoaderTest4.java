package jvm.classloaderProcessed;

/**
 * @author: wyj
 * @date: 2019/9/18
 * @description:
 */
public class LoaderTest4 {
    public static void main(String[] args){

        int[] arr = new int[6];
        ClassLoader classLoader = arr.getClass().getClassLoader();
        System.out.println(classLoader);

        String[] strings = new String[6];
        System.out.println(strings.getClass().getClassLoader());

        String[][] strings1 = new String[23][];
        System.out.println(strings1.getClass().getClassLoader());

        /**
         * 以上结果全是null
         * 对于数组类型的类加载器，其类加载器是跟数组元素的类加载器一致的；
         * 对于原生类型 的数组其类加载器也是null，不是启动类加载器而是没有类加载器；
         */
        Parent2[] parent2s = new Parent2[3];
        System.out.println(parent2s.getClass().getClassLoader());
        /**
         * 结果:sun.misc.Launcher$AppClassLoader@18b4aac2
         * 自己定义的类是由系统类加载器加载的；
         */
    }
}