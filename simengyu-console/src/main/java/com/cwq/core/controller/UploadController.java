package com.cwq.core.controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.cwq.core.dictionary.Constants;
import com.cwq.core.tools.FastDFSTool;

/**
 * 上传文件控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class UploadController {

        // 品牌修改添加页面上传单张图片
        @RequestMapping(value = "/uploadFile.do")
        @ResponseBody
        public HashMap<String, String> uploadFile(
                        @RequestParam(value = "pic", required = false) MultipartFile file) {
                // System.out.println(file.getOriginalFilename());
                // 将文件上传到分布式文件系统,并且返回文件的路径及文件名
                try {
                        // // 例如： group1/M00/00/00/wKg4ZViGbUOAWYBOAAFcP6dp0kY652.jpg
                        String uploadFile = FastDFSTool.uploadFile(file.getBytes(),
                                        file.getOriginalFilename());
                        // 返回json格式的字符串(图片的绝对网络存放地址)
                        HashMap<String, String> map = new HashMap<String, String>();
                        // http://192.168.40.101:8888/group1/M00/00/00/wKg4ZViGbUOAWYBOAAFcP6dp0kY652.jpg
                        map.put("path", Constants.FAST_SERVER + uploadFile);

                        return map;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;
        }
        
        //添加商品同时上传多张图片
        @RequestMapping(value="/upload/uploadPics.do")
        @ResponseBody
        public List<String> uploadFiles(@RequestParam(value="pics", required=false) MultipartFile[] files){
            //上传文件返回的路径集合
            List<String> list = new ArrayList<String>();
            try {
                for (MultipartFile file : files) {
                    //将文件上传到分布式文件系统，并且返回文件的路径及文件名
                        String uploadFile = FastDFSTool.uploadFile(file.getBytes(), file.getOriginalFilename());
                    //返回json格式的字符串(图片的绝对网络存放地址)
                        list.add(Constants.FAST_SERVER+uploadFile);               
                }
                return list;
            } catch (Exception e) {
               
                e.printStackTrace();
            }
            return null;
        }
        
        //接受富文本编辑器上传图片（不考虑文件的name强行接受）
        @RequestMapping("/upload/uploadFck.do")
        @ResponseBody
        public HashMap<String, Object> uploadFile(HttpServletRequest request,
                        HttpServletResponse response) {

                try {
                        // 将request强转为spring提供的multipartRequest
                        MultipartRequest mr = (MultipartRequest) request;
                        // 获得mutiFile里的所有文件
                        Set<Entry<String, MultipartFile>> entrySet = mr.getFileMap().entrySet();
                        for (Entry<String, MultipartFile> entry : entrySet) {
                                MultipartFile mf = entry.getValue();
                                // 将文件上传到分布式文件系统,并且返回路径及图片名称
                                String uploadFile = FastDFSTool.uploadFile(mf.getBytes(),
                                                mf.getOriginalFilename());
                                HashMap<String, Object> map = new HashMap<String, Object>();
                                // error和url都是固定的
                                map.put("error", 0);
                                map.put("url", Constants.FAST_SERVER + uploadFile);
                                return map;
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }
                return null;
        }
        
}
