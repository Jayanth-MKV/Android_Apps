package com.vugido.info.homeservicesadmin.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vugido.info.homeservicesadmin.R;


public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView UserId,UserName,Phone,WhatsApp,Email;
    String toNumber,uid,name,email;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        UserId=findViewById(R.id.textView6);
        UserName=findViewById(R.id.textView7);
        Email=findViewById(R.id.textView8);

        Phone=findViewById(R.id.textView10);
        WhatsApp=findViewById(R.id.textView11);


        WhatsApp.setOnClickListener(this);
        Phone.setOnClickListener(this);


        toNumber=getIntent().getStringExtra("CALL");
        uid=String.valueOf(getIntent().getIntExtra("UID",0));
        email=getIntent().getStringExtra("EMAIL");

       name=getIntent().getStringExtra("NAME");

       UserId.setText("User Id :" + uid);
       UserName.setText(name);
       Email.setText(email);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.textView11:
                // start whatsApp


                try {
                    String text = "From Home Services, Dear: "+ name;// Replace with your message.

                    //String toNumber = "919381776137"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"91"+toNumber +"&text="+text));
                    startActivity(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case R.id.textView10:
                String number = "tel:"+toNumber;
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(number));
                startActivity(callIntent);
                break;

        }
    }
}
