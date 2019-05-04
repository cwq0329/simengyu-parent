package com.cwq.core.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestSolr {
    
    @Autowired
    private HttpSolrServer solrServer;
    
    /**
     * 创建索引传统的
     * @throws IOException 
     * @throws SolrServerException 
     */
    @Test
    public void createdIndex1() throws SolrServerException, IOException{
        //创建solr服务端
        HttpSolrServer solrServer = new HttpSolrServer("http://192.168.40.101:8080/solr/collection1/");
        //创建输入文档对象
        SolrInputDocument document = new SolrInputDocument();
        //添加字段的文档对象
        document.addField("id", "1");
        document.addField("name_ik", "白富美——xxx");
        
        //添加文档对象到solr服务器
        solrServer.add(document);
        
        //t提交
        solrServer.commit();
        
        
    }
    
    @Test
    public void createdIndex2() throws SolrServerException, IOException{
      
      SolrInputDocument document = new SolrInputDocument();
      
      document.addField("id", "2");
      document.addField("name_ik", "高富帅-xxxx");
        
      solrServer.add(document);
      
      solrServer.commit();
        
    }
}
