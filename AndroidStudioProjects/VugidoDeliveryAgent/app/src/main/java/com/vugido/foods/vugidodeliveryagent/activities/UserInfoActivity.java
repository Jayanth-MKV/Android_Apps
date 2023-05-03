package com.vugido.foods.vugidodeliveryagent.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vugido.foods.vugidodeliveryagent.R;

import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView UserId,UserName,Phone,WhatsApp;
    String toNumber;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
//        toolbar=findViewById(R.id.user_info_toolbar);
//
//        toolbar.setTitle("User Details");
//
//
//        setSupportActionBar(toolbar);
//
//
//        //toolbar.setNavigationIcon(R.drawable.back_nav);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                finish();
//            }
//        });
//
//
//
//        int OID= getIntent().getIntExtra("OID",0);


        /*UserId=findViewById(R.id.UserID);
        UserName=findViewById(R.id.UserName);
        Phone=findViewById(R.id.UserPhone);
        WhatsApp=findViewById(R.id.UserWhatsApp);

        Phone.setOnClickListener(this);
        WhatsApp.setOnClickListener(this);*/

       // fetchUserInfo(OID);



    }

  /*  private void fetchUserInfo(int oid) {

       // Log.e("OID",String.valueOf(oid));
        HashMap<String, Object> request = new HashMap<>();
        request.put("OID", String.valueOf(oid));

        Call<Response> response= RetrofitBuilder.getInstance().getRetrofit().getOrderedUserInfo(request);
        response.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if (!response.body().isSTATUS()){
                        // ok data got..

                        DATA data=response.body().getDATA();

                        setUseInfo(data);

                    }else{

                        // no data
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

            }
        });


    }

    private void setUseInfo(DATA data) {




        UserId.setText(String.format(Locale.getDefault(),"User ID :%d", data.getUID()));
        toNumber=data.getPHONE();
        UserName.setText(data.getUSERNAME());
        Phone.setText(data.getPHONE());


    }
*/
    @Override
    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.UserWhatsApp:
//                // start whatsApp
//
//
//                try {
//                    String text = "From Vugido Dailyneeds :";// Replace with your message.
//
//                  //  String toNumber = "919381776137"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
//                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
//
//
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"91"+toNumber +"&text="+text));
//                    startActivity(i);
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//
//                break;
//            case R.id.UserPhone:
//                String number = "tel:"+toNumber;
//                Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                callIntent.setData(Uri.parse(number));
//                startActivity(callIntent);
//                break;
//
//        }
    }
}
