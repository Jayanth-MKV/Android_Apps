package com.dailyneeds.vugido.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dailyneeds.vugido.R;

public class NetworkErrorActivity extends AppCompatActivity {

    public static boolean ON=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_error_activity);
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
