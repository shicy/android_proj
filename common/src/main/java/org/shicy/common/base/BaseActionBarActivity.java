package org.shicy.common.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import org.shicy.common.R;

/**
 *
 * Created by Shicy on 2015/9/27.
 */
public class BaseActionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 保持竖屏
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 初始化ActionBar
        this.initActionBar(this.getSupportActionBar());
    }

    // 初始化页面的ActionBar
    protected void initActionBar(ActionBar actionBar) {
        actionBar.setElevation(0);
    }

    // 初始化导航栏显示用户信息（头像）
    protected void initActionBarWithUser(ActionBar actionBar) {
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setIcon(R.drawable.ic_user_def);
    }

    // 初始化导航栏显示标题
    protected void initActionBarWithTitle(ActionBar actionBar) {
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

}
