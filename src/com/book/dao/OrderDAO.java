package com.book.dao;

import com.book.bean.Order;

import java.util.List;

/**
 * @InterfaceName orderDAO
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/27 21:40
 * @Version 1.0
 **/
public interface OrderDAO {
    /**
     * 保存订单
     * @param order
     */
    void saveOrder(Order order);

    /**
     * 查询全部订单
     * @return
     */
    List<Order> queryOrders();

    /**
     * 修改订单状态
     * @param orderId
     * @param status 0未发货，1已发货，2已签收
     */
    void changeOrderStatus(String orderId,Integer status);

    /**
     * 根据用户编号查询订单信息
     * @param userId
     * @return
     */
    List<Order> queryOrdersByUserId(Integer userId);


}
