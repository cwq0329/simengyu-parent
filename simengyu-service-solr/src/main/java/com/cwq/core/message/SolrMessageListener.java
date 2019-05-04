package com.cwq.core.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import com.cwq.core.service.SolrService;

/**自定义消息监听类
 * @author Administrator
 *
 */

public class SolrMessageListener implements MessageListener{
    @Autowired
    private SolrService solrService;

    /**
     * 当监听到消息后自动调用此方法
     * @author Administrator
     *
     */
    public void onMessage(Message message) {
        ActiveMQTextMessage amessage = (ActiveMQTextMessage)message;
        
        try {
            String ids = amessage.getText();
            System.out.println("消费方监听到消息："+ids);
            solrService.addProductToSolr(ids);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}