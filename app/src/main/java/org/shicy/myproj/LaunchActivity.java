package org.shicy.myproj;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import org.shicy.common.base.BaseActivity;

/**
 * 启动页
 * Created by Shicy on 2015/9/25.
 */
public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = this.getWindow();
            // 设置底部导航栏透明
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.app_launch);
        image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        setContentView(image);

        // 延时跳转到主页页面
        this.waitToMainActivity();
    }

    private void waitToMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                LaunchActivity.this.finish();
            }
        }, 2000);
    }
}
