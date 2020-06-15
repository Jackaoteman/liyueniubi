package com.liyue.liyueniubi.dao;

import com.liyue.liyueniubi.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IOrderService {


    void  addOrder(Order order);

    List<Order> getList(Order order);

    void update(Order updateOrder);
}
