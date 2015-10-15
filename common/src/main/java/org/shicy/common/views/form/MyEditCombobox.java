package org.shicy.common.views.form;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.shicy.common.R;
import org.shicy.common.utils.CommonUtils;
import org.shicy.common.utils.DeviceUtils;
import org.shicy.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Shicy on 2015/10/8.
 */
public class MyEditCombobox extends MyEditText {

    private String labelField = "label";
    private Object[] datas = null;
    private int selectedIndex = -1;
    private boolean autoMatch = true;

    // 构造方法
    public MyEditCombobox(Context context) {
        this(context, null, 0);
    }

    // 构造方法
    public MyEditCombobox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // 构造方法
    public MyEditCombobox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.inputTxt.setSelectAllOnFocus(true);
    }

    @Override
    protected void render(Context context, AttributeSet attrs, LinearLayout container) {
        super.render(context, attrs, container);

        // 下拉对话框按钮（下拉箭头）
        ImageButton dropdownBtn = new ImageButton(context);
        dropdownBtn.setBackgroundResource(R.drawable.bg_btn_null);
        dropdownBtn.setLayoutParams(new LinearLayout.LayoutParams(layout_height - 2, layout_height - 2));
        dropdownBtn.setPadding(DeviceUtils.dimens_px_as_dip15, DeviceUtils.dimens_px_as_dip15,
                DeviceUtils.dimens_px_as_dip15, DeviceUtils.dimens_px_as_dip15);
        dropdownBtn.setAlpha(0.25f);
        dropdownBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);
        dropdownBtn.setImageResource(R.drawable.ic_arrow_down_b2);
        container.addView(dropdownBtn);

        dropdownBtn.setOnClickListener(new DropdownClickListener());
    }

    // 设置文本内容，但不自动配置项
    public void setText(String text, boolean nomatch) {
        autoMatch = nomatch;
        this.setText(text);
    }

    // 设置原数据集
    // labelField指用于选择显示的属性名称
    public void setData(Object[] datas, String labelField) {
        if (!StringUtils.isBlank(labelField))
            this.labelField = labelField;
        this.datas = datas;
    }

    // 设置原数据集
    public void setData(List listData, String labelField) {
        this.setData(listData.toArray(), labelField);
    }

    @Override
    protected void textChanged(String text, String oldText, boolean back) {
        List<String> labels = new ArrayList<>();
        for (Object data: datas) {
            labels.add(getTitleLabel(data));
        }

        int lastIndex = selectedIndex;
        selectedIndex = labels.indexOf(text);

        if (selectedIndex < 0 && !back && autoMatch) {
            int textLength = text.length();
            if (oldText != null && oldText.startsWith(text) && lastIndex >= 0) {
                selectedIndex = lastIndex;
                this.setText(oldText);
                Selection.setSelection(inputTxt.getEditableText(), textLength, oldText.length());
            }
            else {
                for (int i = 0; i < labels.size(); i++) {
                    String label = labels.get(i);
                    if (label.startsWith(text)) {
                        selectedIndex = i;
                        int labelLength = label.length();
                        if (textLength != labelLength) {
                            this.setText(label);
                            Selection.setSelection(inputTxt.getEditableText(), textLength, labelLength);
                        }
                        break;
                    }
                }
            }
        }
        autoMatch = true;
    }

    // 项目被点击
    protected void itemClicked(int index) {
        if (index != selectedIndex)
            this.itemChanged(index, selectedIndex);
    }

    // 选择项变更
    protected void itemChanged(int index, int oldIndex) {
        selectedIndex = index;
        Object data = (index >= 0 && index < datas.length) ? datas[index] : null;
        String text = data != null ? getTitleLabel(data) : null;
        this.setText(text != null ? text : "");
        if (text != null)
            Selection.setSelection(inputTxt.getEditableText(), text.length());
    }

    // 点击下拉箭头
    private class DropdownClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("选择 " + getLabel());

            List<String> labels = new ArrayList<>();
            for (Object data: datas) {
                labels.add(getTitleLabel(data));
            }

            builder.setItems(labels.toArray(new String[0]), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    itemClicked(which);
                }
            });
            builder.show();
        }
    }

    // 获取标签文本内容
    protected String getTitleLabel(Object data) {
        if (data == null)
            return "<Null>";
        if (data instanceof String)
            return (String)data;
        if (StringUtils.isBlank(labelField))
            return "" + data;
        try {
            return "" + CommonUtils.getProperty(data, labelField);
        }
        catch (Exception e) {
            //
        }
        return "" + data;
    }

}
