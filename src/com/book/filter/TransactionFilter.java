package com.book.filter;

import com.book.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName TransactionFilter
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/28 19:25
 * @Version 1.0
 **/
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request,response);
            JDBCUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
