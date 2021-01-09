package com.book.web;

import com.book.bean.Cart;
import com.book.bean.User;
import com.book.service.OrderService;
import com.book.service.impl.OrderServiceImpl;
import com.book.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @ClassName OrderServlet
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/28 15:13
 * @Version 1.0
 **/
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取userId
        User loginUser = (User) req.getSession().getAttribute("user");
        //判断用户是否登录
        if (loginUser == null){
            //请求转发到登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = loginUser.getId();
        //创建订单
        String orderId = orderService.createOrder(cart, userId);

        //保存订单号到域中
        req.getSession().setAttribute("orderId",orderId);
        //重定向到结算页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    /**
     * 查看所有订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * 发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * 查看订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * 查看我的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * 签收订单、确认收货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
