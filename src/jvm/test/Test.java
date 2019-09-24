package jvm.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author: wyj
 * @date: 2019/9/23
 * @description:
 */
public class Test {

    public static void main(String[] args) throws IOException {

        String str = "wyj";
        ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes("utf-8"));

        byte[] bytes = new byte[str.getBytes("utf-8").length];
        bis.read(bytes);
        for(byte b :bytes){
            System.out.print(b+",");
        }
    }
}