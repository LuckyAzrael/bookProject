package com.book.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName order
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/27 21:32
 * @Version 1.0
 **/
public class Order {
    private String orderId;
    private Date createTime;
    private BigDecimal price;
    //0未发货，1已发货，2已签收
    private Integer status = 0;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String ordrId) {
        this.orderId = ordrId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
