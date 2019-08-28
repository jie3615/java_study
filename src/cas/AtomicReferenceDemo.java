package cas;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Auther: wyj
 * @Date: 2019/8/28
 * @Description:
 */
public class AtomicReferenceDemo {

    public static void main(String[] args){
        AtomicReference<User> userAtomicReference = new AtomicReference<>();

        User user1 = new User("wyj", 25);
        User user2 = new User("dnn", 23);
        User user3 = new User("ab", 22);
        User user4 = new User("cd", 21);

        userAtomicReference.set(user1);


        /**
         * AtomicReference依然存在aba问题
         * 解决：AtomticStampedReference
         */
        new Thread(() -> {
            System.out.println(userAtomicReference.compareAndSet(user1, user2) + "******currntData：" + userAtomicReference.get());
            System.out.println(userAtomicReference.compareAndSet(user2, user1) + "******currntData：" + userAtomicReference.get());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程一").start();
        new Thread(() -> {
            System.out.println(userAtomicReference.compareAndSet(user1, user3) + "******currntData：" + userAtomicReference.get());
        }, "线程二").start();

    }
}

class AtomicStampedReferenceDemo {

    public static void main(String[] args){

        User user1 = new User("wyj", 25);
        User user2 = new User("dnn", 23);
        User user3 = new User("ab", 22);
        User user4 = new User("cd", 21);

        AtomicStampedReference<User> userAtomicStampedReference = new AtomicStampedReference<>(user1,1);

        //
        int stamp = userAtomicStampedReference.getStamp();
        System.out.println("第一次版本号:"+stamp);

        /**
         *
         * 解决：AtomticStampedReference
         */
        new Thread(() -> {
            System.out.println(userAtomicStampedReference.compareAndSet(user1, user2,userAtomicStampedReference.getStamp(),userAtomicStampedReference.getStamp()+1) + "******currntData：" + userAtomicStampedReference.getReference());
            System.out.println(userAtomicStampedReference.compareAndSet(user2, user1,userAtomicStampedReference.getStamp(),userAtomicStampedReference.getStamp()+1) + "******currntData：" + userAtomicStampedReference.getReference());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程一").start();
        new Thread(() -> {
            System.out.println(userAtomicStampedReference.compareAndSet(user1, user3,stamp,userAtomicStampedReference.getStamp()+1) + "******currntData：" + userAtomicStampedReference.getReference());
        }, "线程二").start();

        /**
         *
         * 第一次版本号:1
         true******currntData：User{name=dnn, age=23}
         true******currntData：User{name=wyj, age=25}
         false******currntData：User{name=wyj, age=25}
         */
    }
}

class User{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", age=" + age +
                '}';
    }
}