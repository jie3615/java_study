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
public class ByteCodeTest7 {

}