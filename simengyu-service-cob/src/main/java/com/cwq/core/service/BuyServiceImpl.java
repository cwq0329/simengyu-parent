package com.cwq.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwq.core.dao.BuyerDao;
import com.cwq.core.pojo.Buyer;

@Service(value="buyerService")
public class BuyServiceImpl implements BuyerService {
    
    @Autowired
    private BuyerDao buyerDao;

    /**
     * 通过买家用户名，查询买家
     * @param username
     * @return
     */
    public Buyer findByUsername(String username) {
       Buyer buyer = new Buyer();
       buyer.setUsername(username);
//        return buyerDao.selectOne(buyer);
       return buyerDao.findUserByUsername(buyer);
    }

}
