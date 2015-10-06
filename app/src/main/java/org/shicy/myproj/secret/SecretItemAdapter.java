package org.shicy.myproj.secret;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.shicy.common.utils.ArrayUtils;
import org.shicy.common.utils.StringUtils;
import org.shicy.myproj.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Shicy on 2015/10/5.
 */
public class SecretItemAdapter extends BaseAdapter {

    private final static int TYPE_GROUP = 1;
    private final static int TYPE_ITEM_NORMAL = 2;
    private final static int TYPE_ITEM_EDIT = 3;

    private LayoutInflater inflater;
    private List dataList;
    private boolean editable = false;
    private OnItemBtnClickListener listener;

    /**
     * 构造方法
     * @param context 上下文
     * @param data 数据集
     * @param editable 是否可编辑
     */
    public SecretItemAdapter(Context context, List<SecretEntity> data, boolean editable) {
        inflater = LayoutInflater.from(context);
        dataList = this.sortByGroup(data);
        this.editable = editable;
    }

    /**
     * 设置按钮点击事件
     * @param listener 事件监听方法
     */
    public void setOnItemBtnClickListener(OnItemBtnClickListener listener) {
        this.listener = listener;
    }

    /**
     * 按分组排序
     * @param entities 实例对象
     * @return 插入分组并排序
     */
    private List sortByGroup(List<SecretEntity> entities) {
        if (entities == null || entities.size() == 0)
            return new ArrayList();

        List<String> groups = new ArrayList<>();
        List<List<SecretEntity>> items = new ArrayList<>();
        for (SecretEntity entity: entities) {
            String groupName = entity.getGroupName();
            if (StringUtils.isBlank(groupName))
                groupName = "未分组";
            boolean addFlag = true;
            for (int i = 0; i < groups.size(); i++) {
                if (groupName.equals(groups.get(i))) {
                    items.get(i).add(entity);
                    addFlag = false;
                    break;
                }
            }
            if (addFlag) {
                groups.add(groupName);
                items.add(ArrayUtils.arrayToList(new Object[]{entity}, SecretEntity.class));
            }
        }

        List results = new ArrayList();
        for (int i = 0; i < groups.size(); i++) {
            results.add(groups.get(i));
            results.addAll(items.get(i));
        }
        return results;
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
            convertView = this.createView(position, parent);
        }

        int type = this.getItemViewType(position);
        Object item = this.getItem(position);

        ViewHolder holder = (ViewHolder)convertView.getTag();
        if (type == TYPE_GROUP) {
            holder.titleTxt.setText((String)item);
        }
        else {
            SecretEntity entity = (SecretEntity)item;
            holder.titleTxt.setText(entity.getTitle());
            if (type == TYPE_ITEM_EDIT) {
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null)
                            listener.onButtonClick(v);
                    }
                });
            }
        }

        return convertView;
    }

    private View createView(int position, ViewGroup parent) {
        View view;
        ViewHolder holder = new ViewHolder();

        int type = this.getItemViewType(position);
        if (type == TYPE_GROUP) {
            view = inflater.inflate(R.layout.adapter_secret_list_item1, parent, false);
        }
        else if (type == TYPE_ITEM_EDIT) {
            view = inflater.inflate(R.layout.adapter_secret_list_item3, parent, false);
            holder.deleteBtn = (Button)view.findViewById(R.id.secret_list_item_delete);
        }
        else {
            view = inflater.inflate(R.layout.adapter_secret_list_item2, parent, false);
        }
        holder.titleTxt = (TextView)view.findViewById(R.id.secret_list_item_title);
        view.setTag(holder);
        return view;
    }

    /**
     * 视图缓存
     */
    private static class ViewHolder {
        private TextView titleTxt;
        private Button deleteBtn;
    }

    /**
     * 按钮点击事件
     */
    public interface OnItemBtnClickListener {
        void onButtonClick(View button);
    }

}
