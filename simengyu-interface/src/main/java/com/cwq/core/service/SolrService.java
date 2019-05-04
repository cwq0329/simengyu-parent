package com.cwq.core.service;



import com.cwq.core.pojo.SuperPojo;
import com.cwq.core.tools.PageHelper.Page;

public interface SolrService {

    
    /**根据关键字搜索商品
     * @param encodeGetRequest
     * @return
     */
    Page<SuperPojo> findSolrProductByKeyword(String keyword,String sort,
            Integer pageNum,Integer pageSize,Long brandId,String price);

    /**
     * 根据mq监听到商品ids完成solr存入
     * @param ids
     */
    void addProductToSolr(String ids);

}
