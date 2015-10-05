package org.shicy.myproj.secret;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Shicy on 2015/10/5.
 */
public class SecretItemAdapter extends BaseAdapter {

    private final static int TYPE_GROUP = 0;
    private final static int TYPE_ITEM_NORMAL = 1;
    private final static int TYPE_ITEM_EDIT = 2;

    private LayoutInflater inflater;
    private List dataList;
    private boolean editable = false;

    /**
     * 构造方法
     * @param context 上下文
     * @param data 数据集
     * @param editable 是否可编辑
     */
    public SecretItemAdapter(Context context, List data, boolean editable) {
        inflater = LayoutInflater.from(context);
        dataList = data != null ? data : new ArrayList();
        this.editable = editable;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public long getItemId(int position) {
        Object item = this.getItem(position);
        if (item instanceof SecretEntity)
            return ((SecretEntity)item).getId();
        // 如果是分组的话，返回一个负数编号
        return 0 - position;
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = this.getItem(position);
        if (item instanceof SecretEntity) {
            return editable ? TYPE_ITEM_EDIT : TYPE_ITEM_NORMAL;
        }
        return TYPE_GROUP;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            
        }
        return null;
    }

    /**
     * 视图缓存
     */
    private static class ViewHolder {

    }

}
