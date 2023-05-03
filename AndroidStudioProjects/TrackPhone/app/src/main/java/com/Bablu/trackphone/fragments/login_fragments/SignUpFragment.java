package com.Bablu.trackphone.fragments.login_fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Bablu.trackphone.R;
import com.Bablu.trackphone.Retrofit.RetrofitBuilder;
import com.Bablu.trackphone.activities.MainActivity;
import com.Bablu.trackphone.activities.MyPreferences;
import com.Bablu.trackphone.models.Response;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class SignUpFragment extends Fragment implements View.OnClickListener {

    private TextView Login;
    private SweetAlertDialog s;
    private Context context;
    private LoginFragmentInterface loginFragmentInterface;
    TextInputEditText UserName, email,phone, aadhar,pwd,confirm_pwd;
    EditText dob;
    private Button button;
    private IMEI imei;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        imei= (IMEI) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        Login=view.findViewById(R.id.login);
        UserName=view.findViewById(R.id.user_name);
        dob=view.findViewById(R.id.dob);
        email=view.findViewById(R.id.email);
        phone=view.findViewById(R.id.mobile);
        aadhar=view.findViewById(R.id.aadhar);
        pwd=view.findViewById(R.id.pwd);
        confirm_pwd=view.findViewById(R.id.confirm_pwd);

        Login.setOnClickListener(this);
        button=view.findViewById(R.id.button);
        button.setOnClickListener(this);


        String text = "<font color=#cc0029>Already have account? </font> <font color=#ffcc00>Login</font>";
        Login.setText(Html.fromHtml(text));


        return view;
    }


    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.login){

            loginFragmentInterface= (LoginFragmentInterface) context;
            loginFragmentInterface.loginFragment();
        }else if(view.getId()==R.id.button){


            // send the data...

            sendData();


            ///  Intent intent=new Intent(context, VerifyActivity.class);
           // startActivity(intent);

        }

    }

    private void sendData() {

        Map<String, Object> map=new HashMap<>();
        map.put("USER_NAME", Objects.requireNonNull(UserName.getText()).toString());
        map.put("DOB",dob.getText().toString());
        map.put("Email", Objects.requireNonNull(email.getText()).toString());
        map.put("Aadhar", Objects.requireNonNull(aadhar.getText()).toString());
        map.put("Phone", Objects.requireNonNull(phone.getText()).toString());
        map.put("PWD", Objects.requireNonNull(confirm_pwd.getText()).toString());
        map.put("IMEI",imei.fetchIMEI());

        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().sendUserLoginInfo(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){



                        // ok success

                        // set the flag.. and skip login activity...
                        new MyPreferences(context).setVerified(true);
                        new MyPreferences(context).setAPIKEY(imei.fetchIMEI());
                        showDialogStatus(true);
                        startActivity(new Intent(context, MainActivity.class));




                    }else {

                        // not ok error... occurred
                        showDialogStatus(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                showDialogStatus(false);
            }
        });


    }

    private void showDialogStatus(boolean status){
        if(status){
            s=new SweetAlertDialog(context,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Added Successfully")
            ;
            s.show();
        }else{
            s=new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error.. Try again!!")
            ;
            s.show();

        }

    }

    public interface LoginFragmentInterface{

        void loginFragment();

    }

    public interface IMEI{

        String fetchIMEI();

    }
}
