package com.hoshea.aliglobal.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.hoshea.aliglobal.Application.MyApplication;
import com.hoshea.aliglobal.R;
import com.hoshea.aliglobal.model.User;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserDetailsActivity extends AppCompatActivity {

    @Bind(R.id.address)
    EditText address;
    @Bind(R.id.realname)
    EditText realname;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.paypassword)
    EditText paypassword;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.update)
    Button update;
    private MyApplication app;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        initData();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(user.getUsername().toString());

        initLayout();

    }

    private void initData() {
        app = (MyApplication) getApplication();
        user = app.getUser();
    }

    private void initLayout() {
        if (!(user.getAddress().isEmpty())) {
            address.setText(user.getAddress().toString());
        }
        if (!(user.getRealname().isEmpty())) {
            realname.setText(user.getRealname().toString());
        }
        password.setText(user.getPassword().toString());
        paypassword.setText(user.getPaypassword());
        username.setText(user.getUsername());
        phone.setText(user.getPhone());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
