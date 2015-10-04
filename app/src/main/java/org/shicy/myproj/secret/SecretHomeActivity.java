package org.shicy.myproj.secret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.shicy.common.app.MyActionBarTitle;
import org.shicy.common.base.BaseActionBarActivity;
import org.shicy.myproj.R;

/**
 *
 * Created by Shicy on 2015/9/27.
 */
public class SecretHomeActivity extends BaseActionBarActivity {

    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_home);

        password = this.getIntent().getStringExtra("password");
        Log.d(SecretHomeActivity.class.getName(), password);
        Toast.makeText(this, password, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void initActionBar(ActionBar actionBar) {
        super.initActionBar(actionBar);
        this.initActionBarWithCustom(actionBar, new MyActionBarTitle(this,
                getResources().getString(R.string.title_activity_secret)), true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(org.shicy.common.R.menu.menu_btn_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        else if (item.getItemId() == org.shicy.common.R.id.action_edit) {
            startActivity(new Intent(this, SecretEditActivity.class));
            this.finish();
        }
        else return super.onOptionsItemSelected(item);
        return true;
    }
}
