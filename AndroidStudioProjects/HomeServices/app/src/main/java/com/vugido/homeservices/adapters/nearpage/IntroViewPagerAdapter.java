package com.vugido.homeservices.adapters.nearpage;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.vugido.homeservices.R;
import com.vugido.homeservices.models.near_page.images.DATAItem;

import java.util.List;


public class IntroViewPagerAdapter extends PagerAdapter {

    private List<String> screenItemList;
    private Context context;

    public IntroViewPagerAdapter(Context context, List<String> screenItemListc){

        this.screenItemList=screenItemListc;
        this.context=context;

    }


    //Adapter Constructor.....


    @Override
    public int getCount() {
        return screenItemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        ImageView imageView=layoutScreen.findViewById(R.id.intro_img);
        Glide.with(context).load("http://homeservices.vugido.com/IMAGES/"+screenItemList.get(position)).into(imageView);

        container.addView(layoutScreen);

        return layoutScreen;





    }
}
