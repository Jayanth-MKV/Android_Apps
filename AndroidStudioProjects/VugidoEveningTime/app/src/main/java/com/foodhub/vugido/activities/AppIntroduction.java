package com.foodhub.vugido.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.foodhub.vugido.R;
import com.foodhub.vugido.adapters.IntroViewPagerAdapter;
import com.foodhub.vugido.models.ScreenItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static com.foodhub.vugido.fragments.login_fragments.LoginFragment.VA;


public class AppIntroduction extends AppCompatActivity {


    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0 ;
    Button btnGetStarted;
    Animation btnAnim ;
    TextView tvSkip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);


        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);





        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Welcome To","Vugido Food-Hub\nThe World Of Food",R.raw.food_burger));
        mList.add(new ScreenItem("Fast Delivery","",R.raw.delivery_boy));
        mList.add(new ScreenItem("Order Online","Get it delivered to your home",R.raw.h_d));

        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tab-layout with viewpager

        tabIndicator.setupWithViewPager(screenPager);




        btnNext.setOnClickListener(v -> {

            position = screenPager.getCurrentItem();
            if (position < mList.size()) {

                position++;
                screenPager.setCurrentItem(position);


            }

            if (position == mList.size()-1) { // when we rech to the last screen

                // TODO : show the GETSTARTED Button and hide the indicator and the next button

                loaddLastScreen();


            }



        });

        // tablayout add change listener

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                if (tab.getPosition() == mList.size()-1) {

                    loaddLastScreen();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        // Get Started button click listener

        btnGetStarted.setOnClickListener(v -> {



           Intent intent=new Intent(AppIntroduction.this, LoginActivity.class);
           startActivityForResult(intent,VA);

            // also we need to save a boolean value to storage so next time when the user run the app
            // we could know that he is already checked the intro screen activity
            // i'm going to use shared preferences to that process
            //savePrefsData();
            finish();



        });

        // skip button click listener

        tvSkip.setOnClickListener(v -> screenPager.setCurrentItem(mList.size()));



    }





    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
        btnGetStarted.setAnimation(btnAnim);



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==VA && resultCode == RESULT_OK){

            finish();

        }
    }
}
