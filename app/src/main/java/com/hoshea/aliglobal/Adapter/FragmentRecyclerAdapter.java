package com.hoshea.aliglobal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoshea.aliglobal.R;

/**
 * Created by Hoshea on 2016/5/23.
 */
public class FragmentRecyclerAdapter extends RecyclerView.Adapter<FragmentRecyclerAdapter.MyViewHolder> {
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    String[] tiltelist ={
            "Men's Clothing &\nAccessories",
            "Consumer Electronics",
            "Home & Garden",
            "Sports &\nEntertainment"
    };
    int[] image = {
            R.drawable.man,
            R.drawable.eletronic,
            R.drawable.homes,
            R.drawable.sport

    };
    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        this.mOnItemClickListener = OnItemClickListener;
    }

    public interface OnItemClickListener{
        void ItemClickListener(View view,int postion);
        void ItemLongClickListener(View view,int postion);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.cateImage.setImageResource(image[position]);
        holder.title.setText(tiltelist[position]);
        if (mOnItemClickListener != null) {
            //如果设置了监听那么它就不为空，然后回调相应的方法
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
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    public FragmentRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return 4;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView cateImage;
        public MyViewHolder(View itemView) {
            super(itemView);
            cateImage =(ImageView)itemView.findViewById(R.id.category_image);
            title=(TextView)itemView.findViewById(R.id.category_title);
        }
    }
}
