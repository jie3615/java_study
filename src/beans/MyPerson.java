package beans;

/**
 * @author: wyj
 * @date: 2019/9/25
 * @description:
 */
public class MyPerson {
    private MyPerson myPerson;

    public void setMyPerson(Object object) {
        this.myPerson =(MyPerson) object;
    }

    @Override
    public String toString() {
        return "MyPerson{" +
                "myPerson=" + myPerson +
                '}';
    }
}