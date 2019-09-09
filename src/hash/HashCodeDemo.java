package hash;

/**
 * @author: wyj
 * @date: 2019/8/30
 * @description:减少hash碰撞概率，高位参与运算
 */
public class HashCodeDemo {
    public static void main(String[] args){

        String key = "ssssssssss";
        int h;
        System.out.println(key.hashCode());
        h = (h=key.hashCode()) ^ (h >>> 16);
        System.out.println(h);
        int i = h & 15;
        System.out.println(i);

    }
}