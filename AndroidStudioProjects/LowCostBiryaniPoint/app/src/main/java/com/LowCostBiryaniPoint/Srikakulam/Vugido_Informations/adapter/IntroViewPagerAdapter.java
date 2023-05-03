package com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.R;
import com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.models.ScreenItem;

import java.util.List;


public class IntroViewPagerAdapter extends PagerAdapter {

    private List<ScreenItem> screenItemList;
    private Context context;

    public IntroViewPagerAdapter(Context context, List<ScreenItem> screenItemListc){

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

        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);

        title.setText(screenItemList.get(position).getTitle());
        description.setText(screenItemList.get(position).getDescription());
        imgSlide.setImageResource(screenItemList.get(position).getScreenImg());

        container.addView(layoutScreen);

        return layoutScreen;





    }
}
