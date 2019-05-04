package com.cwq.core.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * 自定义转换器类 S:转换前类型
 * 
 * @author Administrator T:转换后类型
 */
public class MyConverter implements Converter<String, String> {

	// 去掉字符串前后空格
	@Override
	public String convert(String source) {
		try {
			if (source != null) {
				String trim = source.trim();
				if (!"".equals(trim)) {
					return trim;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
