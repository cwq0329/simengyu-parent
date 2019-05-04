package com.cwq.core.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cwq.core.pojo.Brand;
import com.cwq.core.pojo.SuperPojo;
import com.cwq.core.service.BrandService;
import com.cwq.core.service.SolrService;
import com.cwq.core.tools.Encoding;
import com.cwq.core.tools.PageHelper.Page;

@Controller
public class IndexController {
   @Autowired
   private SolrService solrService;
   
   @Autowired
   private BrandService brandService;
    //前台显示页面
    @RequestMapping(value="/")
    public String showIndex(){
        return "index";
    }
    
    //前台搜索功能
    @RequestMapping(value="/search")
    public String indexSerach(Model model,String keyword,String sort,
            Integer pageNum,Integer pageSize,Long brandId,String price){
//        List<SuperPojo> solrPageProducts = solrService.findSolrProductByKeyword(Encoding.encodeGetRequest(keyword),sort);
        Page<SuperPojo> solrPageProducts = solrService.findSolrProductByKeyword(Encoding.encodeGetRequest(keyword),sort,
              pageNum,pageSize,brandId,price);
        model.addAttribute("solrPageProducts", solrPageProducts);
        model.addAttribute("keyword",Encoding.encodeGetRequest(keyword));
        
        //将反转前的sort丢给页面（每次排序完后将价格携带在搜索条件后，重新筛选商品则按原来价格排序）
        model.addAttribute("sort2", sort);
        
        //对sort进行反转
        if(sort!=null && sort.equals("price asc")){
            //当点价格升序排序完后修改为降序回显页面
            sort="price desc";
        }else{
            //第一次点搜索时给价格默认升序回显页面，待点价格升序排序
            sort="price asc";
        }
        
        model.addAttribute("sort", sort);  
        //从redis中查询出品牌，并传递到页面
        List<Brand> brands = brandService.findBrandFromRedis();
        model.addAttribute("brands", brands);
        //回传用户传选择的价格
        model.addAttribute("price", price);
        //回传用户选择的品牌id
        model.addAttribute("brandId", brandId);
        
        //已选条件容器map
        HashMap<String, String> map = new HashMap<String,String>();
        //品牌
        if(null!=brandId){
            for (Brand brand : brands) {
                if(brandId == brand.getId()){
                    map.put("品牌", brand.getName());
                    break;
                }   
            }
        }
        
        if(null != price){
            if(price.contains("-")){
                map.put("价格", price);
            }else{
                map.put("价格", price+"以上");
            }
        }
        
        model.addAttribute("map", map);
        return "search";
    }
}
