package com.book.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @ClassName WebUtils
 * @Description TODO
 * @Author LuckyAzrael
 * @Date 2020/12/24 20:19
 * @Version 1.0
 **/
public class WebUtils {

    /**
     * 把Map中的值注入到对应的JavaBean属性中,并返回一个对应的JavaBean对象
     * @param value
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBean(Map value,Class<T> clazz){
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.populate(t,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将字符串转换为int类型的数据
     * @param str
     * @param defaultValue 默认值
     * @return 入如果转换成功，则返回相应的值，如果转换失败，则返回默认值
     */
    public static int parseInt(String str,int defaultValue){
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


}
