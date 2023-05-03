package com.vugido.vugidoupdate.adapters.FactPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.models.facts_page.FactsPageModel;
import com.vugido.vugidoupdate.models.home_page.SliderViewModel;

import java.util.List;


public class FactPagerAdapter extends PagerAdapter {

    private List<FactsPageModel> factsPageModelList;
    private Context context;

    public FactPagerAdapter(List<FactsPageModel> factsPageModelList, Context context) {
        this.factsPageModelList = factsPageModelList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return factsPageModelList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.facts_page_design, container, false);
        //=view.findViewById(R.id.SliderBannerContainer);
        //constraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderModelList.get(position).getBG())));
        ImageView imageView = view.findViewById(R.id.fact_image);

        /*Glide.with(context)
                .load(factsPageModelList.get(position).getImage())
                .into(imageView);*/
        imageView.setImageResource(factsPageModelList.get(position).getImage());
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
