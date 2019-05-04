package com.cwq.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service(value="sessionService")
public class SessionServiceImpl implements SessionService {
    
    @Autowired
    private Jedis jedis;
    /**
     * 将用户信息存到redis中
     * @param key mysession
     * @param value 用户名
     */
    public void addSessionToRedis(String sessionId, String usename) {
        jedis.set(sessionId+":username", usename);
        //设置失效时间
        jedis.expire(sessionId+":username", 1800);

    }
    
    /**
     * 从redis中取出用户信息
     * @param key myssionid
     * @return
     */
    public String getUsernameFromRedis(String sessionId) {
        String username = jedis.get(sessionId+":username");
        if(username != null){
            //设置失效时间
            jedis.expire(sessionId+":username", 1800);
        }
        return username;
    }

}
