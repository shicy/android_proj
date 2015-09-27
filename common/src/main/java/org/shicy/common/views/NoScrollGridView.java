package org.shicy.common.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 当GridView套在ScrollView中使用时，滚动会异常，因为GridView自带滚动条，与ScrollView的滚动条冲突。
 * 本类动态更新GridView的高度，使得GridView滚动条失效
 * Created by Shicy on 2015/9/13.
 */
public class NoScrollGridView extends GridView {

    public NoScrollGridView(Context context) {
        super(context);
    }

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
