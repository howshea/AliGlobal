package com.hoshea.aliglobal.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hoshea.aliglobal.Application.MyApplication;
import com.hoshea.aliglobal.R;
import com.hoshea.aliglobal.Utils.PostFuturnTask;
import com.hoshea.aliglobal.model.Goods;
import com.hoshea.aliglobal.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;

public class GoodsActivity extends AppCompatActivity {

    @Bind(R.id.goods_picture_pager)
    ViewPager goodsPicturePager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.goods_name)
    TextView goodsName;
    @Bind(R.id.goods_price)
    TextView goodsPrice;
    @Bind(R.id.add_to_cart)
    Button addToCart;
    @Bind(R.id.add_to_buy)
    Button addToBuy;
    @Bind(R.id.bt_goods_detail)
    Button btGoodsDetail;
    @Bind(R.id.details_picture)
    ImageView detailsPicture;
    @Bind(R.id.scroll_goods_detail)
    NestedScrollView scrollGoodsDetail;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.count_add)
    TextView countAdd;
    @Bind(R.id.count)
    TextView count;
    @Bind(R.id.count_subtract)
    TextView countSubtract;
    private MyApplication app;
    private User user;
    private String response;
    private List<String> pictureList;
    private Goods goods;
    private List<View> pagerView = new ArrayList<View>();
    private View.OnClickListener listener;
    private String ifSuccess;
    private String ifSuccess2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String goodsId = intent.getStringExtra("goodsId");
        initData();

        toolbarLayout.setTitle("Details");
        toolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        Query(goodsId);

        anaylzeJson(response);

        goodsName.setText(goods.getGoodsName());
        goodsPrice.setText("RMB ￥" + String.valueOf(goods.getPrice()));
        Picasso.with(this).load(app.getURL() + goods.getDetailPicture()).into(detailsPicture);

        pagerView = new ArrayList<View>();
        ImageView image1 = new ImageView(this);
        image1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(this).load(app.getURL() + pictureList.get(0)).into(image1);
        ImageView image2 = new ImageView(this);
        image2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(this).load(app.getURL() + pictureList.get(1)).into(image2);
        ImageView image3 = new ImageView(this);
        image3.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(this).load(app.getURL() + pictureList.get(2)).into(image3);

        pagerView.add(image1);
        pagerView.add(image2);
        pagerView.add(image3);
        goodsPicturePager.setAdapter(new MyAdapter());
        initToolbarAction();
//        behavior = BottomSheetBehavior.from((View)scrollGoodsDetail);

        initClickListener();
        addToCart.setOnClickListener(listener);
        addToBuy.setOnClickListener(listener);

    }

    private void initData() {
        app = (MyApplication) getApplication();
        user = app.getUser();
    }


    private void Query(String goodsId) {
        FormBody body = new FormBody.Builder()
                .add("goodsId", goodsId)
                .build();
        PostFuturnTask task = new PostFuturnTask(app.getGOODSURL(), body);
        new Thread(task).start();
        while (!task.isDone()) {
        }

        try {
            response = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    private void anaylzeJson(String response) {
        goods = new Gson().fromJson(response, Goods.class);
        String[] titlepictures = goods.getTitlePicture().toString().split("\\|");
        pictureList = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            pictureList.add(titlepictures[i]);
        }

    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(pagerView.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(pagerView.get(position));
            return pagerView.get(position);
        }
    }

    private void initToolbarAction() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initClickListener() {
        listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add_to_cart:
                        if (user != null) {
                            AddtoCart();
                        } else {
                            Snackbar
                                    .make(toolbar, "请登录", Snackbar.LENGTH_LONG)
                                    .show();
                        }
                        break;
                    case R.id.add_to_buy:
                        if (user != null) {
                            initBottomDialog();
                        } else {
                            Snackbar
                                    .make(toolbar, "你不登录咋买？", Snackbar.LENGTH_LONG)
                                    .show();
                        }
                        break;
                }
            }
        };
    }

    private void initBottomDialog() {

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottomdialog_pay, null);
        dialog.setContentView(view);
        dialog.show();

        EditText receiveName = (EditText) view.findViewById(R.id.receive_name);
        if (!user.getRealname().toString().isEmpty()) {
            receiveName.setText(user.getRealname().toString());
        }
        EditText receivePhone = (EditText) view.findViewById(R.id.receive_phone);
        receivePhone.setText(user.getPhone().toString());
        EditText receiveAddress = (EditText) view.findViewById(R.id.receive_address);
        receiveAddress.setText(user.getAddress().toString());



    }

    private void AddtoCart() {
        FormBody body = new FormBody.Builder()
                .add("userId", String.valueOf(user.getUserId()))
                .add("goodsId", String.valueOf(goods.getGoodsId()))
                .build();
        PostFuturnTask task = new PostFuturnTask(app.getADDTOCARTSERVLET(), body);
        new Thread(task).start();
        while (!task.isDone()) {

        }
        try {
            ifSuccess = task.get();
            System.out.println(ifSuccess);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (ifSuccess.equals("success")) {
            Snackbar
                    .make(toolbar, "添加购物车成功", Snackbar.LENGTH_LONG)
                    .show();

        } else {
            Snackbar
                    .make(toolbar, "添加失败", Snackbar.LENGTH_LONG)
                    .show();
        }

    }


    private void addtoBuy(){

        FormBody body = new FormBody.Builder()
                .add("userId", String.valueOf(user.getUserId()))
                .add("goodsId", String.valueOf(goods.getGoodsId()))
                .add("paypassword",user.getPaypassword())
                .build();
        PostFuturnTask task = new PostFuturnTask(app.getPAYURL(), body);
        new Thread(task).start();
        while (!task.isDone()) {

        }
        try {
            ifSuccess2 = task.get();
            System.out.println(ifSuccess2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (ifSuccess2.equals("success")) {
            Snackbar
                    .make(toolbar, "购买成功", Snackbar.LENGTH_LONG)
                    .show();

        } else {
            Snackbar
                    .make(toolbar, "购买失败", Snackbar.LENGTH_LONG)
                    .show();
        }
    }
}
