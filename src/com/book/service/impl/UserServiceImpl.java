package com.book.service.impl;

import com.book.bean.User;
import com.book.dao.UserDAO;
import com.book.dao.impl.UserDAOImpl;
import com.book.service.UserService;



/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/23 14:34
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();
    @Override
    public void registUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User login(String username, String password) {

       return userDAO.queryUserByUsernameAndPassword(username,password);

    }

    @Override
    public User login(User user) {

        return userDAO.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());


    }


    @Override
    public boolean existsUsername(String username) {
        User user = userDAO.queryUserByUsername(username);
        return user != null;
    }
}
