package com.book.test;

import com.book.bean.User;
import com.book.dao.UserDAO;
import com.book.dao.impl.UserDAOImpl;
import com.book.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

public class UserDAOTest {
    UserDAO userDAO = new UserDAOImpl();

    @Test
    public void queryUserByUsername() {
        System.out.println(userDAO.queryUserByUsername( "admin"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {

        System.out.println(userDAO.queryUserByUsernameAndPassword("admin", "000000"));

    }

    @Test
    public void saveUser() {
        User user = new User(0, "admin1", "111111", "szm123@szm.com");
        System.out.println(userDAO.saveUser(user));
    }
}