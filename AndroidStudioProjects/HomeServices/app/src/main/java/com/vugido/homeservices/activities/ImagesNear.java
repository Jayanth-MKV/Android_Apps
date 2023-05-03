package com.vugido.homeservices.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.vugido.homeservices.R;
import com.vugido.homeservices.adapters.nearpage.ImageViewer;
import com.vugido.homeservices.adapters.nearpage.IntroViewPagerAdapter;
import com.vugido.homeservices.design.LinePagerIndicatorDecoration;
import com.vugido.homeservices.design.ZoomOutPageTransformer;
import com.vugido.homeservices.models.near_page.images.DATAItem;
import com.vugido.homeservices.models.near_page.images.Response;
import com.vugido.homeservices.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


public class ImagesNear extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {



    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0,id ;

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       // requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);



        id=getIntent().getIntExtra("ID",0);
        // ini views

        // ini views
      //  btnNext = findViewById(R.id.btn_next);
        //btnGetStarted = findViewById(R.id.btn_get_started);
      //  tabIndicator = findViewById(R.id.tab_indicator);
       // btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
       // tvSkip = findViewById(R.id.tv_skip);

        recyclerView=findViewById(R.id.rec);
        swipeRefreshLayout = findViewById(R.id.sw);
        // view pager
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        // get the data here
        swipeRefreshLayout.post(this::fetchImages);


        //screenPager =findViewById(R.id.screen_viewpager);










    }

    private void fetchImages() {

        Map<String,Object> map=new HashMap<>();
        map.put("CID",String.valueOf(id));

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().nearImages(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                swipeRefreshLayout.setRefreshing(false);

                if(response.isSuccessful())
                {


                    assert response.body() != null;
                    if(response.body().isSTATUS()){

                        bindData(response.body().getDATA());
                    }else{


                        //no daa..
                    }
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    private void bindData(List<DATAItem> data) {

        String[] strings= data.get(0).getIMAGES().split(",");

        List<String> stringList = new ArrayList<>(Arrays.asList(strings));

        ImageViewer imageViewer = new ImageViewer(stringList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(imageViewer);

//        // setup tab-layout with viewpager



//        // setup viewpager
//        introViewPagerAdapter = new IntroViewPagerAdapter(this,stringList);
//        //tabIndicator.setupWithViewPager(screenPager);
//        screenPager.setAdapter(introViewPagerAdapter);


      //  screenPager.setPageTransformer(true,new ZoomOutPageTransformer());
        // setup tab-layout with viewpager




//        btnNext.setOnClickListener(v -> {
//
//            position = screenPager.getCurrentItem();
//            if (position < stringList.size()) {
//
//                position++;
//                screenPager.setCurrentItem(position);
//
//
//            }
//
//            if (position == stringList.size()-1) { // when we rech to the last screen
//
//                // TODO : show the GETSTARTED Button and hide the indicator and the next button
//
//                loaddLastScreen();
//
//
//            }
//
//
//
//        });

        // tablayout add change listener

//        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//
////                if (tab.getPosition() == stringList.size()-1) {
////
////                    loaddLastScreen();
////
////                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRefresh() {
        fetchImages();
    }
}
