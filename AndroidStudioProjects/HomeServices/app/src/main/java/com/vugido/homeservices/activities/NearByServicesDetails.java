package com.vugido.homeservices.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.vugido.homeservices.R;
import com.vugido.homeservices.app.MyPreferences;

import java.util.Objects;

public class NearByServicesDetails extends AppCompatActivity {
    Toolbar toolbar;
    RatingBar ratingBar;
    ImageView imageView;
    TextView des,add,hrs,ph,wap,dir,ser;
    int id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_services);


        toolbar=findViewById(R.id.include4);
        imageView=findViewById(R.id.imageView3);
        des=findViewById(R.id.textView5);
        add=findViewById(R.id.textView8);
        hrs=findViewById(R.id.textView11);
        ph=findViewById(R.id.textView12);
        wap=findViewById(R.id.textView13);
        dir=findViewById(R.id.textView14);
        ser=findViewById(R.id.textView7);
        ratingBar=findViewById(R.id.ratingBar);

        id=getIntent().getIntExtra("ID",0);

        Glide.with(this).load(getIntent().getStringExtra("IMG")).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(NearByServicesDetails.this,ImagesNear.class);
                intent.putExtra("ID",id);
                startActivity(intent);

            }
        });
        des.setText(getIntent().getStringExtra("DES"));
        add.setText(getIntent().getStringExtra("ADD"));
        hrs.setText(getIntent().getStringExtra("HRS"));

        ser.setText(getIntent().getStringExtra("S"));

        ratingBar.setRating(Float.parseFloat(getIntent().getStringExtra("RA")));
        dir.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr="+getIntent().getStringExtra("LA")+","+
                            getIntent().getStringExtra("LO")));
            startActivity(intent);
        });

        ph.setOnClickListener(v -> {

            String number = "tel:"+getIntent().getStringExtra("WAP");
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse(number));
            startActivity(callIntent);
        });

        wap.setOnClickListener(v -> {

            try {
                String text = "Dear: "+ getIntent().getStringExtra("N");// Replace with your message.

                //String toNumber = "919381776137"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"91"+getIntent().getStringExtra("WAP") +"&text="+text));
                startActivity(i);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        });



        toolbar.setTitle(getIntent().getStringExtra("N"));
        toolbar.setTitleTextColor(getResources().getColor(R.color.mred));
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void myDialogBox() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_layout_image, null);

        final ImageView n =  dialogView.findViewById(R.id.imageView8);

        Glide.with(this).load(getIntent().getStringExtra("IMG")).into(n);




//        Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
//        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialogBuilder.dismiss();
//            }
//        });
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // DO SOMETHINGS
//
//                // check first for valid name...
//
//
//                // get the text and send to server...
//                String CategoryName= editText.getText().toString();
//
//
//
//
//
//
//            }
//        });






        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

}
