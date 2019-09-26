package jvm.classloaderProcessed;

import com.sun.crypto.provider.AESKeyGenerator;
import org.junit.Test;

/**
 * @author: wyj
 * @date: 2019/9/25
 * @description:
 */
public class LoaderTest8 {
    // 打印三种系统默认类加载的加载路径；
    public static void main(String[] args){

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }

    @Test
    public void test001() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        LoaderTest5 myLoader1 = new LoaderTest5("loader1");
        Class<?> aClass = myLoader1.loadClass("beans.User");
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass.hashCode());
        Object object = aClass.newInstance();

        /**
         * 其中根类加载的路径有C:\Program Files\Java\jdk1.8.0_162\jre\classes
         *  那么把User.class放到上面的目录，是不是就是由根类加载器来加载这个类的？
         *  执行结果：null
                      2027961269
           确实是由根类加载器加载的。。。
         */
    }

    @Test
    public void test002() {

        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
        System.out.println(aesKeyGenerator.getClass().getClassLoader());

        /**
         * 测试扩展类加载器
         * 结果：sun.misc.Launcher$ExtClassLoader@452b3a41
         */
    }
}