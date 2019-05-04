package com.cwq.core.service;

import java.util.List;

import com.cwq.core.pojo.Brand;
import com.cwq.core.pojo.Page1;
import com.cwq.core.tools.PageHelper.Page;

public interface BrandService {

    Page<Brand> findByExample(Brand brand, Integer pageNum, Integer pageSize);

    Brand findById(Long brandId);

    void updateById(Brand brand);

    void addBrand(Brand brand);

    void deleteByIds(String ids);

    void deleteSingleBrandById(Long brandId);

    List<Brand> findBrands();

   

    Page1 findPage(Page1<Brand> page1);

    
    /**
     *从redis中查询出品牌信息 
     * @return
     */
    List<Brand> findBrandFromRedis();

}
