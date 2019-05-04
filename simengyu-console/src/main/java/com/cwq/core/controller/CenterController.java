package com.cwq.core.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping(value="/console")
public class CenterController {
    @RequestMapping(value="{pageName}.do")
    public String consoleIndex(@PathVariable(value="pageName") String pageName){
      
        return pageName;
    }
    
    @RequestMapping(value="/frame/{pageName}.do")
    public String consoleFrameShow(@PathVariable(value="pageName") String pageName){
        return "/frame/"+pageName;
        
    }
    
//    @RequestMapping(value="/product/{pageName}.do")
//    public String consolePrductShow(@PathVariable(value="pageName") String pageName){
//        return "/product/"+pageName;
//        
//    }
    
//    @RequestMapping(value="/brand/{pageName}.do")
//    public String consoleBrandShow(@PathVariable(value="pageName") String pageName){
//        return "/brand/"+pageName;
//        
//    }
}
