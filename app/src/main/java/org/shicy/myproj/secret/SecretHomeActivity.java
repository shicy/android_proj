package org.shicy.myproj.secret;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

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
        this.initActionBarWithTitle(actionBar);
    }

}
