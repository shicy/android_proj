package org.shicy.common.views.form;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.shicy.common.R;
import org.shicy.common.utils.DeviceUtils;

/**
 *
 * Created by Shicy on 2015/10/8.
 */
public class MyEditCombobox extends MyEditText {

    private ImageButton dropdownBtn;

    public MyEditCombobox(Context context) {
        this(context, null, 0);
    }

    public MyEditCombobox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEditCombobox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void render(Context context, AttributeSet attrs, LinearLayout container) {
        super.render(context, attrs, container);

        dropdownBtn = new ImageButton(context);
        dropdownBtn.setBackgroundResource(R.drawable.bg_btn_null);
        dropdownBtn.setLayoutParams(new LinearLayout.LayoutParams(layout_height - 2, layout_height - 2));
        dropdownBtn.setPadding(DeviceUtils.dimens_px_as_dip15, DeviceUtils.dimens_px_as_dip15,
                DeviceUtils.dimens_px_as_dip15, DeviceUtils.dimens_px_as_dip15);
        dropdownBtn.setAlpha(0.25f);
        dropdownBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);
        dropdownBtn.setImageResource(R.drawable.ic_arrow_down_b2);
        container.addView(dropdownBtn);
    }
}
