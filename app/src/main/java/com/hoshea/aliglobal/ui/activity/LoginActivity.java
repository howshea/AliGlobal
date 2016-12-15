package com.hoshea.aliglobal.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hoshea.aliglobal.Application.MyApplication;
import com.hoshea.aliglobal.R;
import com.hoshea.aliglobal.Utils.PostFuturnTask;
import com.hoshea.aliglobal.model.User;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_phone)
    EditText loginPhone;
    @Bind(R.id.login_pwd)
    EditText loginPwd;
    @Bind(R.id.bt_signin)
    Button btSignin;
    @Bind(R.id.toRegister)
    Button toRegister;
    @Bind(R.id.login_toolbar)
    Toolbar loginToolbar;

    private View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(loginToolbar);
        initNavigationAction();
        initClickListener();
        toRegister.setOnClickListener(listener);
        btSignin.setOnClickListener(listener);
    }

    private void initNavigationAction(){
        loginToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    /**
     * 初始化点击事件
     */
    private void initClickListener() {
        listener = new View.OnClickListener() {
            String response;

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.toRegister:
                        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                        break;
                    case R.id.bt_signin:



                        View view = getWindow().peekDecorView();
                        if (view != null) {
                            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }



                        if (TextUtils.isEmpty(loginPhone.getText())) {
                            Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(loginPwd.getText())) {
                            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        FormBody body = new FormBody.Builder()
                                .add("phone", loginPhone.getText().toString())
                                .add("password", loginPwd.getText().toString())
                                .build();
                        MyApplication app = (MyApplication) getApplication();
                        final String url = app.getSIGNINURL();
                        PostFuturnTask task = new PostFuturnTask(url, body);
                        new Thread(task).start();
                        try {
                            response = task.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        System.out.println(response);
                        if (response.isEmpty()) {
                            return;
                        } else if (response.equals("user no exist")) {
                            Toast.makeText(LoginActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (response.equals("passwrod wrong")) {
                            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Gson gson = new Gson();
                            User user = gson.fromJson(response, User.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            app.setUser(user);
                            startActivity(intent);
                            finish();

                            break;
                        }

                }
            }
        };
    }
}
