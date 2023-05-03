package com.vugido_business_panel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;
import com.vugido_business_panel.R;
import com.vugido_business_panel.Retrofit.RetrofitBuilder;
import com.vugido_business_panel.app_congif.MyPreferences;
import com.vugido_business_panel.models.login.DATAItem;
import com.vugido_business_panel.models.login.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextInputEditText email,password;
    Button login;
    private SweetAlertDialog s;
    private TextView toolbar_title;

    LottieAnimationView progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar=findViewById(R.id.login_activity_toolbar);
        email=findViewById(R.id.client_email);
        password=findViewById(R.id.client_password);
        login=findViewById(R.id.login_button);
        progress=findViewById(R.id.lottieProgress);
        toolbar_title=findViewById(R.id.toolbar_title);


        toolbar_title.setText("Login");
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        login.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.login_button){
            String Email= Objects.requireNonNull(email.getText()).toString();
            String Password= Objects.requireNonNull(password.getText()).toString();

            if(!Email.equals(null) && !Password.equals(null)){

                //ok send the data

                Log.d("email",Email);
                Log.d("password",Password);

                sendLoginData(Email,Password);

            }else {

                Toast.makeText(this,"Enter Valid Credentials",Toast.LENGTH_LONG).show();
            }

        }

    }

    private void sendLoginData(String email, String password) {


        progress.setVisibility(View.VISIBLE);
        progress.setSpeed(1.5f);
        progress.playAnimation();
        Map<String, Object> map=new HashMap<>();
        map.put("EMAIL_ID",email);
        map.put("PASSWORD",password);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().clientLogin(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                progress.setVisibility(View.GONE);
                progress.cancelAnimation();
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        MyPreferences myPreferences=new MyPreferences(getApplicationContext());
                        myPreferences.setVerified(true);
                        List<DATAItem> dataItemList =response.body().getDATA();
                        DATAItem dataItem=dataItemList.get(0);

                        myPreferences.setCID(String.valueOf(dataItem.getID()));
                        myPreferences.setClientName(dataItem.getNAME());
                        myPreferences.setClientEmail(dataItem.getEMAILID());
                        myPreferences.setContact(dataItem.getCONTACT());
                        myPreferences.setBusinessName(dataItem.getBUSINESSNAME());
                        myPreferences.setLogoUrl(dataItem.getLOGO());
                        myPreferences.setLocationName(dataItem.getLOCATIONNAME());
                        myPreferences.setLatitude(dataItem.getLATITUDE());
                        myPreferences.setLongitude(dataItem.getLONGITUDE());
                        myPreferences.setCreatedOn(dataItem.getCREATEDON());

                        showDialogStatus(true);


                        int TIME = 1000;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                finish();
                            }
                        }, TIME);


                    }else {

                        showDialogStatus(false);
                        Toast.makeText(getApplicationContext(),"Wrong credentials,, Retry !!",Toast.LENGTH_LONG).show();

                    }


                }else {

                   // showDialogStatus(false);
                    progress.setVisibility(View.GONE);
                    progress.cancelAnimation();
                    Toast.makeText(getApplicationContext(),"Retry !! Error",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                progress.setVisibility(View.GONE);
                progress.cancelAnimation();
                Toast.makeText(getApplicationContext(),"Retry !! Network Problem",Toast.LENGTH_LONG).show();


            }
        });




    }

    private void showDialogStatus(boolean status){
        if(status){
            s=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Successfully verified")
            ;
            s.show();
        }else{
            s=new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Wrong credentials!! Try again")
            ;
            s.show();

        }

    }
}

