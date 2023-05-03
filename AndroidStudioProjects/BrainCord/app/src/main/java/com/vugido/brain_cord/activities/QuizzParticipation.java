package com.vugido.brain_cord.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vugido.brain_cord.R;
import com.vugido.brain_cord.app.MyPreferences;
import com.vugido.brain_cord.dialogs.MyDialogLoader;
import com.vugido.brain_cord.models.login_access.Response;
import com.vugido.brain_cord.models.quiz_reg.USER;
import com.vugido.brain_cord.network.RetrofitBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class QuizzParticipation extends AppCompatActivity {

    Button login;
    private CountDownTimer countDownTimer;
    TextView timer;
    TextInputEditText username,password;
    MyDialogLoader myDialogLoader;
    DatabaseReference databaseReference;
    boolean f=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quizz);
        username=findViewById(R.id.user_name);
        password=findViewById(R.id.user_phone);

        login=findViewById(R.id.button);

        login.setOnClickListener(v -> {
            if (!Objects.requireNonNull(username.getText()).toString().isEmpty() && !Objects.requireNonNull(password.getText()).toString().isEmpty() )
                login(username.getText().toString(),password.getText().toString());
            else
                Toast.makeText(getApplicationContext(),"Invalid Credentials..",Toast.LENGTH_LONG).show();
        });


        timer=findViewById(R.id.textView12);

        login.setEnabled(false);
        loginAccess();
    }


    private void login(String username,String password){
        loginLoader(true,"Checking Credentials..");

//        Map<String,Object> map=new HashMap<>();
//        map.put("USERNAME",username);
//        map.put("PASSWORD",password);


        databaseReference= FirebaseDatabase.getInstance().getReference("USERS");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loginLoader(false,"Checking Credentials..");
//                Log.e("USERS", String.valueOf(snapshot.child("0").child("email_id").getValue()));
//                Log.e("USERSCC", String.valueOf(snapshot.getChildrenCount()));




                for (int i=0;i<snapshot.getChildrenCount();i++){

                    String e= String.valueOf(snapshot.child(String.valueOf(i)).child("email_id").getValue());
                    if (e.equalsIgnoreCase(username)){

                        if (String.valueOf(snapshot.child(String.valueOf(i)).child("logged").getValue()).equals("0")) {
                            String p = String.valueOf(snapshot.child(String.valueOf(i)).child("password").getValue());
                            if (p.equals(password)) {
                                f = true;
                                loginUser(snapshot, i);
                                break;
                            }
                            else if (!f)
                                Toast.makeText(getApplicationContext(),"Invalid Credentials..",Toast.LENGTH_LONG).show();
                        }
                        else if (!f)
                            Toast.makeText(getApplicationContext(),"Someone already logged In with this account",Toast.LENGTH_LONG).show();

                    }
                }
                if (!f)
                    Toast.makeText(getApplicationContext(),"Invalid Credentials..",Toast.LENGTH_LONG).show();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loginLoader(false,"Checking Credentials..");
                Toast.makeText(getApplicationContext(),"Something went wrong..Try Again",Toast.LENGTH_LONG).show();

            }
        });




//
//        Call<com.vugido.brain_cord.models.quiz_reg.Response> responseCall=RetrofitBuilder.getInstance().getRetrofit().login(map);
//
//        responseCall.enqueue(new Callback<com.vugido.brain_cord.models.quiz_reg.Response>() {
//            @Override
//            public void onResponse(Call<com.vugido.brain_cord.models.quiz_reg.Response> call, retrofit2.Response<com.vugido.brain_cord.models.quiz_reg.Response> response) {
//                if (response.isSuccessful()) {
//                    assert response.body() != null;
//                    if (!response.body().isUSEREXISTS())
//                        Toast.makeText(getApplicationContext(),"Invalid Credentials..",Toast.LENGTH_LONG).show();
//                    else
//                        loginUser(response.body().getUSER());
//                }
//
//                loginLoader(false,"Checking Login Access...");
//            }
//
//            @Override
//            public void onFailure(Call<com.vugido.brain_cord.models.quiz_reg.Response> call, Throwable t) {
//
//            }
//        });

    }

    private void loginUser(DataSnapshot snapshot,int i) {
        loginLoader(true,"Logging you in...");


        databaseReference=FirebaseDatabase.getInstance().getReference("USERS");
        databaseReference.child(String.valueOf(i)).child("logged").setValue("1").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    //Do what you need to do
                    loginLoader(false,"Submitting Answer..");
                    new MyPreferences(getApplicationContext()).setUID(String.valueOf(snapshot.child(String.valueOf(i)).child("id").getValue()));
                    new MyPreferences(getApplicationContext()).setUserName(String.valueOf(snapshot.child(String.valueOf(i)).child("username").getValue()));
                    new MyPreferences(getApplicationContext()).setGmail(String.valueOf(snapshot.child(String.valueOf(i)).child("email_id").getValue()));
                    new MyPreferences(getApplicationContext()).setVerified(true);
                    startActivity(new Intent(QuizzParticipation.this,QuizzGuidelines.class));
                    finish();
                } else {
//                    Log.d(TAG, task.getException().getMessage());
                    loginLoader(false,"Submitting Answer..");

                }
            }
        });





//        new MyPreferences(getApplicationContext()).setUID(String.valueOf(snapshot.child(String.valueOf(i)).child("id").getValue()));
//        new MyPreferences(getApplicationContext()).setUserName(String.valueOf(snapshot.child(String.valueOf(i)).child("username").getValue()));
//        new MyPreferences(getApplicationContext()).setGmail(String.valueOf(snapshot.child(String.valueOf(i)).child("email_id").getValue()));
//        new MyPreferences(getApplicationContext()).setVerified(true);
//        startActivity(new Intent(QuizzParticipation.this,QuizzGuidelines.class));
//        finish();


    }

    public void loginLoader(boolean state, String msg) {
        if (myDialogLoader == null) {
            myDialogLoader=new MyDialogLoader();
        }
        if (state){
            Bundle bundle=new Bundle();
            bundle.putString("MSG",msg);
            myDialogLoader.setArguments(bundle);
            myDialogLoader.show(getSupportFragmentManager(), "DL");
        } else {
            myDialogLoader.dismiss();
        }
    }

    private void loginAccess() {

        loginLoader(true,"Checking Login Access...");

        databaseReference=FirebaseDatabase.getInstance().getReference("TIME_LINE");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (countDownTimer!=null)
                    countDownTimer.cancel();
               access(Objects.requireNonNull(snapshot.getValue()).toString());

                loginLoader(false,"Checking Login Access...");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loginLoader(false,"Checking Login Access...");
                Toast.makeText(getApplicationContext(),"Something went wrong..Try Again",Toast.LENGTH_LONG).show();

            }
        });





//        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().loginAccess();
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//
//                if (response.isSuccessful()) {
//                    assert response.body() != null;
//                    access(response.body().getSECONDS());
//                }
//
//                loginLoader(false,"Checking Login Access...");
//            //    Toast.makeText(getApplicationContext(),String.valueOf(response.body().getSECONDS()),Toast.LENGTH_LONG).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable e) {
//
//                loginLoader(false,"Checking Login Access...");
//                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
//
//
//            }
//        });
    }

    private void access(String d) {

        long seconds=0;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();


            Date d2 = formatter.parse(d);
            assert d2 != null;
            seconds = (d2.getTime()-date.getTime())/1000;


        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }


[
        {"id":"639","email_id":"Vamsiaduri563@gmail.com","username":"Student","password":"861539127",             "phone":"123456789","logged":"0"},
        {"id":"640","email_id":"akshayavarma39@gmail.com","username":"Student","password":"040521305",            "phone":"123456789","logged":"0"},
        {"id":"641","email_id":"akunuri.lakshmi.sahithi10@gmail.com","username":"Student","password":"846648098"        ,"phone":"123456789","logged":"0"},
        {"id":"648","email_id":"vasudhadabbiru@gmail.com","username":"Student","password":"033745701",             "phone":"123456789","logged":"0"},
        {"id":"654","email_id":"nikhitagarimella@gmail.com","username":"Student","password":"364834369",                "phone":"123456789","logged":"0"},
        {"id":"658","email_id":"netranandinigorle.gnn6@gmail.com","username":"Student","password":"743864103",           "phone":"123456789","logged":"0"},
        {"id":"660","email_id":"srividyakandalam1416@gmail.com","username":"Student","password":"966541814",           "phone":"123456789","logged":"0"},
        {"id":"662","email_id":"sasivinay2001@gmail.com","username":"Student","password":"609350560",            "phone":"123456789","logged":"0"},
        {"id":"666","email_id":"kondapallilavanya38@gmail.com","username":"Student","password":"380863299",             "phone":"123456789","logged":"0"},
        {"id":"676","email_id":"sameermd6143@gmail.com ","username":"Student","password":"522799622",                "phone":"123456789","logged":"0"},
        {"id":"693","email_id":"tarakaswathi2001@gmail.com","username":"Student","password":"192299144",           "phone":"123456789","logged":"0"},
        {"id":"691","email_id":"ameenabanu0930@gmail.com","username":"Student","password":"369994042",                    "phone":"123456789","logged":"0"}

]







        if (seconds<=0){
            login.setEnabled(true);
           // Toast.makeText(getApplicationContext(),String.valueOf(seconds),Toast.LENGTH_LONG).show();

            timer.setVisibility(View.GONE);
        }
        else{
            //Toast.makeText(getApplicationContext(),String.valueOf(seconds),Toast.LENGTH_LONG).show();

            login.setEnabled(false);
            showTimer(seconds);
            timer.setVisibility(View.VISIBLE);
            }



    }
    private void showTimer(long sec){

        countDownTimer=  new CountDownTimer(

                sec*1000,1000 ) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {

                long remainedSecs = millisUntilFinished / 1000;
                long h=(remainedSecs / 60);
               // long d=h/24;

               // Toast.makeText(getApplicationContext(),"Login After\n"+(remainedSecs / 60) + ":" + (remainedSecs % 60),Toast.LENGTH_LONG).show();
                timer.setText("Login After\n"+(remainedSecs / 60) + ":" + (remainedSecs % 60));
            }

            @Override
            public void onFinish() {
                timer.setVisibility(View.INVISIBLE);

                login.setEnabled(true);


            }
        };
        countDownTimer.start();

    }



}
