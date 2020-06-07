package com.liyue.liyueniubi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

@SpringBootTest
class LiyueniubiApplicationTests {

    //并发量
    private  static   int user_num=1000;
    //产品库存
    private   static  int  total_num=100;
    //发令枪 等待发送指令
    private  static CountDownLatch countDownLatch=new CountDownLatch(user_num);
    //技术器 用于统计购买成功的人数
    private  static  int success_num=0;
    //技术器 用户统计卖出商品数量
    private  static  int  sell_num=0;
    //屏障器，统计所有线程完成时间
    private CyclicBarrier cyclicBarrier=new CyclicBarrier(user_num+1);

    @Test
    void contextLoads() throws InterruptedException {

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < user_num; i++) {
            new Thread(new userRequest()).start();
            if(i==user_num){
                Thread.currentThread().sleep(1000);
            }
            countDownLatch.countDown();
        }
        try {
            //裁判先到达 一共1001个屏障器
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("一共执行="+(System.currentTimeMillis()-startTime/1000)+"秒");

    }
    public  class userRequest implements  Runnable{

        private  String code;
        private  int bus;

        @Override
        public void run() {
            try {
                //进入等待状态 所有请求全部进入起点开始执行
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //逻辑修改
            //
            //
            //
            //

            //每个线程请求完到达 await
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
