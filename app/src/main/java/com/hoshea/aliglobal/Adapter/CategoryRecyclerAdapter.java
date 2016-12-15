package com.hoshea.aliglobal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoshea.aliglobal.Application.MyApplication;
import com.hoshea.aliglobal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoshea on 2016/5/23.
 */
public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.MyViewHolder> {
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    List<String> priceList=new ArrayList<String>();
    List<String> imageList=new ArrayList<String>();
    List<String> goodsIdList=new ArrayList<String>();
    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        this.mOnItemClickListener = OnItemClickListener;
    }

    public interface OnItemClickListener{
        void ItemClickListener(View view, int postion);
        void ItemLongClickListener(View view, int postion);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Picasso.with(context).load(MyApplication.getURL()+imageList.get(position)).into(holder.picture);
        holder.price.setText(priceList.get(position));
        holder.goodsId.setText(goodsIdList.get(position));
        if (mOnItemClickListener != null) {//如果设置了监听那么它就不为空，然后回调相应的方法
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //得到当前点击item的位置postion
                    int postion = holder.getLayoutPosition();
                    mOnItemClickListener.ItemClickListener(holder.itemView, postion);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int postion = holder.getLayoutPosition();
                    mOnItemClickListener.ItemLongClickListener(holder.itemView, postion);
                    return true;
                }
            });

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_goods, parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    public CategoryRecyclerAdapter(Context context,List<String>priceList,List<String>imageList,List<String>goodsIdList) {
        this.context = context;
        this.priceList=priceList;
        this.imageList=imageList;
        this.goodsIdList=goodsIdList;
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        ImageView picture;
        TextView goodsId;
        public MyViewHolder(View itemView) {
            super(itemView);
            picture =(ImageView)itemView.findViewById(R.id.picture);
            price=(TextView)itemView.findViewById(R.id.price);
            goodsId=(TextView)itemView.findViewById(R.id.goodsId);
        }
    }
}
