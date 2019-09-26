package jvm.classloaderProcessed;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: wyj
 * @date: 2019/9/25
 * @description:
 */
public class LoaderTest9 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        LoaderTest5 myLoader1 = new LoaderTest5("loader1");
        myLoader1.setPath("E://");
        Class<?> aClass1 = myLoader1.loadClass("beans.MyPerson");

        LoaderTest5 myLoader2 = new LoaderTest5("loader2");
        myLoader2.setPath("E://");
        Class<?> aClass2 = myLoader2.loadClass("beans.MyPerson");


        System.out.println(aClass1 == aClass2);
        Object object1 = aClass1.newInstance();
        Object object2 = aClass2.newInstance();

        Method setMyPerson = aClass1.getMethod("setMyPerson", Object.class);
        setMyPerson.invoke(object1, object2); // object1和object2属于不同的类命名空间的类创建的对象，互相之间不能强转；
        System.out.println(object1);

        /**
         * 分析：
         * 执行结果：
         * findClass invoked beans.MyPerson
         this.classLoaderName : loader1
         findClass invoked beans.MyPerson
         this.classLoaderName : loader2
         false
         Exception in thread "main" java.lang.reflect.InvocationTargetException
         at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
         at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
         at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
         at java.lang.reflect.Method.invoke(Method.java:498)
         at jvm.classloaderProcessed.LoaderTest9.main(LoaderTest9.java:28)
         Caused by: java.lang.ClassCastException: beans.MyPerson cannot be cast to beans.MyPerson
         at beans.MyPerson.setMyPerson(MyPerson.java:12)
         ... 5 more

         结果分析：分别由两个类加载器加载的类创建的对象是不相等的 。类的命名空间不同；
         在通过反射给MyPerson 添加一个属性的时候，由于两个对象分别是不同的类命名空间的类创建的，
         所以是属于不同的类型，会出现类型转换的错误；
         */
    }
}