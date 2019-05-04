package com.cwq.core.dao;

import java.util.List;

import com.cwq.core.pojo.Color;
import com.github.abel533.mapper.Mapper;

public interface ColorDao extends Mapper<Color>{

    
    /**
     * 查询商品可用的颜色
     * @return
     */
    List<Color> findEnableColors();

}
