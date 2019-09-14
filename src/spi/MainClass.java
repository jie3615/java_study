package spi;

import java.util.ServiceLoader;

/**
 * @author: wyj
 * @date: 2019/9/14
 * @description:
 */
public class MainClass {
//    private static ServiceLoader<IParseDoc> iParseDocs = ServiceLoader.load(IParseDoc.class);

    public static void main(String[] args){

        ServiceLoader<IParseDoc> iParseDocs =  ServiceLoader.load(IParseDoc.class);
        for (IParseDoc iParseDoc : iParseDocs) {
            iParseDoc.parse();
        }
    }
}