package com.cwq.core.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cwq.core.pojo.Product;
import com.github.abel533.mapper.Mapper;

/**
 * 商品管理dao
 * @author Administrator
 *
 */
public interface ProductDao extends Mapper<Product>{

    /**
     * 根据条件查询商品数据
     * @param product
     * @return
     */
    List<Product> findProductPage(Product product);

    /**
     * 添加商品
     * @param product
     */
    void insertProduct(Product product);

    /**
     * 删除单个商品
     * @param productId
     */
    void deleteSingleProductById(Long productId);

    
    /**
     * 删除多个商品
     * @param listIds
     */
   
    void deleteBatchProductByIds(@Param("ids") List<String> listIds);

    
    /**批量修改商品上下架
     * @param product
     * @param listId
     */
    void updateIsShowOrHide(@Param("product") Product product, @Param("ids") List<String> listId);

    List<Product> findinfo(HashMap<String, Object> map);

    
    /**
     * 根据上架的商品ids查出所有的商品信息
     * @param idsList
     * @return
     */
    List<Product> findProductByIds(@Param("ids") List<String> idsList);
    

    /**
     * 根据商品id查询出商品信息
     * @param productId
     * @return
     */
    Product findProductInfoById(Long productId);
    
    
}
