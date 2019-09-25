package jvm.classloaderProcessed;

import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/9/25
 * @description:
 */
public class LoaderTest7 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        LoaderTest5 myLoader1 = new LoaderTest5("loader1");
        Class<?> aClass = myLoader1.loadClass("beans.MySimple");
        System.out.println(aClass.hashCode());
        Object object = aClass.newInstance();
    }

    @Test
    public void test001() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        LoaderTest5 myLoader1 = new LoaderTest5("loader1");
        myLoader1.setPath("E:\\");
        Class<?> aClass = myLoader1.loadClass("beans.MySimple");
        System.out.println(aClass.hashCode());
        Object object = aClass.newInstance();
        /**
         * 结果分析：子加载器加载的类能够访问到父类加载器加载的类
         *           反过来不能
         */
    }
}