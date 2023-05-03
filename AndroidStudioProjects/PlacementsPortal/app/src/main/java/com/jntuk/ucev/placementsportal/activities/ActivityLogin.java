package com.jntuk.ucev.placementsportal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jntuk.ucev.placementsportal.R;

import java.util.Objects;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    Button button;
    TextView SignUp;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        toolbar=findViewById(R.id.toolbar);


        toolbar.setTitle("Login");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(view -> finish());
        button=findViewById(R.id.button);
        button.setOnClickListener(this);
        SignUp=findViewById(R.id.login);
        String text = "<font color=#cc0029>No account? </font> <font color=#ffcc00>Register Now</font>";
        SignUp.setText(Html.fromHtml(text));
        SignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.login){
            startActivity(new Intent(ActivityLogin.this,ActivityRegisteration.class));
            finish();
        }
    }
}
