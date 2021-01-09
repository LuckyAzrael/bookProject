package com.book.dao;

import com.book.bean.User;


/**
 * @InterfaceName UserDAO
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/22 23:15
 * @Version 1.0
 **/
public interface UserDAO {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回null，说明没有这个用户。反之亦然
     */
    User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 如果返回null，用户名或密码错误。反之亦然
     */
    User queryUserByUsernameAndPassword(String username,String password);
    /**
     * 保存用户信息
     * @param user 要保存的用户信息的javaBean对象
     * @return 返回-1表示操作失败，其他表示sql语句影响的行数
     */
    int saveUser(User user);


}
