package com.vugido.brain_cord.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.vugido.brain_cord.R;


public class NetworkErrorActivity extends AppCompatActivity {

    public static boolean ON=false;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_error_activity);
        lottieAnimationView=findViewById(R.id.lottie_no_network);
        lottieAnimationView.setAnimation(R.raw.network_err);
        lottieAnimationView.playAnimation();
        Toast.makeText(this,"Check Your Internet Connection", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ON=true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        ON=false;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(RESULT_CANCELED);
    }
}
