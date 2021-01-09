package com.book.dao;

import com.book.bean.Order;
import com.book.bean.OrderItem;

import java.util.List;


/**
 * @InterfaceName OrderItemDAO
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/27 21:46
 * @Version 1.0
 **/
public interface OrderItemDAO {
    /**
     * 保存订单项
     */
    void saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单号查询订单明细
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
