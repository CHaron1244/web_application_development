package cn.itcast.dao;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class ConnDB {
    public Connection conn =null;    //声明Connection对象的实例
    public Statement stmt = null;    //声明Statement对象的实例
    public ResultSet rs = null;      //声明ResultSet对象的示例
    //指定资源文件保存的位置
    private static String propFileName= "connDB.properties";
    //创建并实例化Properties对象的实例
    private static Properties prop=new Properties();
    //定义并保存数据库驱动的变量
    private static String dbClassName ="";
    private static String dbUrl="";
    private static String user="";
    private static String pass="";
    /**
     * 构造方法
     */
    static{
        try{
            //将Properties文件读取到InputStream对象中
            InputStream in=ConnDB.class.getResourceAsStream(propFileName);
            prop.load(in);
            dbClassName = prop.getProperty("DB_CLASS_NAME"); //获取数据库驱动
            dbUrl = prop.getProperty("DB_URL");  //获取数据库驱动
            user = prop.getProperty("user");  //获取数据库账号
            pass = prop.getProperty("pass");  //获取数据库密码
        }catch (Exception e){
            e.printStackTrace();                             //输出异常信息
        }
    }
    /**
     * 连接数据库
     */
    public  Connection getConection(){
        try{
            Class.forName(dbClassName).newInstance(); //装载数据库驱动
            //建立与数据库URL中定义的数据库的连接
            conn = DriverManager.getConnection(dbUrl,user,pass);
        }catch (Exception ee){
            ee.printStackTrace();
        }
        if(conn==null){
            System.err.print("连接失败");
        }
        return conn;
    }
    /**
     * 执行查询
     */
    public ResultSet executeQuery(String sql){
        try{
            conn=getConection();
            stmt =conn.createStatement
                    (ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //执行SQL语句，并返回一个ResultSet对象rs
            rs =stmt.executeQuery(sql);
        }catch (SQLException ex){
            System.err.print(ex.getErrorCode());
        }
        return rs;
    }
    /**
     * 关闭数据库的连接
     */
    public void close(){
        try{
            if(rs!=null){
                rs.close();
            }
            if (stmt!=null){
                stmt.close();
            }
            if (conn!=null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
