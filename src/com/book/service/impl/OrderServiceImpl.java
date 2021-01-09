package com.book.service.impl;

import com.book.bean.*;
import com.book.dao.BookDAO;
import com.book.dao.OrderDAO;
import com.book.dao.OrderItemDAO;
import com.book.dao.impl.BookDAOIpml;
import com.book.dao.impl.OrderDAOImpl;
import com.book.dao.impl.OrderItemDAOImpl;
import com.book.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/28 14:38
 * @Version 1.0
 **/
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO= new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private BookDAO bookDAO = new BookDAOIpml();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号（唯一性）
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDAO.saveOrder(order);
        //遍历购物车中的每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem> item : cart.getItems().entrySet()) {
            //获取每一个购物车中的商品项
            CartItem cartItem = item.getValue();
            //转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDAO.saveOrderItem(orderItem);
            //查询得到对应的商品
            Book book = bookDAO.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDAO.updateBook(book);

        }
        //清空购物车
        cart.clear();
        //返回订单号
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = orderDAO.queryOrders();
        return orders;
    }

    @Override
    public void sendOder(String orderId) {
        orderDAO.changeOrderStatus(orderId,1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        List<OrderItem> orderItems = orderItemDAO.queryOrderItemsByOrderId(orderId);
        return orderItems;
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        List<Order> orders = orderDAO.queryOrdersByUserId(userId);
        return orders;
    }

    @Override
    public void receiverOrder(String orderId) {
        orderDAO.changeOrderStatus(orderId,2);
    }
}
