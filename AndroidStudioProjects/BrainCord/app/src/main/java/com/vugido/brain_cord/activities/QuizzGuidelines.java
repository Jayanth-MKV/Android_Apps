package com.vugido.brain_cord.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vugido.brain_cord.R;

public class QuizzGuidelines extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    CheckBox checkBox;
    Button quiz;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qg);
        checkBox=findViewById(R.id.checkBox);
        quiz=findViewById(R.id.button3);
        checkBox.setOnCheckedChangeListener(this);
        quiz.setOnClickListener(this);
        quiz.setEnabled(false);

        toolbar=findViewById(R.id.qg_activity_toolbar);


        toolbar.setTitle("Quiz Instructions");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));
        setSupportActionBar(toolbar);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.getId()==R.id.checkBox)
        {
            quiz.setEnabled(isChecked);

        }

    }

    @Override
    public void onClick(View v) {

        Intent intent=new Intent(QuizzGuidelines.this,QuizActivity.class);
        startActivityForResult(intent,1000);
        finish();
    }
}
