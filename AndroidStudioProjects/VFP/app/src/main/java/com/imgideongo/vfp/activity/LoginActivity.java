package com.imgideongo.vfp.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.imgideongo.vfp.ConfigVariables.Config;
import com.imgideongo.vfp.R;
import com.imgideongo.vfp.helper.MyPreferences;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button login;
    EditText Email,Passwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        login=findViewById(R.id.loginButton);
        login.setOnClickListener(this);
        Email=findViewById(R.id.email);
        Passwd=findViewById(R.id.pwd);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:
                checkLoginInfo();
                break;
        }

    }

    private void checkLoginInfo(){

        String username=Email.getText().toString();
        String password=Passwd.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            //check your login info

        }else{
            verifyLoginInfo(username,password);
        }

    }

    private void verifyLoginInfo(final String username, final String password) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.URL_LOGIN_VFP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("responseLogin",response);
                            JSONObject jsonObject=new JSONObject(response);
                            boolean error=jsonObject.getBoolean("error");
                            if(!error){
                                MyPreferences myPreferences=new MyPreferences(getApplicationContext());
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                               // intent.putExtra("CID",jsonObject.getInt("ID"));
                               // intent.putExtra("LOGO",jsonObject.getString("LOGO"));
                               // myPreferences.setLogoString(jsonObject.getString("LOGO"));
                               // myPreferences.setUID(String.valueOf(jsonObject.getInt("ID")));
                                myPreferences.setLoginInState(true);
                               // myPreferences.setClientName(jsonObject.getString("NAME"));
                               // myPreferences.setClientTimings();
                               // myPreferences.setUserName(jsonObject.getString("EMAIL"));
                               // myPreferences.setUserMobile();
                               // myPreferences.setUserPrimaryAddress(jsonObject.getString("ADDRESS"));
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("error",error.toString());
                    }
                }){

            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params=new HashMap<>();
                params.put("UID",username);
                params.put("PWD",password);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
