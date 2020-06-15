package com.liyue.liyueniubi.Controller;

import com.liyue.liyueniubi.dao.IOrderService;
import com.liyue.liyueniubi.dao.Impl.OrderService;
import com.liyue.liyueniubi.domain.Order;
import com.liyue.liyueniubi.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/addOrder")
public class OrderController {

    @GetMapping("/get")
    public  String  get(){
        return  "12222222222222222222";
    }


    @Autowired(required = false)
     @Qualifier("orderService")
    IOrderService orderService;

    @PostMapping("add")
    public Result addOrder(Order order){

        orderService.addOrder(order);

        return  new Result(null,000,"下单成功",true);

    }
}
