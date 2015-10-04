package org.shicy.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 常用方法工具类
 * Created by Shicy on 2015/10/3.
 */
public class CommonUtils {

    /**
     * MD5加密获得加密后的字符串
     * @param str
     * @return
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
