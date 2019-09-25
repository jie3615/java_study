package beans;

/**
 * @author: wyj
 * @date: 2019/9/25
 * @description:
 */
public class MySimple {
    public MySimple() {
        System.out.println("MySimple is load by " + this.getClass().getClassLoader());
        new Mycat();
        System.out.println("from MySimple:"+Mycat.class);
    }

}