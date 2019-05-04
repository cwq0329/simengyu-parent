package com.cwq.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwq.core.dao.SkuDao;
import com.cwq.core.pojo.Sku;
import com.cwq.core.pojo.SuperPojo;
import com.github.abel533.entity.Example;

@Service(value="skuService")
@Transactional
public class SkuServiceImpl implements SkuService {
    
    @Autowired
    private SkuDao skuDao;
    /**
     * 根据商品id查询所有对应的库存信息及对应的颜色名称
     * @param productId
     * @return
     */
    public List<SuperPojo> findSkuInfoByProductId(Long productId) {
//        Example example = new Example(Sku.class);
//        example.createCriteria().andEqualTo("productId", productId);
//        List<Sku> skus = skuDao.selectByExample(example);
       
        return  skuDao.findSkuAndColorByProductId(productId);
    }
    @Override
    public Integer updateSkuList(Sku sku) {
       //修改有值的参数
//        return skuDao.updateByPrimaryKeySelective(sku);
        return skuDao.updateSkuInfo(sku);
    }

}
