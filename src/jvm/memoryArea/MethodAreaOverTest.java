package jvm.memoryArea;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author: wyj
 * @date: 2019/11/19
 * @description:方法区溢出
 */
//  -XX:MaxMetaspaceSize=10m      (jdk1.8 Metaspace)
public class MethodAreaOverTest {
    // 不断的生成类，进行类加载，类放在元空间，metaSpace会溢出；


    public static void main(String[] args){

        for (;;){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MethodAreaOverTest.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor)(obj, method, args1, proxy)-> proxy.invokeSuper(obj, args1));
            System.out.println("hello wyj");
            enhancer.create();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
         at java.lang.Class.forName0(Native Method)
         at java.lang.Class.forName(Class.java:348)
         at net.sf.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:386)
         at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:219)
         at net.sf.cglib.proxy.Enhancer.createHelper(Enhancer.java:377)
         at net.sf.cglib.proxy.Enhancer.create(Enhancer.java:285)
         at jvm.memoryArea.MethodAreaOverTest.main(MethodAreaOverTest.java:23)
         */
    }
}