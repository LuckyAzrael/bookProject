package com.book.test;

import com.book.bean.OrderItem;
import com.book.dao.OrderItemDAO;
import com.book.dao.impl.OrderItemDAOImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDAOTest {
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    @Test
    public void saveOrderItem() {
        orderItemDAO.saveOrderItem(new OrderItem(null,"咸鱼的一生",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
    }

    @Test
    public void queryOrderItemsByOrderId() {
        List<OrderItem> orderItems = orderItemDAO.queryOrderItemsByOrderId("1234567890");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}