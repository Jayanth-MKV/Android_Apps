package com.vugido.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vugido.helloworld.models.DataItem;
import com.vugido.helloworld.models.Response;
import com.vugido.helloworld.network.RetrofitBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    EditText username, paswsword;
    List<DataItem> dataItemList;
    Button login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.editTextTextEmailAddress);
        paswsword=findViewById(R.id.editTextTextPassword);

        login=findViewById(R.id.button3);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendLoginRequest(username.getText().toString(),paswsword.getText().toString());

            }
        });


    }

    private void sendLoginRequest(String username, String password) {

        Map<String,Object> map=new HashMap<>();
        map.put("USERNAME",username);
        map.put("PASSWORD",password);

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().checkLogin(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    if(response.body().isLogin()){

                        Toast.makeText(getApplicationContext(), "Right Credentials", Toast.LENGTH_SHORT).show();
                        dataItemList=response.body().getData();

                        DataItem dataItem=dataItemList.get(0);

                        bindData(dataItem);


                    }else{

                        Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });








    }

    private void bindData(DataItem dataItem) {

        Toast.makeText(getApplicationContext(),dataItem.getNAME()+","+dataItem.getROLE(),Toast.LENGTH_LONG).show();
    }
}
