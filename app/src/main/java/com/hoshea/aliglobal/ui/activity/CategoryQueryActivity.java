package com.hoshea.aliglobal.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hoshea.aliglobal.Adapter.CategoryRecyclerAdapter;
import com.hoshea.aliglobal.Application.MyApplication;
import com.hoshea.aliglobal.R;
import com.hoshea.aliglobal.Utils.PostFuturnTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;

public class CategoryQueryActivity extends AppCompatActivity {

    @Bind(R.id.category_query_recycler)
    RecyclerView categoryQueryRecycler;
    private ActionBar bar;
    List<String> priceList=new ArrayList<String>();
    List<String> pictureList =new ArrayList<String>();
    List<String> goodsIdList=new ArrayList<String>();
    private String barTitle;
    private MyApplication app;
    private String response;
    private List<ItemGoods> itemGoodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_query);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        barTitle = intent.getStringExtra("category");
        bar = getSupportActionBar();
        bar.setTitle(barTitle);
        bar.setDisplayHomeAsUpEnabled(true);
        initData();
        query();
        anaylzeJson(response);
        categoryQueryRecycler.setHasFixedSize(true);
        categoryQueryRecycler.setItemAnimator(new DefaultItemAnimator());
        categoryQueryRecycler.setLayoutManager(new GridLayoutManager(this,2));
        CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(this,priceList,pictureList,goodsIdList);
        categoryQueryRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new CategoryRecyclerAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, int postion) {
                System.out.println("点击的是：" + (postion + 1));

                queryGoodsAll(view);
            }

            @Override
            public void ItemLongClickListener(View view, int postion) {
                Toast.makeText(CategoryQueryActivity.this, "长按的是：" + (postion + 1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        app = (MyApplication) getApplication();

    }
    private void query(){
        FormBody body = new FormBody.Builder().add("category", barTitle).build();
        PostFuturnTask task = new PostFuturnTask(app.getCATEGORYURL(), body);
        new Thread(task).start();
        while (!task.isDone()){

        }
        try {
            response = task.get();
            System.out.println(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void anaylzeJson(String response){
        System.out.println(response);
        ItemGoods[] itemGoodses = new Gson().fromJson(response, ItemGoods[].class);
        for (int i=0;i<itemGoodses.length;i++){
            System.out.println(itemGoodses.toString());
            priceList.add(String.valueOf(itemGoodses[i].getPrice()));
            goodsIdList.add(String.valueOf(itemGoodses[i].getGoodsId()));
            pictureList.add(itemGoodses[i].getTitlePicture());
            System.out.println(goodsIdList.get(i)+"========******========&&&");
        }
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


    public class ItemGoods {
        @Override
        public String toString() {
            return "ItemGoods{" +
                    "goodsId=" + goodsId +
                    ", price=" + price +
                    ", titlePicture='" + titlePicture + '\'' +
                    '}';
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        private int goodsId;

        private Float price;
        private String titlePicture;

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public String getTitlePicture() {
            return titlePicture;
        }

        public void setTitlePicture(String titlePicture) {
            this.titlePicture = titlePicture;
        }


    }


    private void queryGoodsAll(View view){
        TextView goodsId = (TextView) view.findViewById(R.id.goodsId);
        System.out.println(goodsId.getText().toString());
        Intent intent = new Intent(CategoryQueryActivity.this, GoodsActivity.class);
        intent.putExtra("goodsId",goodsId.getText().toString().trim());
        startActivity(intent);
    }
}
