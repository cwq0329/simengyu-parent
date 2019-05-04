package com.cwq.core.dao;

import com.cwq.core.pojo.Buyer;
import com.github.abel533.mapper.Mapper;

/**
 * 买家管理dao
 * @author Administrator
 *
 */
public interface BuyerDao extends Mapper<Buyer>{

    
    /**通过用户名查询用于信息
     * @param buyer
     * @return
     */
    Buyer findUserByUsername(Buyer buyer);

}
