package com.hoshea.aliglobal.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hoshea.aliglobal.Adapter.FragmentRecyclerAdapter;
import com.hoshea.aliglobal.R;
import com.hoshea.aliglobal.ui.activity.CategoryQueryActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {


    private RecyclerView recylerview;
    private Intent intent;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);
        view.findViewById(R.id.nvzhuang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), CategoryQueryActivity.class);
                intent.putExtra("category","女装");
                startActivity(intent);
            }
        });
        recylerview = (RecyclerView) view.findViewById(R.id.category_recylerview);
        recylerview.setHasFixedSize(true);
        recylerview.setItemAnimator(new DefaultItemAnimator());
        recylerview.setLayoutManager(new GridLayoutManager(getContext(),2));
        FragmentRecyclerAdapter adapter = new FragmentRecyclerAdapter(this.getContext());
        recylerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new FragmentRecyclerAdapter.OnItemClickListener() {//添加监听器
            @Override
            public void ItemClickListener(View view, int postion) {
                switch (postion){
                    case 0:
                        intent = new Intent(getContext(), CategoryQueryActivity.class);
                        intent.putExtra("category","男装");
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getContext(), CategoryQueryActivity.class);
                        intent.putExtra("category","电子");
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getContext(), CategoryQueryActivity.class);
                        intent.putExtra("category","家居");
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getContext(), CategoryQueryActivity.class);
                        intent.putExtra("category","运动");
                        startActivity(intent);

                }

            }

            @Override
            public void ItemLongClickListener(View view, int postion) {
                Toast.makeText(getContext(), "长按的是：" + (postion + 1), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}
