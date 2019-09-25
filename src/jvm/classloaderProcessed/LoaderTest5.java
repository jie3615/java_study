package jvm.classloaderProcessed;

import java.io.*;

/**
 * @author: wyj
 * @date: 2019/9/21
 * @description:
 */
public class LoaderTest5 extends ClassLoader {

    private String classLoaderName;
    private final String fileExtension = ".class";
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public LoaderTest5(String classLoaderName){
        super();//使用系统类加载器作为当前类的父类委托加载器
        this.classLoaderName = classLoaderName;
    }

    public LoaderTest5(ClassLoader classLoader,String classLoaderName){
        super(classLoader);//使用自定义的类加载器作为当前类的父类委托加载器
        this.classLoaderName = classLoaderName;
    }

    private byte[] loadClassData(String name ){
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        byte [] bytes = null;

        try{
            //this.classLoaderName = this.classLoaderName.replace(".","\\");
            name = name.replace(".","\\");//将包名里边的"."替换为路径分隔符
            inputStream = new FileInputStream(new File(this.path + name + this.fileExtension));//文件来自于加载路径path下的包里边的class文件
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 !=(ch = inputStream.read())){
                baos.write(ch);
            }
            bytes = baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
                baos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("findClass invoked "+className);
        System.out.println(" this.classLoaderName : "+ this.classLoaderName);
        byte [] data = loadClassData(className);//中间调用子类的findClass方法
        return defineClass(className,data,0,data.length);
    }

    public static void main(String[] args) throws Exception {
        LoaderTest5 myClassLoader = new LoaderTest5("myClassLoader");
        myClassLoader.setPath("E:\\");


       /* Class<?> clazz = myClassLoader.loadClass("beans.User");

        System.out.println("class :"+clazz.hashCode());
        // 需要有无参构造器
        Object object = clazz.newInstance();
        System.out.println(object.getClass().getClassLoader());
        System.out.println(object);*/

        System.out.println("###############################");
        LoaderTest5 myClassLoader2 = new LoaderTest5(myClassLoader,"myClassLoader2"); // 传入父classLoader 会由父类加载
        myClassLoader2.setPath("E:\\");

        Class<?> clazz2 = myClassLoader2.loadClass("beans.User");
        System.out.println("class :"+clazz2.hashCode());
        // 需要有无参构造器
        Object object2 = clazz2.newInstance();
        System.out.println(object2.getClass().getClassLoader());
        System.out.println(object2);

        System.out.println("#########################");
        LoaderTest5 myClassLoader3 = new LoaderTest5( myClassLoader2,"myClassLoader3"); // 传入父classLoader 会由父类加载
        myClassLoader3.setPath("E:\\");

        Class<?> clazz3 = myClassLoader3.loadClass("beans.User");

        System.out.println("class :"+clazz3.hashCode());
        // 需要有无参构造器
        Object object3 = clazz3.newInstance();
        System.out.println(object3.getClass().getClassLoader());
        System.out.println(object3);
        /**
         * 结果：###############################
         findClass invoked beans.User
         this.classLoaderName : myClassLoader
         class :1173230247
         jvm.classloaderProcessed.LoaderTest5@6d6f6e28
         User{name='null', age=0}

         Process finished with exit code 0
         */
    }

    }