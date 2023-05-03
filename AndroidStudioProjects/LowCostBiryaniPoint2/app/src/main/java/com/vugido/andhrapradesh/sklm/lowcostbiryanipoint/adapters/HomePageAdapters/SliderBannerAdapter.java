package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters.HomePageAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.foodhub.vugido.R;
import com.foodhub.vugido.models.VEG_HOME_PAGE_MODEL.OFFERSItem;

import java.util.List;



public class SliderBannerAdapter extends PagerAdapter {

    private List<OFFERSItem> offersItemList;
    private Context context;

    public SliderBannerAdapter(Context context, List<OFFERSItem> offersItemList){

        this.context=context;
        this.offersItemList=offersItemList;

    }


    //Adapter Constructor.....


    @Override
    public int getCount() {
        return offersItemList.size();
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
        View layoutScreen = inflater.inflate(R.layout.slider,null);


        //  ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        //TextView title = layoutScreen.findViewById(R.id.intro_title);
        //  TextView description = layoutScreen.findViewById(R.id.intro_description);

        //  title.setText(screenItemList.get(position).getTitle());
        // description.setText(screenItemList.get(position).getDescription());
        // imgSlide.setImageResource(screenItemList.get(position).getScreenImg());

        container.addView(layoutScreen);

        return layoutScreen;





    }
}
