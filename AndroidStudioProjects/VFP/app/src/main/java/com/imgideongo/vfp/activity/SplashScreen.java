package com.imgideongo.vfp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.imgideongo.vfp.R;
import com.imgideongo.vfp.helper.MyPreferences;

public class SplashScreen extends AppCompatActivity {

    MyPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        int TIME = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setupWindowAnimations();
                myPreferences=new MyPreferences(getApplicationContext());
                boolean Logged = myPreferences.getLoginState();

                if (!Logged) {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                } else {
                    //start your custom activity
                    Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                   // intent.putExtra("CID",Integer.parseInt(myPreferences.getUID()));
                   // intent.putExtra("LOGO",myPreferences.getLogoString());
                    startActivity(intent);
                }
                finish();

            }
        }, TIME);
    }



    }

