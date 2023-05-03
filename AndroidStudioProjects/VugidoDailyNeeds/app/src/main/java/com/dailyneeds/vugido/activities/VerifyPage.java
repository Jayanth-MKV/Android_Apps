package com.dailyneeds.vugido.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.dailyneeds.vugido.R;
import com.dailyneeds.vugido.app.ConfigVariables;
import com.dailyneeds.vugido.app.JsonResponseParser;
import com.dailyneeds.vugido.app.MyPreferences;
import com.dailyneeds.vugido.app.NetworkQueries;
import com.dailyneeds.vugido.dialogs.ResponseStatus;
import com.dailyneeds.vugido.receivers.NetworkCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class VerifyPage extends AppCompatActivity implements View.OnClickListener, TextWatcher , NetworkQueries.NetworkQueryInterface {
PinView pinView;

Button VerifyButton,Resend;
CountDownTimer countDownTimer;
TextView timer,EditPhone;
private  String OTP="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifypage);
        pinView=findViewById(R.id.firstPinView);
        VerifyButton = findViewById(R.id.verify_button);
        timer=findViewById(R.id.timer_text);
        Resend=findViewById(R.id.ResendButton);
        EditPhone=findViewById(R.id.phone_number_text);
        EditPhone.setOnClickListener(this);
        EditPhone.setText(getIntent().getStringExtra("Phone"));
        VerifyButton.setOnClickListener(this);
        Resend.setOnClickListener(this);
        Resend.setVisibility(View.GONE);
        showTimer();

        pinView.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.verify_button:


                //check the fields.
               if(checkNumber(OTP) && OTP.length()==4){


                   //now send the otp to the server to verify...
                   NetworkQueries networkQueries=new NetworkQueries(this, ConfigVariables.VERIFY_OTP_URL,buildParams());
                   networkQueries.sendRequest("Sending OTP to Verify");


               }
               else{

                   Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                   pinView.startAnimation(shake);
                   Toast.makeText(this,"Enter Valid Pin",Toast.LENGTH_SHORT).show();
               }

                break;
            case R.id.ResendButton:
                //resend OTP..
                pinView.setEnabled(true);
                Resend.setVisibility(View.GONE);
                timer.setVisibility(View.VISIBLE);
                showTimer();
                VerifyButton.setEnabled(true);
                VerifyButton.setBackground(getDrawable(R.drawable.button));

                break;
            case R.id.phone_number_text:
                setResult(0);
                finish();
                break;

        }

    }


    private boolean checkNumber(String number) {


        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(number);

        return matcher.find() && matcher.group().equals(number);


    }

    private void showTimer(){

        countDownTimer=  new CountDownTimer(

                60000,1000 ) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {

                long remainedSecs = millisUntilFinished / 1000;
                timer.setText((remainedSecs / 60) + ":" + (remainedSecs % 60));
            }

            @Override
            public void onFinish() {
                timer.setVisibility(View.GONE);
                Resend.setVisibility(View.VISIBLE);
                Resend.setEnabled(true);
                VerifyButton.setEnabled(false);
                pinView.setEnabled(false);
                VerifyButton.setBackground(getDrawable(R.drawable.disable_button));


            }
        };
        countDownTimer.start();

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {




    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {





    }

    @Override
    protected void onResume() {
        super.onResume();

        int TIME = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!NetworkCallBack.NetworkAvailability){

                    startActivity(new Intent(VerifyPage.this, NetworkErrorActivity.class));

                }
            }
        }, TIME);
    }
    @Override
    public void afterTextChanged(Editable editable) {
        OTP= editable.toString();


    }

    @Override
    public void onBackPressed() {

        if(VerifyButton.isEnabled())
        Toast.makeText(this,"Enter Otp before Exit",Toast.LENGTH_LONG).show();
        else
            super.onBackPressed();
    }

    private Map<String,String> buildParams(){

        Map<String,String> params=new HashMap<>();
        params.put("otp",OTP);
        return  params;

    }

    @Override
    public void networkQueryInterface(String Response) {

        // check for the response.. then proceed forward
        SweetAlertDialog s;
        JsonResponseParser jsonResponseParser=new JsonResponseParser(Response);
        final JSONObject jsonObject=jsonResponseParser.getJsonOTPParserData();

        if(jsonObject!=null){

            try {
                final boolean error=jsonObject.getBoolean("error");
               // final ResponseStatus responseStatus=new ResponseStatus();
                Bundle bundle=new Bundle();
                bundle.putBoolean("error",error);

                if(!error){
                     s=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Verified Successfully")
                            ;
                    s.show();


                   // bundle.putString("msg","Verified Successfully");
                    // verified successfully..

                }else {
                    s=new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Wrong OTP")
                    ;
                    s.show();


                  //  bundle.putString("msg","Wrong OTP");
                    // not a valid otp..

                }
               // responseStatus.setArguments(bundle);
               // responseStatus.show(getSupportFragmentManager(),"STATUS");

                int TIME = 2000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        s.dismissWithAnimation();
                       // responseStatus.dismiss();
                        if(!error){
                            // also here set all the preferences...

                            setAllPreferences(jsonObject);
                            startActivity(new Intent(VerifyPage.this,MainActivity.class));
                            setResult(1);
                            finish();

                        }
                    }
                }, TIME);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }




    }

    private void setAllPreferences(JSONObject jsonObject) {

        try {
            JSONObject object=jsonObject.getJSONObject("profile");
            MyPreferences myPreferences=new MyPreferences(getApplicationContext());
            myPreferences.setUID(object.getString("UID"));
            myPreferences.setUserMobile(object.getString("mobile"));
            myPreferences.setUserName(getIntent().getStringExtra("User"));
            myPreferences.setVerified(true);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void networkQueryError(String error) {

        Toast.makeText(this,error,Toast.LENGTH_LONG).show();

    }
}
