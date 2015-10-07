package org.shicy.common.views.form;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.shicy.common.R;
import org.shicy.common.utils.CommonUtils;
import org.shicy.common.utils.DeviceUtils;
import org.shicy.common.utils.StringUtils;

/**
 *
 * Created by Shicy on 2015/10/7.
 */
public class MyEditText extends RelativeLayout {

    private TextView labelTxt;
    private EditText inputTxt;
    private ImageButton clearBtn;

    public MyEditText(Context context) {
        this(context, null, 0);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setBackgroundResource(R.drawable.bg_myedittext);

        TypedArray styles = context.obtainStyledAttributes(attrs, R.styleable.MyEditText);

        LinearLayout linear = new LinearLayout(context);
        linear.setOrientation(LinearLayout.HORIZONTAL);
        linear.setPadding(DeviceUtils.dip2px(context, 10), 0, DeviceUtils.dip2px(context, 10), 0);
        linear.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DeviceUtils.dip2px(context, 45)));
        this.addView(linear);

        labelTxt = new TextView(context);
        labelTxt.setTextSize(14);
        labelTxt.setTextColor(0x50000000);
        labelTxt.setLayoutParams(new LinearLayout.LayoutParams(DeviceUtils.dip2px(context, 80), ViewGroup.LayoutParams.WRAP_CONTENT));
        String labelText = styles.getString(R.styleable.MyEditText_labelText);
        labelTxt.setText(StringUtils.isBlank(labelText) ? "标签" : labelText);
        linear.addView(labelTxt);

        inputTxt = new EditText(context);
        inputTxt.setTextSize(14);
        inputTxt.setBackgroundResource(R.drawable.bg_null);
        inputTxt.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        inputTxt.setText(StringUtils.trimToEmpty(styles.getString(R.styleable.MyEditText_text)));
        inputTxt.setHint(StringUtils.trimToEmpty(styles.getString(R.styleable.MyEditText_placeholder)));
        linear.addView(inputTxt);
    }

}
