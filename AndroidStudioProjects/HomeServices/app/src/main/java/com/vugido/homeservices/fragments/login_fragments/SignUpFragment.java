package com.vugido.homeservices.fragments.login_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vugido.homeservices.R;
import com.vugido.homeservices.activities.TermsAndConditionsPrivacyPolicy;
import com.vugido.homeservices.activities.VerifyActivity;
import com.vugido.homeservices.models.user_reg.Response;
import com.vugido.homeservices.network.RetrofitBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;
import static com.vugido.homeservices.fragments.login_fragments.LoginFragment.VA;

public class SignUpFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

     private CheckBox checkBox;
    private Activity activity;
    TextView TC, PP;
    private static final int RESOLVE_HINT = 10;
    private TextView Login;
    private Context context;
    private LoginFragmentInterface loginFragmentInterface;
    private Button button;
    private TextInputEditText Phone,UserName,gmail;
    TextInputLayout textInputLayout;
    private static final int LA_CODE = 100;
    private BottomLoader bottomLoader;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        activity=getActivity();
        bottomLoader= (BottomLoader) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        Login=view.findViewById(R.id.login);
        UserName=view.findViewById(R.id.username);
        textInputLayout=view.findViewById(R.id.student_mobile);
        gmail=view.findViewById(R.id.email);
        Phone=view.findViewById(R.id.mobile);
        Login.setOnClickListener(this);
        button=view.findViewById(R.id.button);
        button.setEnabled(false);
        button.setOnClickListener(this);

         checkBox=view.findViewById(R.id.checkBox_login);
        TC=view.findViewById(R.id.tc_link);
        PP=view.findViewById(R.id.pp_link);
        checkBox.setOnCheckedChangeListener(this);
        TC.setOnClickListener(this);
        PP.setOnClickListener(this);

        textInputLayout.setOnClickListener(this);
        Phone.setOnClickListener(this);

        loginFragmentInterface= (LoginFragmentInterface) context;

        String text = "<font color=#cc0029>Already have account? </font> <font color=#ffcc00>Login</font>";
        Login.setText(Html.fromHtml(text));
        return view;
    }


    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.login){

            loginFragmentInterface.loginFragment();
        }else if(view.getId()==R.id.button){

            // check for user name and phone... if valid then proceed..
            String name= Objects.requireNonNull(UserName.getText()).toString();
            String phone= Objects.requireNonNull(Phone.getText()).toString();
            String g= Objects.requireNonNull(gmail.getText()).toString();

            if(checkName(name) && checkNumber(phone) && !g.equals("") ){
                // ok valid
                button.setEnabled(false);

                // send details to the server..
                signUp(name,phone,g);


            }else {

                button.setEnabled(true);
                // not valid..
                Toast.makeText(context,"Enter Valid Information",Toast.LENGTH_LONG).show();
            }

        }else if(view.getId()==R.id.tc_link){

        Intent i1=new Intent(context, TermsAndConditionsPrivacyPolicy.class);
        i1.putExtra("ID",1);
        startActivity(i1);
    }else if(view.getId()==R.id.pp_link){
        Intent i2=new Intent(context, TermsAndConditionsPrivacyPolicy.class);
        i2.putExtra("ID",2);
        startActivity(i2);

    }

    }

    private void signUp( final String name, final String phone,String s) {

        Map<String,Object> map=new HashMap<>();
        map.put("MOBILE",phone);
        //map.put("USER_NAME",name);

        bottomLoader.bottomLoader(true,"Signing Up");


       Call<Response> call= RetrofitBuilder.getInstance().getRetrofit().userReg(map);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if(response.isSuccessful()){

                    assert response.body() != null;
                    if(!response.body().isUSEREXISTS()){
                        Intent intent=new Intent(context, VerifyActivity.class);
                        intent.putExtra("M",phone);
                        intent.putExtra("N",name);

                        intent.putExtra("G",s);
                        intent.putExtra("T",0);
                        startActivityForResult(intent,VA);
                    }else{

                        loginFragmentInterface.loginFragment();
                        Toast.makeText(context,"Please Login ! You already have account",Toast.LENGTH_LONG).show();
                    }

                }

                bottomLoader.bottomLoader(false,"");
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                bottomLoader.bottomLoader(false,"");

            }
        });

    }

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

        if (requestCode==VA && resultCode == RESULT_OK){

            activity.setResult(RESULT_OK);
            activity.finish();

        }
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

    public interface LoginFragmentInterface{

        void loginFragment();



    }
    public interface BottomLoader{

        void bottomLoader(boolean state, String msg);

    }


}
