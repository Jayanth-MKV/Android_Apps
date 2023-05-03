package com.foodhub.vugido.sklm.fresh_cuts.adapters.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.foodhub.vugido.sklm.fresh_cuts.R;
import com.foodhub.vugido.sklm.fresh_cuts.models.MAIN_MODEL.SLIDERSItem;

import java.util.List;

public class MyHomeSlider extends PagerAdapter {

    private List<SLIDERSItem> screenItemList;
    private Context context;
    private FragmentActivity fragmentActivity;


    public MyHomeSlider(List<SLIDERSItem> screenItemList, Context context) {
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



//        imgSlide.setOnClickListener(v -> {
//            Intent intent=new Intent(context, AllProductsActivity.class);
//            intent.putExtra("CID",screenItemList.get(position).getCID());
//            intent.putExtra("TITLE",screenItemList.get(position).getDESCRIPTION());
//            fragmentActivity.startActivityForResult(intent,0);
//        });
        container.addView(layoutScreen);

        return layoutScreen;





    }
}
