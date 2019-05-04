package com.cwq.core.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * 自定义转换器类
 * 
 * @author Administrator
 * @param <S>
 *            转换前的类型
 * @param <T>
 *            转换后的类型
 *
 */
public class MyConverter implements Converter<String, String> {

        @Override
        public String convert(String source) {
                // 去掉字符串前后空格
                try {
                        if (source != null) {
                                source = source.trim();
                                if (!"".equals(source)) {
                                        return source;
                                }
                        }
                } catch (Exception e) {
                        // TODO: handle exception
                }
                return null;
        }
}

