package com.vugido.online_groceries.adapters.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.models.homepage.SliderModel;
import com.vugido.online_groceries.models.homepage.updated.SLIDERItem;

import java.util.List;


public class SliderAdapter extends PagerAdapter {

    private List<SLIDERItem> sliderModelList;
    private Context context;

    public SliderAdapter(List<SLIDERItem> sliderModelList, Context context) {
        this.sliderModelList = sliderModelList;
        this.context=context;
    }


    @Override
    public int getCount() {
        return sliderModelList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout, container, false);
       // ConstraintLayout constraintLayout=view.findViewById(R.id.SliderBannerContainer);
        //constraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderModelList.get(position).getBG())));
        ImageView imageView = view.findViewById(R.id.slider_image);

        Glide.with(context)
                .load(sliderModelList.get(position).getIMAGE())
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
