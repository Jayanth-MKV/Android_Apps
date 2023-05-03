package com.dailyneeds.vugido.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.app.MyPreferences;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int TIME = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                MyPreferences myPreferences=new MyPreferences(getApplicationContext());

                if(myPreferences.getVerified()){
                    // launch main activity directly

                    startActivity(new Intent(SplashScreen.this,MainActivity.class));

                }else{
                    // launch login activity..
                    startActivity(new Intent(SplashScreen.this,Intro_Activity.class));

                }

                finish();
            }
        }, TIME);

    }


}
