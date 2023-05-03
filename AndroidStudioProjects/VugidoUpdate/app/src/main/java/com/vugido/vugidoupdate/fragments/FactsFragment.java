package com.vugido.vugidoupdate.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.adapters.FactPage.FactPagerAdapter;
import com.vugido.vugidoupdate.adapters.SliderViewAdapter;
import com.vugido.vugidoupdate.designs.VerticalViewPager;
import com.vugido.vugidoupdate.models.facts_page.FactsPageModel;

import java.util.ArrayList;
import java.util.List;

public class FactsFragment extends Fragment {

    private LottieAnimationView SwipeUp;
    private VerticalViewPager viewPager;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_facts, container, false);
        SwipeUp=view.findViewById(R.id.swipe_up);
        viewPager=view.findViewById(R.id.fact_pager);

        SwipeUp.playAnimation();

        List<FactsPageModel> factsPageModelList= getFactsData();

        FactPagerAdapter factPagerAdapter=new FactPagerAdapter(factsPageModelList,context);

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(10);
        viewPager.setAdapter(factPagerAdapter);
        return view;
    }

    private List<FactsPageModel> getFactsData() {
        List<FactsPageModel> factsPageModelList=new ArrayList<>();
        factsPageModelList.add(new FactsPageModel(R.drawable.f1));
        factsPageModelList.add(new FactsPageModel(R.drawable.f2));
        factsPageModelList.add(new FactsPageModel(R.drawable.f3));
        factsPageModelList.add(new FactsPageModel(R.drawable.f4));
        factsPageModelList.add(new FactsPageModel(R.drawable.f5));
        return factsPageModelList;

    }
}