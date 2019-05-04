package com.cwq.core.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwq.core.dao.TbTestDAO;
import com.cwq.core.pojo.TbTest;
@Service("tbTestService")
@Transactional
public class TbTestServiceImpl implements TbTestService {
    @Autowired
    private TbTestDAO tbTestDAO;
    @Override
    public void add(TbTest t) {
        tbTestDAO.add(t);
//        int num = 1/0;
//        TbTest tbTest = new TbTest();   
//        tbTest.setName("bkfgbsjkgbs");
//        tbTest.setBirthday(new Date());
//        tbTestDAO.add(tbTest);

    }

}
