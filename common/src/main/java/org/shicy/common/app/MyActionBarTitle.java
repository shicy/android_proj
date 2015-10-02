package org.shicy.common.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.shicy.common.utils.DeviceUtils;

/**
 * 自定义导航栏标题
 * Created by Shicy on 2015/10/1.
 */
public class MyActionBarTitle extends LinearLayout {

    private TextView textView = null;

    public MyActionBarTitle(Context context) {
        this(context, null, 0);
    }

    public MyActionBarTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyActionBarTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyActionBarTitle(Context context, String title) {
        super(context);

        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER_VERTICAL);

        View separator = new RelativeLayout(context);
        separator.setBackgroundColor(0x20ffffff);
        LayoutParams separatorLayout = new LayoutParams(DeviceUtils.dip2px(context, 1), DeviceUtils.dip2px(context, 24));
        separatorLayout.setMargins(0, 0, DeviceUtils.dip2px(context, 10), 0);
        separator.setLayoutParams(separatorLayout);
        this.addView(separator);

        textView = new TextView(context);
        textView.setTextColor(getResources().getColor(android.R.color.white));
        textView.setTextSize(20);
        textView.setText(title);
        this.addView(textView);
    }

    public void setTitle(String title) {
        textView.setText(title);
    }

}
