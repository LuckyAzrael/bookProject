package com.book.service;

import com.book.bean.Cart;
import com.book.bean.Order;
import com.book.bean.OrderItem;

import java.util.List;

/**
 * @InterfaceName OrderService
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/28 14:36
 * @Version 1.0
 **/
public interface OrderService {
    /**
     * 生成订单
     * @param cart
     * @param userId
     * @return
     */
    String createOrder(Cart cart,Integer userId);

    /**
     * 查询全部订单
     * @return
     */
    List<Order> showAllOrders();

    /**
     * 发货
     * @param orderId
     */
    void sendOder(String orderId);

    /**
     * 查看订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> showOrderDetail(String orderId);

    /**
     * 查看我的订单
     * @param userId
     * @return
     */
    List<Order> showMyOrders(Integer userId);

    /**
     * 签收订单、确认收货
     * @param orderId
     */
    void receiverOrder(String orderId);
}
