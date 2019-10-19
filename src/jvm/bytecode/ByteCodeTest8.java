package jvm.bytecode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: wyj
 * @date: 2019/10/14
 * @description:字节码层面分析动态代理
 */
public class ByteCodeTest8 {
    public static void main(String[] args){

        /**
         *  generateProxyClass()
         *  private static final boolean saveGeneratedFiles = (Boolean)AccessController.doPrivileged(new GetBooleanAction("sun.misc.ProxyGenerator.saveGeneratedFiles"));
         */
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");//开关，将代理类序列化到磁盘；

        RealSubject realSubject = new RealSubject();
        InvocationHandler ds = new DynamicSubject(realSubject);
        Class<? extends RealSubject> aClass = realSubject.getClass();
        Subject subject = (Subject) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), ds);
        subject.request();
        System.out.println(subject.getClass());
        System.out.println(subject.getClass().getSuperclass());


    }

}
 interface Subject{
     void request();
}
class RealSubject implements Subject{

    @Override
    public void request() {
        System.out.println("from real subject");
    }
}

class DynamicSubject implements InvocationHandler{

    private Object sub;

    public DynamicSubject(Object sub) {
        this.sub = sub;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before calling:"+method);
        method.invoke(this.sub, args);
        System.out.println("after calling:"+method);
        return null;
    }
}