package com.book.dao;

import com.book.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName BaseDAO
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/22 22:48
 * @Version 1.0
 **/
public abstract class BaseDAO {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update() 方法用于执行：insert、update、delete语句
     * @return 返回操作的行数，如果返回值为-1，操作失败
     */
    public int update(String sql,Object ... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个javaBean的sql语句
     * @param clazz 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数
     * @param <T> 返回的类型的泛型
     * @return
     */

    public <T> T queryForOne(Class<T> clazz,String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(clazz),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个javaBean的sql语句
     * @param clazz 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数
     * @param <T> 返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> clazz,String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql 返回的对象类型
     * @param args sql对应的参数
     * @return
     */
    public Object queryForSingleValue(String sql,Object ... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
