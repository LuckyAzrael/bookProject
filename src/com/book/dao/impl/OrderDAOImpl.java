package com.book.dao.impl;

import com.book.bean.Order;
import com.book.dao.BaseDAO;
import com.book.dao.OrderDAO;
import com.book.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName OrderDAOImpl
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/27 21:49
 * @Version 1.0
 **/
public class OrderDAOImpl extends BaseDAO implements OrderDAO {
    @Override
    public void saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)values(?,?,?,?,?)";
        update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    public void changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set `status`=? where `order_id`=?";
        update(sql,status,orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where `user_id`=?";
        return queryForList(Order.class, sql,userId);
    }
}
