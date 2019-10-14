package jvm.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

/**
 * @author: wyj
 * @date: 2019/10/13
 * @description:
 */

/**
 * 这个操作是在编译期间完成，由javac编译器在编译的时候将对this的访问转化为对一个普通实例方法参数的访问；接下来在运行期间，
 * 由jvm在调用实例方法时，自动向实例方法传入该this参数，所以，在实例方法局部变量表中，至少会有一个指向当前对象的局部变量；
 */
public class ByteCodeTest3 {
    public void test() {
        {
            try {
                InputStream is = new FileInputStream("test.txt");
                ServerSocket serverSocket = new ServerSocket(9999);
                serverSocket.accept();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ex) {

            } finally {
                System.out.println("finally...");
            }
        }
    }
    /**
     * 方法表test 分析
     *public void test();
     descriptor: ()V
     flags: ACC_PUBLIC
     Code:
     stack=3, locals=4, args_size=1  //     local=4 分别是this,is,serverSocket ,还有一个异常对象，虽然捕获了三个，但是只有一个异常会进入，被进入的
                                            异常块会被赋值；包含局部变量的数量，方法传入的参数
                                            stack：操作数栈的最大深度
     0: new           #2                  // class java/io/FileInputStream
     3: dup
     4: ldc           #3                  // String test.txt

     省略... ...
     */
    /**
     * 异常表分析：每个exception_table表由start_pc end_pc handler_pc catch_type构成；
     * start_pc ：指令的起始位置，包含
     * end_pc：指令的结束位置，不包含这个位置
     * handler_pc：处理异常逻辑的起始位置
     * catch_type：指向常量池中异常类型

     * Exception table:
     from    to  target type
     0    26    37   Class java/io/FileNotFoundException ：0-25出现了异常的话，跳到37字节码指令，astore_1：把异常对象赋值给ex局部变量 catch执行完毕后进入
     0    26    53   Class java/io/IOException
     0    26    69   Class java/lang/Exception
     0    26    81   any
     37    42    81   any
     53    58    81   any

     当异常处理存在finally语句块时候，现代化的jvm采取的处理方式是将finally语句块的字节码拼接到每一个catch块后面；
     也就是说程序中存在多少个catch块，就会在每一个块后面存在多少个finally块的字节码；
     如下字节码
     采用了一种重复的方式，减少了跳转；
     */
    /**行号表
     * LineNumberTable:
      字节码  源代码行号
     line 25: 0
     line 26: 10
     line 27: 21
     line 35: 26
     line 36: 34
     line 28: 37
     line 29: 38
     line 35: 42
     省    略... ...

     *
     */

    /**
     * 局部变量表：
     * LocalVariableTable:
     Start  Length  Slot  Name   Signature
     10      16     1    is   Ljava/io/LInputStream;
     21       5     2 serverSocket   Ljava/net/ServerSocket;
     38       4     1     e   Ljava/io/FileNotFoundException;
     54       4     1     e   Ljava/io/IOException;
     0      93     0  this   Ljvm/bytecode/ByteCodeTest3;

     solt :是可以复用的，
     */

    /**
     * 字节码指令分析test
     *
      0 new #2 <java/io/FileInputStream> 创建对象
     3 dup 复制操作数栈的最顶层的值压入栈
     4 ldc #3 <test.txt>
     6 invokespecial #4 <java/io/FileInputStream.<init>> 调用一个实例方法，父类的构造方法
     9 astore_1 将引用存储到局部变量中
     之前五行字节码对应 源代码InputStream is = new FileInputStream("test.txt");
     10 new #5 <java/net/ServerSocket>
     13 dup
     14 sipush 9999 将一个short类型
     17 invokespecial #6 <java/net/ServerSocket.<init>>
     20 astore_2
     21 aload_2
     22 invokevirtual #7 <java/net/ServerSocket.accept>
     25 pop 弹出操作数栈最顶层的值
     26 getstatic #8 <java/lang/System.out>
     29 ldc #9 <finally...>
     31 invokevirtual #10 <java/io/PrintStream.println> 调用Java的虚方法
     34 goto 92 (+58) 通过goto实现异常的跳转
     37 astore_1
     38 aload_1
     39 invokevirtual #12 <java/io/FileNotFoundException.printStackTrace>
     42 getstatic #8 <java/lang/System.out>
     45 ldc #9 <finally...>
     47 invokevirtual #10 <java/io/PrintStream.println>
     50 goto 92 (+42)
     53 astfinallyore_1
     54 aload_1
     55 invokevirtual #14 <java/io/IOException.printStackTrace>
     58 getstatic #8 <java/lang/System.out>
     61 ldc #9 <finally...>
     63 invokevirtual #10 <java/io/PrintStream.println>
     66 goto 92 (+26)
     69 astore_1
     70 getstatic #8 <java/lang/System.out>
     73 ldc #9 <finally...>
     75 invokevirtual #10 <java/io/PrintStream.println>
     78 goto 92 (+14)
     81 astore_3
     82 getstatic #8 <java/lang/System.out>
     85 ldc #9 <finally...>
     87 invokevirtual #10 <java/io/PrintStream.println>
     90 aload_3
     91 athrow
     92 return
     */

}