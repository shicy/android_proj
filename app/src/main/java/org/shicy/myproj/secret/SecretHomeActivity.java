package org.shicy.myproj.secret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.shicy.common.app.MyActionBarTitle;
import org.shicy.common.base.BaseActionBarActivity;
import org.shicy.common.base.BaseService;
import org.shicy.common.utils.ArrayUtils;
import org.shicy.common.utils.StringUtils;
import org.shicy.myproj.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 密保主页，一个列表页面
 * Created by Shicy on 2015/9/27.
 */
public class SecretHomeActivity extends BaseActionBarActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private String password = "";
    private boolean emptyFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_home);

        // 用户口令
        this.password = this.getIntent().getStringExtra("password");
    }

    @Override
    protected void onStart() {
        super.onStart();

        SecretService secretService = BaseService.getService(SecretService.class);
        List<SecretEntity> secretEntities = secretService.getAllSecretInfos();
        this.emptyFlag = secretEntities == null || secretEntities.size() <= 0;

        ListView listView = (ListView)this.findViewById(R.id.listview);
        if (secretEntities != null && secretEntities.size() > 0) {
            this.findViewById(R.id.secret_home_empty).setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(new SecretItemAdapter(this, secretEntities, false));
        }
        else {
            this.findViewById(R.id.secret_home_btn_add).setOnClickListener(this);
        }
    }

    @Override
    protected void initActionBar(ActionBar actionBar) {
        super.initActionBar(actionBar);
        this.initActionBarWithCustom(actionBar, new MyActionBarTitle(this,
                getResources().getString(R.string.title_activity_secret)), true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!this.emptyFlag)
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

    @Override
    public void onClick(View v) {
        // 点击添加按钮
        if (v.getId() == R.id.secret_home_btn_add) {
            Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
