package org.shicy.common.views.form;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.shicy.common.R;
import org.shicy.common.utils.DeviceUtils;
import org.shicy.common.utils.ResourceUtils;
import org.shicy.common.utils.StringUtils;

/**
 *
 * Created by Shicy on 2015/10/7.
 */
public class MyEditText extends RelativeLayout implements TextWatcher {

    private TextView labelTxt;
    private EditText inputTxt;
    private ImageButton clearBtn;
    private LinearLayout container;

    protected int layout_height = DeviceUtils.standard_line_height;

    public MyEditText(Context context) {
        this(context, null, 0);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setBackgroundResource(R.drawable.bg_myedittext);

        LinearLayout linear = container = new LinearLayout(context);
        linear.setOrientation(LinearLayout.HORIZONTAL);
        linear.setGravity(Gravity.CENTER_VERTICAL);
        linear.setPadding(DeviceUtils.size_box_padding, 0, 0, 0);
        this.addView(linear, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, layout_height));

        this.render(context, attrs, linear);
    }

    protected void render(Context context, AttributeSet attrs, LinearLayout container) {
        TypedArray styles = context.obtainStyledAttributes(attrs, R.styleable.MyEditText);
        this.renderLabel(context, container, styles);
        this.renderInput(context, container, styles);
        styles.recycle();
    }

    private void renderLabel(Context context, LinearLayout container, TypedArray styles) {
        labelTxt = new TextView(context);
        labelTxt.setTextSize(DeviceUtils.size_font_normal);
        labelTxt.setTextColor(getResources().getColor(R.color.form_label));
        labelTxt.setText(ResourceUtils.getStringStyle(styles, R.styleable.MyEditText_labelText, "标签"));
        int layout_width = styles.getDimensionPixelSize(R.styleable.MyEditText_labelSize, DeviceUtils.dip2px(context, 80));
        container.addView(labelTxt, new LinearLayout.LayoutParams(layout_width, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void renderInput(Context context, LinearLayout container, TypedArray styles) {
        inputTxt = new EditText(context);
        inputTxt.setTextSize(DeviceUtils.size_font_normal);
        inputTxt.setBackgroundResource(R.drawable.bg_null);
        inputTxt.setPadding(0, 0, 0, 0);
        inputTxt.setText(StringUtils.trimToEmpty(styles.getString(R.styleable.MyEditText_text)));
        inputTxt.setHint(StringUtils.trimToEmpty(styles.getString(R.styleable.MyEditText_placeholder)));
        container.addView(inputTxt, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        inputTxt.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (StringUtils.isBlank(s.toString())) {
            if (clearBtn != null)
                clearBtn.setVisibility(GONE);
        }
        else {
            if (clearBtn == null) {
                clearBtn = new ImageButton(getContext());
                clearBtn.setBackgroundResource(R.drawable.bg_btn_null);
                clearBtn.setImageResource(R.drawable.ic_close_b);
                clearBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);
                clearBtn.setAlpha(0.25f);
                clearBtn.setPadding(DeviceUtils.dimens_px_as_dip12, DeviceUtils.dimens_px_as_dip12,
                        DeviceUtils.dimens_px_as_dip12, DeviceUtils.dimens_px_as_dip12);
                clearBtn.setLayoutParams(new LinearLayout.LayoutParams(layout_height - 2, layout_height - 2));
                container.addView(clearBtn);

                clearBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inputTxt.setText("");
                    }
                });
            }
            clearBtn.setVisibility(VISIBLE);
        }
    }
}
