package com.book.service;

import com.book.bean.User;

/**
 * @InterfaceName UserService
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/23 14:18
 * @Version 1.0
 **/
public interface UserService {
    /**
     * 注册用户
     * @param user 新用户
     */
    void registUser(User user);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 如果返回null说明登录失败,返回有值说明登录成功
     */
    User login(String username,String password);

    /**
     * 登录
     * @param user 用户名和密码所在的user对象
     * @return 如果返回null说明登录失败,返回有值说明登录成功
     */
    User login(User user);

    /**
     * 查询用户名是否可用
     * @param username 要查询的用户名
     * @return 返回true表示用户名表示用户名已存在，返回false表示用户名可用
     */
    boolean existsUsername(String username);
}
