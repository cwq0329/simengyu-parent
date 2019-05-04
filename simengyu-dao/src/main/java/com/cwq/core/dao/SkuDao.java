package com.cwq.core.dao;

import java.util.List;

import com.cwq.core.pojo.Sku;
import com.cwq.core.pojo.SuperPojo;
import com.github.abel533.mapper.Mapper;

public interface SkuDao extends Mapper<Sku> {

    /**根据productId查询出所有的库存及对应的颜色
     * @param productId
     * @return
     */
    List<SuperPojo> findSkuAndColorByProductId(Long productId);
    
    
    
    /**
     * 根据库存id查询出库存，颜色和商品信息
     * @param skuId
     * @return
     */
    SuperPojo findSkuAndColorAndProductBySkuId(Long skuId);



    
    /**添加库存信息
     * @param sku
     */
    void insertSku(Sku sku);



    
    /**修改库存信息
     * @param sku
     * @return
     */
    Integer updateSkuInfo(Sku sku);


    

    /**根据商品id查出对应的库存信息b并按价格升序
     * @param id
     * @return
     */
    List<Sku> findSkuMinPriceByProductId(Long productId);

    
}
