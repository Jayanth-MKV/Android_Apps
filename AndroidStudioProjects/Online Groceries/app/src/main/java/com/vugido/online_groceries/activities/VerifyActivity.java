package com.vugido.online_groceries.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.vugido.online_groceries.R;
import com.vugido.online_groceries.app.MyPreferences;
import com.vugido.online_groceries.dialogs.MyDialogLoader;
import com.vugido.online_groceries.dialogs.MyStatusDialog;
import com.vugido.online_groceries.models.user.Response;
import com.vugido.online_groceries.models.user.USER;
import com.vugido.online_groceries.network.RetrofitBuilder;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;


public class VerifyActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    Toolbar toolbar;
    private Button VerifyButton,Resend;
    private PinView pinView;
    private String OTP="",name="",phone,gmail="";
    private TextView timer,user_mobile;
    private CountDownTimer countDownTimer;
    private MyDialogLoader myDialogLoader;

    int L;
    private MyStatusDialog myStatusDialog;






    private void showDialogStatus(int status){

        if (myStatusDialog==null)
            myStatusDialog=new MyStatusDialog();

        Bundle bundle=new Bundle();
        if(status==2){
            bundle.putString("MSG","Wrong OTP...");

            bundle.putBoolean("STATUS",false);
            myStatusDialog.setArguments(bundle);
            myStatusDialog.show(getSupportFragmentManager(),"S");

        }else if(status==1){
            bundle.putString("MSG","Verified Successfully...");

            bundle.putBoolean("STATUS",true);
            myStatusDialog.setArguments(bundle);
            myStatusDialog.show(getSupportFragmentManager(),"S");

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


    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseAuth auth;
    String mVerificationId,mResendToken,code;
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
        user_mobile.setText(getIntent().getStringExtra("M"));
        setSupportActionBar(toolbar);
        pinView.addTextChangedListener(this);
        VerifyButton=findViewById(R.id.verify_button);
        VerifyButton.setOnClickListener(this);
        Resend.setOnClickListener(this);
        Resend.setEnabled(false);
        showTimer();
      //  initSmsRetriever();


        L=getIntent().getIntExtra("T",0);
        phone=getIntent().getStringExtra("M");
        if(getIntent().getIntExtra("T",0)==0){
            //signup
            name=getIntent().getStringExtra("N");
            gmail=getIntent().getStringExtra("G");
            firebaseAuth(getIntent().getStringExtra("N"),getIntent().getStringExtra("M"),gmail);

        }else{
            //login
            firebaseAuth(null,getIntent().getStringExtra("M"),null);

        }


    }





private void creUser(){

    Map<String,Object> map=new HashMap<>();

    map.put("MOBILE",phone);
    map.put("NAME",name);
    map.put("GMAIL",gmail);
    map.put("L",String.valueOf(L));


    myStatusDialog.dismiss();


    if(myDialogLoader==null){
        myDialogLoader=new MyDialogLoader();
    }
    Bundle bundle=new Bundle();
    bundle.putString("MSG","Creating Your Account");
    myDialogLoader.setArguments(bundle);
    myDialogLoader.show(getSupportFragmentManager(), "DL");
    Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().userCre(map);

    call.enqueue(new Callback<Response>() {
        @Override
        public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {


            Log.e("response",response.toString());
            myDialogLoader.dismiss();
            if(response.isSuccessful()){

                assert response.body() != null;
                if (response.body().isSTATUS()){
                    //ok success user

                    Log.e("ok","preferences called");
                    sePreferences(response.body().getUSER());
                }else {

                    // error no user... cre
                }

            }
        }

        @Override
        public void onFailure(@NonNull Call<Response> call, @NonNull Throwable x) {

            myDialogLoader.dismiss();
            Log.e("error",x.toString());
        }
    });
}

    private void sePreferences(USER user) {




        MyPreferences myPreferences=new MyPreferences(getApplicationContext());
        myPreferences.setUID(String.valueOf(user.getUID()));
        myPreferences.setUserMobile(user.getPHONE());
        myPreferences.setUserName(user.getUSERNAME());
        myPreferences.setGmail(user.getgMAIL());
        myPreferences.setVerified(true);
        //myPreferences.se

        Log.e("ok all  done","preferences called");

        setResult(RESULT_OK);
        finish();
        startActivity(new Intent(VerifyActivity.this,MainActivity.class));

        Log.e("ok all  done launched","preferences called");

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d("TAG", "signInWithCredential:success");
                        //Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_LONG).show();



                        FirebaseUser user = task.getResult().getUser();
                      //  myPreferences.s
                        showDialogStatus(1);
                        creUser();

                        // ...
                    } else {
                        // Sign in failed, display a message and update the UI
                        //Log.w("TAG", "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was
                           // Toast.makeText(getApplicationContext()," FAILED",Toast.LENGTH_LONG).show();
                            showDialogStatus(2);

                        }
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
            if(checkNumber(OTP) && OTP.length()==6){


                //now send the otp to the server to verify...
              // signInWithPhoneAuthCredential();
                PhoneAuthCredential credential1 = PhoneAuthProvider.getCredential(mVerificationId, OTP);
                signInWithPhoneAuthCredential(credential1);

            }
            else{

               // Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
               // pinView.startAnimation(shake);
                //Toast.makeText(this,"Enter Valid Pin",Toast.LENGTH_SHORT).show();
            }


        }else if(view.getId()==R.id.resend){




            timer.setVisibility(View.VISIBLE);
            Resend.setVisibility(View.INVISIBLE);
            Resend.setEnabled(false);
            VerifyButton.setEnabled(true);
            pinView.setEnabled(true);
            VerifyButton.setVisibility(View.VISIBLE);
            showTimer();

            if(getIntent().getIntExtra("T",0)==0){
                //signup
                name=getIntent().getStringExtra("N");
                firebaseAuth(getIntent().getStringExtra("N"), getIntent().getStringExtra("M"),getIntent().getStringExtra("G"));

            }else{
                //login
                firebaseAuth(getIntent().getStringExtra("N"), getIntent().getStringExtra("M"),null);

            }

        }

    }
    private void firebaseAuth( String name, String phone,String n){
        auth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.

                code=credential.getSmsCode();
                PhoneAuthCredential credential1 = PhoneAuthProvider.getCredential(mVerificationId, code);
                pinView.setText(code);
                signInWithPhoneAuthCredential(credential1);
            }



            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w("TAG", "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }

                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token.toString();



            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+91"+phone)       // Phone number to verify

                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}
