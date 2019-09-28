package jvm.classloaderProcessed;

/**
 * @author: wyj
 * @date: 2019/9/28
 * @description:
 */
public class LoaderTest12 implements Runnable {
    private Thread thread;

    public LoaderTest12() {

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ClassLoader classLoader = this.thread.getContextClassLoader();

        this.thread.setContextClassLoader(classLoader);
        System.out.println("Class:" + classLoader.getClass());
        System.out.println("Class:"+classLoader.getParent().getClass());
    }
    public static void main(String[] args){

        new LoaderTest12();

        /**
         * 结果："C:\Program Files\Java\jdk1.8.0_162\bin\java" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2017.3.5\lib\idea_rt.jar=3233:D:\Program Files\JetBrains\IntelliJ IDEA 2017.3.5\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_162\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_162\jre\lib\rt.jar;F:\gitPro\java_study\out\production\java_study;D:\Program Files\JetBrains\IntelliJ IDEA 2017.3.5\lib\junit-4.12.jar;D:\Program Files\JetBrains\IntelliJ IDEA 2017.3.5\lib\hamcrest-core-1.3.jar" jvm.classloaderProcessed.LoaderTest12
         Class:class sun.misc.Launcher$AppClassLoader
         Class:class sun.misc.Launcher$ExtClassLoader
         *
         */
    }
}