package org.shicy.myproj.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.shicy.common.base.BaseActionBarActivity;
import org.shicy.myproj.R;
import org.shicy.myproj.secret.SecretActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActionBarActivity {

    private List<Map<String, Object>> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.showItems();
    }

    @Override
    protected void initActionBar(ActionBar actionBar) {
        super.initActionBar(actionBar);
        this.initActionBarWithUser(actionBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(org.shicy.common.R.menu.menu_user_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void showItems() {
        this.addItem("secret", "密宝", R.drawable.ic_main_secret, 0xffE63015);
        this.addItem("home", "我的首页", R.drawable.ic_main_home, 0xff0099FF);
        this.addItem("calendar", "日历", R.drawable.ic_main_calendar, 0xff99CC66);
        this.addItem("express", "查快递", R.drawable.ic_main_express, 0xff66CC00);
        this.addItem("more", "更多", R.drawable.ic_main_more, 0xffFFCC66);

        GridView grid = (GridView)findViewById(R.id.gridview);
        grid.setAdapter(new MainItemAdapter(this, dataList));
        grid.setOnItemClickListener(new ItemClickListener());
    }

    private void addItem(String name, String title, int imgId, int bgcolor) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("image", imgId);
        map.put("title", title);
        map.put("bgcolor", bgcolor);
        dataList.add(map);
    }

    // 模块点击事件
    private class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, Object> data = (Map<String, Object>)parent.getItemAtPosition(position);
            switch ((String)data.get("name")) {
                case "secret":
                    startActivity(new Intent(MainActivity.this, SecretActivity.class));
                    break;
                default:
                    Toast.makeText(getApplicationContext(), (String) data.get("title"), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
