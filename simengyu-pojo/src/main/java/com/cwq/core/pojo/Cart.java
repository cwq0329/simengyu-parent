package com.cwq.core.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 购物车对象类，里面包括用户选择的多个库存商品，以及结算等相关信息
 * 
 * @author Administrator
 *
 */
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	// 购物项的集合
	private List<Item> items = new ArrayList<Item>();

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	/**
	 * 自定义添加item到cart中方法，如果item的skuId（id）一样，则item的购买数量加上amount
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		for (Item item2 : items) {
			if (item2.getSkuId().equals(item.getSkuId())) {
				item2.setAmount(item2.getAmount() + item.getAmount());
				return;
			}
		}
		items.add(item);
	}
	//获得商品总数量，商品总金额，运费 总价格
	/**获得商品总数量
	 * 
	 * @return
	 */
	@JsonIgnore
	public Integer getProductAmount(){
	    Integer result=0;
	    for (Item item : items) {
                result += item.getAmount();
            }
	    return result;
	}
	
	/**
	 * 获得商品总金额
	 * @return
	 */
	@JsonIgnore
	public Float getProductPrice(){
	    Float result=0f;
	    for (Item item : items) {
                result += item.getAmount()*Float.parseFloat(item.getSku().get("price").toString());
            }
	    return result;
	}
	
	/**
	 * 计算运费
	 * @return
	 */
	@JsonIgnore
	public Float getFee(){
	    Float result=0f;
	    if(this.getProductPrice()<80){
	        result=10f;
	    }
	    return result;
	}
	
	/**
	 * 计算总价格
	 * @return
	 */
	@JsonIgnore
	public Float getTotalPrice(){
	    return this.getProductPrice()+this.getFee();
	}

}
