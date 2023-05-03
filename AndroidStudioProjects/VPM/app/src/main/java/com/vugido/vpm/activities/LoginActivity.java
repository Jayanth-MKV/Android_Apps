package com.vugido.vpm.activities;

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

import com.google.android.material.textfield.TextInputEditText;
import com.vugido.vpm.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/*
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;*/

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextInputEditText email,password;
    Button login;
    private TextView toolbar_title;

    //LottieAnimationView progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar=findViewById(R.id.login_activity_toolbar);
        email=findViewById(R.id.client_email);
        password=findViewById(R.id.client_password);
        login=findViewById(R.id.login_button);
       // progress=findViewById(R.id.lottieProgress);
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
                sendLoginData(Email,Password);

            }else {

                Toast.makeText(this,"Enter Valid Credentials",Toast.LENGTH_LONG).show();
            }

        }

    }

    private void sendLoginData(String email, String password) {


        /*progress.setVisibility(View.VISIBLE);
        progress.setSpeed(1.5f);
        progress.playAnimation();*/
        Map<String, Object> map=new HashMap<>();
        map.put("EMAIL_ID",email);
        map.put("PASSWORD",password);




    }


}

