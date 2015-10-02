package org.shicy.myproj.secret;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;

import org.shicy.common.app.MyActionBarTitle;
import org.shicy.common.base.BaseActionBarActivity;
import org.shicy.myproj.R;

/**
 *
 * Created by Shicy on 2015/9/27.
 */
public class SecretHomeActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_home);
    }

    @Override
    protected void initActionBar(ActionBar actionBar) {
        super.initActionBar(actionBar);
        MyActionBarTitle myActionBarTitle = new MyActionBarTitle(this,
                getResources().getString(R.string.title_activity_secret));
        this.initActionBarWithCustom(actionBar, myActionBarTitle, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_btn_edit, menu);
        return true;
    }
}
