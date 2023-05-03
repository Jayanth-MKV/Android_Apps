package com.Bablu.trackphone.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.Bablu.trackphone.R;
import com.Bablu.trackphone.fragments.login_fragments.LoginFragment;
import com.Bablu.trackphone.fragments.login_fragments.SignUpFragment;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener , SignUpFragment.LoginFragmentInterface, LoginFragment.SignUpFragmentInterface ,
        LoginFragment.ShowProgress, SignUpFragment.IMEI {

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;
    Toolbar toolbar;
//    View Progress;
String IMEINumber;
    private static final int REQUEST_CODE = 101;
    public static final int CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        frameLayout=findViewById(R.id.login_fragment_container);
        fragmentManager=getSupportFragmentManager();
      //  Progress=findViewById(R.id.my_progress);
      //  Progress.setVisibility(View.INVISIBLE);

        getIMEI();
        loadDefaultSignUpFragment();


        toolbar=findViewById(R.id.login_activity_toolbar);


        toolbar.setTitle("Login");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });






    }

    private void loadDefaultSignUpFragment() {

        fragment=new SignUpFragment();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.login_fragment_container,fragment,"SIGN_UP");
        fragmentTransaction.commit();

    }



    @Override
    public void onClick(View view) {




    }


    @Override
    public void loginFragment() {
        // replace the sign up with  login fragment


        fragment=new LoginFragment();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_fragment_container,fragment,"LOGIN");
        fragmentTransaction.commit();

        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);

    }

    @Override
    public void signUpFragment() {

        fragment=new SignUpFragment();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_fragment_container,fragment,"SIGN_UP");
        fragmentTransaction.commit();
        toolbar.setTitle("Sign- Up");
        setSupportActionBar(toolbar);
    }



    @Override
    public void showProgress(boolean show) {

        if(show){
            //Progress.setVisibility(View.VISIBLE);
        }else {
          //  Progress.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void getIMEI(){

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
            return;
        }
        IMEINumber = telephonyManager.getDeviceId();
        Toast.makeText(this,"IMEI fetched : "+IMEINumber,Toast.LENGTH_LONG).show();
       // textView.setText(IMEINumber);
    }


    @Override
    public String fetchIMEI() {

        return IMEINumber;
    }
}

