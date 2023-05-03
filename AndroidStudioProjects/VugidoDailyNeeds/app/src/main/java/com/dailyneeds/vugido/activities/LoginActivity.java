package com.dailyneeds.vugido.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.NetworkQueries;
import com.dailyneeds.vugido.receivers.NetworkCallBack;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, NetworkQueries.NetworkQueryInterface {

    public static final int CODE = 1;
    ImageButton SendOtp;
    ImageView Back;
    EditText Username,Phone;
    CheckBox TC;
    String User,Mobile;
    TextView PrivacyPolicy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        SendOtp=findViewById(R.id.sendOtpButton);
        Username=findViewById(R.id.username);
        Phone=findViewById(R.id.phone);
        TC=findViewById(R.id.TCPolicy);
        Back=findViewById(R.id.back);
        PrivacyPolicy=findViewById(R.id.privacyLink);
        PrivacyPolicy.setOnClickListener(this);
        Back.setOnClickListener(this);
        TC.setOnClickListener(this);
        SendOtp.setOnClickListener(this);
        SendOtp.setEnabled(false);

    }


    @Override
    protected void onResume() {
        super.onResume();

        int TIME = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!NetworkCallBack.NetworkAvailability){

                    startActivity(new Intent(LoginActivity.this, NetworkErrorActivity.class));

                }
            }
        }, TIME);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.sendOtpButton:
                // check the fields and pass the data....
                User=Username.getText().toString();
                Mobile=Phone.getText().toString();

                boolean numberStatus=checkNumber(Mobile);
                boolean nameStatus=checkName(User);

                if(numberStatus && nameStatus){


                    //send the otp to that number and launch the verify page..


                    NetworkQueries networkQueries=new NetworkQueries(this,ConfigVariables.SEND_OTP_URL,buildParams());
                    networkQueries.sendRequest("Waiting for Otp from server");

                    // put this in monitor...




                }else{

                  Toast toast=  Toast.makeText(this,"Enter Valid Information",Toast.LENGTH_SHORT);
                  toast.show();

                }



                break;
            case R.id.back:
                finish();
                break;

            case R.id.TCPolicy:

                if(TC.isChecked()){
                    //enable the submit button
                    SendOtp.setEnabled(true);
                    SendOtp.setBackground(getDrawable(R.drawable.button));
                }else {
                    // disable the button..
                    SendOtp.setEnabled(false);
                    SendOtp.setBackground(getDrawable(R.drawable.disable_button));

                }

                break;
            case R.id.privacyLink:

                break;


        }



        }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CODE ){

            if(resultCode==1){
                finish();
            }

        }



    }

    private boolean checkName(String username) {
        Pattern pattern= Pattern.compile("^[\\p{L} .'-]+$");
        Matcher matcher= pattern.matcher(username);

        if(matcher.find() && matcher.group().equals(username)){
            return  true;
        }
        else {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            Username.startAnimation(shake);
            return  false;
        }


    }

    private boolean checkNumber(String number) {


        Pattern pattern = Pattern.compile("[6-9][0-9]{9}");
        Matcher matcher = pattern.matcher(number);

        if(matcher.find() && matcher.group().equals(number)){
            return  true;
        }
        else {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            Phone.startAnimation(shake);
            return  false;
        }

    }

    @Override
    public void networkQueryError(String error) {

        // error from the volley Request ... show to user

        Toast.makeText(this,error,Toast.LENGTH_LONG).show();

    }

    @Override
    public void networkQueryInterface(String Response) {




          Intent intent=new Intent(LoginActivity.this,VerifyPage.class);
                   intent.putExtra("Name",User);
                   intent.putExtra("Phone",Mobile);
                   startActivityForResult(intent,CODE);


    }

    private Map<String,String> buildParams(){

        Map<String,String> params=new HashMap<>();
        params.put("user",User);
        params.put("mobile",Mobile);
        return  params;

    }
}
