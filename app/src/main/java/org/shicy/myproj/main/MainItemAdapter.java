package org.shicy.myproj.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.shicy.myproj.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Shicy on 2015/9/8.
 */
public class MainItemAdapter extends BaseAdapter {

    private List<? extends Map<String, ?>> data;
    private LayoutInflater inflater;

    public MainItemAdapter(Context context, List<? extends Map<String, ?>> data) {
        this.data = data != null ? data : new ArrayList<Map<String, Object>>();
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = this.inflater.inflate(R.layout.activity_main_grid_item, parent, false);

        Map<String, Object> map = (Map<String, Object>)this.getItem(position);

        ImageView imageView = (ImageView)convertView.findViewById(R.id.grid_item_img);
        imageView.setImageResource((int) map.get("image"));

        if (map.get("bgcolor") != null)
            convertView.findViewById(R.id.grid_item_content).setBackgroundColor((int)map.get("bgcolor"));

        return convertView;
    }
}
