package com.dailyneeds.vugido.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.dailyneeds.vugido.Intro.First_Intro_Fragment;
import com.dailyneeds.vugido.Intro.Second_Intro_Fragment;
import com.dailyneeds.vugido.Intro.Third_Intro_Fragment;
import com.dailyneeds.vugido.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;


public class Intro_Activity extends AppIntro {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        addSlide(new First_Intro_Fragment());
        addSlide(new Second_Intro_Fragment());
        addSlide(new Third_Intro_Fragment());
        showSkipButton(true);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setBarColor(getResources().getColor(R.color.colorPrimaryDark));
        setSeparatorColor(getResources().getColor(R.color.colorAccent));


        setVibrate(true);
        setVibrateIntensity(50);



    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(Intro_Activity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
