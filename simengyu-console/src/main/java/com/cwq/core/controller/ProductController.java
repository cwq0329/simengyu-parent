package com.cwq.core.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.cwq.core.pojo.Brand;
import com.cwq.core.pojo.Color;
import com.cwq.core.pojo.Product;
import com.cwq.core.service.BrandService;
import com.cwq.core.service.ProductService;
import com.cwq.core.tools.Encoding;
import com.cwq.core.tools.PageHelper.Page;

@RequestMapping(value="/console/product")
@Controller
public class ProductController {
    
    @Autowired
    private BrandService brandService;
    
    @Autowired
    private ProductService productService;
    //商品的通用映射
    @RequestMapping(value="/{pageName}.do")
    public String consolePrductShow(@PathVariable(value="pageName") String pageName){
        return "/product/"+pageName;
        
    }
    
    //显示商品列表   
    @RequestMapping(value="/list.do")
    public String consoleProductShowList(Model model,
            String name,Long brandId,Integer isShow,Integer pageNum,Integer pageSize){
        System.out.println("name: "+name);
        System.out.println("BrandId: "+brandId);
        System.out.println("isShow: "+isShow);
        System.out.println("pageNum: "+pageNum);
        System.out.println("pageSzie: "+pageSize);
        Product product = new Product();
        product.setBrandId(brandId);
        product.setName(Encoding.encodeGetRequest(name));
        product.setIsShow(isShow);
        //查询商品的分页信息
        Page<Product> productPage= productService.findProductPageByExample(product,pageNum,pageSize);
        //查询所有的品牌
        List<Brand> brands = brandService.findBrands();
        //返回品牌信息
        model.addAttribute("brands", brands);
        model.addAttribute("productPage", productPage);
        model.addAttribute("name", Encoding.encodeGetRequest(name));
        model.addAttribute("brandId", brandId);
        model.addAttribute("isShow", isShow);
        
        return "/product/list";
    }
    
    //显示添加商品页面
    @RequestMapping(value="/showAdd.do")
    public String consoleProductShowAdd(Model model){
       //加载所有可用颜色
       List<Color> colors = productService.findEnableColors();
       //加载所有品牌
       List<Brand> brands = brandService.findBrands();
       model.addAttribute("colors", colors);
       model.addAttribute("brands", brands);
        return "/product/add";
    }
    
    //添加商品
    @RequestMapping(value="/doAdd.do")
    public String consoleProductDoAdd(Model model,Product product){
        System.out.println(product);
        productService.addProductInfo(product);
        return"redirect:/console/product/list.do";
    }
    
    
    //删除单个商品 
    @RequestMapping(value="/singleDelete.do")
    public String consoleProductSingleDelete(Model model,Long productId,
            String name,String brandId,String isShow
            ,Integer pageNum){ 
        productService.singleDelete(productId);      
//        return "redirect:/console/product/list.do?name="+name+"&brandId="+brandId+"&isShow="+isShow+"&pageNum="+pageNum;
        return "redirect:/console/product/list.do?name="+name+"&brandId="+brandId+"&isShow="+isShow+"&pageNum="+pageNum;
    }
    
    //删除多个商品
    @RequestMapping(value="/deleteBatch.do")
    public String consoleProductDeleteBatch(Model model,String ids,String name,
            String isShow,String brandId,Integer pageNum){
        productService.batchDelete(ids);
        return "redirect:/console/product/list.do?name="+name+"&brandId="+brandId+"&isShow="+isShow+"&pageNum="+pageNum;
    }
    
    //商品上下架
    @RequestMapping(value="/isShow.do")
//    public String consoleProductIsShow(Model model,String ids,String isStandOrDown,
//   String name,String brandId,Integer pageNum){
    public String consoleProductIsShow(Model model,String ids,Integer isShow){
        Product product = new Product();
//        String substring = isShow.substring(0, 1);      
        product.setIsShow(isShow);
        productService.isShowOrHide(product,ids);
//        return "redirect:/console/product/list.do?name="+name+"&brandId="+brandId+"&pageNum="+pageNum;
        return "redirect:/console/product/list.do";
    }
}
