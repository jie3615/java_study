package jvm.classloaderProcessed;

import java.sql.*;

/**
 * @author: wyj
 * @date: 2019/9/29
 * @description:
 */
public class LoaderTest14 {
    // mysql驱动连接案例，讲解类加载

    /**
     * 数据库信息
     *   name         startTime
     *   dd	          2019-04-10 00:00:00
         dfdf	      2019-04-20 00:00:00
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 这行代码完成的事情非常多，会初始化参数中的类（会执行静态的代码块，从上到下）
        Class.forName("com.mysql.jdbc.Driver");

        Connection conns = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
        System.out.println(conns);
        Statement statement = conns.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM test_time");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("startTime"));
        }
    }
}