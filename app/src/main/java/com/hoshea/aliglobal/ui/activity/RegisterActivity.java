package com.hoshea.aliglobal.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hoshea.aliglobal.R;
import com.hoshea.aliglobal.Utils.HttpUtil;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.register)
    Button register;
    private Toolbar toolbar;
    String post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                }
        );



        /*以下为测试代码
        *
        * 注册
        * */
        register.setOnClickListener(new View.OnClickListener() {

            private String url;

            @Override
            public void onClick(View v) {
            }
        });
    }

    /*以下为测试代码
       *
       * 注册
       * */
    private String post(String url, String json) {


        RequestBody requestBody = FormBody.create(HttpUtil.JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = HttpUtil.okHttpClient.newCall(request).execute();

            if (response.isSuccessful()){
                return response.body().string();
            }
            else{
                throw new IOException("unexpexted code :"+response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
