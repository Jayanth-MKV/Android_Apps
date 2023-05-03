package com.Bablu.trackphone.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Bablu.trackphone.R;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    private CardView profile,protect,track;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.main_toolbar);
        profile=findViewById(R.id.card_profile);
        protect=findViewById(R.id.card_protect);
        track=findViewById(R.id.card_track);


        profile.setOnClickListener(this);
        protect.setOnClickListener(this);
        track.setOnClickListener(this);

        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setTitle("TrackPhone");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.card_profile:
                // start profile activity... and show the data
                startActivity(new Intent(MainActivity.this,ActionActivity.class).putExtra("ACTION",0));
                break;
            case R.id.card_protect:
                // start protect activity
                startActivity(new Intent(MainActivity.this,ActionActivity.class).putExtra("ACTION",1));
                break;
            case R.id.card_track:
                myDialogBox();
                // start the track activity...
              //  startActivity(new Intent(MainActivity.this,ActionActivity.class).putExtra("ACTION",2));
                break;

        }
    }


    private void myDialogBox() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.login_track_dialog, null);

       // final EditText editText = (EditText) dialogView.findViewById(R.id.edt_commen);
        Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS

                // check first for valid name...


                // get the text and send to server...
              //  String CategoryName= editText.getText().toString();



                dialogBuilder.dismiss();
                startActivity(new Intent(MainActivity.this,ActionActivity.class).putExtra("ACTION",2));

            /*    if(checkName(CategoryName)){
                   // addSubCategory(CategoryName);
                    dialogBuilder.dismiss();

                }else {
                    Toast.makeText(getApplicationContext(),"Enter Valid Category Name(all letters)",Toast.LENGTH_LONG).show();
                }*/


            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }
}
