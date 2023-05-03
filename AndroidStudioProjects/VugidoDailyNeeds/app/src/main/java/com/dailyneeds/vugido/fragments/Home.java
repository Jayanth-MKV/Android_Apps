package com.dailyneeds.vugido.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.activities.CartActivity;
import com.dailyneeds.vugido.activities.MainActivity;
import com.dailyneeds.vugido.adapters.SliderAdapterExample.SliderAdapterExample;
import com.dailyneeds.vugido.adapters.ViewPagerAdapter;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.design.ZoomOutPageTransformer;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import static com.dailyneeds.vugido.app.ConfigVariables.ORDER_PLACED_CODE;

public class Home extends Fragment {

    private Context context;
    private FragmentActivity fragmentActivity;
    private TextView CartCount;
    RecyclerView recyclerView;
    SliderView sliderView;
    Activity activity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context=getActivity();
        fragmentActivity=getActivity();
        activity=getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home,container,false);

        recyclerView = view.findViewById(R.id.recyclerview_categories);
        //  GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 3, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(linearLayoutManager);
       // sliderView =view.findViewById(R.id.vp_slider_layout);
        //sliderView.setSliderAdapter(new SliderAdapterExample(context));
       // sliderView.startAutoCycle();
       // sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
       // sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
     //  CallCategories(recyclerView);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.cart){


            // showStartDate();
            activity.startActivityForResult(new Intent(context, CartActivity.class),MainActivity.ORDER_PLACED_CODE);
            return true;

        }
        return false;
    }




    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // configure your search here
        super.onCreateOptionsMenu(menu, inflater);
      /*  final MenuItem cart = menu.findItem(R.id.cart);
        View view=  cart.getActionView();
        CartCount=view.findViewById(R.id.cart_badge);
        ConfigVariables.setupBadge(new MyPreferences(context).getCartCount(),CartCount);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionsItemSelected(cart);
            }
        });*/

      /*  MenuItem cart = menu.findItem(R.id.cart);
        CartCount= (TextView) cart.getActionView();


        ConfigVariables.setupBadge(0,CartCount);*/




    }


    /*@Override
    public void updateCartCount(int Count) {

        assert CartCount!=null;
        ConfigVariables.setupBadge(Count,CartCount);

    }*/
}
