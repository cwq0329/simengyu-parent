package com.cwq.core.service;

import com.cwq.core.pojo.Buyer;

/**
 * 买家服务类接口
 * @author Administrator
 *
 */
public interface BuyerService {
    /**
     * 通过买家用户名，查询买家
     * @param username
     * @return
     */
    public Buyer findByUsername(String username);
}
