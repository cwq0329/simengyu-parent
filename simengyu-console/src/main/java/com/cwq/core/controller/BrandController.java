package com.cwq.core.controller;




import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cwq.core.pojo.Brand;

import com.cwq.core.service.BrandService;
import com.cwq.core.tools.Encoding;
import com.cwq.core.tools.PageHelper.Page;





@RequestMapping(value="/console")
@Controller
public class BrandController {
    private static final Logger logger =Logger.getLogger(BrandController.class);
    
    @Autowired
    private BrandService brandService;
    //显示通用映射
    @RequestMapping(value="/brand/{pageName}.do")
    public String consoleBrandShow(@PathVariable(value="pageName") String pageName){
        return "/brand/"+pageName;
    }
    
    //查询品牌列表（分页）
    @RequestMapping(value="/brand/list.do")
//    @ResponseBody
//    public   Page<Brand>  consoleBrandShowList(Model model,String name,Integer isDisplay
//            ,Integer pageNum,Integer pageSize){
    public  String  consoleBrandShowList(Model model,String name,Integer isDisplay
            ,Integer pageNum,Integer pageSize){
        
       logger.debug("name: "+ name);
       logger.debug("isDisplay: "+ isDisplay);
       logger.debug("pageNum: "+ pageNum);
       logger.debug("pageSize: "+ pageSize);
       System.out.println("Encoding.encodeGetRequest(name)Encoding.encodeGetRequest(name)"+name);
       Brand brand = new Brand();
       brand.setName(Encoding.encodeGetRequest(name));
      
       brand.setIsDisplay(isDisplay);
       Page<Brand> pageBrand = brandService.findByExample(brand, pageNum, pageSize);
       logger.debug("pageBrand.result: "+pageBrand.getResult());
       model.addAttribute("pageBrand", pageBrand);
       model.addAttribute("name", Encoding.encodeGetRequest(name));
       model.addAttribute("isDisplay", isDisplay);
       return "/brand/list";
//       return pageBrand;

    }
    
    //显示修改页面
    @RequestMapping(value="/brand/showEdit.do")
    public String consoleBrandShowEdit(Model model,Long brandId){
        logger.debug("brandId : "+brandId);
        Brand brand = brandService.findById(brandId);
        model.addAttribute("brand", brand);
        return "/brand/edit";
    }
    
    //执行修改 
    @RequestMapping(value="/brand/doEdit.do")
    public String consoleBrandDoEdit(Brand brand){
        Logger.getLogger("brand:iiiiiiiii"+brand+brand.getId());
        brandService.updateById(brand);
        return "redirect:/console/brand/list.do";
    }
    
    
    //添加品牌 
    @RequestMapping(value="/brand/addBrand.do")
    public String consoleBrandAdd(Brand brand){
        Logger.getLogger("brand:iiiiiiiii"+brand+brand.getId());
        brandService.addBrand(brand);
        return "redirect:/console/brand/list.do";
    }
    
    
    //删除多品牌
    @RequestMapping(value="/brand/doBatchDelete.do")
    public String consoleBrandDoBatchDelete(String ids, String name,
            String isDisplay, Integer pageNum) {
        

        System.out.println("ids"+ids);
        brandService.deleteByIds(ids);
//        if(name == null && isDisplay != null){
//        return "redirect:/console/brand/list.do?name=" + "&isDisplay="+isDisplay+
//               "&pageNum=" + pageNum;
//        }else if(name != null && isDisplay == null){
//        return "redirect:/console/brand/list.do?name=" + name+"&isDisplay="+
//                "&pageNum=" + pageNum;
//        }        
        return "redirect:/console/brand/list.do?name=" +name+ "&isDisplay="
        + isDisplay + "&pageNum=" + pageNum;
    }
    
    
    
    //删除单个品牌
    @RequestMapping(value="/brand/singleDelete.do")
    public String consoleBrandDoBatchDelete(Model model,Long brandId,String name,
            Integer isDisplay, Integer pageNum){
        brandService.deleteSingleBrandById(brandId);

//        if("".equals(name) || (isDisplay == null)){
//            return "redirect:/console/brand/list.do?name=" + "&isDisplay="
//                   + "&pageNum=" + pageNum;
//        }
        //null直接传过去会变成字符串“null”
            return "redirect:/console/brand/list.do?name=" + (name==null?"":name)+ "&isDisplay="
            + (isDisplay == null ? "" : isDisplay) + "&pageNum=" + pageNum;
    }
    
   

    
    
    
    
    
    
    
    
//    @RequestMapping(value="/brand/listpages.do")
//    public String findPages(Model model,String name,Integer isDisplay
//            ,Integer pageNum,Integer pageSize){
//        Page1<Brand> page1 = new Page1<Brand>(pageNum,pageSize);
//        Brand brand = new Brand();
//        brand.setName(Encoding.encodeGetRequest(name));   
//        brand.setIsDisplay(isDisplay);
//        page1.setT(brand);
//       Page1 pageBrand = brandService.findPage(page1);
//       logger.debug("pageBrand.result: "+pageBrand.getResult());
//       model.addAttribute("pageBrand", pageBrand);
//       model.addAttribute("name", Encoding.encodeGetRequest(name));
//       model.addAttribute("isDisplay", isDisplay);
//        return "/brand/listpages";
//    }
    
    

}
