package com.cwq.core.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwq.core.dao.ProductDao;
import com.cwq.core.dao.SkuDao;
import com.cwq.core.pojo.Product;
import com.cwq.core.pojo.Sku;
import com.cwq.core.pojo.SuperPojo;
import com.cwq.core.tools.PageHelper;
import com.cwq.core.tools.PageHelper.Page;
import com.github.abel533.entity.Example;

@Service(value="solrService")
@Transactional
public class SolrServiceImpl implements SolrService {
    @Autowired
    private HttpSolrServer solrServer;
    
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private SkuDao skuDao;
    
    
    @Override
    public Page<SuperPojo> findSolrProductByKeyword(String keyword,String sort,Integer pageNum,Integer pageSize,Long brandId,String price) {
        //设置查询条件
       SolrQuery solrQuery = new SolrQuery("name_ik:"+keyword);
       
       //品牌过滤
       if(brandId != null){
           solrQuery.addFilterQuery("brandId: "+brandId);
       }
       
       //价格过滤
       if(null!= price){
           String[] p = price.split("-");
           if (p.length == 2) {

                   solrQuery.addFilterQuery("price:[" + p[0] + " TO " + p[1] + "]");
           } else {

                   solrQuery.addFilterQuery("price:[" + p[0] + " TO *]");
           }
       }
       
       
       //分页设置
       Page<SuperPojo> page = new Page<SuperPojo>(pageNum, pageSize);
       
       solrQuery.setStart(page.getStartRow());
       solrQuery.setRows(page.getPageSize());
       
       //设置高亮
       solrQuery.setHighlight(true);
       solrQuery.addHighlightField("name_ik");
       solrQuery.setHighlightSimplePre("<span style='color:red'>");
       solrQuery.setHighlightSimplePost("</span>");
       
       //设置排序
       if(sort!=null && sort.trim().length()>0){
           solrQuery.setSort(new SortClause(sort.split(" ")[0], sort.split(" ")[1]));
       }
       
       
       //开始查询
       QueryResponse response = null;
        try {
            response = solrServer.query(solrQuery);
        } catch (SolrServerException e) {
            
            e.printStackTrace();
        }
        
        //获得高亮数据集合
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        
       //获得结果集
       SolrDocumentList results = response.getResults();
      
       //获得总数量
       long numFound = results.getNumFound();
       page.setTotal(numFound);
       
       //将结果集中的信息封装到商品对象中
       List<SuperPojo> superProducts =new ArrayList<SuperPojo>();
       
       for (SolrDocument doc : results) {
           //设置superPojo用来封装两张表单的数据
        SuperPojo superProduct = new  SuperPojo();
        String id = (String)doc.get("id");
        superProduct.setProperty("id", id);
        
        //获得高亮数据集合中的商品名称
        Map<String, List<String>> map = highlighting.get(id);
        String string = map.get("name_ik").get(0);
//        String name = (String) doc.get("name_ik");
        superProduct.setProperty("name", string);
               
        Long brandId1 = (Long) doc.get("brandId");
        superProduct.setProperty("brandId", brandId1);
        
        String imgUrl=(String) doc.get("url");
        superProduct.setProperty("imgUrl", imgUrl);
        
        Float price1 = (float) doc.get("price");
        superProduct.setProperty("price", price1);
        
        Date createTime=(Date) doc.get("createTime");
        superProduct.setProperty("createTime", createTime);
        
        superProducts.add(superProduct);
        
       }
       //将结果添加到分页对象中
       page.setResult(superProducts);
       return page;
//        return superProducts;
    }
    
    /**
     * 根据mq监听到商品ids完成solr存入
     * @param ids
     */
    public void addProductToSolr(String ids) {
        
        String[] idsStrArr = ids.split(",");
        List<String> idsList = Stream.of(idsStrArr).collect(Collectors.toList());
//       Example example = new Example(Product.class);
//       example.createCriteria().andIn("id", idsList);
//       //查询出所有的上架商品
//       List<Product> products = productDao.selectByExample(example);
        List<Product> products =productDao.findProductByIds(idsList);
       
       for (Product product : products) {
        //创建文档对象，将商品信息添加到solr文本对象
           try {
            SolrInputDocument doc = new SolrInputDocument();
               doc.addField("id", product.getId());
               doc.addField("name_ik", product.getName());
               doc.addField("brandId", product.getBrandId());
               doc.addField("url", product.getImgUrl().split(",")[0]);
               doc.addField("createTime", product.getCreateTime());
               //取出商品对应库存中的最低价格
//               Example example2 = new Example(Sku.class);
//               example2.createCriteria().andEqualTo("productId", product.getId());
//               example2.setOrderByClause("price asc");
               //开始分页
               PageHelper.startPage(1, 1);
               //查出某个商品的库存
//               List<Sku> skus = skuDao.selectByExample(example2);
               List<Sku> skus = skuDao.findSkuMinPriceByProductId(product.getId());
               PageHelper.endPage();
               //取出库存第一条数据的价格           
              doc.addField("price", skus.get(0).getPrice());
               //将文档对象存入solr库中
               solrServer.add(doc);
               solrServer.commit();
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
           
           
    }
        
    }

}
