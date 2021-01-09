package com.book.dao.impl;

import com.book.bean.User;
import com.book.dao.BaseDAO;
import com.book.dao.UserDAO;
import com.book.utils.JDBCUtils;

import java.sql.Connection;

/**
 * @ClassName UserDAOimpl
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/22 23:21
 * @Version 1.0
 **/
public class UserDAOImpl extends BaseDAO implements UserDAO {
    @Override
    public User queryUserByUsername( String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword( String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int saveUser( User user) {
        String sql = "insert into t_user(`username`,`password`,`email`)values(?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }

}
