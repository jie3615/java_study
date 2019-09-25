package beans;

/**
 * @author: wyj
 * @date: 2019/9/25
 * @description:
 */
public class Mycat {
    public Mycat() {
        System.out.println("Mycat is load by"+this.getClass().getClassLoader());
//        System.out.println("form Mycat:"+MySimple.class);
    }
}