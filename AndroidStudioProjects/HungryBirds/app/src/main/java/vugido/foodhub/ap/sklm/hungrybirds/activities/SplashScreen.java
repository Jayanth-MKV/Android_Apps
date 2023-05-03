package vugido.foodhub.ap.sklm.hungrybirds.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import vugido.foodhub.ap.sklm.hungrybirds.R;
import vugido.foodhub.ap.sklm.hungrybirds.app_config.MyPreferences;


public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        int TIME = 3000;
        new Handler().postDelayed(() -> {

            MyPreferences myPreferences=new MyPreferences(getApplicationContext());

            if(myPreferences.getVerified()){
                // launch main activity directly

                startActivity(new Intent(SplashScreen.this,MainActivity.class));

            }else{

                startActivity(new Intent(SplashScreen.this,AppIntroduction.class));
            }

            finish();
        }, TIME);

    }
}
