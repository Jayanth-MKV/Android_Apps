package com.dailyneeds.vugido.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dailyneeds.vugido.R;

import java.util.Objects;

public class AboutUs extends AppCompatActivity implements View.OnClickListener {
private Toolbar toolbar;
private Button FeedBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        toolbar=findViewById(R.id.aboutUs_toolbar);
        FeedBack=findViewById(R.id.feedback);
        FeedBack.setOnClickListener(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.about_us);



    }


    @Override
    public void onClick(View view) {


        if(view.getId()==R.id.feedback){

            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "imgideongo@gmail.com" });
            Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            Email.putExtra(Intent.EXTRA_TEXT, "Dear Vugido Team..." + "");
            startActivity(Intent.createChooser(Email, "Send Feedback:"));
        }
    }
}
