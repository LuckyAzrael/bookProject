package com.book.web;

import com.book.bean.Book;
import com.book.bean.Cart;
import com.book.bean.CartItem;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CartServlet
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/27 19:31
 * @Version 1.0
 **/
public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    /**
     * 添加商品到购物车的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id):Book得到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(id,book.getName(),1,book.getPrice(),book.getPrice());
        //调用Cart.addItem(CartItem)
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());
        //重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
    /**
     * ajax添加商品到购物车的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id):Book得到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(id,book.getName(),1,book.getPrice(),book.getPrice());
        //调用Cart.addItem(CartItem)
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();

        }
        cart.addItem(cartItem);
        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());
        //返回购物车总的商品数量和最后一个添加的商品名称
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);

    }

    /**
     * 从购物车中删除商品的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        Integer id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //删除购物车对象中的指定商品
        cart.deleteItem(id);
        //重定向回原来购物车展示页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 清空购物车的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //清空购物车对象中的商品
        cart.clear();
        //重定向回原来购物车展示页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改购物车中商品数量的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //修改商品数量
        cart.updateCount(id,count);
        //重定向回原来购物车展示页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
