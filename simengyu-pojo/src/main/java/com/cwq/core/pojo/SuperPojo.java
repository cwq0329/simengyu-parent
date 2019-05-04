package com.cwq.core.pojo;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * 超级实体类对象,可以装载任意实体类之任意属性,可以用于装载多表连接查询返回的复合数据
 * 
 * @author Administrator
 *
 */
public class SuperPojo extends TreeMap<String, Object> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 设置实体类属性
	 * 
	 * @param name属性名
	 * @param value属性值
	 * @return已经设置好的实体类本身,实现连点设置属性效果
	 */
	public SuperPojo setProperty(String name, Object value) {
		this.put(name, value);
		return this;
	}
}
