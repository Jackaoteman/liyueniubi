package com.liyue.liyueniubi.mapper;

import com.liyue.liyueniubi.domain.Product;

public interface ProductMapper  {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}