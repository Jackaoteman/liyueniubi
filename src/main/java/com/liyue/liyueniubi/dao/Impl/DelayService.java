package com.liyue.liyueniubi.dao.Impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.liyue.liyueniubi.Enum.ConstantsUtil;
import com.liyue.liyueniubi.domain.Order;
import org.assertj.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class DelayService implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static DelayQueue<Order> delayQueue = new DelayQueue<Order>();
    private static AtomicBoolean start = new AtomicBoolean(false);
    // 线程池
    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("useragent-pool-%d").build();
    private static ExecutorService executorService = new ThreadPoolExecutor(
            3, // 核心线程池大小
            6, // 最大线程池大小
            60L, // 线程最大空闲时间
            TimeUnit.MILLISECONDS, // 时间单位(毫秒)
            new LinkedBlockingQueue<Runnable>(),  // 线程等待队列
            threadFactory, // 线程创建工厂
            new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
    );


    @Autowired
    private OrderService tjOrderService;

    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }

    public <T> void start() {
        if (start.get()) {
            return;
        }
        start.set(true);

        //放入待付款订单
        Order order = new Order();
        // 0 未支付,常量池状态
        order.setState(ConstantsUtil.ORDER_STATE_CREATE);
        List<Order> list = tjOrderService.getList(order);
        list.forEach(item -> delayQueue.add(item));

        executorService.submit(() -> {
            // 取消订单
            while (start.get()) {
                try {
                    Order order1 = delayQueue.take();
                    if (order1 != null) {
                        // 订单过期
                        Order updateOrder = new Order();
                        updateOrder.setId(order1.getId());
                        // 常量池取消状态
                        updateOrder.setState(ConstantsUtil.ORDER_STATE_CANCEL);
                        tjOrderService.update(updateOrder);
                        // 还原
                        logger.info("=======订单号为[" + order.getOrderno() + "]未支付过期，过期时间为:" + new Date() + "=======");
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    public void add(Order order) {
        logger.info("加入订单队列订单号===={}", order.getOrderno());
        if (!start.get()) {
            throw new RuntimeException("订单服务还未启动");
        }
        delayQueue.add(order);
    }

    public void delete(Order order) {
        if (!start.get()) {
            throw new RuntimeException("订单服务还未启动");
        }
        delayQueue.remove(order);
    }

    public int getSize() {
        if (!start.get()) {
            throw new RuntimeException("订单服务还未启动");
        }
        return delayQueue.size();
    }
}
