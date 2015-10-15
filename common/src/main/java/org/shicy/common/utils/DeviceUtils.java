package org.shicy.common.utils;

import android.content.Context;
import android.util.Log;

/**
 * 设备相关
 * Created by Shicy on 2015/10/2.
 */
public class DeviceUtils {

    private static boolean hasInit = false;

    public static int dimens_px_as_dip10 = 10;
    public static int dimens_px_as_dip12 = 12;
    public static int dimens_px_as_dip15 = 15;
    public static int dimens_px_as_dip20 = 20;
    public static int dimens_px_as_dip30 = 30;
    public static int dimens_px_as_dip50 = 50;

    public static int size_box_padding = 10;

    public static int size_font_normal = 14;

    public static int standard_line_height = 45;

    /**
     * 根据应用上下文初始化设备信息
     * @param context 应用上下文信息
     */
    public static void initWithContext(Context context) {
        hasInit = true;

        dimens_px_as_dip10 = dip2px(context, dimens_px_as_dip10);
        dimens_px_as_dip12 = dip2px(context, dimens_px_as_dip12);
        dimens_px_as_dip15 = dip2px(context, dimens_px_as_dip15);
        dimens_px_as_dip20 = dip2px(context, dimens_px_as_dip20);
        dimens_px_as_dip30 = dip2px(context, dimens_px_as_dip30);
        dimens_px_as_dip50 = dip2px(context, dimens_px_as_dip50);

        size_box_padding = dip2px(context, size_box_padding);

        standard_line_height = dip2px(context, standard_line_height);
    }

    /**
     * 辅助开发
     * @param context 开发时临时的上下文信息
     */
    public static void tryInitWithContext(Context context) {
        if (hasInit) return ;
        hasInit = true;
        initWithContext(context);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
