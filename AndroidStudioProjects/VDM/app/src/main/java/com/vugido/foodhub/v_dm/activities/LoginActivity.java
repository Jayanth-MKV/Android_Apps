package com.vugido.foodhub.v_dm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.vugido.foodhub.v_dm.R;
import com.vugido.foodhub.v_dm.Retrofit.RetrofitBuilder;
import com.vugido.foodhub.v_dm.app_config_variables.MyPreferences;
import com.vugido.foodhub.v_dm.dialogs.MyDialogLoader;
import com.vugido.foodhub.v_dm.models.login.ProfileItem;
import com.vugido.foodhub.v_dm.models.login.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextInputEditText email,password;
    Button login;
    private MyDialogLoader myDialogLoader;
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


        if (myDialogLoader == null) {
            myDialogLoader=new MyDialogLoader();
        }
            Bundle bundle=new Bundle();
            bundle.putString("MSG","Verifying Login");
            myDialogLoader.setArguments(bundle);
            myDialogLoader.show(getSupportFragmentManager(), "DL");

        /*progress.setVisibility(View.VISIBLE);
        progress.setSpeed(1.5f);
        progress.playAnimation();*/
        Map<String, Object> map=new HashMap<>();
        map.put("MID",email);
        map.put("MPD",password);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().loginUrl(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call,@NonNull retrofit2.Response<Response> response) {

                myDialogLoader.dismiss();
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){

                        List<ProfileItem> profileItemList=response.body().getProfile();
                        bindData(profileItemList);
                    }else {
                        Toast.makeText(getApplicationContext(),"Wrong Credentials or Already Logged On Another Device",Toast.LENGTH_LONG).show();
                    }
                }else {

                    //error
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                myDialogLoader.dismiss();
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
        });





    }

    private void bindData(List<ProfileItem> profileItemList) {
        ProfileItem profileItem=profileItemList.get(0);
        MyPreferences myPreferences=new MyPreferences(this);
        myPreferences.setDID(String.valueOf(profileItem.getMID()));
//        myPreferences.setUserName(profileItem.getNAME());
//        //myPreferences.setUserPrimaryAddress();
//        myPreferences.setUserMobile(profileItem.getPHONE());
        myPreferences.setVerified(true);

        Toast.makeText(this,"Verified Success",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,MainActivity.class));
        finish();

    }


}

