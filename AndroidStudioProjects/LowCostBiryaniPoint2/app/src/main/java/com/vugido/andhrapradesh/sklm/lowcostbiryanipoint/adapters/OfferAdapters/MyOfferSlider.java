package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.adapters.OfferAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.R;
import com.foodhub.vugido.activities.AllProductsActivity;
import com.foodhub.vugido.activities.ReferEarnPolicy;
import com.foodhub.vugido.models.HomeFragmentHomeModel.HomePageModel.TOPVIEWSItem;
import com.foodhub.vugido.models.OffersPageModel.SLIDERItem;

import java.util.List;

public class MyOfferSlider extends PagerAdapter {

    private List<TOPVIEWSItem> screenItemList;
    private Context context;
    private FragmentActivity fragmentActivity;


    public MyOfferSlider(List<TOPVIEWSItem> screenItemList, Context context) {
        this.screenItemList = screenItemList;
        this.context = context;
        fragmentActivity= (FragmentActivity) context;
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
        View layoutScreen = inflater.inflate(R.layout.coupon_item_design,null);

          ImageView imgSlide = layoutScreen.findViewById(R.id.imageView4);
        //TextView title = layoutScreen.findViewById(R.id.intro_title);
        //  TextView description = layoutScreen.findViewById(R.id.intro_description);

        //  title.setText(screenItemList.get(position).getTitle());
        // description.setText(screenItemList.get(position).getDescription());
        Glide.with(context).load(screenItemList.get(position).getIMAGE()).into(imgSlide);

        imgSlide.setOnClickListener(v -> {
            Intent intent=new Intent(context, ReferEarnPolicy.class);
            intent.putExtra("TITLE",screenItemList.get(position).gettITLE());
            intent.putExtra("URL",screenItemList.get(position).getURL());
            intent.putExtra("BUTTON",screenItemList.get(position).getBUTTON());
            intent.putExtra("ID",screenItemList.get(position).getID());
            fragmentActivity.startActivityForResult(intent,0);
        });
        container.addView(layoutScreen);

        return layoutScreen;





    }
}
