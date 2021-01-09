package com.book.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName JDBCUtils
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/22 21:35
 * @Version 1.0
 **/
public class JDBCUtils {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接池中的连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = conns.get();
        if (conn == null){
            try {
                conn = dataSource.getConnection();//获取数据库连接池中的连接
                conns.set(conn);//保存到ThreadLocal对象中,供后面的jdbc操作使用
                conn.setAutoCommit(false);//设置手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并关闭释放连接
     */
    public static void commitAndClose(){
        Connection conn = conns.get();
        if (conn != null){//如果不等于null，说明之前使用过连接，操作过数据库
            try {
                conn.commit();//提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close();//关闭连接，资源释放
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。（因为TomCat服务器底层使用了线程池技术）
        conns.remove();
    }

    /**
     * 回滚事务并关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if (conn != null){//如果不等于null，说明之前使用过连接，操作过数据库
            try {
                conn.rollback();//回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close();//关闭连接，资源释放
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。（因为TomCat服务器底层使用了线程池技术）
        conns.remove();
    }




    /**
     * 关闭连接，放回数据库连接池
    public static void close(Connection conn){
        try {
            if (conn != null){
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    */
}
