package com.book.web;

import com.book.bean.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @ClassName UserServlet
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/24 19:33
 * @Version 1.0
 **/
public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    /**
     * 处理登录的功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.login(username, password);
        if (user == null) {
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 销毁Session中用户登录的信息（或者销毁Session）
        req.getSession().invalidate();
        //2. 重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 处理注册的功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //2.检查 验证码是否正确
        if (token != null && token.equals(code)) {
            //正确
            //3.检查 用户名是否可用
            if (userService.existsUsername(username)) {
                //不可用
                //把回显信息保存到Request域中
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                System.out.println("用户名" + username + "已存在！");
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //可用
                //调用Service保存到数据库
                userService.registUser(new User(null, username, password, email));
                //跳到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        } else {
            //错误
            //把回显信息保存到Request域中
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码" + code + "错误");
            //跳回注册页面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 通过ajax请求判断用户名是否可用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数username
        String username = req.getParameter("username");
        //调用userService.existsUsername()
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }
}
