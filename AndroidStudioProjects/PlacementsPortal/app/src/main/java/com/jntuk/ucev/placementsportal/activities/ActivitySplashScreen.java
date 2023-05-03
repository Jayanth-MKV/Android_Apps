package com.jntuk.ucev.placementsportal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jntuk.ucev.placementsportal.R;
import com.jntuk.ucev.placementsportal.app_config.MyPreferences;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        int TIME = 3000;
        new Handler().postDelayed(() -> {

            MyPreferences myPreferences=new MyPreferences(getApplicationContext());

            if(myPreferences.getVerified()){

                startActivity(new Intent(ActivitySplashScreen.this, MainActivity.class));


            }else{
                //launch login activity..
                // startActivity(new Intent(SplashScreen.this,SelectLocationServiceActivity.class));
                startActivity(new Intent(ActivitySplashScreen.this,ActivityLogin.class));
            }

            finish();
        }, TIME);

    }
}
