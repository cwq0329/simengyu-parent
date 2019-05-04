package com.cwq.core.service;

import java.util.List;

import com.cwq.core.pojo.Sku;
import com.cwq.core.pojo.SuperPojo;

public interface SkuService {

    /**
     * 根据商品id查询所有对应的库存信息
     * @param productId
     * @return
     */
    List<SuperPojo> findSkuInfoByProductId(Long productId);

    
    /**
     * 修改指定参数的库存信息
     * @return
     */
    Integer updateSkuList(Sku sku);


}
