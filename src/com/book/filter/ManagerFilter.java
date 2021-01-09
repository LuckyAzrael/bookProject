package com.book.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName ManagerFilter
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/28 17:56
 * @Version 1.0
 **/
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //类型转换以便获取Session域中的数据
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //获取Session域中的user
        Object user = httpServletRequest.getSession().getAttribute("user");
        //判断user是否为空
        if (user == null){
            //为空则请求转发到登录页面
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            chain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }
}
