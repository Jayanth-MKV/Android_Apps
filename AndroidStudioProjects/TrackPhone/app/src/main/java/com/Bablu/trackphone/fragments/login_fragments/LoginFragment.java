package com.Bablu.trackphone.fragments.login_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Bablu.trackphone.R;
import com.Bablu.trackphone.activities.LoginActivity;
import com.Bablu.trackphone.activities.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Context context;
    private Button button;
    private CheckBox checkBox;
    TextView TC, PP,SignUp;
    private String User,Mobile;
    private TextInputEditText U,P;
    private ShowProgress showProgress;
    private Activity activity;
    private SignUpFragmentInterface signUpFragmentInterface;





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        activity=getActivity();
        showProgress= (ShowProgress) getActivity();
        signUpFragmentInterface= (SignUpFragmentInterface) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        button=view.findViewById(R.id.button);
        checkBox=view.findViewById(R.id.checkBox_login);
        SignUp=view.findViewById(R.id.SignUp);
        TC=view.findViewById(R.id.tc_link);
        U=view.findViewById(R.id.user_name);
        P=view.findViewById(R.id.user_phone);
        PP=view.findViewById(R.id.pp_link);
        button.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(this);
        TC.setOnClickListener(this);
        PP.setOnClickListener(this);
        button.setEnabled(false);
        String text = "<font color=#cc0029>No account? </font> <font color=#ffcc00>Sign-Up Now</font>";
        SignUp.setText(Html.fromHtml(text));

        SignUp.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

    if(view.getId()==R.id.button){


        startActivity(new Intent(context, MainActivity.class));
        // check the fields and pass the data....
        User= Objects.requireNonNull(U.getText()).toString();
        Mobile= Objects.requireNonNull(P.getText()).toString();

        boolean numberStatus=checkNumber(Mobile);
        boolean nameStatus=checkName(User);

        if(numberStatus && nameStatus){


            //send the otp to that number and launch the verify page..

            Toast.makeText(context, "Ok We can initiate OTP", Toast.LENGTH_SHORT).show();

            // send info to server...

         //   sendUserLoginInfo();




        }else{

            Toast toast=  Toast.makeText(context,"Enter Valid Information",Toast.LENGTH_SHORT);
            toast.show();

        }



        // now check the entered details
          /*  Intent intent=new Intent(context, VerifyActivity.class);
            startActivity(intent);
*/
        }/*else if(view.getId()==R.id.tc_link){

        Intent i1=new Intent(context, TermsAndConditionsPrivacyPolicy.class);
        i1.putExtra("ID",1);
        startActivity(i1);
    }else if(view.getId()==R.id.pp_link){
        Intent i2=new Intent(context,TermsAndConditionsPrivacyPolicy.class);
        i2.putExtra("ID",2);
        startActivity(i2);

    }*/else if(view.getId()==R.id.SignUp){

        signUpFragmentInterface.signUpFragment();

    }

    }
/*
    private void sendUserLoginInfo() {


        // disable the login button and show progress bar..

        button.setEnabled(false);
        showProgress.showProgress(true);

        Map<String, Object> map=new HashMap<>();
        map.put("mobile",Mobile);
        map.put("user",User);
        Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().sendUserLoginInfo(map);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                Log.e("res",response.toString());
                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isError()){
                        //ok otp initiated
                       // initSmsRetriever();
                        Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(context,VerifyActivity.class);
                        intent.putExtra("USER_NAME",User);
                        intent.putExtra("MOBILE",Mobile);
                        startActivityForResult(intent,CODE);


                    }else {

                        // not initiated..
                        Toast.makeText(context,"Some Error ..Try Again!..",Toast.LENGTH_LONG).show();
                    }

                }else {

                    // some error
                }

                showProgress.showProgress(false);
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                showProgress.showProgress(false);

Log.e("res",t.toString());
            }
        });
    }
*/
    private boolean checkName(String username) {
        Pattern pattern= Pattern.compile("^[\\p{L} .'-]+$");
        Matcher matcher= pattern.matcher(username);

        if(matcher.find() && matcher.group().equals(username)){
            return  true;
        }
        else
        return false;



    }

    private boolean checkNumber(String number) {


        Pattern pattern = Pattern.compile("[6-9][0-9]{9}");
        Matcher matcher = pattern.matcher(number);

        if(matcher.find() && matcher.group().equals(number)){
            return  true;
        }
        else
        return false;


    }
     @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         Log.e("executed","fragment");

          /*  if(requestCode==CODE ){

                Log.e("executed","fragment");
                if(resultCode==1){

                    Log.e("ok","ok");
                activity.finish();
                }else {

                    Log.e("not","ok");
                }

            }*/



    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(buttonView.getId()==R.id.checkBox_login){

            if(buttonView.isChecked()){
                // enable button
                button.setEnabled(true);
            }else {
                button.setEnabled(false);
            }
        }

    }



    public interface ShowProgress{

        void showProgress(boolean show);
    }

    public interface SignUpFragmentInterface{

        void signUpFragment();

    }

    public interface IMEI{

        String fetchIMEI();

    }

}
