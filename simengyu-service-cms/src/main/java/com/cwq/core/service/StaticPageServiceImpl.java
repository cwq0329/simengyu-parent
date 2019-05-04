package com.cwq.core.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 静态化服务实现类
 * @author Administrator
 *
 */
@Service(value="StaticPageService")
public class StaticPageServiceImpl implements StaticPageService,ServletContextAware {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public void staticProductPage(Map<String, Object> map, String id) {
            try {
                // 使用spring的freemarker配置获得标准的freemarker配置器
                Configuration configuration = freeMarkerConfigurer.getConfiguration();
                // 生成的文件位置
                String realPath = servletContext.getRealPath("/html/product/" + id + ".html");
                System.out.println("生成的文件位置:" + realPath);
                // 获得最终文件的父目录
                File file = new File(realPath);
                File parentFile = file.getParentFile();
                // 如果父目录不存在,则进行创建
                if (!parentFile.exists()) {
                        parentFile.mkdirs();
                }
                // 加载模板文件
                Template template = configuration.getTemplate("product.html");
                // 设置输出文件的位置
                Writer fileWriter = new FileWriter(new File(realPath));
                // 开始的输出
                template.process(map, fileWriter);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (TemplateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
            this.servletContext = servletContext;

    }
}
