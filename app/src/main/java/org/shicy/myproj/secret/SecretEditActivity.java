package org.shicy.myproj.secret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.shicy.common.app.MyActionBarTitle;
import org.shicy.common.base.BaseActionBarActivity;
import org.shicy.common.views.form.MyEditCombobox;
import org.shicy.myproj.R;

/**
 * 密保编辑页面
 * Created by Shicy on 2015/10/2.
 */
public class SecretEditActivity extends BaseActionBarActivity {

    MyEditCombobox groupIpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_secret_edit);

        String description = "<font color='#999999'>您账户和密码是进行安全加密存储的。<br/><br/>" +
                "密保的加密和解密都依赖于你的口令，每个人的口令不同，即使是相同的账号密码加密或解密的结果也是不同的。" +
                "（</font><font color='#ff0000'>从此保护好你的口令，常换口令是一个好习惯</font><font color='#999999'>）<br/><br/>" +
                "密保允许任何口令通过，如果口令错误，只会提供给你一个错误的结果。（</font><font color='#ff0000'>防止暴力破解</font><font color='#999999'>）</font>";
        TextView descriptionTxt = (TextView)findViewById(R.id.secret_edit_description);
        descriptionTxt.setText(Html.fromHtml(description));

        this.groupIpt = (MyEditCombobox)findViewById(R.id.secret_edit_group);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String[] groups = new String[]{"囧分为氛围分", "就few减肥我减肥我", "ifudshfueiwhf", "会few金佛额忘记哦", "jfd",
            "减肥的死哦但是", "囧非我非我附加额外fewfewfew", "菲菲我few热哦捂热偶哦俄乌切", "ifodsjfoewfje", "汇丰我发觉额我减肥"};
        this.groupIpt.setData(groups, "");
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
            if (this.save()) {
                startActivity(new Intent(this, SecretHomeActivity.class));
                this.finish();
            }
        }
        else return super.onOptionsItemSelected(item);
        return true;
    }

    // 保存密保信息
    private boolean save() {
        return false;
    }
}
