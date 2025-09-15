package com.itheima;

import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web01", "root", "1234");

        //3.获取SQL语句执行对象
        Statement statement =connection.createStatement();

        //4.执行SQL
        int i = statement.executeUpdate("update user set age = 25 where id = 1");
        System.out.println("SQL执行完毕影响的记录数为：" + i);

        //5.释放资源
        statement.close();
        connection.close();
    }

    @Test
    public void testSelect() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // 1. 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 获取数据库连接
            String url = "jdbc:mysql://localhost:3306/web01";
            String username = "root";
            String password = "1234";
            connection = DriverManager.getConnection(url, username, password);

            // 3. 定义SQL语句
            // 预编译SQL
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

            // 4. 获取PreparedStatement对象
            preparedStatement = connection.prepareStatement(sql);

            // 5. 设置参数(为占位符赋值)
            preparedStatement.setString(1, "daqiao");
            preparedStatement.setString(2, "123456");

            // 6. 执行SQL并获取结果集
            rs = preparedStatement.executeQuery();

            // 7. 处理结果集
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));

                // 输出User对象数据
                System.out.println("查询到的用户信息: " + user);
            } else {
                System.out.println("未找到匹配的用户");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("找不到MySQL驱动类");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库操作异常");
            e.printStackTrace();
        } finally {
            // 8. 释放资源
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
