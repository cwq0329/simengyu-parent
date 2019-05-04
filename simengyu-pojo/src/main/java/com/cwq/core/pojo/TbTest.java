package com.cwq.core.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试表实体类
 * @author Administrator
 *
 */
public class TbTest implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Date birthday;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public TbTest() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
   
    
}
