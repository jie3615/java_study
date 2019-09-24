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


        Class<?> clazz = myClassLoader.loadClass("beans.User");

        System.out.println("class :"+clazz.hashCode());
        // 需要有无参构造器
        Object object = clazz.newInstance();
        System.out.println(object);
    }
    }