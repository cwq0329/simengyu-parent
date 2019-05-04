package com.cwq.core.pojo;

import java.io.Serializable;
import java.util.List;

public class Page1<T> implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer pageNum;
    private Integer pageSize;

    
    private List<T> result;
    private Long totalNum;
    private Long totalPages;
    
    public Page1() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Page1(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum < 1) {
                pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
                pageSize = 3;
        }
        this.pageNum = pageNum;
        this.pageSize = pageSize;
}
    public Integer getPageNum() {
        return pageNum;
    }
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
  
    public List<T> getResult() {
        return result;
    }
    public void setResult(List<T> result) {
        this.result = result;
    }
    public Long getTotalNum() {
        return totalNum;
    }
    public void setTotalNum(Long totalNum) {
        totalPages=(totalNum+pageSize-1)/pageSize;
        this.totalNum = totalNum;
    }
    public Long getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

      

    }


