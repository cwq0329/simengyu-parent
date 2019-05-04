package com.cwq.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cwq.core.pojo.Brand;


/**品牌Dao
 * @author Administrator
 *
 */
public interface BrandDao {
    /**
     * 根据条件查询品牌列表
     * @param brand
     * @return
     */
    public List<Brand> findByExample(Brand brand);
    
    
    /**
     * 根据id查询单个品牌修改页面
     * @param id
     * @return
     */
    public Brand findById(Long id);
    
    /**
     * 根据id修改品牌
     * @param brand
     */
    public void updatedById(Brand brand);
    
    /**
     * 
     * 添加品牌
     * @param brand
     */
    public void addBrand(Brand brand);
    
    /**
     * 根据id删除多个品牌对象
     * @param ids
     */
    public void deleteBatchBrandByIds(String ids);
    
    /**
     *  删除单个品牌                                
     * @param brandId
     */
    public void deleteSingleBrandById(Long brandId);
    
    /**
     * 查询所有的品牌信息
     * @return
     */
    public List<Brand> findBrands();
    
    public Long getTotalNum();
    
    public List<Brand> findBrandPage(@Param("firstNum") int startNum,@Param("pageSize") int pageSize);
   
}
