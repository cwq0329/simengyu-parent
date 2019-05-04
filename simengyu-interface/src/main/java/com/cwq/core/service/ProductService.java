package com.cwq.core.service;

import java.util.List;

import com.cwq.core.pojo.Color;
import com.cwq.core.pojo.Product;
import com.cwq.core.pojo.SuperPojo;
import com.cwq.core.tools.PageHelper.Page;

/**
 * 商品服务接口
 * @author Administrator
 *
 */
public interface ProductService {

    /**根据条件查询带分页
     * @param product 查询条件的模板对象
     * @param pageNum  当前页码
     * @param pageSize 每页显示行数
     * @return
     */
    Page<Product> findProductPageByExample(Product product, Integer pageNum, Integer pageSize);

    /**
     * 添加商品页查询所有可用颜色
     * 颜色的父id不为0
     * @return
     */
    List<Color> findEnableColors();

    /**
     * 添加商品的时候添加库存
     * @param product
     */
    void addProductInfo(Product productId);

    /**
     * 根据id删除单个商品对象
     * @param productId
     */
    void singleDelete(Long productId);

    /**
     * 根据id删除多个商品
     * @param ids
     */
    void batchDelete(String ids);

    /**
     * 根据ids和标识完成商品上下架
     * @param product
     * @param ids
     */
    void isShowOrHide(Product product, String ids);

    /**
     * 根据商品id查询出单个商品详细（商品编号，商品名称，商品颜色，商品尺寸，商品价格）
     * @param productId
     * @return
     */
    SuperPojo findSingleProductById(Long productId);

    
   
   

}
