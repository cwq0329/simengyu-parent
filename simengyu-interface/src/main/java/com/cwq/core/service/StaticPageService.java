package com.cwq.core.service;

import java.util.Map;

/**
 * 静态化页面服务接口
 * @author Administrator
 *
 */
public interface StaticPageService {
    
    /**
     * 静态化商品详情页面
     * @param map
     * @param id
     */
    public void staticProductPage(Map<String,Object> map, String id);
}
