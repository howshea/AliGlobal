package com.hoshea.aliglobal.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hoshea.aliglobal.Application.MyApplication;
import com.hoshea.aliglobal.R;
import com.hoshea.aliglobal.Utils.PostFuturnTask;
import com.hoshea.aliglobal.model.Goods;
import com.hoshea.aliglobal.model.User;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;

public class ShoppingCartActivity extends AppCompatActivity {


    private int length;

    static class ViewHolder{
        ImageView image;
        TextView title;
        TextView price;
    }


    @Bind(R.id.cart_select_all)
    CheckBox cartSelectAll;
    @Bind(R.id.cartList)
    ListView cartList;
    @Bind(R.id.bt_pay)
    Button btPay;
    @Bind(R.id.cart_total)
    TextView cartTotal;
    @Bind(R.id.cart_total_money)
    TextView cartTotalMoney;
    private User user;
    private MyApplication app;
    private CartAdapter cartAdapter;
    private ActionBar actionBar;
    private String response;
    private int[] stocks;
    private String[] goodsNames;
    private float[] prices;
    private String[] detailPictures;
    private String[][] titlePictures;
    private float totalMoney=0;
    private ImageView[] bitmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Shopping Cart");
        actionBar.setDisplayHomeAsUpEnabled(true);

        initData();

        Integer userId = user.getUserId();
        String s = String.valueOf(userId);

    //    System.out.println("userId :"+s);  正确
        Toast.makeText(ShoppingCartActivity.this, "正在请求数据", Toast.LENGTH_SHORT).show();
        FormBody body = new FormBody.Builder().add("userId", s).build();
        PostFuturnTask task = new PostFuturnTask(app.getCARTURL(), body);
        new Thread(task).start();
        while (!task.isDone()){
        }
        try {
            response= task.get();
            System.out.println(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(response.equals("carthasnogoods")){
            Toast.makeText(ShoppingCartActivity.this, "购物车为空", Toast.LENGTH_SHORT).show();
        }else {
            analyzeJson(response);
            cartList.setAdapter(new CartAdapter());
            cartTotalMoney.setText(String.valueOf(totalMoney));
        }


        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(ShoppingCartActivity.this);
                View view = LayoutInflater.from(ShoppingCartActivity.this).inflate(R.layout.bottomdialog_pay, null);
                dialog.setContentView(view);
                dialog.show();
            }
        });
    }

    /**
     * 初始化全局数据
     */
    private void initData() {
        app = (MyApplication) getApplication();
        user = app.getUser();

    }

    public void analyzeJson(String response){
        Goods[] goodses = new Gson().fromJson(response, Goods[].class);
        length = goodses.length;
        stocks=new int[length];
        goodsNames=new String[length];
        prices=new float[length];
        detailPictures=new String[length];
        titlePictures=new String[length][3];


        for (int i=0;i<goodses.length;i++){
            Goods goods =goodses[i];
            totalMoney+=goods.getPrice();
            System.out.println(goods.toString());

            stocks[i]=goods.getStock();
            goodsNames[i]=goods.getGoodsName();
            prices[i]=goods.getPrice();
            detailPictures[i]=app.getURL()+goods.getDetailPicture();

            String title_pictures=goods.getTitlePicture();
            String[] pictureItem = title_pictures.split("\\|");

            for (int j=0;j<3;j++){

                titlePictures[i][j]=app.getURL()+pictureItem[j];
                System.out.println(titlePictures[i][j]);
            }



        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class CartAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return length ;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View view;
            if (convertView==null){
                view= LayoutInflater.from(ShoppingCartActivity.this).inflate(R.layout.shopcartitem,null);
                viewHolder=new ViewHolder();
                viewHolder.image= (ImageView) view.findViewById(R.id.cart_goods_image);
                viewHolder.title= (TextView) view.findViewById(R.id.cart_title);
                viewHolder.price= (TextView) view.findViewById(R.id.cart_goods_price);
            }else {
                view=convertView;
                viewHolder= (ViewHolder) view.getTag();
            }
            Picasso.with(ShoppingCartActivity.this).load(titlePictures[position][0]).into(viewHolder.image);
            viewHolder.title.setText(goodsNames[position]);
            viewHolder.price.setText(String.valueOf(prices[position]));
            return view;
        }
    }

}
