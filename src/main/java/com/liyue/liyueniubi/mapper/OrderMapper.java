package com.liyue.liyueniubi.mapper;

import com.liyue.liyueniubi.domain.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderMapper extends Mapper<Order> {


  //  List<Order> selectUnOrder(@Param("order") Order order);

}