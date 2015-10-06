package org.shicy.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Shicy on 2015/10/6.
 */
public class ArrayUtils {

    /**
     * 数组转成列表
     * @param items 原数组对象集
     * @param cls 目标对象类
     * @param <T> 类
     * @return 对象列表
     */
    public static <T> List<T> arrayToList(Object[] items, Class<T> cls) {
        List<T> list = new ArrayList<>();
        for (Object obj: items) {
            list.add((T)obj);
        }
        return list;
    }
}
