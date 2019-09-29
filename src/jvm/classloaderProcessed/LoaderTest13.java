package jvm.classloaderProcessed;

/**
 * @author: wyj
 * @date: 2019/9/28
 * @description:
 */

import org.junit.Test;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 线程上下文类加载器一般使用方式，（获取-使用-还原）
 */
public class LoaderTest13 {
    public static void main(String[] args){

        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("当前上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的加载器："+ServiceLoader.class.getClassLoader());
        /**
         * 执行结果：
         * "C:\Program Files\Java\jdk1.8.0_162\bin\java" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2017.3.5\lib\idea_rt.jar=30501:D:\Program Files\JetBrains\IntelliJ IDEA 2017.3.5\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_162\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\rt.jar;F:\gitPro\java_study\out\production\java_study;F:\gitPro\java_study\lib\mysql-connector-java-5.1.44.jar;F:\gitPro\java_study\lib\junit-4.8.2.jar" jvm.classloaderProcessed.LoaderTest13
         driver:class com.mysql.jdbc.Driver,loader:sun.misc.Launcher$AppClassLoader@18b4aac2
         driver:class com.mysql.fabric.jdbc.FabricMySQLDriver,loader:sun.misc.Launcher$AppClassLoader@18b4aac2
         当前上下文类加载器：sun.misc.Launcher$AppClassLoader@18b4aac2
         ServiceLoader的加载器：null
         */

    }

    @Test
    public void test001() {
        // 手动设置当前线程的上下文类加载器，由于相应的classpath下面没有具体的实现，所以无法加载Driver的具体实现
        Thread.currentThread().setContextClassLoader(LoaderTest13.class.getClassLoader().getParent());
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("当前上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的加载器："+ServiceLoader.class.getClassLoader());

        /**
         * 执行结果：
         * 当前上下文类加载器：sun.misc.Launcher$ExtClassLoader@a09ee92
         ServiceLoader的加载器：null

         */
    }

}