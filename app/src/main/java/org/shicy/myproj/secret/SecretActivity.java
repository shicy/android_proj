package org.shicy.myproj.secret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.shicy.common.base.BaseActionBarActivity;
import org.shicy.myproj.R;

/**
 *
 * Created by Shicy on 2015/9/27.
 */
public class SecretActivity extends BaseActionBarActivity {

    private TextView pwdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);

        this.pwdText = (TextView)findViewById(R.id.secret_text_pwd);

        ButtonClickListener clickListener = new ButtonClickListener();
        findViewById(R.id.secret_btn_num0).setOnClickListener(clickListener);
        findViewById(R.id.secret_btn_num1).setOnClickListener(clickListener);
        findViewById(R.id.secret_btn_num2).setOnClickListener(clickListener);
        findViewById(R.id.secret_btn_num3).setOnClickListener(clickListener);
        findViewById(R.id.secret_btn_num4).setOnClickListener(clickListener);
        findViewById(R.id.secret_btn_num5).setOnClickListener(clickListener);
        findViewById(R.id.secret_btn_num6).setOnClickListener(clickListener);
        findViewById(R.id.secret_btn_num7).setOnClickListener(clickListener);
        findViewById(R.id.secret_btn_num8).setOnClickListener(clickListener);
        findViewById(R.id.secret_btn_num9).setOnClickListener(clickListener);
        findViewById(R.id.secret_btn_cancel).setOnClickListener(clickListener);
        findViewById(R.id.secret_btn_ok).setOnClickListener(clickListener);
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

    private class ButtonClickListener implements View.OnClickListener {

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
            Intent intent = new Intent();
            intent.putExtra("password", pwdText.getText());
            intent.setClass(SecretActivity.this, SecretHomeActivity.class);
            startActivity(intent);
            SecretActivity.this.finish();
        }

        private void doCancelClick() {
            pwdText.setText("");
        }
    }

}
