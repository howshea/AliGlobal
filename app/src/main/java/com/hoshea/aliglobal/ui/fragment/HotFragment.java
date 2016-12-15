package com.hoshea.aliglobal.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hoshea.aliglobal.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {


    private ViewPager pager;
    private ArrayList<View> pagerView;

    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, null);

        pager = (ViewPager) view.findViewById(R.id.first_header_viewPager);

        pagerView = new ArrayList<View>();
        ImageView image1 = new ImageView(getContext());
        image1.setImageResource(R.drawable.tab1);
        image1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView image2 = new ImageView(getContext());
        image2.setImageResource(R.drawable.tab2);
        image2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView image3 = new ImageView(getContext());
        image3.setImageResource(R.drawable.tab3);
        image3.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView image4 = new ImageView(getContext());
        image4.setImageResource(R.drawable.tab4);
        image4.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView image5 = new ImageView(getContext());
        image5.setImageResource(R.drawable.tab5);
        image5.setScaleType(ImageView.ScaleType.CENTER_CROP);

        pagerView.add(image1);
        pagerView.add(image2);
        pagerView.add(image3);
        pagerView.add(image4);
        pagerView.add(image5);

        pager.setAdapter(new MyAdapter());

        return view;
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(pagerView.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(pagerView.get(position));
            return pagerView.get(position);
        }
    }

}
