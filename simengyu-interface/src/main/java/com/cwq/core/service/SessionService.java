package com.cwq.core.service;

/**
 * session服务类主要做session信息存取redis
 * @author Administrator
 *
 */
public interface SessionService {
    /**
     * 将用户信息存到redis中
     * @param key mysession
     * @param value 用户名
     */
    public void addSessionToRedis(String sessionId,String username);
    /**
     * 从redis中取出用户信息
     * @param key myssionid
     * @return
     */
    public String getUsernameFromRedis(String sessionId);
}
