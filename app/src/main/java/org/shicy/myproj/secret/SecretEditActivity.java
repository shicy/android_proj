package org.shicy.myproj.secret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import org.shicy.common.app.MyActionBarTitle;
import org.shicy.common.base.BaseActionBarActivity;
import org.shicy.myproj.R;

/**
 * 密保编辑页面
 * Created by Shicy on 2015/10/2.
 */
public class SecretEditActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_secret_edit);
    }

    @Override
    protected void initActionBar(ActionBar actionBar) {
        super.initActionBar(actionBar);
        this.initActionBarWithCustom(actionBar, new MyActionBarTitle(this,
                getResources().getString(R.string.title_activity_secret)), true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(org.shicy.common.R.menu.menu_btn_finish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        else if (item.getItemId() == org.shicy.common.R.id.action_finish) {
            startActivity(new Intent(this, SecretHomeActivity.class));
            this.finish();
        }
        else return super.onOptionsItemSelected(item);
        return true;
    }
}
