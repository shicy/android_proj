package org.shicy.common.utils;

import android.content.res.TypedArray;

/**
 * 资源相关工具类
 * Created by Shicy on 2015/10/9.
 */
public class ResourceUtils {

    public static String getStringStyle(TypedArray style, int index, String defValue) {
        String value = style.getString(index);
        return StringUtils.isBlank(value) ? defValue : value;
    }

}
