package com.book.dao.impl;

import com.book.bean.OrderItem;
import com.book.dao.BaseDAO;
import com.book.dao.OrderItemDAO;
import com.book.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName OrderItemDAOImpl
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/27 21:50
 * @Version 1.0
 **/
public class OrderItemDAOImpl extends BaseDAO implements OrderItemDAO {
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql = "insert into `t_order_item`(`name`,`count`,`price`,`total_price`,`order_id`)values(?,?,?,?,?)";
        update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());


    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from `t_order_item` where `order_id`=?";
        return queryForList( OrderItem.class, sql, orderId);
    }
}
