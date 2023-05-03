package com.vugido.com.vugidoeats.activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vugido.com.vugidoeats.R;
import com.vugido.com.vugidoeats.app_config.MyPreferences;

public class ScanActivity extends AppCompatActivity {

    private static final int SCANNED_REQUEST_CODE = 1;
    ImageView imageView;
    Button scan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scan);
        scan=findViewById(R.id.button);
        imageView=findViewById(R.id.imageView);
        if(new MyPreferences(getApplicationContext()).isOnGoing()){
            Intent intent=new Intent(ScanActivity.this,MenuActivity.class);
            startActivityForResult(intent,SCANNED_REQUEST_CODE);
        }
        imageView.setOnClickListener(v -> {

            if(new MyPreferences(getApplicationContext()).getVerified()){

            }else {
                startActivity(new Intent(ScanActivity.this,LoginActivity.class));

            }
        });
        scan.setOnClickListener(v -> {
            Intent intent=new Intent(ScanActivity.this,MainActivity.class);
            startActivityForResult(intent,SCANNED_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SCANNED_REQUEST_CODE){

            if(resultCode==RESULT_OK){
                finish();

            }
        }

    }
}
