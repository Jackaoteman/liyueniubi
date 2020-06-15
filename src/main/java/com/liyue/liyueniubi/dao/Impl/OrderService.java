package com.liyue.liyueniubi.dao.Impl;


import com.liyue.liyueniubi.dao.IOrderService;
import com.liyue.liyueniubi.domain.Order;
import com.liyue.liyueniubi.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "orderService")
public class OrderService implements IOrderService {

    @Autowired
    DelayService delayService;

    @Autowired
    OrderMapper  orderMapper;

    @Override
    public void addOrder(Order order) {
        orderMapper.insert(order);
        delayService.add(order);
    }

    @Override
    public List<Order> getList(Order order) {

        return  null;
       // return orderMapper.selectUnOrder(order);
    }

    @Override
    public void update(Order updateOrder) {

        orderMapper.updateByPrimaryKeySelective(updateOrder);
    }

}
