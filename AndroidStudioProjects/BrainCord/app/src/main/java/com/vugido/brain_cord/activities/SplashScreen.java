package com.vugido.brain_cord.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vugido.brain_cord.R;
import com.vugido.brain_cord.activities.MainActivity;
import com.vugido.brain_cord.app.MyPreferences;


public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        int TIME = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                MyPreferences myPreferences=new MyPreferences(getApplicationContext());
//
                if(myPreferences.getVerified()){
                     //launch main activity directly

                        startActivity(new Intent(SplashScreen.this, QuizzGuidelines.class));


                }else{
//                    // launch login activity..
                    startActivity(new Intent(SplashScreen.this,QuizzParticipation.class));
                }

                finish();
            }
        }, TIME);

    }
}
