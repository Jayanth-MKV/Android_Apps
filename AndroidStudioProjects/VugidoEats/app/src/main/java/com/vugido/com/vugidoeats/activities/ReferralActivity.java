package com.vugido.com.vugidoeats.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vugido.com.vugidoeats.R;
import com.vugido.com.vugidoeats.dialogs.ReVerifyAccount;

public class ReferralActivity extends AppCompatActivity {

    EditText editText;
    Button Submit;
    ImageButton cancel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral);
//        editText=findViewById(R.id.editTextTextPersonName2);
//        cancel=findViewById(R.id.imageButton);
//        Submit=findViewById(R.id.button2);
//
//        cancel.setOnClickListener(this);
//        Submit.setOnClickListener(this);
        ReVerifyAccount reVerifyAccount=new ReVerifyAccount();
        reVerifyAccount.show(getSupportFragmentManager(),"REVERIFY");



    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.imageButton:
//                //cancel... show dialog
//                break;
//            case R.id.button2:
//                // send referral code... and verified..
//                if(!editText.getText().toString().equals("")){
//                    sendRC(editText.getText().toString());
//                }
//                break;
//        }
//    }
//
//    private void sendRC(String toString) {
//
//        Map<String,Object> map=new HashMap<>();
//        map.put("UID",new MyPreferences(this).getUID());
//        map.put("RC",toString);
//
//        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit(new MyPreferences(this).getBaseLocationURL()).sendRC(map);
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
//
//                if(response.isSuccessful()){
//
//                    assert response.body() != null;
//                    if(!response.body().isError()){
//                        // ok successfully verified..
//
//                    }else {
//                        // not verified...
//
//                    }
//                }else {
//
//                    // error
//                }
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
//
//            }
//        });
//    }


}
