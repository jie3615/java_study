package jvm.bytecode;

/**
 * @author: wyj
 * @date: 2019/10/14
 * @description:虚方法表
 */

/**
 * 对于方法的调用分派过程》虚拟机会在类的方法区创建一个虚方法表的数据结构；（virtual method table ,vtable）
 * 对于invokeinterface指令来说，虚拟机会建立一个叫做接口方法表的数据结构；(interface menthod table,itable)
 */
// 虚方法表的每一个项都表示每一个方法的具体的入口地址，对于一个子类没有重写任何一个父类的方法，那么子类不会复制一份父类的方法表，而是直接指向父类的方法表；
    // 类加载的连接阶段是 会把符号引用替换为直接引用；

/**
 * jvm 执行指令是通过基于栈的方式：入栈出栈
 * 优势：具有很好的移植性；
 * 基于寄存器的指令集跟硬件紧密联系，不具有移植性；
 *
 *  基于栈的指令集要比基于寄存器的指令集慢的多，寄存器是在高速缓存中完成的，属于cpu的组成部分；
 */
public class ByteCodeTest7 {

    public int calculate() {
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;
        int result = (a + b - c)*d;
       return result;
    }

    public static void main(String[] args){

        ByteCodeTest7 byteCodeTest7 = new ByteCodeTest7();
        System.out.println(byteCodeTest7.calculate());

    }

    /**
     * 字节码分析：基于栈的执行方式
     *  stack=2, locals=6, args_size=1 ：栈的最大深度2，局部变量表大小为6
     0 iconst_1 ：将整型数字1 推送到操作数栈中；
     1 istore_1 ：istore_<n> n：局部变量表中的索引；将操作数栈顶的1弹出来，放到局部变量表的索引为1的位置（0位置是this）
     2 iconst_2 ：将整型数字2推送到栈顶（操作数栈）
     3 istore_2 ：将操作数栈顶的值2弹出来，放入到局部变量表索引为2的位置
     现在操作数栈为空
     4 iconst_3 ：栈顶放入3
     5 istore_3 ：栈顶的3放入到局部变量表索引为3的位置上；
     6 iconst_4 ：栈顶放4
     7 istore 4 ：istore 4 是指令加参数，istore_3 是简写的指令，常用；也是将操作数栈顶的4弹出来放到局部变量表中的索引为4的位置；
     目前栈为空，局部变量表中的1-4有值；
     9 iload_1 ： iload_<n>局部变量中索引为n的放到栈顶，把1放到栈顶；
     10 iload_2： 把局部变量表中的索引为2的变量放到栈顶；
     现在栈满了，从上到下为2 ，1
     11 iadd ：执行加法，不接收参数，参数是从操作数栈中弹出来的，结果又会被压入到操作数栈中，
     这一步结束，操作数栈中只有一个数值，3
     12 iload_3 ：把局部变量表中索引为3的压入栈顶
     此时栈满了，3,3
     13 isub ：执行减法：参数从栈中弹出来，把结果压入栈顶；
     14 iload 4： 把局部变量表中索引为4的值压入栈顶，此时栈中的值为4,0
     16 imul ：执行乘法操作：参数从栈中弹出，把结果压入栈顶；此时栈中为0；
     17 istore 5 ：将操作数栈顶的元素弹出来，赋值给局部变量表中索引为5的元素
     19 iload 5 ：把索引为5的局部变量表中压入栈顶；
     21 ireturn ：返回int类型的结果；返回当前操作的栈顶的数值，其他值全部丢弃；
     */
}