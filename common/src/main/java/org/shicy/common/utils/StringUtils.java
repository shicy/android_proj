package org.shicy.common.utils;

import java.util.List;

/**
 * 字符串常用方法
 * Created by Shicy on 2015/10/4.
 */
public class StringUtils {

    /**
     * 判断字符串是否为空字符串，null, "", " ", "\t", "\n"等认为是空字符串
     * @param str 原字符串
     * @return 字符串为空时返回true，否则返回false
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() <= 0;
    }

    /**
     * 判断列表是否为空
     * @param data 列表
     * @return 为null或空列表时返回true，否则返回false
     */
    public static boolean isEmpty(List data) {
        return data == null || data.size() == 0;
    }

}
