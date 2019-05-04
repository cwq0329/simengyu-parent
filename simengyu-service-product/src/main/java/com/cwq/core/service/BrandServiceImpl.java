package com.cwq.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.fastjson.JSONObject;
import com.cwq.core.dao.BrandDao;
import com.cwq.core.pojo.Brand;
import com.cwq.core.pojo.Page1;
import com.cwq.core.tools.PageHelper;
import com.cwq.core.tools.PageHelper.Page;

import redis.clients.jedis.Jedis;

@Service(value="brandService")
@Transactional
public class BrandServiceImpl implements BrandService {
    
    
    @Autowired
    private BrandDao brandDao;

    @Autowired
    private Jedis jedis;
    
    /**
     * 条件分页查询
     * @param brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<Brand> findByExample(Brand brand, Integer pageNum, Integer pageSize) {
        //开始分页
        PageHelper.startPage(pageNum, pageSize);
        //拦截dao的sql
        brandDao.findByExample(brand);
        //结束分页
        Page<Brand> endPage = PageHelper.endPage();
        return endPage;
    }

    /**
     * 根据id查询单个品牌信息
     * @param id
     * @return
     */
    public Brand findById(Long id) {
       
        return brandDao.findById(id);
    }

    @Override
    public Page1 findPage(Page1 page1) {
       Long totalNum = brandDao.getTotalNum();   
       int firstNum=(page1.getPageNum()-1)*(page1.getPageSize());
       List<Brand> findBrandPage = brandDao.findBrandPage(firstNum, page1.getPageSize());
       page1.setTotalNum(totalNum);
     
       page1.setResult(findBrandPage); 
        return page1;
    }

    @Override
    public void updateById(Brand brand) {
        //品牌修改保存到redis中
        jedis.hset("brand", String.valueOf(brand.getId()), JSONObject.toJSONString(brand, true));
        brandDao.updatedById(brand);
        
    }

    @Override
    public void addBrand(Brand brand) {
        //添加品牌信息到redis中
//        jedis.hset("brand",  String.valueOf(brand.getId()), JSONObject.toJSONString(brand, true));
        brandDao.addBrand(brand);
        jedis.hset("brand",  String.valueOf(brand.getId()), JSONObject.toJSONString(brand, true));
      
        
    }

    /**根据多个id删除多个品牌
     * @param ids
     */
    public void deleteByIds(String ids) {
        if(ids != null){
            String[] strlids = ids.split(",");
            jedis.hdel("brand", strlids);
        }
        
        brandDao.deleteBatchBrandByIds(ids);
    }

    /**删除单个品牌
     * @param brandId
     */
    public void deleteSingleBrandById(Long brandId) {
        jedis.hdel("brand", String.valueOf(brandId));
        brandDao.deleteSingleBrandById(brandId);
        
    }

    @Override
    public List<Brand> findBrands() {
      
        try {
            List<Brand> brands = new ArrayList<Brand>();
            Map<String, String> hgetAll = jedis.hgetAll("brand");
            if(hgetAll.size()!=0){
                Set<Entry<String, String>> entrySet = hgetAll.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    String value = entry.getValue();
                    Brand parse = JSON.parse(value, Brand.class);
                    brands.add(parse);
                }
                return brands;
            }
            
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return brandDao.findBrands();
    }

    @Override
    public List<Brand> findBrandFromRedis() {
        try {
            ArrayList<Brand> brands = new ArrayList<Brand>();
            Map<String, String> hgetAll = jedis.hgetAll("brand");
            if(hgetAll.size()!=0){
                Collection<String> values = hgetAll.values();
                for (String string : values) {
                    brands.add(JSON.parse(string, Brand.class));
                }
                return brands;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        return brandDao.findBrands();
    }

    


}
