package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.activities;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.chaos.view.PinView;
import com.foodhub.vugido.services.ReGenerateFCMToken;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.foodhub.vugido.R;
import com.foodhub.vugido.app_config_variables.MyPreferences;
import com.foodhub.vugido.dialogs.MyDialogLoader;
import com.foodhub.vugido.models.verify_response.PROFILE;
import com.foodhub.vugido.models.verify_response.Response;
import com.foodhub.vugido.network.Retrofit.RetrofitBuilder;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;


public class VerifyActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    Toolbar toolbar;
    private Button VerifyButton,Resend;
    private PinView pinView;
    private String OTP="";
    //View Progress;
    private TextView timer,user_mobile;
    private CountDownTimer countDownTimer;
    private int LS;
    private MyDialogLoader myDialogLoader;
    private int OTP_ID;




    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            // get otp and initialize or send to server..
            String OTP = intent.getStringExtra("OTP");
          //  pinView.set

            pinView.setText(OTP);
            sendOTPToServer(OTP);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getPackageName());
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
    private void sendOTPToServer(String otp) {


        //show the progress bar
        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Verifying OTP");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");
        VerifyButton.setEnabled(false);


        Log.e("We can make a ","call to verify otp ok");
        Map<String, Object> map=new HashMap<>();
        map.put("OTP",otp);
        map.put("ID",String.valueOf(OTP_ID));
        map.put("LS",String.valueOf(LS));
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit("http://www.vugido.com/VUGIDO_MAIN/INVENTORY_MANAGEMENT_FILES/PHP_FILES/").verifyUsrUrl(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull final retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isERROR()){
                        //ok verified successfully..

                        showDialogStatus(1);

                        int TIME = 1000;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                // showDialogStatus(3);
                                // s.dismissWithAnimation();
                                // responseStatus.dismiss();
                                    // also here set all the preferences...

                                setAllPreferences(response.body().getPROFILE());
                                    startActivity(new Intent(VerifyActivity.this, MainActivity.class));
                                    setResult(1);
                                    finish();



                            }
                        }, TIME);

                    }else {

                        // wrong otp entered

                        showDialogStatus(2);
                        Toast.makeText(getApplicationContext(),"Wrong OTP",Toast.LENGTH_LONG).show();
                        VerifyButton.setEnabled(true);

                    }
                }else {

                    // not ok
                }

                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();


            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {

                // show some error
                VerifyButton.setEnabled(true);
                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();
            }
        });


        
    }

    private void setAllPreferences(PROFILE profile) {


        MyPreferences myPreferences=new MyPreferences(this);
        myPreferences.setChannelId(0);
        myPreferences.setUID(String.valueOf(profile.getUID()));
        myPreferences.setUserMobile(profile.getPHONE());
        myPreferences.setUserName(profile.getUSERNAME());
        myPreferences.setReferralCode(profile.getREFERRALCODE());
        if(profile.getISREFERRED()==1)
            myPreferences.setReferralCodeStatus(true);
        else
            myPreferences.setReferralCodeStatus(false);
        myPreferences.setVerified(true);
        if(profile.getCOINS()!=null) {
            myPreferences.setCoinsCount(profile.getCOINS().get(0).getCOINS());
            if (profile.getCOINS().get(0).getACTIVATED() == 1)
                myPreferences.setCoinsActivated(true);
            else
                myPreferences.setCoinsActivated(false);
        }else {
            myPreferences.setCoinsActivated(false);
            myPreferences.setCoinsCount(0);
        }



    }

    private void showDialogStatus(int status){

        if(status==2){
            Toast.makeText(this,"Wrong OTP",Toast.LENGTH_LONG).show();
        }else if(status==1){
            Toast.makeText(this,"Verified Successfully",Toast.LENGTH_LONG).show();

        }
        }


    @Override
    public void onBackPressed() {

        if(VerifyButton.isEnabled())
            Toast.makeText(this,"Enter Otp before Exit",Toast.LENGTH_LONG).show();
        else
           // setResult(100);
           super.onBackPressed();
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
                timer.setVisibility(View.INVISIBLE);
                Resend.setVisibility(View.VISIBLE);
                Resend.setEnabled(true);
                VerifyButton.setEnabled(false);
                pinView.setEnabled(false);
                VerifyButton.setVisibility(View.INVISIBLE);


            }
        };
        countDownTimer.start();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        toolbar=findViewById(R.id.verify_toolbar);
        pinView=findViewById(R.id.firstPinView);
        timer=findViewById(R.id.timer);
        Resend=findViewById(R.id.resend);
        user_mobile=findViewById(R.id.mobile_number);
        user_mobile.setText("");
        user_mobile.setText(getIntent().getStringExtra("MOBILE"));
        setSupportActionBar(toolbar);

        LS=getIntent().getIntExtra("LS",0);
        OTP_ID=getIntent().getIntExtra("ID",0);
        pinView.addTextChangedListener(this);

        VerifyButton=findViewById(R.id.verify_button);
        VerifyButton.setOnClickListener(this);
        Resend.setOnClickListener(this);
        Resend.setEnabled(false);
        showTimer();
        initSmsRetriever();



    }


    private void initSmsRetriever(){


        // Get an instance of SmsRetrieverClient, used to start listening for a matching
// SMS message.
        SmsRetrieverClient client = SmsRetriever.getClient(this);

// Starts SmsRetriever, which waits for ONE matching SMS message until timeout
// (5 minutes). The matching SMS message will be sent via a Broadcast Intent with
// action SmsRetriever#SMS_RETRIEVED_ACTION.
        Task<Void> task = client.startSmsRetriever();

// Listen for success/failure of the start Task. If in a background thread, this
// can be made blocking using Tasks.await(task, [timeout]);
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Successfully started retriever, expect broadcast intent
                // ...
                Log.e("Success Listener","started");
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Failed to start retriever, inspect Exception for more details
                // ...
                Log.e("Failure Listener","started");

            }
        });

    }


    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.verify_button){

            // check the otp valid or not..

            // send it to server to verify..

            // if all ok then

            //check the fields.
            if(checkNumber(OTP) && OTP.length()==4){


                //now send the otp to the server to verify...
                sendOTPToServer(OTP);


            }
            else{

               // Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
               // pinView.startAnimation(shake);
                Toast.makeText(this,"Enter Valid Pin",Toast.LENGTH_SHORT).show();
            }


        }else if(view.getId()==R.id.resend){


            // resend otp...
            if(LS==1)
                sendUserLoginInfo();
            else
                sendSignUpInfo();

        }

    }

    private void sendSignUpInfo() {


        Resend.setEnabled(false);
        //show the progress bar

        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Requesting Resend OTP");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");

        Map<String, Object> map=new HashMap<>();
        map.put("MOBILE",getIntent().getStringExtra("MOBILE"));
        map.put("USER_NAME",getIntent().getStringExtra("NAME"));

        Call<com.foodhub.vugido.models.login_sign_up_response.Response> call= RetrofitBuilder.getInstance().getRetrofit("http://www.vugido.com/VUGIDO_MAIN/INVENTORY_MANAGEMENT_FILES/PHP_FILES/").signUpUrl(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.login_sign_up_response.Response>() {
            @Override
            public void onResponse(@NonNull Call<com.foodhub.vugido.models.login_sign_up_response.Response> call, @NonNull retrofit2.Response<com.foodhub.vugido.models.login_sign_up_response.Response> response) {
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isERROR()){
                        //ok otp initiated
                        OTP_ID=response.body().getID();
                        timer.setVisibility(View.VISIBLE);
                        Resend.setVisibility(View.INVISIBLE);
                        Resend.setEnabled(false);
                        VerifyButton.setEnabled(true);
                        pinView.setEnabled(true);
                        VerifyButton.setVisibility(View.VISIBLE);
                        showTimer();




                    }else {

                        // not initiated..
                        //  Toast.makeText(context,"Some Error ..Try Again!..",Toast.LENGTH_LONG).show();
                    }

                }else {

                    // some error
                }

                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<com.foodhub.vugido.models.login_sign_up_response.Response> call, @NonNull Throwable t) {
                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();
            }
        });

    }
    private void sendUserLoginInfo() {


        // disable the login button and show progress bar..

        Resend.setEnabled(false);
        //show the progress bar

        if(myDialogLoader==null){
            myDialogLoader=new MyDialogLoader();
        }
        Bundle bundle=new Bundle();
        bundle.putString("MSG","Requesting Resend OTP");
        myDialogLoader.setArguments(bundle);
        myDialogLoader.show(getSupportFragmentManager(), "DL");

        Map<String, Object> map=new HashMap<>();
        map.put("MOBILE",getIntent().getStringExtra("MOBILE"));


        Call<com.foodhub.vugido.models.login_sign_up_response.Response> call=RetrofitBuilder.getInstance().getRetrofit("http://www.vugido.com/VUGIDO_MAIN/INVENTORY_MANAGEMENT_FILES/PHP_FILES/").loginUrl(map);

        call.enqueue(new Callback<com.foodhub.vugido.models.login_sign_up_response.Response>() {
            @Override
            public void onResponse(Call<com.foodhub.vugido.models.login_sign_up_response.Response> call, retrofit2.Response<com.foodhub.vugido.models.login_sign_up_response.Response> response) {
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isERROR()){
                        //ok otp initiated
                        OTP_ID=response.body().getID();
                        timer.setVisibility(View.VISIBLE);
                        Resend.setVisibility(View.INVISIBLE);
                        Resend.setEnabled(false);
                        VerifyButton.setEnabled(true);
                        pinView.setEnabled(true);
                        VerifyButton.setVisibility(View.VISIBLE);
                        showTimer();




                    }else {

                        // not initiated..
                        //  Toast.makeText(context,"Some Error ..Try Again!..",Toast.LENGTH_LONG).show();
                    }

                }else {

                    // some error
                }

                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();
            }

            @Override
            public void onFailure(Call<com.foodhub.vugido.models.login_sign_up_response.Response> call, Throwable t) {
                if(myDialogLoader!=null)
                    myDialogLoader.dismiss();
            }
        });

    }
    private boolean checkNumber(String number) {


        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(number);

        return matcher.find() && matcher.group().equals(number);


    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        OTP= editable.toString();
    }
}
