package org.shicy.myproj.secret;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.shicy.common.base.BaseActionBarActivity;
import org.shicy.common.datasource.AppConfig;
import org.shicy.common.utils.CommonUtils;
import org.shicy.common.utils.StringUtils;
import org.shicy.myproj.R;
import org.shicy.myproj.common.AppConst;

import java.util.Date;

/**
 *
 * Created by Shicy on 2015/9/27.
 */
public class SecretActivity extends BaseActionBarActivity implements View.OnClickListener {

    private TextView pwdText;
    private boolean hasUserInit = false;
    private String confirmPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);

        this.pwdText = (TextView)findViewById(R.id.secret_text_pwd);

        findViewById(R.id.secret_btn_num0).setOnClickListener(this);
        findViewById(R.id.secret_btn_num1).setOnClickListener(this);
        findViewById(R.id.secret_btn_num2).setOnClickListener(this);
        findViewById(R.id.secret_btn_num3).setOnClickListener(this);
        findViewById(R.id.secret_btn_num4).setOnClickListener(this);
        findViewById(R.id.secret_btn_num5).setOnClickListener(this);
        findViewById(R.id.secret_btn_num6).setOnClickListener(this);
        findViewById(R.id.secret_btn_num7).setOnClickListener(this);
        findViewById(R.id.secret_btn_num8).setOnClickListener(this);
        findViewById(R.id.secret_btn_num9).setOnClickListener(this);
        findViewById(R.id.secret_btn_cancel).setOnClickListener(this);
        findViewById(R.id.secret_btn_ok).setOnClickListener(this);

        this.hasUserInit = this.checkIfUserInit();
    }

    /**
     * 检查用户是否设置了密码，第一次访问提示设置密码
     */
    private boolean checkIfUserInit() {
        if (!AppConfig.getInstance().getBoolValue(AppConst.CFG_SECRET_HAS_INIT)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("欢迎使用密保~");
            builder.setMessage("为你摆脱记忆一大堆密码的烦恼，首次输入将作为你今后的口令");
            builder.create().show();
            return false;
        }
        return true;
    }

    @Override
    protected void initActionBar(ActionBar actionBar) {
        super.initActionBar(actionBar);
        this.initActionBarWithUser(actionBar);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(org.shicy.common.R.menu.menu_user_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.secret_btn_ok:
                doSubmitClick();
                break;
            case R.id.secret_btn_cancel:
                doCancelClick();
                break;
            case R.id.secret_btn_num0:
                doNumberClick(0);
                break;
            case R.id.secret_btn_num1:
                doNumberClick(1);
                break;
            case R.id.secret_btn_num2:
                doNumberClick(2);
                break;
            case R.id.secret_btn_num3:
                doNumberClick(3);
                break;
            case R.id.secret_btn_num4:
                doNumberClick(4);
                break;
            case R.id.secret_btn_num5:
                doNumberClick(5);
                break;
            case R.id.secret_btn_num6:
                doNumberClick(6);
                break;
            case R.id.secret_btn_num7:
                doNumberClick(7);
                break;
            case R.id.secret_btn_num8:
                doNumberClick(8);
                break;
            case R.id.secret_btn_num9:
                doNumberClick(9);
                break;
            default:
                break;
        }
    }

    private void doNumberClick(int number) {
        pwdText.setText("" + pwdText.getText() + number);
    }

    private void doSubmitClick() {
        String password = pwdText.getText().toString();
        if (StringUtils.isBlank(password)) {
            Toast.makeText(this, "请输入口令", Toast.LENGTH_LONG).show();
            return ;
        }
        else if (!this.hasUserInit) {
            if (this.confirmPassword == null) {
                this.confirmPassword = password;
                this.pwdText.setText("");
                ((TextView) findViewById(R.id.secret_text_title)).setText("请再次输入口令确认");
                Toast.makeText(this, "请再次输入口令确认", Toast.LENGTH_LONG).show();
                return ;
            }
            else if (!password.equals(this.confirmPassword)) {
                this.confirmPassword = null;
                this.pwdText.setText("");
                ((TextView) findViewById(R.id.secret_text_title)).setText("请重新输入口令");
                Toast.makeText(this, "两次口令不一致，请重新设置", Toast.LENGTH_LONG).show();
                return ;
            }
            else {
                AppConfig appConfig = AppConfig.getInstance();
                appConfig.setBoolValue(AppConst.CFG_SECRET_HAS_INIT, true);
                appConfig.setStringValue(AppConst.CFG_SECRET_PASSWORD, CommonUtils.md5(password));
            }
        }

        AppConfig.getInstance().setStringValue(AppConst.CFG_SECRET_PASSWORD_NOW, password);
        AppConfig.getInstance().setLongValue(AppConst.CFG_SECRET_PASSWORD_EXP, new Date().getTime());

        startActivity(new Intent(this, SecretHomeActivity.class));
        SecretActivity.this.finish();
    }

    private void doCancelClick() {
        pwdText.setText("");
    }

}
