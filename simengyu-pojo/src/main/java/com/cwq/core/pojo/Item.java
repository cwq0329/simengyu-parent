package com.cwq.core.pojo;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	// 原始库存信息（复合型对象）
	private SuperPojo sku;
	// 库存id（单独提出来方便操作）
	private Long skuId;
	// 购买数量
	private Integer amount;
	
	   //是否有货
        private Boolean isHave=true;
        

 



    public Boolean getIsHave() {
            return isHave;
        }

        public void setIsHave(Boolean isHave) {
            this.isHave = isHave;
        }

    public SuperPojo getSku() {
		return sku;
	}

	public void setSku(SuperPojo sku) {
		this.sku = sku;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
