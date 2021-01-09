package com.book.test;


import com.book.bean.Order;
import com.book.dao.OrderDAO;
import com.book.dao.impl.OrderDAOImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class OrderDAOTest {
    OrderDAO orderDAO = new OrderDAOImpl();

    @Test
    public void saveOrder() {
        orderDAO.saveOrder(new Order("1234567891",new Date(),new BigDecimal(130),0,1));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDAO.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        orderDAO.changeOrderStatus("1234567890",1);
    }

    @Test
    public void queryOrdersByUserId() {
        List<Order> orders = orderDAO.queryOrdersByUserId(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}