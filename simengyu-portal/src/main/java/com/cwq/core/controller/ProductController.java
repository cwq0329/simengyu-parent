package com.cwq.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cwq.core.pojo.Product;
import com.cwq.core.pojo.Sku;
import com.cwq.core.pojo.SuperPojo;
import com.cwq.core.service.ProductService;


@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    //查询单个商品详情
    @RequestMapping(value="/product/detail")
    public String portalShowSingleProductDetail(Model model,Long productId){
        SuperPojo singleProductDetail = productService.findSingleProductById(productId);
        //一个商品对应多个库存，库存与颜色表对应
        List<SuperPojo> skus = (List<SuperPojo>) singleProductDetail.get("skus");
        //去重重复颜色
        HashMap<Long, String> colors = new HashMap<Long,String>();
        for (SuperPojo sku : skus) {
            colors.put((Long)sku.get("color_id"), (String)sku.get("colorName"));
        }
        singleProductDetail.setProperty("colors", colors);
        model.addAttribute("singleProductDetail", singleProductDetail);
        return "product";
    }


}
