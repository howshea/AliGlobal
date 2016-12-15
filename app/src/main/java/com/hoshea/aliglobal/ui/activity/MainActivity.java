package com.hoshea.aliglobal.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoshea.aliglobal.Adapter.MyFragmentPagerAdapter;
import com.hoshea.aliglobal.Application.MyApplication;
import com.hoshea.aliglobal.R;
import com.hoshea.aliglobal.model.User;
import com.hoshea.aliglobal.ui.fragment.CategoryFragment;
import com.hoshea.aliglobal.ui.fragment.HotFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_toolbar)
    Toolbar mainToolbar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.navigation)
    NavigationView navigation;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;


    private String url;
    private String username;
    private ImageView headerImage;
    private TextView main_username;
    private View navigationLayout;
    private View.OnClickListener listener;
    private MyApplication app;
    private User user;
    private Intent intentToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        app = (MyApplication) getApplication();
        user = app.getUser();



        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        navigationLayout = navigation.inflateHeaderView(R.layout.navigation_header);
        if (user != null) {
            username = user.getUsername();
            headerImage = (ImageView) navigationLayout.findViewById(R.id.header_image);
            main_username = (TextView) navigationLayout.findViewById(R.id.main_username);

            if (main_username.getText().toString().equals("Sign In")) {
                main_username.setText(username);
                System.out.println(main_username.getText().toString());
            }
        }
        setSupportActionBar(mainToolbar);



        initNavigationView();
        initToolbarAction();

        initViewPager();
        initTabLayout();

        initOnclickListener();

        navigation.setOnClickListener(listener);
    }

    private void initViewPager() {
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HotFragment(), "What'new");
        adapter.addFragment(CategoryFragment.newInstance(), "All Category");
        viewPager.setAdapter(adapter);
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("What'new"));
        tabLayout.addTab(tabLayout.newTab().setText("All Category"));
        tabLayout.setupWithViewPager(viewPager);
    }


    private void initToolbarAction() {
        mainToolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawerLayout.openDrawer(navigation);
                    }
                }
        );
    }

    private void initNavigationView(){


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        drawerLayout.closeDrawer(navigation);
                        break;
                    case R.id.account:
                        if (user!=null){
                            Intent intentToAccount = new Intent(MainActivity.this, PersonInfoActivity.class);
                            startActivity(intentToAccount);
                        }else {

                        }
                        break;
                    case R.id.cart:
                        if (user!=null) {
                            Intent intentToShopCart = new Intent(MainActivity.this, ShoppingCartActivity.class);
                            startActivity(intentToShopCart);
                        }else {
                            if (intentToLogin==null){
                                intentToLogin = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intentToLogin);
                            }else {
                                startActivity(intentToLogin);
                            }
                        }
                        break;

                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mainToolbar.inflateMenu(R.menu.main_menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void initOnclickListener(){
        listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){

                }
            }
        };

    }

    public void nameClick(View v){
        if (user==null){
            intentToLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intentToLogin);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.shopCart:
                if (user!=null) {
                    Intent intentToShopCart = new Intent(this, ShoppingCartActivity.class);
                    startActivity(intentToShopCart);
                }else {
                    if (intentToLogin==null){
                        intentToLogin = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intentToLogin);
                    }else {
                        startActivity(intentToLogin);
                    }
                }
                break;

    }
        return super.onOptionsItemSelected(item);
    }


}
