package com.vugido.brain_cord.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vugido.brain_cord.R;
import com.vugido.brain_cord.app.MyPreferences;
import com.vugido.brain_cord.dialogs.MyDialogLoader;
import com.vugido.brain_cord.dialogs.MyStatusDialog;
import com.vugido.brain_cord.models.Response;
import com.vugido.brain_cord.network.RetrofitBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class CollegeForm extends AppCompatActivity {


    TextView name,email;
    Button button;
    RatingBar ratingBar;
    TextInputEditText editText;
    MyStatusDialog myStatusDialog;
    MyDialogLoader myDialogLoader;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_college);
        name=findViewById(R.id.textView13);
        email=findViewById(R.id.textView14);

        name.setText("Thank you "+new MyPreferences(getApplicationContext()).getUserName()+"\nFor Participating");
        email.setText("Results will be sent to: "+new MyPreferences(getApplicationContext()).getGmail());

        button=findViewById(R.id.button);
        ratingBar=findViewById(R.id.ra);
        editText=findViewById(R.id.mobile);

        button.setOnClickListener(v -> feedback());



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

    private void feedback() {

        loginLoader(true,"Submitting Feedback..");

        Map<String,Object> map=new HashMap<>();
        map.put("UID",new MyPreferences(getApplicationContext()).getUID());
        map.put("Rating",String.valueOf(ratingBar.getRating()));
        map.put("Message", Objects.requireNonNull(editText.getText()).toString());

        databaseReference= FirebaseDatabase.getInstance().getReference("Feeedbacks");

        databaseReference.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    //Do what you need to do
                    loginLoader(false,"Submitting Answer..");
                    done();

                } else {
//                    Log.d(TAG, task.getException().getMessage());
                    loginLoader(false,"Submitting Answer..");
                    showDialogStatus(2);
                }
            }
        });



//        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().feedback(map);
//
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//
//                loginLoader(false,"Submitting Answer..");
//
//                if (response.isSuccessful()) {
//                    assert response.body() != null;
//                    if (!response.body().isError()){
//
//
//                        showDialogStatus(1);
//
//                    }
//                    else
//                        showDialogStatus(2);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//                showDialogStatus(2);
//
//            }
//        });





    }

    private void done() {
        showDialogStatus(1);
        int TIME = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

               //
               myStatusDialog.dismiss();
                finish();
            }
        }, TIME);


    }

    private void showDialogStatus(int status){

        if (myStatusDialog==null)
            myStatusDialog=new MyStatusDialog();

        Bundle bundle=new Bundle();
        if(status==2){
            bundle.putString("MSG","Please try again...");

            bundle.putBoolean("STATUS",false);
            myStatusDialog.setArguments(bundle);
            myStatusDialog.show(getSupportFragmentManager(),"S");

        }else if(status==1){
            bundle.putString("MSG","Feedback Submitted");

            bundle.putBoolean("STATUS",true);
            myStatusDialog.setArguments(bundle);
            myStatusDialog.show(getSupportFragmentManager(),"S");

        }

    }

}
