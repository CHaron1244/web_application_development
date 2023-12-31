package cn.itcast.jdbc.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement ;
import java.sql.SQLException;
public class Example02 {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement  preStmt = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=GMT%2B8";
            String username = "root";
            String password = "root";
            // 创建应用程序与数据库连接的Connection对象
            conn = DriverManager.getConnection(url, username, password);
            // 执行的SQL语句
            String sql = "INSERT INTO users(name,password,email,birthday)"
                    + "VALUES(?,?,?,?)";
            // 1.创建执行SQL语句的PreparedStatement对象
            preStmt = conn.prepareStatement(sql);
            // 2.为SQL语句中的参数赋值
            preStmt.setString(1, "zl");
            preStmt.setString(2, "123456");
            preStmt.setString(3, "zl@sina.com");
            preStmt.setString(4, "1989-12-23");
            // 3.执行SQL
            preStmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {    // 释放资源
            if (preStmt != null) {
                try {
                    preStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                preStmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
        }
    }
}
