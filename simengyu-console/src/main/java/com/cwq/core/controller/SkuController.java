package com.cwq.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cwq.core.pojo.Sku;
import com.cwq.core.pojo.SuperPojo;
import com.cwq.core.service.SkuService;

@RequestMapping(value="/console/sku")
@Controller
public class SkuController {
    
    @Autowired
    private SkuService skuService;
    //根据productId查询库存信息
    @RequestMapping(value="/list.do")
    public String consloeShowSkuList(Model model,Long productId){
        List<SuperPojo> skus = skuService.findSkuInfoByProductId(productId);
        model.addAttribute("skus", skus);
        return"/sku/list";
    }
    
    @RequestMapping(value="/updateSku.do")
    @ResponseBody
    public String consoleUpdateSkuList(Model model,Sku sku){
        Integer count = skuService.updateSkuList(sku);
        return count + "";
    }
}
