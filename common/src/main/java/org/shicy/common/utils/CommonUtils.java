package org.shicy.common.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

/**
 * 常用方法工具类
 * Created by Shicy on 2015/10/3.
 */
public class CommonUtils {

    /**
     * 获取对象的公共属性值，优先通过getter,is方法获取对象
     * @param owner 原始对象
     * @param fieldName 属性名称
     * @return 对象属性值
     */
    public static Object getProperty(Object owner, String fieldName) throws Exception {
        if (StringUtils.isBlank(fieldName))
            return null;
        if (!ArrayUtils.isArray(owner)) {
            if (owner instanceof Map) {
                return ((Map)owner).get(fieldName);
            }
            else {
                Class ownerCls = owner.getClass();
                Method method = null;
                String methodName = ("" + fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);

                try {
                    method = ownerCls.getMethod("get" + methodName, new Class[0]);
                    if (Modifier.isPublic(method.getModifiers())) {
                        return method.invoke(owner, new Object[0]);
                    }
                }
                catch (NoSuchMethodException e) {
                    try {
                        method = ownerCls.getMethod("is" + methodName, new Class[0]);
                    }
                    catch (NoSuchMethodException e1) {
                        //
                    }
                }

                if (method != null && Modifier.isPublic(method.getModifiers())) {
                    return method.invoke(owner, new Object[0]);
                }

                try {
                    Field field = ownerCls.getField(fieldName);
                    return field.get(owner);
                }
                catch (NoSuchFieldException e) {
                    //
                }
            }
        }
        return null;
    }

    /**
     * MD5加密获得加密后的字符串
     * @param str 原字符串
     * @return 加密后的字符串
     */
    public static String md5(String str) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("不支持 MD5 加密：");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("使用 UTF8 编码格式.");
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
