package com.vugido.vugidoupdate.adapters;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.models.home_page.SliderViewModel;

import java.util.List;


public class SliderViewAdapter extends PagerAdapter {

    private List<SliderViewModel> sliderModelList;
    private Context context;
    private int HID;

    public SliderViewAdapter(List<SliderViewModel> sliderModelList,Context context,int HID) {
        this.sliderModelList = sliderModelList;
        this.context=context;
        this.HID=HID;
    }


    @Override
    public int getCount() {
        return sliderModelList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout, container, false);
        //=view.findViewById(R.id.SliderBannerContainer);
        //constraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderModelList.get(position).getBG())));
        CardView cardView=view.findViewById(R.id.slider_card);
        ImageView imageView = view.findViewById(R.id.slider_image);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // launch activity with all products..
                // HID, ID.. pass..
            }
        });
        Glide.with(context)
                .load(sliderModelList.get(position).getImage())
                .into(imageView);
        //imageView.setImageResource(sliderModelList.get(position).getBanner());
        container.addView(view,0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
