package com.hoshea.aliglobal.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hoshea.aliglobal.Application.MyApplication;
import com.hoshea.aliglobal.R;
import com.hoshea.aliglobal.Utils.PostFuturnTask;
import com.hoshea.aliglobal.model.User;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;

public class PersonInfoActivity extends AppCompatActivity {


    @Bind(R.id.user_header_image)
    CircleImageView userHeaderImage;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.order_list)
    ListView orderList;
    private MyApplication app;
    private User user;
    private String response;

    private int length;
    private OrdersItem[] ordersItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initData();

        String username = user.getUsername();
        userName.setText(username);

        post();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });

        userHeaderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonInfoActivity.this, UserDetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initData() {
        app = (MyApplication) getApplication();
        user = app.getUser();

    }


    private void post(){
        FormBody body = new FormBody.Builder()
                .add("task", "checkorders")
                .add("userId",String.valueOf(user.getUserId()))
                .build();

        PostFuturnTask futurnTask = new PostFuturnTask(app.getORDERSURL(), body);
        new Thread(futurnTask).start();
        while (!futurnTask.isDone()){

        }
        try {
            response = futurnTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (response.equals("has_no_order")){
            Snackbar
                    .make(fab, "还没有订单，快去买买买",Snackbar.LENGTH_LONG)
                    .show();
        }else {
            System.out.println(response);
            anaylzeJson(response);
            orderList.setAdapter(new MyAdapter());
        }
    }

    private void anaylzeJson(String response){
        ordersItems = new Gson().fromJson(response, OrdersItem[].class);
        length = ordersItems.length;
        for (OrdersItem item: ordersItems){
            System.out.println(item.toString());
        }

    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return length;
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
            View view;
            if (convertView==null){
                view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.my_orders_item_cardview,null);
            }else {
                view=convertView;
            }
            ((TextView)view.findViewById(R.id.order_number)).setText("订单号： "+String.valueOf(ordersItems[position].getOrderId()));
            ((TextView)view.findViewById(R.id.order_state)).setText(ordersItems[position].getState());
            ((TextView)view.findViewById(R.id.order_titles)).setText(ordersItems[position].getTitle());
            Picasso.with(getApplicationContext())
                    .load(app.getURL()+ordersItems[position].getPicture())
                    .into(((ImageView)view.findViewById(R.id.order_image)));

            return view;
        }
    }



    class OrdersItem
    {
        private String title;
        private String picture;

        @Override
        public String toString() {
            return "OrdersItem{" +
                    "orderId=" + orderId +
                    ", title='" + title + '\'' +
                    ", picture='" + picture + '\'' +
                    ", state='" + state + '\'' +
                    '}';
        }

        private int orderId;
        private String state;

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getPicture() {
            return picture;
        }
        public void setPicture(String picture) {
            this.picture = picture;
        }
        public int getOrderId() {
            return orderId;
        }
        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state = state;
        }


    }

}
