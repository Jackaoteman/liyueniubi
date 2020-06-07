package com.liyue.liyueniubi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

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


    @Test
    void contextLoads() throws InterruptedException {

        for (int i = 0; i < user_num; i++) {
            new Thread(new userRequest()).start();
            if(i==user_num){
                Thread.currentThread().sleep(1000);
            }
            countDownLatch.countDown();
        }
    }
    public  class userRequest implements  Runnable{

        private  String code;
        private  int bus;

        @Override
        public void run() {

            try {
                //进入等待状态
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }






        }
    }

}
