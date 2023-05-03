package com.vugido.vugidoupdate.activities;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.vugido.vugidoupdate.R;
import com.vugido.vugidoupdate.app_settings.AppSignatureHelper;
import com.vugido.vugidoupdate.app_settings.MyPreferences;

import java.util.ArrayList;


public class SplashScreen extends AppCompatActivity {

    ConstraintLayout c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        c=findViewById(R.id.const_layout);

        AppSignatureHelper appSignatureHelper=new AppSignatureHelper(this);
        ArrayList<String> stringArrayList=appSignatureHelper.getAppSignatures();
        Log.e("HashString",stringArrayList.toString());
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
                    startActivity(new Intent(SplashScreen.this,AppIntroduction.class));

                }

                finish();
            }
        }, TIME);

    }
}
